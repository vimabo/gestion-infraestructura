/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoasd.apigestionordenes.impl;

import com.grupoasd.apigestionordenes.dao.EquipoDao;
import com.grupoasd.apigestionordenes.dao.OrdenDao;
import com.grupoasd.apigestionordenes.dto.EmpleadoDto;
import com.grupoasd.apigestionordenes.dto.EquipoDto;
import com.grupoasd.apigestionordenes.dto.GrupoDto;
import com.grupoasd.apigestionordenes.dto.OrdenDto;
import com.grupoasd.apigestionordenes.entity.Equipo;
import com.grupoasd.apigestionordenes.entity.Orden;
import com.grupoasd.apigestionordenes.enumeraciones.EstadoEquipoEnum;
import com.grupoasd.apigestionordenes.enumeraciones.EstadoOrdenEnum;
import com.grupoasd.apigestionordenes.service.OrdenService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author vbocanegra
 */
@Service
public class OrdenServiceImpl implements OrdenService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(OrdenServiceImpl.class);

    @Autowired
    private OrdenDao ordenDao;

    @Autowired
    private EquipoDao equipoDao;

    @Autowired
    private AsyncEmailService emailService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.service.token}")
    private String apiToken;

    @Value("${api.service.empleados}")
    private String apiEmpleados;

    @Value("${api.service.grupos}")
    private String apiGrupos;

    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private final String mensaje = "La orden de servicio No: %d cambia a estado: %s";

    /**
     * Metodo que retorna listado de todas las ordenes en BD.
     *
     * @author Victor Bocanegra
     * @return List OrdenDto
     */
    @Override
    public List<OrdenDto> getOrdenes() {

        List<Orden> list = ordenDao.findAll();
        List<OrdenDto> result = new ArrayList<>();
        list.stream().map((orden) -> {
            OrdenDto dto = new OrdenDto();
            dto.setOrdenId(orden.getOrdenId());
            dto.setDescripcion(orden.getDescripcion());
            dto.setFechaRegistro(orden.getFechaRegistro() != null ? format.format(orden.getFechaRegistro()) : null);
            dto.setFechaModificacion(orden.getFechaModificacion() != null ? format.format(orden.getFechaModificacion()) : null);
            dto.setEquipos(returnListDto(orden.getEquipos()));
            dto.setEmpleadoId(orden.getEmpleado());
            dto.setGrupoId(orden.getGrupo());
            dto.setEstado(orden.getEstado().getNombre());
            return dto;
        }).forEachOrdered((dto) -> {
            result.add(dto);
        });
        return result;
    }

    /**
     * Metodo que permite la creacion de una orden.
     *
     * @author Victor Bocanegra
     * @param ordenDto OrdenDto
     * @return OrdenDto
     */
    @Override
    @Transactional
    public OrdenDto createOrden(OrdenDto ordenDto) {

        OrdenDto result = null;
        validacionesOrden(ordenDto, true);
        EstadoOrdenEnum estado = EstadoOrdenEnum.getValueOf(ordenDto.getEstado());
        List<Equipo> equipos = null;
        Orden entity = new Orden(ordenDto.getDescripcion(), estado, new Date(), new Date(),
                ordenDto.getEmpleadoId(), ordenDto.getGrupoId());
        entity = ordenDao.save(entity);
        List<String> correos = null;
        if (entity.getEmpleado() != 0) {
            correos = consultarCorreos(entity, true);
        } else if (entity.getGrupo() != 0) {
            correos = consultarCorreos(entity, false);
        }
        emailService.enviarCorreoService(correos, String.format(mensaje, entity.getOrdenId(), estado.getNombre()));

        result = new OrdenDto();
        result.setOrdenId(entity.getOrdenId());
        result.setDescripcion(entity.getDescripcion());
        result.setFechaRegistro(entity.getFechaRegistro() != null ? format.format(entity.getFechaRegistro()) : null);
        result.setFechaModificacion(entity.getFechaModificacion() != null ? format.format(entity.getFechaModificacion()) : null);
        result.setEstado(entity.getEstado().getNombre());
        result.setEmpleadoId(entity.getEmpleado());
        result.setGrupoId(entity.getGrupo());
        if (ordenDto.getEquipos() != null && !ordenDto.getEquipos().isEmpty()) {
            List<Long> filteredList = ordenDto.getEquipos().stream()
                    .map(EquipoDto::getEquipoId)
                    .collect(Collectors.toList());

            equipos = equipoDao.findEquiposById(filteredList);
            final Orden create = entity;

            equipos = equipos.stream().map(i -> {
                i.setOrden(create);
                i.setEstado(EstadoEquipoEnum.EQUIPO_OCUPADO);
                return i;
            }).collect(Collectors.toList());

            equipoDao.saveAll(equipos);

        }
        result.setEquipos(returnListDto(equipos));
        result.setMensaje("Orden creada satisfatoriamente");

        return result;
    }

    public void validacionesOrden(OrdenDto ordenDto, boolean create) {

        if (ordenDto.getDescripcion() == null || ordenDto.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("La descripción de la orden es obligatoria");
        }
        if (create) {
            if (ordenDto.getEstado() == null || ordenDto.getEstado().isEmpty()) {
                throw new IllegalArgumentException("El estado de la orden del es obligatorio");
            }
            if (ordenDto.getEquipos() == null || ordenDto.getEquipos().isEmpty()) {
                throw new IllegalArgumentException("Debe asignar al menos un equipo a la orden");
            }
            if (ordenDto.getEmpleadoId() == 0 && ordenDto.getGrupoId() == 0) {
                throw new IllegalArgumentException("Debe asignar la orden a  un empleado o a un grupo");
            }
        }
    }

    public List<EquipoDto> returnListDto(List<Equipo> equipos) {
        List<EquipoDto> result = new ArrayList<>();
        equipos.stream().map((equipo) -> {
            EquipoDto dto = new EquipoDto();
            dto.setEquipoId(equipo.getEquipoId());
            dto.setNombre(equipo.getNombre());
            dto.setMarca(equipo.getMarca());
            dto.setCodigoBarras(equipo.getCodigoBarras());
            dto.setFechaRegistro(equipo.getFechaRegistro() != null ? format.format(equipo.getFechaRegistro()) : null);
            dto.setEstado(equipo.getEstado().getNombre());
            dto.setOrdenId(equipo.getOrden() != null ? equipo.getOrden().getOrdenId() : 0);
            return dto;
        }).forEachOrdered((dto) -> {
            result.add(dto);
        });
        return result;
    }

    /**
     * Metodo que retorna una orden de la BD.
     *
     * @author Victor Bocanegra
     * @param ordenId Long
     * @return List OrdenDto
     */
    @Override
    public OrdenDto getOrden(Long ordenId) {

        Optional<Orden> orden = ordenDao.findById(ordenId);
        OrdenDto result = null;

        if (orden.isPresent()) {
            result = new OrdenDto();
            result.setOrdenId(orden.get().getOrdenId());
            result.setDescripcion(orden.get().getDescripcion());
            result.setFechaRegistro(orden.get().getFechaRegistro() != null ? format.format(orden.get().getFechaRegistro()) : null);
            result.setFechaModificacion(orden.get().getFechaModificacion() != null ? format.format(orden.get().getFechaModificacion()) : null);
            result.setEquipos(returnListDto(orden.get().getEquipos()));
            result.setEmpleadoId(orden.get().getEmpleado());
            result.setGrupoId(orden.get().getGrupo());
            result.setEstado(orden.get().getEstado().getNombre());
        } else {
            throw new IllegalArgumentException("No existe la orden en BD.");
        }
        return result;
    }

    /**
     * Metodo que permite la actualizacion de una orden.
     *
     * @author Victor Bocanegra
     * @param idOrden Long
     * @param ordenDto OrdenDto
     * @return OrdenDto
     */
    @Override
    public OrdenDto updateOrden(Long idOrden, OrdenDto ordenDto) {

        OrdenDto result = null;
        validacionesOrden(ordenDto, false);

        Optional<Orden> update = ordenDao.findById(idOrden);

        if (update != null) {
            Orden save = update.get();
            save.setDescripcion(ordenDto.getDescripcion());
            EstadoOrdenEnum estado = EstadoOrdenEnum.getValueOf(ordenDto.getEstado());
            //PREGUNTAR ESTADO Y ENVIAR CORREO
            if (estado.equals(EstadoOrdenEnum.EN_PROCESO) || estado.equals(EstadoOrdenEnum.FINALIZADA)) {
                //Envio correo
                //Buscar los destinos en el otro micro
                List<String> correos = null;
                if (save.getEmpleado() != 0) {
                    correos = consultarCorreos(save, true);
                } else if (save.getGrupo() != 0) {
                    correos = consultarCorreos(save, false);
                }
                emailService.enviarCorreoService(correos, String.format(mensaje, save.getOrdenId(), estado.getNombre()));
                if (estado.equals(EstadoOrdenEnum.FINALIZADA)) {
                    List<Equipo> equipos = save.getEquipos();
                    equipos = equipos.stream().map(i -> {
                        i.setEstado(i.getEstadoOriginal());
                        i.setOrden(null);
                        return i;
                    }).collect(Collectors.toList());
                    equipoDao.saveAll(equipos);
                }
            }
            save.setEstado(estado);
            save.setFechaModificacion(new Date());
            ordenDao.save(save);
            result = new OrdenDto(save.getDescripcion(), save.getEstado().getNombre(), format.format(save.getFechaRegistro()),
                    format.format(save.getFechaModificacion()), "Orden actualizada satisfatoriamente");
        } else {
            throw new IllegalArgumentException("No existe la orden en BD");
        }
        return result;
    }

    public String consultarToken() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("Boca", headers);
        return restTemplate.
                exchange(apiToken,
                        HttpMethod.POST, entity, String.class).getBody();
    }

    public List<String> consultarCorreos(Orden orden, boolean control) {

        List<String> correos = null;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", consultarToken());
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        if (control) {
            EmpleadoDto response = restTemplate.
                    exchange(apiEmpleados + orden.getEmpleado(),
                            HttpMethod.GET, entity, EmpleadoDto.class).getBody();
            correos = Arrays.asList(response != null ? response.getEmail() : "");
        } else {
            GrupoDto response = restTemplate.
                    exchange(apiGrupos + orden.getGrupo(),
                            HttpMethod.GET, entity, GrupoDto.class).getBody();

            if (response != null && response.getEmpleados() != null) {
                correos = response.getEmpleados().stream()
                        .map(EmpleadoDto::getEmail)
                        .collect(Collectors.toList());
            }
        }
        return correos;
    }
}
