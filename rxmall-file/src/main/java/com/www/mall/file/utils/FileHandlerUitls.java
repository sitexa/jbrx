package com.www.mall.file.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.gavin.business.DBTrans;


public class FileHandlerUitls {
	private static Logger log = Logger.getLogger(FileHandlerUitls.class);
	public static List<FileItem> getFileItem(HttpServletRequest request) {
		/**
		 * 创建文件上传工厂
		 */
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置小于sizeThreshold　默认5M 临界值时，直接保存在内存中　
		factory.setSizeThreshold(DBTrans.getInstance().getParameter("lMemStoreThreshold",5242880));
		// 创建一个上传文件的ServletFileUpload对象
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置最大的上传限制
		upload.setSizeMax(DBTrans.getInstance().getParameter("lUploadMaxFileSize",209715200));

		// 处理HTTP请求，items是所有的表单项
		List<FileItem> fileItems = new ArrayList<FileItem>();
		try {
			fileItems = upload.parseRequest(request);
		} catch (FileUploadException e) {
			log.warn("getFileItem error",e);
		}
		List<FileItem> files=new ArrayList<FileItem>();
		for (int i = 0; i < fileItems.size(); i++) {
			FileItem fileItem=fileItems.get(i);
			if(fileItem.isFormField()==false){
				files.add(fileItem);
//				return fileItem;
			}
		}
		return files;
	}
	
	public static Properties getProp(String strPropFile)throws IOException {
		return getProp(strPropFile, FileHandlerUitls.class);
	}
	
	public static Properties getProp(String strPropFile, Class<FileHandlerUitls> clasz)throws IOException {
		InputStream is = clasz.getResourceAsStream(strPropFile);
		Properties p = new Properties();
		p.load(is);
		is.close();
		return p;
		
	}
	
	public static String getSuffix(String strFileName){
		String strSuffix = "";
		if(strFileName==null){
			return strSuffix;
		}
		int nExtIndex = strFileName.lastIndexOf(".");
		
		if (nExtIndex != -1) {
			strSuffix = strFileName.substring(strFileName.lastIndexOf(".")); // jpg
		}
		return strSuffix;
	}
	
	
	public static String getStrDate(String strFormat){
		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
		Date date = new Date();
		return sdf.format(date);
	}
	
	public static String getHost(String strURL){
		URL url=null;
		try {
			url = new URL(strURL);
		} catch (MalformedURLException e1) {
			log.error(e1.getLocalizedMessage());
			return "";
		}
		String strHost = url.getHost();
		return strHost;
	}
	
	public static String verifyDir(String strDir){
		if(strDir==null)
		{
			throw new RuntimeException("Invalid   dir: "+strDir);
		}
		File dir=new File(strDir);
		if(dir.exists()==false)
		{
			throw new RuntimeException("Invalid  dir: "+strDir);
		}
		if(strDir.endsWith("/")==false)
		{
			strDir+="/";
		}
		return strDir;
	}
}
