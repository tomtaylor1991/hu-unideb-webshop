package hu.unideb.webshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.webshop.dto.ImageInfoDTO;
import hu.unideb.webshop.dto.ProductDTO;
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

	@Override
	public void removeImage(ImageInfoDTO image) {
		imageService.removeImage(image);

	}

	@Override
	public List<ImageInfoDTO> getProductImages(ProductDTO product) {
		return imageService.getProductImages(product);
	}

	@Override
	public void removeAllOfProductImages(ProductDTO product) {
		imageService.removeAllOfProductImages(product);
	}

	@Override
	public void updateImage(ImageInfoDTO image) {
		imageService.updateImage(image);
		
	}

}
