package com.iwc.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

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
	
	
	/**
	 * 將圖片壓縮到指定大小以內
	 * 
	 * @param srcImgData 源圖片資料
	 * @param maxSize 目的圖片大小
	 * @return 壓縮後的圖片資料
	 */
	public static byte[] compressUnderSize(byte[] srcImgData, long maxSize) {
		double scale = 0.9;
		byte[] imgData = Arrays.copyOf(srcImgData, srcImgData.length);

		if (imgData.length > maxSize) {
			do {
				try {
					imgData = compress(imgData, scale);

				} catch (IOException e) {
					throw new IllegalStateException("壓縮圖片過程中出錯,請及時聯絡管理員!", e);
				}

			} while (imgData.length > maxSize);
		}

		return imgData;
	}

	/**
	 * 按照 寬高 比例壓縮
	 * 
	 * @param imgIs 待壓縮圖片輸入流
	 * @param scale 壓縮刻度
	 * @param out 輸出
	 * @return 壓縮後圖片資料
	 * @throws IOException 壓縮圖片過程中出錯
	 */
	public static byte[] compress(byte[] srcImgData, double scale) throws IOException {
		BufferedImage bi = ImageIO.read(new ByteArrayInputStream(srcImgData));
		int width = (int) (bi.getWidth() * scale); // 源圖寬度
		int height = (int) (bi.getHeight() * scale); // 源圖高度

		Image image = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics g = tag.getGraphics();
		g.setColor(Color.RED);
		g.drawImage(image, 0, 0, null); // 繪製處理後的圖
		g.dispose();

		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		ImageIO.write(tag, "JPEG", bOut);

		return bOut.toByteArray();
	}
	
}
