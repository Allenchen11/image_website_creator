package com.iwc.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ZipUtil {

	private static final int BUFFER_SIZE = 4096;

	private static final Logger logger = LogManager.getLogger(ZipUtil.class);

	/**
	 * Compresses a list of files to a destination zip file
	 * 
	 * @param listFiles   A collection of files and directories
	 * @param destZipFile The path of the destination zip file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void zip(List<File> listFiles, String destZipFile) throws FileNotFoundException, IOException {
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(destZipFile));
		for (File file : listFiles) {
			if (file.isDirectory()) {
				zipDirectory(file, file.getName(), zos);
			} else {
				zipFile(file, zos);
			}
		}
		zos.flush();
		zos.close();
	}

	/**
	 * Compresses files represented in an array of paths
	 * 
	 * @param files       a String array containing file paths
	 * @param destZipFile The path of the destination zip file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void zip(String[] files, String destZipFile) throws FileNotFoundException, IOException {
		List<File> listFiles = new ArrayList<File>();
		for (int i = 0; i < files.length; i++) {
			listFiles.add(new File(files[i]));
		}
		zip(listFiles, destZipFile);
	}

	/**
	 * Adds a directory to the current zip output stream
	 * 
	 * @param folder       the directory to be added
	 * @param parentFolder the path of parent directory
	 * @param zos          the current zip output stream
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void zipDirectory(File folder, String parentFolder, ZipOutputStream zos)
			throws FileNotFoundException, IOException {
		for (File file : folder.listFiles()) {
			if (file.isDirectory()) {
				zipDirectory(file, parentFolder + "/" + file.getName(), zos);
				continue;
			}
			zos.putNextEntry(new ZipEntry(parentFolder + "/" + file.getName()));
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			long bytesRead = 0;
			byte[] bytesIn = new byte[BUFFER_SIZE];
			int read = 0;
			while ((read = bis.read(bytesIn)) != -1) {
				zos.write(bytesIn, 0, read);
				bytesRead += read;
			}
			zos.closeEntry();
		}
	}

	/**
	 * Adds a file to the current zip output stream
	 * 
	 * @param file the file to be added
	 * @param zos  the current zip output stream
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void zipFile(File file, ZipOutputStream zos) throws FileNotFoundException, IOException {
		zos.putNextEntry(new ZipEntry(file.getName()));
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		long bytesRead = 0;
		byte[] bytesIn = new byte[BUFFER_SIZE];
		int read = 0;
		while ((read = bis.read(bytesIn)) != -1) {
			zos.write(bytesIn, 0, read);
			bytesRead += read;
		}
		zos.closeEntry();
	}

	public void pack(String sourceDirPath, String zipFilePath) throws IOException {
		Path p = Files.createFile(Paths.get(zipFilePath));
		try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
			Path pp = Paths.get(sourceDirPath);
			Files.walk(pp).filter(path -> !Files.isDirectory(path)).forEach(path -> {
				ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
				try {
					zs.putNextEntry(zipEntry);
					Files.copy(path, zs);
					zs.closeEntry();
				} catch (IOException e) {
					logger.error(e);
				}
			});
		}
	}

}
