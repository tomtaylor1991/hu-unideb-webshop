package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dao.MaterialDao;
import hu.unideb.webshop.dto.MaterialDTO;
import hu.unideb.webshop.entity.Material;
import hu.unideb.webshop.service.MaterialService;
import hu.unideb.webshop.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("materialService")
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	MaterialDao materialDao;

	@Autowired
	UserService userService;

	@Transactional(propagation = Propagation.REQUIRED)
	public List<MaterialDTO> getMaterialList(int page, int size) {
		List<MaterialDTO> ret = new ArrayList<MaterialDTO>(size);
		Page<Material> entities = materialDao.findAll(new PageRequest(page,
				size/* sort */));
		if (entities != null && entities.getSize() > 0) {
			List<Material> contents = entities.getContent();
			for (Material material : contents) {
				ret.add(materialDao.toDto(material));
			}
		}
		return ret;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void createMaterial(MaterialDTO material) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetails = (UserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			material.setRecUserId(userService
					.getUser(userDetails.getUsername()).getId() + "");
			material.setRecDate(new Date());
			material.setModUserId(userService
					.getUser(userDetails.getUsername()).getId() + "");
			material.setModDate(new Date());
		}
		Material materialentity = materialDao.toEntity(material, null);
		materialentity = materialDao.save(materialentity);
		material.setId(materialDao.toDto(materialentity).getId());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void removeMaterial(MaterialDTO material) {
		materialDao.delete(materialDao.toEntity(material, null));
	}

	@Override
	public void updateMaterial(MaterialDTO material) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetails = (UserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			material.setModUserId(userService
					.getUser(userDetails.getUsername()).getId() + "");
			material.setModDate(new Date());
		}
		materialDao.save(materialDao.toEntity(material, null));
	}

	@Override
	public MaterialDTO getMaterial(Long materialId) {
		return materialDao.toDto(materialDao.findOne(materialId));
	}

	@Override
	public List<MaterialDTO> getAllMaterialList() {
		List<MaterialDTO> ret = new ArrayList<MaterialDTO>();
		List<Material> contents = (List<Material>) materialDao.findAll();
		for (Material material : contents) {
			ret.add(materialDao.toDto(material));
		}
		return ret;
	}

	@Override
	public List<MaterialDTO> getMaterialList(int page, int size,
			String sortField, int sortOrder, String filter,
			String filterColumnName) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC
				: Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
				new Order(dir, sortField)));
		List<MaterialDTO> ret = new ArrayList<MaterialDTO>(size);
		Page<Material> entities;
		if (filter.length() != 0 && filterColumnName.equals("materialName")) {
			entities = materialDao.findByMaterialNameStartsWith(filter,
					pageRequest);
		} else {
			entities = materialDao.findAll(pageRequest);
		}

		if (entities != null && entities.getSize() > 0) {
			List<Material> contents = entities.getContent();
			for (Material m : contents) {
				ret.add(materialDao.toDto(m));
			}
		}
		return ret;
	}

	@Override
	public int getRowNumber() {

		return materialDao.countById();
	}

}
