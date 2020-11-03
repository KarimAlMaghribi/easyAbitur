package de.l.stadtwerke.loga3jobofferservice.serviceTests;

import de.l.stadtwerke.loga3jobofferservice.service.XMLService;
import javassist.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class XMLParsingTests {
    @Autowired
    XMLService xs;

    @Test
    public void testXML() {
        xs.parseJobs();
    }
}
