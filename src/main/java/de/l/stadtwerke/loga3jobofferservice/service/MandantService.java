package de.l.stadtwerke.loga3jobofferservice.service;

import de.l.stadtwerke.loga3jobofferservice.model.Mandant;
import de.l.stadtwerke.loga3jobofferservice.repository.MandantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MandantService {
    @Autowired
    MandantRepository mandantRepository;

    public Mandant getMandant(String mandant) {
        Mandant newMandant = new Mandant();

        if (mandant.equals("LSW")) {
            newMandant = mandantRepository.find_LSW_Mandant();
        } else if (mandant.equals("Netz")) {
            newMandant = mandantRepository.find_Netz_Mandant();
        } else if (mandant == "LAS") {
            newMandant = mandantRepository.find_LAS_Mandant();
        } else if (mandant == "KWL") {
            newMandant = mandantRepository.find_KWL_Mandant();
        } else if (mandant == "BSL") {
            newMandant = mandantRepository.find_BSL_Mandant();
        } else if (mandant == "LSI") {
            newMandant = mandantRepository.find_LSI_Mandant();
        } else if (mandant == "WGC") {
            newMandant = mandantRepository.find_WGC_Mandant();
        } else if (mandant == "SBL") {
            newMandant = mandantRepository.find_SBL_Mandant();
        } else if (mandant == "LVB") {
            newMandant = mandantRepository.find_LVB_Mandant();
        } else if (mandant == "LAB") {
            newMandant = mandantRepository.find_LAB_Mandant();
        } else if (mandant == "LSB") {
            newMandant = mandantRepository.find_LSB_Mandant();
        } else if (mandant == "LTB") {
            newMandant = mandantRepository.find_LTB_Mandant();
        } else if (mandant == "LeoBus") {
            newMandant = mandantRepository.find_LeoBus_Mandant();
        } else if (mandant == "LSVB") {
            newMandant = mandantRepository.find_LSVB_Mandant();
        } else if (mandant == "IFTEC") {
            newMandant = mandantRepository.find_IFTEC_Mandant();
        } else if (mandant == "LVV") {
            newMandant = mandantRepository.find_LVV_Mandant();
        }
        return newMandant;
    }
}
