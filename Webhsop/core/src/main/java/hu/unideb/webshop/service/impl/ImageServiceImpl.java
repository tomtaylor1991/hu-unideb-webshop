package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dao.ImageDataRepository;
import hu.unideb.webshop.dao.ImageInfoDao;
import hu.unideb.webshop.dto.ImageInfoDTO;
import hu.unideb.webshop.dto.ProductDTO;
import hu.unideb.webshop.entity.image.ImageData;
import hu.unideb.webshop.entity.image.ImageInfo;
import hu.unideb.webshop.entity.image.ImageUtils;
import hu.unideb.webshop.service.ImageService;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("imageService")
@Transactional(propagation = Propagation.REQUIRED)
public class ImageServiceImpl implements ImageService {

	@Autowired
	ImageDataRepository imageDataRepository;
	@Autowired
	ImageInfoDao imageInfoDao;

	@Override
	public ImageInfoDTO saveImage(byte[] data, String name) {
		ImageData imageData = new ImageData(data);
		ImageData thumbnailData;
		try {
			thumbnailData = new ImageData(ImageUtils.createThumbnail(data, 200,
					200));

			imageDataRepository.save(imageData);
			imageDataRepository.save(thumbnailData);

			ImageInfo imageInfo = new ImageInfo();
			imageInfo.setFileName(name);

			imageInfo.setImageDataId(imageData.getImageDataId());
			imageInfo.setThumbnailDataId(thumbnailData.getImageDataId());

			imageInfoDao.save(imageInfo);

			return imageInfoDao.toDto(imageInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public byte[] getImage(long imageDataId) {
		return imageDataRepository.getOne(imageDataId).getData();
	}

	@Override
	public void removeImage(ImageInfoDTO image) {
		imageInfoDao.delete(imageInfoDao.toEntity(image, null));

	}

	@Override
	public List<ImageInfoDTO> getProductImages(ProductDTO product) {
		List<ImageInfo> entities = imageInfoDao
				.findByProductId(product.getId());
		List<ImageInfoDTO> ret = new LinkedList<ImageInfoDTO>();
		if (entities != null && entities.size() > 0) {
			for (ImageInfo i : entities) {
				ret.add(imageInfoDao.toDto(i));
			}
		}
		return ret;
	}

	@Override
	public void removeAllOfProductImages(ProductDTO product) {
		imageInfoDao.deleteByProductId(product.getId());

	}

	@Override
	public void updateImage(ImageInfoDTO image) {
		imageInfoDao.save(imageInfoDao.toEntity(image, null));

	}

}
