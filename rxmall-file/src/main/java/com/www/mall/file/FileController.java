package com.www.mall.file;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.gavin.model.Response;
import com.jfinal.log.Log;
import com.www.mall.common.base.BaseController;
import com.www.mall.common.bean.RC;
import com.www.mall.file.handler.FileHandlerProxy;
import com.www.mall.file.utils.Common;
import com.www.mall.file.utils.FileHandlerUitls;

import io.jboot.web.controller.annotation.RequestMapping;

/**
 * ------------------------------
 * 文件操作
 * ------------------------------
 * @author wdm（l311576@sina.com）  @date 2018年6月15日
 * @version 1.0
 */
@RequestMapping(value =  "/")
public class FileController extends BaseController{
	Log log=Log.getLog(FileController.class);
	
	public void index(){
        render("/FileUpload.html");
	}
	
	public void flash(){
        render("/Flashupload.html");
	}

	public void upload() throws IOException {
		HttpServletRequest httpRequest=getRequest();
		Response response = new Response();
		try {
			System.out.println(isMultipartRequest()+httpRequest.getContentType());
			response = FileHandlerProxy.getInstance().handle(httpRequest,Common.strFileDomain);
			log.info("fileInfo="+response);
		} catch (Exception e) {
			log.error("FileHandlerProxy.handle",e);
			response.set("result",RC.BUSINESS_FAIL.getState());
			response.set("message","上传异常");
			result(response);
			return;
		}
		String strFileName = "";
		String strURLPath = "";
		String strSourceFileName = "";
		if (response!=null&&response.empty() == false) {
			strFileName = response.getString("strFileName");
			strURLPath = Common.strFileDomain+ response.getString("strURIPath");
			strSourceFileName = response.getString("strSourceFileName");
		}

		Response responseClient=new Response();
		String paramCallBacke = httpRequest.getParameter("paramCallBacke"); // 是否是工具上传还是   ckeditor
		String editorType = httpRequest.getParameter("editorType");
		String servletPath = httpRequest.getServletPath().toUpperCase();
		if (servletPath.startsWith("/SERVLET/IMAGEUPLOAD.SVL")) {
			if("ckeditor".equals(paramCallBacke)){
				String CKEditorH5FuncNum = httpRequest.getParameter("CKEditorFuncNum");
				String callBackURL = httpRequest.getHeader("REFERER");//
				String callBackServlet = httpRequest.getParameter("callBackServlet");
				StringBuilder sbReturn = new StringBuilder();
				sbReturn.append("<script type=\"text/javascript\">");
				sbReturn.append("window.parent.CKEDITOR.tools.callFunction(");
				sbReturn.append(CKEditorH5FuncNum);
				sbReturn.append(", '");
				sbReturn.append(strURLPath + strFileName);
				sbReturn.append("', '');</script>");
				String returnString = sbReturn.toString();
				String sendUrl="http://"+FileHandlerUitls.getHost(callBackURL)+ callBackServlet + "?backData=" + returnString;
				redirect(sendUrl);
				return;
			} else if ("ueditor".equals(editorType)) {//上传图片方式为ueditor
				responseClient.set("url", strURLPath + strFileName);
				responseClient.set("title", "strSourceFileName");
				responseClient.set("state", "SUCCESS");
			} else{
				responseClient.set("strSourceFileName", strSourceFileName);
				responseClient.set("strFilePath", strURLPath + strFileName);
			}
		}
//		else {// Servlet/fileUpload.svl
//			responseClient.set("strSourceFileName", strSourceFileName);
//			responseClient.set("strFilePath", strURLPath);
//			responseClient.set("strFileName", strFileName);
//		}
		
		response.set("fileDomain", Common.strFileDomain);
		renderJson(response.toJson());
	}
	
	public void download(){
		String strFileName=getPara("name");
		String strURL=getPara("url"); //文件下载地址（整个路径包括文件名）
		log.info("strURL="+strURL+" strFileName="+strFileName);
		redirect(strURL);//http://localhost:8080/wfile/download.dld?url=http://pay.0731333.com/file/M00/00/00/wKgAT1rO9g6AUxrwAAFBNEFUOWk615.png&name=0bcac34b5cda4f30b8b08c3719fe2ca8.png
	}
}