package org.quickstart.druid.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigFileUtil {
	private static final boolean isWin
		= System.getProperty("os.name").toUpperCase().substring(0,3).equals("WIN");
	private static Logger logger = Logger.getLogger(ConfigFileUtil.class);
	private static String basePath;

	static {		
		basePath = getPath(ConfigFileUtil.class,"");
		basePath = new File(basePath).getParent();
		logger.debug("basepath:" + basePath);
	}
	
	public static String getPath(Class<?> classz,String filename) {
		URL url = classz.getClassLoader().getResource(filename);
		String configPath = null;
		if (url != null) {
			configPath = url.getPath().replaceAll("%20", " ");
			logger.debug(configPath);
			if (isWin) {
				configPath = configPath.toLowerCase();
			}
		}
		return configPath;
	}
	
	public static String getPath(String filename) {
		filename = filename.replaceAll("%20", " ");
		if (isWin) {
			filename = filename.toLowerCase();
		}

		String path = basePath + filename;
		return path;
	}
	
	public static Properties getProperties(Class<?> classz,String filename) {
		Properties p = new Properties();
		String configPath = getPath(classz,filename);
		try {        	
		            
            InputStream input = new FileInputStream(configPath);
            if (input != null) {
            	p.load(input);
            }
		} catch (Exception e) {
			logger.error("读取配置文件失败",e);
		}
		return p;
	}
}
