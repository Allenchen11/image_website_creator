package com.iwc.utils;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileUtil {
	private static final Logger logger = LogManager.getLogger(FileUtil.class);

	@SuppressWarnings("unused")
	public static void traversalFolder(File folder, List<String> filePathList) {

		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				traversalFolder(file, filePathList);
			}
			if (file.isFile()) {
				logger.info("add = "+file.getAbsolutePath());
				filePathList.add(file.getAbsolutePath());
			}
		}
	}

}
