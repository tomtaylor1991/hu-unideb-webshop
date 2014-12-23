package hu.unideb.webshop.service;

import java.util.List;

import hu.unideb.webshop.dto.ImageInfoDTO;
import hu.unideb.webshop.dto.ProductDTO;

public interface ImageService {

	public ImageInfoDTO saveImage(byte[] data, String name);

	void updateImage(ImageInfoDTO image);

	public byte[] getImage(long imageDataId);

	void removeImage(ImageInfoDTO image);

	List<ImageInfoDTO> getProductImages(ProductDTO product);

	void removeAllOfProductImages(ProductDTO product);
}
