String.prototype.trim=function(){return this.replace(/(^[\s]*)|([\s]*$)/g,"");}
String.prototype.lTrim=function(){return this.replace(/(^[\s]*)/g,"");}
String.prototype.rTrim=function(){return this.replace(/([\s]*$)/g,"");}
String.prototype.realLength=function(){return this.replace(/[^\x00-\xff]/g,"**").length;}
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {if (!RegExp.prototype.isPrototypeOf(reallyDo)) {return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);} else {return this.replace(reallyDo, replaceWith);}} 

function $(){ var objElements=new Array(); var i; for(i=0;i<arguments.length;i++){ var objEle=arguments[i]; if(typeof arguments[i]=='string'){ objEle=document.getElementById(arguments[i]); } objElements.push(objEle);} if(arguments.length==1){return objEle;}else{return objElements;}}
function getElementsByClassName(className,oBox) {var o = $(oBox) || document;var children = o.getElementsByTagName('*');var cs = new Array();for (var j= 0; j < children.length; j++) {var child = children[j];var classNames = child.className.split(' ');for (var j = 0; j < classNames.length; j++) {if (classNames[j] == className) {cs.push(child);break;}}}return cs;}
function _post(Url, Args) 
{ 
	 var xmlhttp; 
	 var error; 
	 if(window.XMLHttpRequest){
	 	xmlhttp = new XMLHttpRequest();
	 }else if(typeof ActiveXObject != "undefined"){
	 	eval('try {xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); } catch (e) {xmlhttp = null;error=e;}'); 
	 }
	 if(typeof(Args)=='undefined'){ Args = '_null=null';}
	 if(null != xmlhttp) 
	 { 
		 xmlhttp.open("POST", Url, false); 
		 xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); 
		 xmlhttp.send(Args); 
		 strText = xmlhttp.responseText; 
	 } 
 	return strText; 
} 
