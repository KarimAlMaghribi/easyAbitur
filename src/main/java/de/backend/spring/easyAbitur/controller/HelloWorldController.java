package de.backend.spring.easyAbitur.controller;

import de.backend.spring.easyAbitur.service.XMLService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloWorldController {

    @Autowired
    XMLService xs;


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

    @GetMapping("/xmltest")
    public void testXML() throws NotFoundException {

    }
}
