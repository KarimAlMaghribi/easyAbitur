package de.l.stadtwerke.loga3jobofferservice.controller;

import de.l.stadtwerke.loga3jobofferservice.model.Stellenausschreibung;
import de.l.stadtwerke.loga3jobofferservice.service.XMLService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
