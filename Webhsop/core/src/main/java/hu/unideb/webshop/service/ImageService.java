package hu.unideb.webshop.service;

import hu.unideb.webshop.dto.ImageInfoDTO;

public interface ImageService {

	public ImageInfoDTO saveImage(byte[] data, String name);
	public byte[] getImage(long imageDataId);
	
}
