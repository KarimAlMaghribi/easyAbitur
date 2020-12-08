package de.l.stadtwerke.loga3jobofferservice.repository;

import de.l.stadtwerke.loga3jobofferservice.model.EMandant;
import de.l.stadtwerke.loga3jobofferservice.model.Mandant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MandantRepository extends JpaRepository<Mandant, String> {

    default Mandant find_LSW_Mandant() {
        return findByName(EMandant.LSW);
    }

    default Mandant find_LVV_Mandant() {
        return findByName(EMandant.LVV);
    }

    default Mandant find_IFTEC_Mandant() {
        return findByName(EMandant.IFTEC);
    }

    default Mandant find_LSVB_Mandant() {
        return findByName(EMandant.LSVB);
    }

    default Mandant find_LeoBus_Mandant() {
        return findByName(EMandant.LeoBus);
    }

    default Mandant find_LTB_Mandant() {
        return findByName(EMandant.LTB);
    }

    default Mandant find_LSB_Mandant() {
        return findByName(EMandant.LSB);
    }

    default Mandant find_LAB_Mandant() {
        return findByName(EMandant.LAB);
    }

    default Mandant find_LVB_Mandant() {
        return findByName(EMandant.LVB);
    }

    default Mandant find_SBL_Mandant() {
        return findByName(EMandant.SBL);
    }

    default Mandant find_WGC_Mandant() {
        return findByName(EMandant.WGC);
    }

    default Mandant find_LSI_Mandant() {
        return findByName(EMandant.LSI);
    }

    default Mandant find_BSL_Mandant() {
        return findByName(EMandant.BSL);
    }

    default Mandant find_KWL_Mandant() {
        return findByName(EMandant.KWL);
    }

    default Mandant find_LAS_Mandant() {
        return findByName(EMandant.LAS);
    }

    default Mandant find_Netz_Mandant() {
        return findByName(EMandant.Netz);
    }

    Mandant findByName(EMandant name);
}