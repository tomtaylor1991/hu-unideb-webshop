package hu.unideb.webshop.entity.image;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "image_data")
public class ImageData {
	
	@Id
	@GeneratedValue
	@Column(name = "image_data_id")
	private Long imageDataId;

	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] data;

	public ImageData() {}
	
	public ImageData(byte[] data) {
		this.data = data;
	}
	
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	public Long getImageDataId() {
		return imageDataId;
	}
	
	public void setImageDataId(Long imageDataId) {
		this.imageDataId = imageDataId;
	}
	
	
	
}
