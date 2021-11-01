package com.iwc.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("resource")
public class IOUtil {
	private static final Logger logger = LogManager.getLogger(IOUtil.class);
	
	public static String stringToHtmlFile(String destFilePath,String context) {
		try {
			FileWriter fw = new FileWriter(destFilePath);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(context);
			bw.close();
			
			return "0000";
		} catch (Exception e) {
			logger.error(e);
			return "1111";
		}
	}

	public static String readFileContextToString(String srcFilePath) {
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader bufferIn = new BufferedReader(new InputStreamReader(new FileInputStream(srcFilePath),"UTF-8"));
			String str = ""; 

			while((str =bufferIn.readLine() )!=null) {
				sb.append(str);
			}
			
			return sb.toString();
		} catch (Exception e) {
			logger.error(e);
			return "1111";
		}
	}

	public static boolean copyAllFolder(String srcPath, String destPath) {
		try {
			String source = srcPath;
			File srcDir = new File(source);

			String destination = destPath;
			File destDir = new File(destination);

			FileUtils.copyDirectory(srcDir, destDir);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}

		return true;
	}
}
