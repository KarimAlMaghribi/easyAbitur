package de.l.stadtwerke.loga3jobofferservice.dto.mapper;

import de.l.stadtwerke.loga3jobofferservice.dto.model.StellenausschreibungDTO;
import de.l.stadtwerke.loga3jobofferservice.model.Stellenausschreibung;
import org.mapstruct.Mapper;

@Mapper
public interface StellenausschreibungMapper {
    Stellenausschreibung mapToStellenausschreibung(StellenausschreibungDTO stellenausschreibungDTO);
    StellenausschreibungDTO mapStellenausschreibungToDTO(Stellenausschreibung stellenausschreibung);
}
