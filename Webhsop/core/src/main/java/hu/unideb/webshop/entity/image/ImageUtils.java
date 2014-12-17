package hu.unideb.webshop.entity.image;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;

public class ImageUtils {

	public String getImageFileFormat(byte[] imageFile) throws IOException, UnsupportedImageFileFormatException {
		ByteArrayInputStream bais = new ByteArrayInputStream(imageFile);
		ImageInputStream iis = ImageIO.createImageInputStream(bais);
		Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
		if (!iter.hasNext())
			throw new UnsupportedImageFileFormatException();
		return iter.next().getFormatName();
	}

	public static byte[] createThumbnail(byte[] imageFile, int max_width, int max_height) throws IOException {

		BufferedImage source = ImageIO.read(new ByteArrayInputStream(imageFile));

		int w = source.getWidth();
		int h = source.getHeight();
		float scale = 0;

		if (w > h) {
			scale = (float) max_width / (float) w;
		} else {
			scale = (float) max_height / (float) h;
		}

		int nw = Math.round(source.getWidth() * scale);
		int nh = Math.round(source.getHeight() * scale);

		BufferedImage scaledImage = new BufferedImage(nw, nh, BufferedImage.TYPE_INT_RGB);
		AffineTransform scale_trans = AffineTransform.getScaleInstance(scale, scale);
		AffineTransformOp scale_op = new AffineTransformOp(scale_trans, AffineTransformOp.TYPE_BICUBIC);
		scaledImage.createGraphics().drawImage(source, scale_op, 0, 0);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();
		ImageWriteParam iwp = writer.getDefaultWriteParam();
		iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		iwp.setCompressionQuality(0.85f);
		writer.setOutput(ImageIO.createImageOutputStream(baos));
		writer.write(null, new IIOImage(scaledImage, null, null), iwp);
		writer.dispose();

		return baos.toByteArray();

	}

}
