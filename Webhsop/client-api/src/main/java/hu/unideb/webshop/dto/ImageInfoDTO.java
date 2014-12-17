package hu.unideb.webshop.dto;

import java.io.Serializable;

public class ImageInfoDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String fileName;

	private Long imageDataId = 0L;	

	private Long thumbnailDataId = 0L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "ImageInfoDTO [id=" + id + ", fileName=" + fileName
				+ ", imageDataId=" + imageDataId + ", thumbnailDataId="
				+ thumbnailDataId + "]";
	}

}
