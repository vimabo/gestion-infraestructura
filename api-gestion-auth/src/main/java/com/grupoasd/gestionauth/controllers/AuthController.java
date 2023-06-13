package com.grupoasd.gestionauth.controllers;

import com.grupoasd.gestionauth.util.JwtUtil;
import com.grupoasd.gestionauth.util.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/api/1.0/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Token> login(@RequestBody String userName) {
        String token = jwtUtil.generateToken(userName);
        Token res = new Token(token);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
