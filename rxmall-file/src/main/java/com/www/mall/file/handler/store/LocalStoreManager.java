package com.www.mall.file.handler.store;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.gavin.model.Response;
import com.www.mall.file.utils.Common;
import com.www.mall.file.utils.FileHandlerUitls;
import com.www.mall.file.utils.Utility;

/**
 * ------------------------------
 * 本地文件存储
 * ------------------------------
 * @author wdm（l311576@sina.com）  @date 2018年4月12日
 * @version 1.0
 */
public class LocalStoreManager {
	private LocalStoreManager() {
//		init();
	}

//	private String strLocalFilePath;
//
//	public synchronized void init() {
//		Properties prop = null;
//		try {
//			prop = FileHandlerUitls.getProp("/LocalStore.properties");
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//		strLocalFilePath = prop.getProperty("strLocalFilePath");
//	}

	public static LocalStoreManager getInstance() {
		return LocalStoreManagerHolder.instance;
	}

	private static class LocalStoreManagerHolder {
		private static LocalStoreManager instance = new LocalStoreManager();
	}

	public Response save(InputStream is, String strSuffix) throws IOException {
		FileOutputStream fos = null;
		Response cdoFilePath= getSavePath(Common.strLocalFilePath, strSuffix);
		String strSavePath=cdoFilePath.getString("strSavePath");
 	 
		long lSize = 0;
		try {
			fos = new FileOutputStream(strSavePath);
			byte[] buffer = new byte[1024];
			int len;
			// 读取上传文件的内容，并将其写入服务器的文件中
			while ((len = is.read(buffer)) > 0) {
				lSize = lSize + len;
				fos.write(buffer, 0, len);
			}
		}  finally {
			// 关闭输入流和输出流
			Utility.closeStream(is);
       		Utility.closeStream(fos);
		}
		return cdoFilePath;
	}

	public Response save(byte[] data, String strSuffix) throws IOException {
		FileOutputStream fos = null;
		Response cdoFilePath= getSavePath(Common.strLocalFilePath, strSuffix);
		String strSavePath=cdoFilePath.getString("strSavePath");
 		 
		try {
			fos = new FileOutputStream(strSavePath);
			fos.write(data, 0, data.length);
		}  finally {
			// 关闭输入流和输出流
        	Utility.closeStream(fos);
		}
		return cdoFilePath;
	}

	private Response getSavePath(String strLocalFilePath, String strSuffix) {
		 
		String strDate=FileHandlerUitls.getStrDate("yyyyMMdd");
		String strFileName=java.util.UUID.randomUUID().toString().replaceAll("-", "");
		
		StringBuffer strSavePath = new StringBuffer();
		strSavePath.append(strLocalFilePath).append("file").append(File.separator);
		strSavePath.append(strDate).append(File.separator);
		File file = new File(strSavePath.toString());
		if (!file.exists()) {
			file.mkdir();
		}
		strSavePath.append(strFileName);
		strSavePath.append(strSuffix);
		
		//相对路径，相对于Common.strLocalFilePath的路径
		StringBuffer strURIPath = new StringBuffer();
		strURIPath.append("file").append("/").append(strDate).append("/");
		
		
		Response cdoFilePath=new Response();
		cdoFilePath.set("strSavePath", strSavePath.toString());
		cdoFilePath.set("strURIPath", strURIPath.toString());
		cdoFilePath.set("strFileName", strFileName+strSuffix);
		return cdoFilePath;
	}
 
}
