package hu.unideb.webshop.dao;

import hu.unideb.webshop.dto.ImageInfoDTO;
import hu.unideb.webshop.entity.image.ImageInfo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageInfoDao extends JpaRepository<ImageInfo, Long>,
		BaseConvertDao<ImageInfo, ImageInfoDTO> {

	List<ImageInfo> findByProductId(Long id);

	@Query("delete from ImageInfo i where i.productId = ?1")
	void deleteByProductId(Long id);
}
