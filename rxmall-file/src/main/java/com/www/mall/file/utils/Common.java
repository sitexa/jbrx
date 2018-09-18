package com.www.mall.file.utils;

import com.gavin.business.DBTrans;

public class Common {
	//访问域名
	public static String 	strFileDomain=DBTrans.get("strFileDomain");
	//文件存放路径-绝对路径
	public static String 	strLocalFilePath=DBTrans.get("strLocalFilePath");
	
	//tfs
	public static String 	TFSNativeURI=DBTrans.get("TFSNativeURI","v1/tfs/");
	public static String TFSMasterIP=DBTrans.get("TFSMasterIP");
	public static int TFSTimeout=DBTrans.get("TFSTimeout",60000);
	public static int TFSMaxWaitThread=DBTrans.get("TFSMaxWaitThread",200);
	
}
