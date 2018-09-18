package com.www.mall.file.handler.store;

import com.gavin.model.Response;
import com.taobao.common.tfs.DefaultTfsManager;
import com.www.mall.file.utils.Common;

/**
 * ------------------------------
 * tfs文件存储
 * ------------------------------
 * @author wdm（l311576@sina.com）  @date 2018年4月12日
 * @version 1.0
 */
public class TFSStoreManager {
	private DefaultTfsManager tfsManager;

	private TFSStoreManager() {
		init();
	}
	
	public synchronized void  init(){
		if(tfsManager!=null){
			return ;
		}
		
		tfsManager = new DefaultTfsManager();
		tfsManager.setMasterIP(Common.TFSMasterIP);
		tfsManager.setTimeout(Common.TFSTimeout);
		tfsManager.setMaxWaitThread(Common.TFSMaxWaitThread);
	}

	public static TFSStoreManager getInstance() {
		return TFSManagerHolder.instance;
	}
	private static class TFSManagerHolder {
		private static TFSStoreManager instance = new TFSStoreManager();
	}

	public Response save(byte[] data, String strSuffix) {
		String strTFSName=tfsManager.saveFile(data, null, strSuffix);
		Response cdoFilePath=new Response();
 		cdoFilePath.set("strURIPath", Common.TFSNativeURI);
		cdoFilePath.set("strFileName", strTFSName+strSuffix);
		return cdoFilePath;
	}
	
//	public static void main(String[] args) {
//		TFSStoreManager.getInstance().init();
//	}

}
