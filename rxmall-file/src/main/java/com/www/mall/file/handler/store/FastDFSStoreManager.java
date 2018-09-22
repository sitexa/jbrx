package com.www.mall.file.handler.store;

import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import com.gavin.model.Response;

/**
 * ------------------------------
 * fastdfs文件存储
 * ------------------------------
 * @author wdm（l311576@sina.com）  @date 2018年4月12日
 * @version 1.0
 */
public class FastDFSStoreManager {
	private StorageClient storageClient;
	
	private FastDFSStoreManager() {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void  init() throws Exception{
	    // 1、把FastDFS提供的jar包添加到工程中
	    // 2、初始化全局配置。加载一个配置文件。
	    ClientGlobal.init("config.properties");
	    // 3、创建一个TrackerClient对象。
	    TrackerClient trackerClient = new TrackerClient();
	    // 4、创建一个TrackerServer对象。
	    TrackerServer trackerServer = trackerClient.getConnection();
	    // 5、声明一个StorageServer对象，null。
	    StorageServer storageServer = null;
	    // 6、获得StorageClient对象。
	    storageClient = new StorageClient(trackerServer,  storageServer);
	    
		if(storageClient!=null){
			return ;
		}
	}

	public static FastDFSStoreManager getInstance() {
		return TFSManagerHolder.instance;
	}
	private static class TFSManagerHolder {
		private static FastDFSStoreManager instance = new FastDFSStoreManager();
	}

	public Response save(byte[] data, String strSuffix) {
		String[] result =null;
		try {
			result = storageClient.upload_file(data, strSuffix.replace(".", ""), null);
		} catch (IOException | MyException e) {
			e.printStackTrace();
		}
		if(result==null){
			return null;
		}
		Response cdoFilePath=new Response();
 		cdoFilePath.set("strURIPath", "");
		cdoFilePath.set("strFileName", result[0]+"/"+result[1]);
		return cdoFilePath;
	}
	
//	public static void main(String[] args) {
//		TFSStoreManager.getInstance().init();
//	}

}
