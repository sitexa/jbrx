/* Demo Note:  This demo uses a FileProgress class that handles the UI for displaying the file name and percent complete.
The FileProgress class is not part of SWFUpload.
*/


/* **********************
   Event Handlers
   These are my custom event handlers to make my
   web application behave the way I went when SWFUpload
   completes different tasks.  These aren't part of the SWFUpload
   package.  They are part of my application.  Without these none
   of the actions SWFUpload makes will show up in my application.
   ********************** */
 
function swfUploadPreLoad() {
	var self = this;
	var loading = function () {
		//document.getElementById("divSWFUploadUI").style.display = "none";
		document.getElementById("divLoadingContent").style.display = "";

		var longLoad = function () {
			document.getElementById("divLoadingContent").style.display = "none";
			document.getElementById("divLongLoading").style.display = "";
		};
		this.customSettings.loadingTimeout = setTimeout(function () {
				longLoad.call(self)
			},
			15 * 1000
		);
	};
	
	this.customSettings.loadingTimeout = setTimeout(function () {
			loading.call(self);
		},
		1*1000
	);
}


function swfUploadLoaded() {
	var self = this;
	clearTimeout(this.customSettings.loadingTimeout);
	//document.getElementById("divSWFUploadUI").style.visibility = "visible";
	//document.getElementById("divSWFUploadUI").style.display = "block";
	document.getElementById("divLoadingContent").style.display = "none";
	document.getElementById("divLongLoading").style.display = "none";
	document.getElementById("divAlternateContent").style.display = "none";
	
	//document.getElementById("btnBrowse").onclick = function () { self.selectFiles(); };
	//document.getElementById("btnCancel").onclick = function () { self.cancelQueue(); };
	this.settings.original_file_upload_limit=this.settings.file_upload_limit;
	if(self.settings.file_upload_limit!=0&& "undefined" != typeof self.settings.uploadedFile){
						var temp_file_upload_limit=this.settings.original_file_upload_limit-self.settings.uploadedFile;
						self.setFileUploadLimit(temp_file_upload_limit<0?0:temp_file_upload_limit);
	}
	document.getElementById(self.settings.custom_settings.cancelButtonId).onclick = function () { self.cancelQueue(); document.getElementById(self.customSettings.cancelButtonId).disabled = true;};
	//上传按钮执行的动作 add by stephen
	document.getElementById(self.settings.custom_settings.uploadButtonId).onclick = function () { self.startUpload(); };
}
   
function swfUploadLoadFailed() {
	clearTimeout(this.customSettings.loadingTimeout);
	//document.getElementById("divSWFUploadUI").style.display = "none";
	document.getElementById("divLoadingContent").style.display = "none";
	document.getElementById("divLongLoading").style.display = "none";
	document.getElementById("divAlternateContent").style.display = "";
}
   
   
function fileQueued(file) {
	try {
		if(this.settings.original_file_upload_limit!=0&&this.settings.file_upload_limit<=0){
			alert("你已达到文件上传个数限制");
			this.cancelUpload(file.id);
			return;
		}
		FileProgress(file, this.customSettings.myFileListTarget,this);
	} catch (ex) {
		this.debug(ex);
	}
	/* comment by stephen
	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setStatus("Pending...");
		progress.toggleCancel(true, this);

	} catch (ex) {
		this.debug(ex);
	}
	*/

}

function fileQueueError(file, errorCode, message) {
	try {
		
		if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
			alert(message === "0" ? "你已达到文件上传个数限制." : "你还可以上传"+ message + "个文件.");
			return;
		}
		
		FileProgress(file, this.customSettings.myFileListTarget,this);
		var tr = document.getElementById(file.id);
		tr.style.color="red";
		var bar = document.getElementById(file.id+"_bar");
		var errInfo = "选择失败";
		/* comment by stephen
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setError();
		progress.toggleCancel(false);
		*/

		switch (errorCode) {
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			//progress.setStatus("File is too big.");
			errInfo = errInfo + ":文件太大";
			this.debug("Error Code: File too big, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
			//progress.setStatus("Cannot upload Zero Byte files.");
			errInfo = errInfo + ":0字节文件";
			this.debug("Error Code: Zero byte file, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
			//progress.setStatus("Invalid File Type.");
			errInfo = errInfo + ":文件类型错误";
			this.debug("Error Code: Invalid File Type, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		default:
			if (file !== null) {
				//progress.setStatus("Unhandled Error");
				errInfo = errInfo + ":系统未知错误";
			}
			this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		}
		
		bar.parentNode.innerHTML=errInfo;
		var delObject = document.getElementById(file.id+"_del");
		delObject.innerHTML="删除";
		swfUploadInstance.cancelUpload(file.id);
	} catch (ex) {
        this.debug(ex);
    }
}

function fileDialogComplete(numFilesSelected, numFilesQueued) {
	try {
		if (numFilesSelected > 0) {
			document.getElementById(this.customSettings.cancelButtonId).disabled = false;
		}
		
	
		/* I want auto start the upload and I can do that here */
		//add by stephen
		if(this.settings.auto_upload){//是否要上传
			this.startUpload();
		}
		//this.startUpload(); comment by stephen
	} catch (ex)  {
        this.debug(ex);
	}
}

function uploadStart(file) {
	try {
		/* I don't want to do any file validation or anything,  I'll just update the UI and
		return true to indicate that the upload should start.
		It's important to update the UI here because in Linux no uploadProgress events are called. The best
		we can do is say we are uploading.
		 */
		
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setStatus("Uploading...");
		progress.toggleCancel(true, this);
	}
	catch (ex) {}
	
	return true;
}

function uploadProgress(file, bytesLoaded, bytesTotal) {
	try {
		var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);
		var bar = document.getElementById(file.id+"_bar");
		bar.style.width=percent+"px";
		var tr = document.getElementById(file.id);
		tr.style.color="blue";
	} catch (ex) {
		this.debug(ex);
	}
	/*
	try {
		var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);

		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setProgress(percent);
		progress.setStatus("Uploading...");
	} catch (ex) {
		this.debug(ex);
	}
	*/
}

function uploadSuccess(file, serverData) {
	var self = this;
	var jsonObj=eval("["+serverData+"]");
	try {
		if(!jsonObj[0].error){
			var tr = document.getElementById(file.id);
			tr.style.color="green";
			var bar = document.getElementById(file.id+"_bar");
			bar.parentNode.innerHTML="上传成功";
			var delObject = document.getElementById(file.id+"_del");
			delObject.innerHTML="删除";
			delObject.onclick = function () {
					deleteFile(file.id)
					
			}
			
			SWFUpload.instances[file.id.substr(0,file.id.lastIndexOf("_"))].settings.fileInfoMap.put(file.id,serverData);
			if("undefined" != typeof SWFUpload.instances[file.id.substr(0,file.id.lastIndexOf("_"))].settings.l_uploadSucesssAfter)
				SWFUpload.instances[file.id.substr(0,file.id.lastIndexOf("_"))].settings.l_uploadSucesssAfter(serverData);			 
		}else{
			var tr = document.getElementById(file.id);
			tr.style.color="red";
			var bar = document.getElementById(file.id+"_bar");
			bar.parentNode.innerHTML="上传失败:"+serverData;
			var delObject = document.getElementById(file.id+"_del");
			delObject.innerHTML="删除";
			if(SWFUpload.instances[file.id.substr(0,file.id.lastIndexOf("_"))].settings.original_file_upload_limit!=0){
				var temp_file_upload_limit=SWFUpload.instances[file.id.substr(0,file.id.lastIndexOf("_"))].settings.file_upload_limit;
				SWFUpload.instances[file.id.substr(0,file.id.lastIndexOf("_"))].setFileUploadLimit(++temp_file_upload_limit);
			}
		}
	} catch (ex) {
		this.debug(ex);
	}
	/*
	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setComplete();
		progress.setStatus("Complete.");
		progress.toggleCancel(false);

	} catch (ex) {
		this.debug(ex);
	}
	*/
}
var delRow=function(fileId){
	var tr = document.getElementById(fileId);
		var tb =tr.parentNode;
		tb.deleteRow(tr.rowIndex);
}
var deleteFile = function(key)
{	
	 
    var serverData = SWFUpload.instances[key.substr(0,key.lastIndexOf("_"))].settings.fileInfoMap.get(key);
    if(serverData=="")
    {
   	 return "fail";
    }
    else
    {
	    var jsonData=eval("("+serverData+")");
	    var strFilePath = jsonData.strFilePath;
	    var strFileName = jsonData.strFileName;
	    var strSourceFileName = jsonData.strSourceFileName;
	    var js_obj = document.createElement( "script" ); 
		js_obj.type = "text/javascript" ; 
		var url=filedomain+"/Servlet/fileUpload.svl?op=delFile&strFilePath="+strFilePath+"&strFileName="+strFileName+"&strSourceFileName="+strSourceFileName+"&fileId="+key;
		js_obj.setAttribute( "src" ,encodeURI(url).replace(/#/g,"%23")); 
		document.body.appendChild(js_obj);
	}
}
var deleteFileHandler=function(rServerData){
		if(rServerData!=-1){
			delRow(rServerData);
			if(SWFUpload.instances[rServerData.substr(0,rServerData.lastIndexOf("_"))].settings.original_file_upload_limit!=0){
				var temp_file_upload_limit=SWFUpload.instances[rServerData.substr(0,rServerData.lastIndexOf("_"))].settings.file_upload_limit;
				SWFUpload.instances[rServerData.substr(0,rServerData.lastIndexOf("_"))].setFileUploadLimit(++temp_file_upload_limit);
			}
			if("undefined" != typeof SWFUpload.instances[rServerData.substr(0,rServerData.lastIndexOf("_"))].settings.l_deleteSucesssAfter)
				SWFUpload.instances[rServerData.substr(0,rServerData.lastIndexOf("_"))].settings.l_deleteSucesssAfter(rServerData);
				SWFUpload.instances[rServerData.substr(0,rServerData.lastIndexOf("_"))].settings.fileInfoMap.remove(rServerData);
		}else{
				alert("删除失败");
		}
	
}


function uploadError(file, errorCode, message) {
	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setError();
		progress.toggleCancel(false);

		switch (errorCode) {
		case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
			progress.setStatus("Upload Error: " + message);
			this.debug("Error Code: HTTP Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
			progress.setStatus("Upload Failed.");
			this.debug("Error Code: Upload Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.IO_ERROR:
			progress.setStatus("Server (IO) Error");
			this.debug("Error Code: IO Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
			progress.setStatus("Security Error");
			this.debug("Error Code: Security Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
			progress.setStatus("Upload limit exceeded.");
			this.debug("Error Code: Upload Limit Exceeded, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
			progress.setStatus("Failed Validation.  Upload skipped.");
			this.debug("Error Code: File Validation Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
			// If there aren't any files left (they were all cancelled) disable the cancel button
			if (this.getStats().files_queued === 0) {
				document.getElementById(this.customSettings.cancelButtonId).disabled = true;
			}
			progress.setStatus("Cancelled");
			progress.setCancelled();
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
			progress.setStatus("Stopped");
			break;
		default:
			progress.setStatus("Unhandled Error: " + errorCode);
			this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		}
	} catch (ex) {
        this.debug(ex);
    }
}

function uploadComplete(file) {
	if (this.getStats().files_queued === 0) {
		document.getElementById(this.customSettings.cancelButtonId).disabled = true;
	}
}

// This event comes from the Queue Plugin
function queueComplete(numFilesUploaded) {
	//document.getElementById(this.customSettings.myFileListTarget+"SuccessUploadCount").innerHTML=this.getStats().successful_uploads;//add by stephen
	/* comment by stephen
	var status = document.getElementById("divStatus");
	status.innerHTML = numFilesUploaded + " file" + (numFilesUploaded === 1 ? "" : "s") + " uploaded.";
	*/
}

//add by stephen 
//version 2.2 why remove follow function, i must add again.
var _K = 1024;
var _M = _K*1024;
function getNiceFileSize(bitnum){
	if(bitnum<_M){
		if(bitnum<_K){
			return bitnum+'B';
		}else{
			return Math.ceil(bitnum/_K)+'K';
		}
		
	}else{
		return Math.ceil(100*bitnum/_M)/100+'M';
	}
}

