package com.grupoasd.apigestionordenes.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.grupoasd.apigestionordenes.dto.EquipoDto;
import com.grupoasd.apigestionordenes.service.EquipoService;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/api/1.0/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    /**
     * Metodo que permite obtener todos los equipos de la BD.
     *
     * @author Victor Bocanegra
     * @return List EquipoDto
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<EquipoDto>> findAll() {
        try {
            List<EquipoDto> list = equipoService.getEquipos();
            return ResponseEntity.ok(list);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Metodo que permite obtener todos los equipos disponibles de la BD.
     *
     * @author Victor Bocanegra
     * @return List EquipoDto
     */
    @RequestMapping(value = "/disponibles", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<EquipoDto>> findDisponibles() {
        try {
            List<EquipoDto> list = equipoService.getDisponibles();
            return ResponseEntity.ok(list);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Metodo que permite obtener un equipo de la BD.
     *
     * @author Victor Bocanegra
     * @param equipoId int
     * @return EquipoDto
     */
    @RequestMapping(value = "/{equipoId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<EquipoDto> getEquipo(@ApiParam(value = "equipoId", required = true) @PathVariable int equipoId) {
        try {
            EquipoDto eqp = equipoService.getEquipo(Long.valueOf(equipoId));
            return new ResponseEntity(eqp, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Metodo que permite la creacion de un equipo.
     *
     * @author Victor Bocanegra
     * @param request EquipoDto
     * @return EquipoDto
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<EquipoDto> create(@RequestBody EquipoDto request) {
        try {
            request = equipoService.createEquipo(request);
            return ResponseEntity.ok(request);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Metodo que permite la eliminacion de un equipo en BD.
     *
     * @author Victor Bocanegra
     * @param equipoId Long
     * @return EquipoDto
     */
    @RequestMapping(value = "/delete/{equipoId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<EquipoDto> delete(@PathVariable("equipoId") long equipoId) {
        try {
            EquipoDto delete = equipoService.deleteEquipo(equipoId);
            return ResponseEntity.ok(delete);
        } catch (EmptyResultDataAccessException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Metodo que permite la actualizacion de un equipo.
     *
     * @author Victor Bocanegra
     * @param equipoId equipo a actualizar
     * @param request EquipoDto
     * @return EquipoDto
     */
    @RequestMapping(value = "/update/{equipoId}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<EquipoDto> update(@PathVariable("equipoId") Long equipoId, @RequestBody EquipoDto request) {
        try {
            request = equipoService.updateEquipo(equipoId, request);
            return ResponseEntity.ok(request);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
     /**
     * Metodo que permite obtener los equipos de una orden.
     *
     * @author Victor Bocanegra
     * @param ordenId int
     * @return List EquipoDto
     */
    @RequestMapping(value = "/getOrden/{ordenId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<EquipoDto>> getEquiposByOrden(@ApiParam(value = "ordenId", required = true) @PathVariable int ordenId) {
        try {
            List<EquipoDto> eqp = equipoService.getEquiposByOrden(Long.valueOf(ordenId));
            return new ResponseEntity(eqp, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
