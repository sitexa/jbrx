/**
import swfobject.js
_swfId  控件ID,控件容器,
_limitFileSize 上传单个文件大小;//单位kb,
_limitFileCount  最多上传文件的个数,
_fileExt  可以选择的文件,
_fileUploadURL;文件上传地址 默认为:http://file.dafy.com.cn/Servlet/fileUpload.svl
_imageSizes:图片上传后的压缩尺寸
可以向上传组件注册的事件 data为json对象
addEventListener(FileUpload.SelectFile,function(){})开始选择文件事件
addEventListener(FileUpload.AddItem,function(data){})文件选择完成事件
addEventListener(FileUpload.Complete,function(fileId,data){})单个文件上传事件
addEventListener(FileUpload.Process,function(data){})单个文件上传中事件
addEventListener(FileUpload.Error,function(data){})//选择或上传文件过错事件

可以调用的上传组件的方法
startUpload()开始上传
delFile(fileId)删除对应文件
inc()自增最多上传文件个数
dec()自减最多上传文件个数
*/
 
function FileUpload(_swfId,_limitFileSize,_limitFileCount,_fileExt,_fileUploadURL,_imageSizes){
		
		var swfId=_swfId;
		this.addFileItemEvent_Handler=null;
		this.uploadCompleteData_Handler=null;
		this.upLoadProgressEvent_Handler=null;
		this.addFileErrorEvent_Handler=null;
		this.selectFile_Handler=null;
		this.addFileItemCompleteEvent_Handler=null;
		var self=this;
		this.binddata=null;
		function init(){
			if("undefined" != typeof  window.varSwfInstanceEvent){
					if("undefined" != typeof  window.varSwfInstanceEvent.getSwfInstance(swfId)&&window.varSwfInstanceEvent.getSwfInstance(swfId)!=null){
						alert("文件上传组件ID重复:"+swfId);
						return;
					}
			}
			if("undefined" == typeof  document.getElementById(swfId)||document.getElementById(swfId)==null){
				alert("所传入文件上传组件容器ID不存在，请确认ID是否正确，swfId:"+swfId);
				return;
			}
			var swfVersionStr = "10.2.0";
			var xiSwfUrlStr = "https://static.dafy.com/upload/playerProductInstall.swf";
            var flashvars = {};
            
            flashvars.swfId=swfId;  
            flashvars.limitFileSize=_limitFileSize;
            flashvars.limitFileCount=_limitFileCount;
            flashvars.fileType=_fileExt;
            if("undefined" != typeof  _fileUploadURL){
            	if("undefined" != typeof _imageSizes){
            		if(_fileUploadURL.indexOf("?")==-1){
            			_fileUploadURL=_fileUploadURL+"?imageSizes="+_imageSizes;
            		}else{
            			_fileUploadURL=_fileUploadURL+"&imageSizes="+_imageSizes;
            		}
            	}
              	flashvars.fileUploadURL=encodeURIComponent(_fileUploadURL);
            }
           
            var params = {};
            params.quality = "high";
            params.bgcolor = "#ffffff";
            params.allowscriptaccess = "always";
            
            params.allowfullscreen = "true";
           	params.wmode="transparent";
           	
            var attributes = {};
            attributes.id = swfId;
            attributes.name = swfId;
            attributes.align = "middle";
            swfobject.embedSWF(
                "https://static.dafy.com/upload/FileUpload.swf", swfId,  "65", "25",swfVersionStr, xiSwfUrlStr, 
                flashvars, params, attributes);
			if("undefined" == typeof  window.varSwfInstanceEvent){
				window.varSwfInstanceEvent=new swfInstanceEvent();
			}
			window.varSwfInstanceEvent.addSwfInstance(swfId,self);
		}

		this.startUpload=function(){
				document.getElementById(swfId).startUpload();
		}

		this.cancelUpload=function(){
			document.getElementById(swfId).cancelUpload();
		
		}
		this.delFile=function(fileId){
			 
			document.getElementById(swfId).delFile(fileId);
		
		}
		this.delAllFile=function(){
		 	document.getElementById(swfId).delAllFile();
		}
		this.setBinddata=function(_data){
			this.binddata=_data;
		}
		this.inc=function(){
			document.getElementById(swfId).inc();
		}
		this.dec=function(){
			document.getElementById(swfId).dec();
		}
		this.addEventListener=function(eventType,handler){
		 
			if(eventType==FileUpload.AddItem){
				this.addFileItemEvent_Handler=handler;
			}else if(eventType==FileUpload.Complete){
				this.uploadCompleteData_Handler=handler;				
			}else  if(eventType==FileUpload.Process){
				this.upLoadProgressEvent_Handler=handler;				
			}else  if(eventType==FileUpload.Error){
				this.addFileErrorEvent_Handler=handler;			
			}else if(eventType==FileUpload.SelectFile){
				this.selectFile_Handler=handler;
			}else if(eventType==FileUpload.addFileItemComplete){
				this.addFileItemCompleteEvent_Handler=handler;
			}
		}

		init();
}
window.FileUpload.AddItem=1;
window.FileUpload.Complete=2;
window.FileUpload.Process=3;
window.FileUpload.Error=4;
window.FileUpload.SelectFile=5;
window.FileUpload.addFileItemComplete=6;
window.FileUpload.ImageUploadURL="http://127.0.0.1:8091/upload";//"http://upload.dafy.com/Servlet/imageUpload.svl";
window.FileUpload.FileUploadURL="http://127.0.0.1:8091/upload";//"http://upload.dafy.com/Servlet/fileUpload.svl";
window.FileUpload.CourseUploadURL="http://127.0.0.1:8091/upload";//"http://upload.dafy.com/Servlet/coursefileupload.svl";
window.FileUpload.destory=function(swfId){
	if("undefined" != typeof  window.varSwfInstanceEvent){
			if("undefined" != typeof  window.varSwfInstanceEvent.getSwfInstance(swfId)&&window.varSwfInstanceEvent.getSwfInstance(swfId)!=null){
				window.varSwfInstanceEvent.removeSwfInstance(swfId);
			}
	}
}
function swfInstanceEvent(){
		
		var swfInstanceMap=new Array();
		this.addSwfInstance=function(swfId,swfInstance){
				swfInstanceMap[swfId]=swfInstance;
		}
		
		this.getSwfInstance=function(swfId){
			return swfInstanceMap[swfId];
		}
		this.removeSwfInstance=function(swfId){
				swfInstanceMap[swfId]=null;
		}
		this.addFileItemEvent_Handler=function(swfId,data){
			 data=unescape(data);
			var swfInstance=swfInstanceMap[swfId];
			if("undefined" != typeof swfInstance && "undefined" != typeof swfInstance.addFileItemEvent_Handler && swfInstance.addFileItemEvent_Handler!=null){
				var jobject=eval("("+data+")");
				jobject.target=swfInstance;
				if(swfInstance.binddata!=null){
					jobject.binddata=swfInstance.binddata;
				}
				swfInstance.addFileItemEvent_Handler(jobject);
			}
		}
		
		this.addFileErrorEvent_Handler=function(swfId,data){
		    var swfInstance=swfInstanceMap[swfId];
		    data=unescape(data);
			if("undefined" != typeof swfInstance &&"undefined" != typeof swfInstance.addFileErrorEvent_Handler && swfInstance.addFileErrorEvent_Handler!=null){
				var jobject=eval("({})");
				jobject.target=swfInstance;
				jobject.msg=data;
				jobject.toString=function(){return this.msg;}
				if(swfInstance.binddata!=null){
					jobject.binddata=swfInstance.binddata;
				}
				swfInstance.addFileErrorEvent_Handler(jobject);
			}
		
		}

		this.upLoadProgressEvent_Handler=function(swfId,data){
			var swfInstance=swfInstanceMap[swfId];
			 data=unescape(data);
			if("undefined" != typeof swfInstance && "undefined" != typeof swfInstance.upLoadProgressEvent_Handler && swfInstance.upLoadProgressEvent_Handler!=null){
				var jobject=eval("("+data+")");
				jobject.target=swfInstance;
				if(swfInstance.binddata!=null){
					jobject.binddata=swfInstance.binddata;
				}
				swfInstance.upLoadProgressEvent_Handler(jobject);
			}
		
		}

		this.uploadCompleteData_Handler=function(swfId,fileId,data){
			var swfInstance=swfInstanceMap[swfId];
			 data=unescape(data);
			if("undefined" != typeof swfInstance && "undefined" != typeof swfInstance.uploadCompleteData_Handler && swfInstance.uploadCompleteData_Handler!=null){
				var jobject=eval("("+data+")");
				jobject.id=fileId;
				jobject.target=swfInstance;
				if(swfInstance.binddata!=null){
					jobject.binddata=swfInstance.binddata;
				}
				swfInstance.uploadCompleteData_Handler(jobject);
			}
		}
		
		this.selectFile_Handler=function(swfId){
			var swfInstance=swfInstanceMap[swfId];
			
			if("undefined" != typeof swfInstance && "undefined" != typeof swfInstance.selectFile_Handler && swfInstance.selectFile_Handler!=null){
				var jobject=eval("({})");
				jobject.target=swfInstance;
				if(swfInstance.binddata!=null){
					jobject.binddata=swfInstance.binddata;
				}
				swfInstance.selectFile_Handler(jobject);
			}
		}
		this.addFileItemCompleteEvent_Handler=function(swfId){
			var swfInstance=swfInstanceMap[swfId];
			if("undefined" != typeof swfInstance && "undefined" != typeof swfInstance.addFileItemCompleteEvent_Handler && swfInstance.addFileItemCompleteEvent_Handler!=null){
				var jobject=eval("({})");
				jobject.target=swfInstance;
				if(swfInstance.binddata!=null){
					jobject.binddata=swfInstance.binddata;
				}
				swfInstance.addFileItemCompleteEvent_Handler(jobject);
			}
		}
}
 