package hu.unideb.webshop.dao;

import hu.unideb.webshop.entity.image.ImageData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDataRepository extends JpaRepository<ImageData, Long> {

}
