package com.grupoasd.apigestionordenes.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.grupoasd.apigestionordenes.dto.OrdenDto;
import com.grupoasd.apigestionordenes.service.OrdenService;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/api/1.0/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    /**
     * Metodo que permite obtener todas las ordenes de la BD.
     *
     * @author Victor Bocanegra
     * @return List OrdenDto
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OrdenDto>> findAll() {
        try {
            List<OrdenDto> list = ordenService.getOrdenes();
            return ResponseEntity.ok(list);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Metodo que permite la creacion de una orden.
     *
     * @author Victor Bocanegra
     * @param request OrdenDto
     * @return OrdenDto
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<OrdenDto> create(@RequestBody OrdenDto request) {
        try {
            request = ordenService.createOrden(request);
            return ResponseEntity.ok(request);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Metodo que permite obtener una orden de la BD.
     *
     * @author Victor Bocanegra
     * @param ordenId int
     * @return OrdenDto
     */
    @RequestMapping(value = "/{ordenId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<OrdenDto> getOrden(@ApiParam(value = "ordenId", required = true) @PathVariable int ordenId) {
        try {
            OrdenDto eqp = ordenService.getOrden(Long.valueOf(ordenId));
            return new ResponseEntity(eqp, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Metodo que permite la actualizacion de una orden.
     *
     * @author Victor Bocanegra
     * @param ordenId orden a actualizar
     * @param request OrdenDto
     * @return OrdenDto
     */
    @RequestMapping(value = "/update/{ordenId}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<OrdenDto> update(@PathVariable("ordenId") Long ordenId, @RequestBody OrdenDto request) {
        try {
            request = ordenService.updateOrden(ordenId, request);
            return ResponseEntity.ok(request);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
