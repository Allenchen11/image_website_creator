package com.iwc.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageUtil {

	@SuppressWarnings("unused")
	public static void changeImageToMono(File imageFile, File outputFile) throws Exception {

        BufferedImage image = ImageIO.read(imageFile);

        BufferedImage result = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_BYTE_BINARY);

        Graphics2D graphic = result.createGraphics();
        graphic.drawImage(image, 0, 0, Color.WHITE, null);
        graphic.dispose();

        ImageIO.write(result, "png", outputFile);
	}
	
}
