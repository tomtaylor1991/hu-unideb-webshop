package hu.unideb.webshop.entity.image;
	
import hu.unideb.webshop.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "image_info")
public class ImageInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "image_data_id")
	private Long imageDataId = 0L;
	
	@Column(name = "thumbnail_data_id")
	private Long thumbnailDataId = 0L;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getImageDataId() {
		return imageDataId;
	}

	public void setImageDataId(Long imageDataId) {
		this.imageDataId = imageDataId;
	}

	public Long getThumbnailDataId() {
		return thumbnailDataId;
	}

	public void setThumbnailDataId(Long thumbnailDataId) {
		this.thumbnailDataId = thumbnailDataId;
	}

}
