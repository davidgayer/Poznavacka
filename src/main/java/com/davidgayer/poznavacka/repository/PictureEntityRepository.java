package com.davidgayer.poznavacka.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.davidgayer.poznavacka.model.PictureEntity;

@RepositoryRestResource(collectionResourceRel = "picture", path = "picture")
public interface PictureEntityRepository extends JpaRepository<PictureEntity, Long> {
    List<PictureEntity> findAllByCategoryId(Long id);
} 
