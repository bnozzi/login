package com.bnozzi.login.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class LoginResourse {


    @PostMapping
    public String login(String email, String password){
        return "inicio sesion correctamente ";
    }

    @PostMapping(value="register")
    public String signin(@RequestBody String email , @RequestBody String pw) {
        //TODO: process POST request
        
        return "registrado!";
    }
    
    //generate jwt
    

    
}
