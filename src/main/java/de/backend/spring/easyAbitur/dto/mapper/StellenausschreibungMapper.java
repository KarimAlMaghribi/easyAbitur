package de.backend.spring.easyAbitur.dto.mapper;

import de.backend.spring.easyAbitur.dto.model.StellenausschreibungDTO;
import de.backend.spring.easyAbitur.model.Stellenausschreibung;
import org.mapstruct.Mapper;

@Mapper
public interface StellenausschreibungMapper {
    Stellenausschreibung mappToStellenausschreibung(StellenausschreibungDTO stellenausschreibungDTO);
    StellenausschreibungDTO mapStellenausschreibungToDTO(Stellenausschreibung stellenausschreibung);
}
