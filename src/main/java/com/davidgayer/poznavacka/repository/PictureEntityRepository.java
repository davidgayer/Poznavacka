package com.davidgayer.poznavacka.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.davidgayer.poznavacka.model.PictureEntity;

@RepositoryRestResource(collectionResourceRel = "picture", path = "picture")
public interface PictureEntityRepository extends JpaRepository<PictureEntity, Long> {
}
