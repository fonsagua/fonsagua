package es.udc.cartolab.gvsig.fonsagua.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageUtils {

    public static byte[] convertImageToBytea(File image) throws IOException {
	BufferedImage bufferedImage = ImageIO.read(image);
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write(bufferedImage, "jpg", baos);
	baos.flush();
	byte[] imageBytes = baos.toByteArray();
	baos.close();
	return imageBytes;
    }
    
    public static Image convertByteaToImage(byte[] imageBytes) {
	InputStream in = new ByteArrayInputStream(imageBytes);
	try {
	    return ImageIO.read(in);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }
}
