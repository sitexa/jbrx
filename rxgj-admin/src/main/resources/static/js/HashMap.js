function HashMap(){this.arrKey=[];this.arrValue=[];this.exists=function(a){a=""+a;a=a.toUpperCase();for(var b=0;b<this.arrKey.length;b++)if(this.arrKey[b]==a)return true;return false};this.length=function(){return this.arrKey.length};this.put=function(a,c){a=""+a;a=a.toUpperCase();for(var b=0;b<this.arrKey.length;b++)if(this.arrKey[b]==a){this.arrValue[b]=c;return}this.arrKey[this.arrKey.length]=a;this.arrValue[this.arrValue.length]=c};this.get=function(a){a=""+a;a=a.toUpperCase();for(var b=0;b<this.arrKey.length;b++)if(this.arrKey[b]==a)return this.arrValue[b];return null};this.remove=function(b){b=""+b;b=b.toUpperCase();for(var a=0;a<this.arrKey.length;a++)if(this.arrKey[a]==b){this.arrKey.splice(a,1);var c=this.arrValue[a];this.arrValue.splice(a,1);return c}return null};this.getKeys=function(){return this.arrKey};this.getValues=function(){return this.arrValue}}