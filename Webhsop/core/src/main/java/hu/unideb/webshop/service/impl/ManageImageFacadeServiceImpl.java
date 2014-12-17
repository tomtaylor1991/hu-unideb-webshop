package hu.unideb.webshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.webshop.dto.ImageInfoDTO;
import hu.unideb.webshop.service.ImageService;
import hu.unideb.webshop.service.ManageImageFacadeService;

@Service("manageImageFacadeService")
public class ManageImageFacadeServiceImpl implements ManageImageFacadeService {

	@Autowired
	ImageService imageService;

	@Override
	public ImageInfoDTO saveImage(byte[] data, String name) {
		return imageService.saveImage(data, name);
	}

	@Override
	public byte[] getImage(long imageDataId) {
		return imageService.getImage(imageDataId);
	}

}
