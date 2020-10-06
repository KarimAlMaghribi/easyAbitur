package de.l.stadtwerke.loga3jobofferservice.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class HelloWorldController {
    /**
     * Greet users!
     * See SecurityConfig.java
     */
    @RequestMapping(
            value="/hello",
            method = RequestMethod.GET,
            produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private ResponseEntity<?> getHelloForUser() {
        return ResponseEntity.ok("hello user!");
    }
}
