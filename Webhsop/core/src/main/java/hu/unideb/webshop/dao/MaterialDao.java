package hu.unideb.webshop.dao;

import hu.unideb.webshop.dto.MaterialDTO;
import hu.unideb.webshop.entity.Material;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialDao extends PagingAndSortingRepository<Material, Long>,
        BaseConvertDao<Material, MaterialDTO> {

    Page<Material> findByMaterialNameStartsWith(String materialName, Pageable pageable);

    @Query(value = "SELECT count(*) FROM MATERIAL", nativeQuery = true)
    int countById();

}
