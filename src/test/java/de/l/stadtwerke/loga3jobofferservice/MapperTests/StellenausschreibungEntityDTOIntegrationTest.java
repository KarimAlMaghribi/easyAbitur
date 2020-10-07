package de.l.stadtwerke.loga3jobofferservice.MapperTests;

import de.l.stadtwerke.loga3jobofferservice.dto.StellenausschreibungDTO;
import de.l.stadtwerke.loga3jobofferservice.dto.mapper.StellenausschreibungMapper;
import de.l.stadtwerke.loga3jobofferservice.model.Stellenausschreibung;
import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

public class StellenausschreibungEntityDTOIntegrationTest {
    private StellenausschreibungMapper mapper
            = Mappers.getMapper(StellenausschreibungMapper.class);
    @Test
    public void givenSourceToDestination_whenMaps_thenCorrect() {
        Stellenausschreibung stellenausschreibung = new Stellenausschreibung();
        // TO-DO: Set attributes
        StellenausschreibungDTO stellenausschreibungDTO = mapper.mapStellenausschreibungToDTO(stellenausschreibung);

        /* TO-DO
        Assert.assertEquals(simpleSource.getName(), destination.getName());
        Assert.assertEquals(simpleSource.getDescription(),
                destination.getDescription());
         */

    }
    @Test
    public void givenDestinationToSource_whenMaps_thenCorrect() {
        StellenausschreibungDTO stellenausschreibungDTO = new StellenausschreibungDTO();
        /*
        stellenausschreibungDTO.setName("Wir suchen dich!");
        stellenausschreibungDTO.setDescription("bitte Arbeite super");
        Stellenausschreibung stellenausschreibung = mapper.mapToStellenausschreibung(stellenausschreibungDTO);
        Assert.assertEquals(stellenausschreibungDTO.getName(), stellenausschreibung.getName());
        Assert.assertEquals(stellenausschreibungDTO.getDescription(),
                stellenausschreibung.getDescription());
         */
    }
}
