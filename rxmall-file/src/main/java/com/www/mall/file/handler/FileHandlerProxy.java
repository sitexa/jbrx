package com.www.mall.file.handler;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.gavin.business.DBTrans;
import com.gavin.model.Request;
import com.gavin.model.Response;
import com.www.mall.common.bean.RC;
import com.www.mall.file.utils.FileHandlerUitls;


public   class FileHandlerProxy {
	private Logger log = Logger.getLogger(FileHandlerProxy.class);
	FileHandler handler;
	
	public static FileHandlerProxy getInstance() {
		return FileHandlerProxyHolder.instance;
	}
	private static class FileHandlerProxyHolder {
		private static FileHandlerProxy instance = new FileHandlerProxy();
	}
	
	private FileHandlerProxy(){
 		FileHandler streamFileHanler=new StreamFileHanler();
 		handler=new H5FileHanler();
		handler.setHandler(streamFileHanler);
	}
	
	public Response handle(HttpServletRequest httpRequest,String strFileDomain){
		Response response=Response.ok();
		try {
			response = handler.handleUploadRequest(httpRequest);
			response.set("result",RC.SUCCESS.getState());
			response.set("message","上传成功");
		} catch (Exception e) {
			log.error("handler.handleUploadRequest error",e);
			response.set("result",RC.BUSINESS1.getState());
			response.set("message","上传异常");
		}
		log.info("FileHandlerProxy.handleUploadRequest fileInfo="+response);

		// save info
		if (response!=null&&response.empty() == false) {
			String strFileName = response.getString("strFileName");
			String strSourceFileName = response.getString("strSourceFileName");
			int nFileCode=Math.abs(strFileName.hashCode()%DBTrans.get("FileTableCount",500));
			
			Request requests=Request.build("FileService", "saveFile")
			.set("files", response.getModels("files"))
			.set("fileDomain", strFileDomain)				//文件访问域名
			.set("filePath", response.getString("strURIPath"))//文件相对路径
			.set("fileName",strFileName)					//文件名
			.set("sourceFileName",strSourceFileName)//原文件名
			.set("createTime",FileHandlerUitls.getStrDate("yyyy-MM-dd HH:mm:ss") )
			.set("fileSize", response.getLong("lSize"))
			.set("fileCode",nFileCode);
			Response resp=DBTrans.execute(requests);
			if(resp.fail()){
				log.error("FileService.saveFile error response="+resp.log());
			}
		}
		return response;
	}
	
}
