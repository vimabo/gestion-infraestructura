/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoasd.gestionempleados.dto;

import java.io.Serializable;

/**
 *
 * @author boca-
 */
public class Response implements Serializable {

    private static final long serialVersionUID = 385528334753803078L;
    final boolean success;
    final String message;

    /**
     * Constructor
     *
     * @author vbocanegra
     * @param success boolean
     * @param message String
     */
    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public static Response success(String message) {
        return new Response(true, message);
    }

    public static Response error(String message) {
        return new Response(false, message);
    }
}
