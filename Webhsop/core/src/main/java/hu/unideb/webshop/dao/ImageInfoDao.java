package hu.unideb.webshop.dao;

import hu.unideb.webshop.dto.ImageInfoDTO;
import hu.unideb.webshop.entity.image.ImageInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageInfoDao extends JpaRepository<ImageInfo, Long>,
		BaseConvertDao<ImageInfo, ImageInfoDTO> {

}
