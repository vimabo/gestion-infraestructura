/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoasd.apigestionordenes.impl;

import com.grupoasd.apigestionordenes.dao.EquipoDao;
import com.grupoasd.apigestionordenes.dto.EquipoDto;
import com.grupoasd.apigestionordenes.entity.Equipo;
import com.grupoasd.apigestionordenes.enumeraciones.EstadoEquipoEnum;
import com.grupoasd.apigestionordenes.service.EquipoService;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vbocanegra
 */
@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    private EquipoDao equipoDao;

    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /**
     * Metodo que permite la creacion de un equipo.
     *
     * @author Victor Bocanegra
     * @param equipoDto EquipoDto
     * @return EquipoDto
     */
    @Override
    @Transactional
    public EquipoDto createEquipo(EquipoDto equipoDto) {

        EquipoDto result = null;
        validacionesEquipo(equipoDto, null);
        EstadoEquipoEnum estado = EstadoEquipoEnum.getValueOf(equipoDto.getEstado());
        Equipo entity = new Equipo(equipoDto.getNombre(), equipoDto.getMarca(),
                equipoDto.getCodigoBarras(), new Date(), estado);
        entity = equipoDao.save(entity);
        if (entity != null) {
            result = new EquipoDto();
            result.setEquipoId(entity.getEquipoId());
            result.setNombre(entity.getNombre());
            result.setMarca(entity.getMarca());
            result.setCodigoBarras(entity.getCodigoBarras());
            result.setFechaRegistro(entity.getFechaRegistro() != null ? format.format(entity.getFechaRegistro()) : null);
            result.setEstado(estado.getNombre());
            result.setMensaje("Equipo creado satisfatoriamente");
        } else {
            throw new IllegalArgumentException("Ocurrio un error al momento de crear el equipo");
        }

        return result;
    }

    public void validacionesEquipo(EquipoDto equipoDto, Long idEquipo) {

        if (equipoDto.getNombre() == null || equipoDto.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El Nombre del equipo es obligatorio");
        }
        if (equipoDto.getMarca() == null || equipoDto.getMarca().isEmpty()) {
            throw new IllegalArgumentException("La Marca del equipo es obligatorio");
        }
        if (equipoDto.getCodigoBarras() == null || equipoDto.getCodigoBarras().isEmpty()) {
            throw new IllegalArgumentException("El Codigo de Barra es obligatorio");
        }

        if (equipoDto.getEstado() == null || equipoDto.getEstado().isEmpty()) {
            throw new IllegalArgumentException("El Estado del equipo es obligatorio");
        }

        if (idEquipo == null) {
            Optional<Equipo> consulta = equipoDao.findByCodigoBarras(equipoDto.getCodigoBarras());
            if (!consulta.isEmpty()) {
                throw new IllegalArgumentException(
                        "Ya existe un equipo con codigo de barras: " + equipoDto.getCodigoBarras() + "");
            }
        } else {
            Optional<Equipo> consulta = equipoDao.findByCodigoBarrasAndId(equipoDto.getCodigoBarras(), idEquipo);
            if (!consulta.isEmpty()) {
                throw new IllegalArgumentException(
                        "Ya existe un equipo con codigo de barras:" + equipoDto.getCodigoBarras() + "");
            }
        }
    }

    /**
     * Metodo que permite la actualizacion de un equipo.
     *
     * @author Victor Bocanegra
     * @param idEquipo Long
     * @param equipoDto EquipoDto
     * @return EquipoDto
     */
    @Override
    @Transactional
    public EquipoDto updateEquipo(Long idEquipo, EquipoDto equipoDto) {

        EquipoDto result = null;
        validacionesEquipo(equipoDto, idEquipo);

        Optional<Equipo> update = equipoDao.findById(idEquipo);

        if (update != null) {
            Equipo save = update.get();
            save.setNombre(equipoDto.getNombre());
            save.setMarca(equipoDto.getMarca());
            save.setCodigoBarras(equipoDto.getCodigoBarras());
            EstadoEquipoEnum estado = EstadoEquipoEnum.getValueOf(equipoDto.getEstado());
            save.setEstado(estado);
            equipoDao.save(save);
            result = new EquipoDto(save.getEquipoId(), save.getNombre(), save.getMarca(), save.getCodigoBarras(),
                    save.getFechaRegistro() != null ? format.format(save.getFechaRegistro()) : null, estado.getNombre(), "Equipo actualizado satisfatoriamente");

        } else {
            throw new IllegalArgumentException("No existe el equipo en BD");
        }

        return result;
    }

    /**
     * Metodo que retorna listado de todos los equipos en BD.
     *
     * @author Victor Bocanegra
     * @return List EquipoDto
     */
    @Override
    public List<EquipoDto> getEquipos() {

        List<Equipo> list = equipoDao.findAll();
        List<EquipoDto> result = new ArrayList<>();
        list.stream().map((equipo) -> {
            EquipoDto dto = new EquipoDto();
            dto.setEquipoId(equipo.getEquipoId());
            dto.setNombre(equipo.getNombre());
            dto.setMarca(equipo.getMarca());
            dto.setCodigoBarras(equipo.getCodigoBarras());
            dto.setFechaRegistro(equipo.getFechaRegistro() != null ? format.format(equipo.getFechaRegistro()) : null);
            dto.setOrdenId(equipo.getOrden() != null ? equipo.getOrden().getOrdenId(): 0);
            dto.setEstado(equipo.getEstado().getNombre());
            return dto;
        }).forEachOrdered((dto) -> {
            result.add(dto);
        });
        return result;
    }

    /**
     * Metodo que retorna un equipo por id en BD.
     *
     * @author Victor Bocanegra
     * @param id Long
     * @return EquipoDto
     */
    @Override
    public EquipoDto getEquipo(Long id) {
        Optional<Equipo> equipo = equipoDao.findById(id);
        EquipoDto result = null;
        if (equipo.isPresent()) {
            result = new EquipoDto();
            result.setEquipoId(equipo.get().getEquipoId());
            result.setNombre(equipo.get().getNombre());
            result.setMarca(equipo.get().getMarca());
            result.setCodigoBarras(equipo.get().getCodigoBarras());
            result.setFechaRegistro(equipo.get().getFechaRegistro() != null
                    ? format.format(equipo.get().getFechaRegistro()) : null);
            result.setEstado(equipo.get().getEstado().getNombre());

        } else {
            throw new IllegalArgumentException("No existe el equipo en BD");
        }
        return result;
    }

    /**
     * Metodo que permite la eliminacion de un equipo en BD.
     *
     * @author Victor Bocanegra
     * @param idEquipo Long
     * @return EquipoDto
     */
    @Override
    public EquipoDto deleteEquipo(Long idEquipo) {
        EquipoDto result = new EquipoDto();
        try {
            equipoDao.deleteById(idEquipo);
            result.setMensaje("Equipo Eliminado satisfatoriamente");
        } catch (EmptyResultDataAccessException ex) {
            result.setMensaje("No existe Equipo con la informaciòn ingresada");
        }
        return result;
    }

    /**
     * Metodo que retorna lista de Equipos disponibles
     *
     * @return Optional Equipo
     * @author vbocanegra
     */
    @Override
    public List<EquipoDto> getDisponibles() {
        List<EstadoEquipoEnum> disponibles;
        List<EquipoDto> dtos = null;
        disponibles = Arrays.asList(EstadoEquipoEnum.BUENAS_CONDICIONES, EstadoEquipoEnum.REGULAR);
        List<Equipo> dispo = equipoDao.findByEstados(disponibles);
        if (dispo != null && !dispo.isEmpty()) {
            dtos = returnListDto(dispo);
        }
        return dtos;
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
            return dto;
        }).forEachOrdered((dto) -> {
            result.add(dto);
        });
        return result;
    }
    
    /**
     * Metodo que retorna listado de todos los equipos en BD.
     *
     * @author Victor Bocanegra
     * @param ordenId Long
     * @return List EquipoDto
     */
    @Override
    public List<EquipoDto> getEquiposByOrden(Long ordenId) {

        List<Equipo> list = equipoDao.findEquiposByOrdenId(ordenId);
        List<EquipoDto> result = new ArrayList<>();
        list.stream().map((equipo) -> {
            EquipoDto dto = new EquipoDto();
            dto.setEquipoId(equipo.getEquipoId());
            dto.setNombre(equipo.getNombre());
            dto.setMarca(equipo.getMarca());
            dto.setCodigoBarras(equipo.getCodigoBarras());
            dto.setFechaRegistro(equipo.getFechaRegistro() != null ? format.format(equipo.getFechaRegistro()) : null);
            dto.setOrdenId(equipo.getOrden() != null ? equipo.getOrden().getOrdenId() : 0);
            dto.setEstado(equipo.getEstado().getNombre());
            return dto;
        }).forEachOrdered((dto) -> {
            result.add(dto);
        });
        return result;
    }
}
