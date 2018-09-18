package com.www.mall.file.handler.store;

import java.io.IOException;
import java.io.InputStream;

import com.gavin.business.DBTrans;
import com.gavin.model.Response;


public class StoreManagerProxy {
	public static StoreManagerProxy getInstance() {
		return StoreManagerProxyHolder.instance;
	}

	private static class StoreManagerProxyHolder {
		private static StoreManagerProxy instance = new StoreManagerProxy();
	}

	private String strStorePriority;

	private StoreManagerProxy() {
		strStorePriority = DBTrans.get("strStorePriority");
	}

	public Response save(byte[] data, String strSuffix) throws IOException {
		 
		Response cdoURIPath;
		boolean isTFSStore = "TFS".equals(strStorePriority);
		boolean isFastDFSStore="FastDFS".equals(strStorePriority);
		
		if (data.length > DBTrans.get("lTFSStoreSize",5242880)) {//如果上传的文件超过指定MB也不要向TFS存了
			isTFSStore = false;
		}
		
		if (data.length > DBTrans.get("lFastDFSStoreSize",5242880)) {//如果上传的文件超过指定MB也不要向FastDFS存了
			isFastDFSStore = false;
		}
		
		if (isTFSStore) {
			cdoURIPath = TFSStoreManager.getInstance().save(data, strSuffix);
		}else if(isFastDFSStore){
			cdoURIPath = FastDFSStoreManager.getInstance().save(data, strSuffix);
		}else{
			cdoURIPath = LocalStoreManager.getInstance().save(data, strSuffix);
		}
		return cdoURIPath;
	}

	public Response save(InputStream is, String strSuffix) throws IOException {
		return LocalStoreManager.getInstance().save(is, strSuffix);
	}
}
