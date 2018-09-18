$.fn.localResizeIMG = function(obj) {
	
	var imgUrl = "http://file.dafy.com.cn/Servlet/imageUpload.svl?imageSizes=900x900";
	var maxLimitSize = 1500000;
		
    this.on('change', function() {
        var file = this.files[0];
        var URL = window.URL || window.webkitURL;
         //alert(file.size)
        
        if(file.size < maxLimitSize)
        {
        	var reader = new FileReader();
        	reader.readAsDataURL(file);
        	reader.onload = function(e){
        		var oriBase64 = e.target.result;
        		var oriPic = oriBase64.replace(/^data:image\/(png|jpeg|jpg);base64,/, "");
        		var jsonOriData = new Object();
        		jsonOriData.imageData = oriPic;
        		
        		jsonOriData.imgName = file.name||"";
        		$.post(imgUrl,jsonOriData,function(data){
        			// 成功执行后函数
        			obj.success(data);
        		});
        		
        		obj.render(oriBase64);
            };
        	
        	
		    return;
        }
        
        var blob = URL.createObjectURL(file);
        _create(blob, file);
        this.value = ''; // 清空临时数据
    });

    /**
     * 生成base64
     * @param blob 通过file获得的二进制
     */
    function _create(blob,file) {
    	
    	
        var img = new Image();
        img.src = blob;
        
        img.onload = function() {
        	
            var that = this;
           
            //生成比例
            var w = that.width,
                h = that.height,
                scale = w / h;
            
            if(w > 1000){
            	w = 1000;
            	h = w / scale;
            }
            if(h > 1200)
            {
            	h=1200;
            	w=h*scale
            }
            
            //生成canvas
            var canvas = document.createElement('canvas');
            var ctx = canvas.getContext('2d');
          
		  $(canvas).attr({
                width: w,
                height: h
            });
            ctx.drawImage(that, 0, 0, w, h);

            var base64 = canvas.toDataURL('image/jpg', obj.quality || 0.8);

            ////////////////////////////////////////////////////////////////
            
            while(base64.length > maxLimitSize)
            {
              w=w-50;
              h = w / scale;
      		  $(canvas).attr({
                  width: w,
                  height: h
              });
      		  
              ctx.drawImage(that, 0, 0, w, h);
              base64 = canvas.toDataURL('image/jpg', obj.quality || 0.8);
              
            }
            
            //alert(base64.length)
            
		    Pic = base64.replace(/^data:image\/(png|jpg);base64,/, "");
		    var jsonData = new Object();
		    jsonData.imageData = Pic;
		       		    
		    jsonData.imgName = file.name||"";
		    $.post(imgUrl,jsonData,function(data){
		    	// 成功执行后函数
		    	obj.success(data);
		    });
		    
		    obj.render(base64);
		    
        };
    }
};
