package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dto.MaterialDTO;
import hu.unideb.webshop.service.ManageMaterialFacadeService;
import hu.unideb.webshop.service.MaterialService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("manageMaterialFacadeService")
public class ManageMaterialFacadeServiceImpl implements ManageMaterialFacadeService{

	@Autowired
	MaterialService materialService;
	
	@Override
	public List<MaterialDTO> getMaterialList(int page, int size) {
		return materialService.getMaterialList(page, size);
	}

	@Override
	public void createMaterial(MaterialDTO material) {
		materialService.createMaterial(material);
		
	}

	@Override
	public void removeMaterial(MaterialDTO material) {
		materialService.removeMaterial(material);
	}

	@Override
	public void updateMaterial(MaterialDTO material) {
		materialService.updateMaterial(material);
	}

	@Override
	public MaterialDTO getMaterial(Long materialId) {
		return materialService.getMaterial(materialId);
	}

	@Override
	public List<MaterialDTO> getAllMaterialList() {
		return materialService.getAllMaterialList();
	}

	@Override
	public List<MaterialDTO> getMaterialList(int page, int size,
			String sortField, int sortOrder, String filter,
			String filterColumnName) {
		
		return materialService.getMaterialList(page, size, sortField, sortOrder, filter, filterColumnName);
	}

	@Override
	public int getRowNumber() {
		return materialService.getRowNumber();
	}

}
