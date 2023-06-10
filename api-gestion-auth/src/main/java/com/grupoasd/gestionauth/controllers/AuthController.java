package com.grupoasd.gestionauth.controllers;

import com.grupoasd.gestionauth.util.JwtUtil;
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
    public ResponseEntity<String> login(@RequestBody String userName) {
        String token = jwtUtil.generateToken(userName);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> register(@RequestBody String userName) {
        // Persist user to some persistent storage
        System.out.println("Info saved...");

        return new ResponseEntity<>("Registered", HttpStatus.OK);
    }
}
