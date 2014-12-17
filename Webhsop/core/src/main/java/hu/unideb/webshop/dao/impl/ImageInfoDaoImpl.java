package hu.unideb.webshop.dao.impl;

import hu.unideb.webshop.dao.BaseConvertDao;
import hu.unideb.webshop.dto.ImageInfoDTO;
import hu.unideb.webshop.entity.image.ImageInfo;

public class ImageInfoDaoImpl implements
		BaseConvertDao<ImageInfo, ImageInfoDTO> {

	@Override
	public ImageInfo toEntity(ImageInfoDTO dto, ImageInfo entity) {
		ImageInfo ret = entity;
		if (dto.getId() == null || entity == null) {
			ret = new ImageInfo();
			ret.setId(dto.getId());
			ret.setRecDate(dto.getRecDate());
			ret.setRecUserId(dto.getRecUserId());
		}
		ret.setModDate(dto.getModDate());
		ret.setModUserId(dto.getModUserId());
		ret.setFileName(dto.getFileName());
		ret.setImageDataId(dto.getImageDataId());
		ret.setThumbnailDataId(dto.getThumbnailDataId());
		return ret;
	}

	@Override
	public ImageInfoDTO toDto(ImageInfo entity) {
		ImageInfoDTO ret = new ImageInfoDTO();
		ret.setId(entity.getId());
		ret.setRecDate(entity.getRecDate());
		ret.setRecUserId(entity.getRecUserId());
		ret.setModDate(entity.getModDate());
		ret.setModUserId(entity.getModUserId());
		ret.setFileName(entity.getFileName());
		ret.setImageDataId(entity.getImageDataId());
		ret.setThumbnailDataId(entity.getThumbnailDataId());
		return ret;
	}

}
