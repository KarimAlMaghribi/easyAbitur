package de.l.stadtwerke.loga3jobofferservice.repository;

import de.l.stadtwerke.loga3jobofferservice.model.TitleImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TitleImageRepository extends JpaRepository<TitleImage, String> {
    boolean existsTitleImageByTitleID(String titleId);
    TitleImage findByTitleID(String titleId);
    @Transactional
    void deleteByTitleID(String titleId);
}
