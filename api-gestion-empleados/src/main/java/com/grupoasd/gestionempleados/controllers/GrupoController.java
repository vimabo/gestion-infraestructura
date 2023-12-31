package com.grupoasd.gestionempleados.controllers;

import com.grupoasd.gestionempleados.dto.GrupoDto;
import com.grupoasd.gestionempleados.service.GrupoService;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/api/1.0/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    /**
     * Metodo que permite obtener todos los grupos de la BD.
     *
     * @author Victor Bocanegra
     * @return List GrupoDto
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<GrupoDto>> findAll() {
        try {
            List<GrupoDto> list = grupoService.getGrupos();
            return ResponseEntity.ok(list);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Metodo que permite un grupo de la BD.
     *
     * @author Victor Bocanegra
     * @param grupoId int
     * @return GrupoDto
     */
    @RequestMapping(value = "/{grupoId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<GrupoDto> getGrupo(@ApiParam(value = "grupoId", required = true) @PathVariable int grupoId) {
        try {
            GrupoDto gru = grupoService.getGrupo(Long.valueOf(grupoId));
            return new ResponseEntity(gru, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Metodo que permite la creacion de un grupo.
     *
     * @author Victor Bocanegra
     * @param request GrupoDto
     * @return GrupoDto
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<GrupoDto> create(@RequestBody GrupoDto request) {
        try {
            request = grupoService.createGrupo(request);
            return ResponseEntity.ok(request);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Metodo que permite la eliminacion de un grupo en BD.
     *
     * @author Victor Bocanegra
     * @param grupoId Long
     * @return GrupoDto
     */
    @RequestMapping(value = "/delete/{grupoId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<GrupoDto> delete(@PathVariable("grupoId") int grupoId) {
        try {
            GrupoDto delete = grupoService.deleteGrupo(Long.valueOf(grupoId));
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
     * @param grupoId grupo a actualizar
     * @param request GrupoDto
     * @return GrupoDto
     */
    @RequestMapping(value = "/update/{grupoId}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<GrupoDto> update(@PathVariable("grupoId") Long grupoId, @RequestBody GrupoDto request) {
        try {
            request = grupoService.updateGrupo(grupoId, request);
            return ResponseEntity.ok(request);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
