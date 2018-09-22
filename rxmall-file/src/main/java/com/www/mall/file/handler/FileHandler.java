package com.www.mall.file.handler;

import javax.servlet.http.HttpServletRequest;

import com.gavin.model.Response;

/**
 * ------------------------------
 * 文件处理器
 * ------------------------------
 * @author wdm（l311576@sina.com）  @date 2018年4月12日
 * @version 1.0
 */
public abstract class FileHandler {
		
	private FileHandler handler;

	public FileHandler getHandler() {
		return handler;
	}

	public void setHandler(FileHandler handler) {
		this.handler = handler;
	}
	
	
	/**
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public abstract Response handleUploadRequest(HttpServletRequest req)throws Exception ;
	
}
