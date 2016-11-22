var ua = navigator.userAgent.toLowerCase();
isOpera = ua.indexOf("opera") > -1,
isIE = !isOpera && ua.indexOf("msie") > -1;
String.prototype.trim=function(){return this.replace(/(^[\s]*)|([\s]*$)/g,"");}
String.prototype.lTrim=function(){return this.replace(/(^[\s]*)/g,"");}
String.prototype.rTrim=function(){return this.replace(/([\s]*$)/g,"");}
String.prototype.realLength=function(){return this.replace(/[^\x00-\xff]/g,"**").length;}
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {if (!RegExp.prototype.isPrototypeOf(reallyDo)) {return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);} else {return this.replace(reallyDo, replaceWith);}} 

function $(){var objElements = new Array();var i;for (i=0;i< arguments.length;i++){var objEle = arguments[i];if(typeof arguments[i] == 'string'){objEle = document.getElementById(arguments[i]);}objElements.push(objEle);}if(arguments.length==1){return objEle;}else{return objElements;}}
function getElementsByClassName(className,oBox) {var o = $(oBox) || document;var children = o.getElementsByTagName('*');var cs = new Array();for (var j= 0; j < children.length; j++) {var child = children[j];var classNames = child.className.split(' ');for (var k = 0; k < classNames.length; k++) {if (classNames[k] == className) {cs.push(child);break;}}}return cs;}
function hasClass(ele,cls) {return ele.className.match(new RegExp('(\\s|^)'+cls+'(\\s|$)'));}
function addClass(ele,cls) {if (!this.hasClass(ele,cls)) ele.className += " "+cls;}
function removeClass(ele,cls) {if (hasClass(ele,cls)) {var reg = new RegExp('(\\s|^)'+cls+'(\\s|$)');ele.className=ele.className.replace(reg,' ');}}
Date.prototype.format = function(format){
    var o = {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds(), //millisecond
        "w" : this.getDay()
    }
    if(/(y+)/.test(format))
    format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
    if(new RegExp("("+ k +")").test(format))
    format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}
function _post(Url, Args){ 
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
function GetCookie (name) { 
	var arg = name + "="; 
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	while (i < clen) {
		var j = i + alen;
		if (document.cookie.substring(i, j) == arg) return getCookieVal (j);
		i = document.cookie.indexOf(" ", i) + 1;
		if (i == 0) break; 
	} 
	return null;
}
function SetCookie (name, value) {
	var argv = SetCookie.arguments;
	var argc = SetCookie.arguments.length;
	var expires = (argc > 2) ? argv[2] : null;
	var path = (argc > 3) ? argv[3] : null;
	var domain = (argc > 4) ? argv[4] : null;
	var secure = (argc > 5) ? argv[5] : false;
	document.cookie = name + "=" + escape (value) 
		+ ((expires == null) ? "" : ("; expires=" + expires.toGMTString())) 
		+ ((path == null) ? "" : ("; path=" + path)) 
		+ ((domain == null) ? "" : ("; domain=" + domain))
		+ ((secure == true) ? "; secure" : "");
}
function DeleteCookie (name) { 
	var exp = new Date(); 
	exp.setTime (exp.getTime() - 1); /* This cookie is history	*/
	var cval = 0;
	document.cookie = name + "=" + cval + "; expires=" + exp.toGMTString();
}
function getCookieVal(offset) {
	var endstr = document.cookie.indexOf (";", offset);
	if (endstr == -1)endstr = document.cookie.length;
	return unescape(document.cookie.substring(offset, endstr));
}
function keyDown(e){
	var e=(typeof event!="undefined")?window.event:e;   // IE : Firefox
	var s=(document.getSelection)?document.getSelection():document.selection.createRange().text;
	if(e.ctrlKey && e.keyCode==13){
		if(!document.errForm) return;
		if (s!=""){
			document.errForm.Content.value=s;
			document.errForm.URL.value=document.location;
			document.errForm.submit();
		}else{
			alert("请先用鼠标选择出错的内容片断！");
			return false;
		}
	}
}
function questionDetail(communicateId){
	var path = "http://xf.meizhou.gov.cn/communicateWebAction.do?action=viewDetail&forwardName=msViewDetail&id="+communicateId;
	window.open(path,'','scrollbars=yes,width=600px,height=500px');
}
function query(){
	if(document.getElementById('inTxt').value==""){
		alert("请输入查询值！");
		document.all.busyName.focus();
		return false;
	}
	document.getElementById('_action').name="action";
	document.busyQueryForm.submit();
}
function changeType(v){
	if(v=='') return;
	document.getElementById('inTxt').name=v;
	document.getElementById('_action').name="_action";
	if(v=='busyName'){
		document.busyQueryForm.action="http://sp.meijiang.gov.cn/approveModelAction.do";
		document.getElementById('_action').value="approveBusy_out_dept";
	}else{
		document.busyQueryForm.action="http://sp.meijiang.gov.cn/busyQueryAction.do";
		document.getElementById('_action').value="allBusyList";
	}
	document.getElementById('_action').name="action";
}
function AddFavorite(sURL, sTitle){ 
	try{ 
		window.external.AddFavorite(sURL, sTitle);
	} catch (e) { 
		try{
			window.sidebar.addPanel(sTitle, sURL,""); 
		}catch (e){ 
			alert("请使用Ctrl+D进行添加"); 
		} 
	} 
}    
function getQueryString(name) 
{
    if(location.href.indexOf("?")==-1 || location.href.indexOf(name+'=')==-1) return '';
    var queryString = location.href.substring(location.href.indexOf("?")+1);
    var parameters = queryString.split("&");
    var pos, paraName, paraValue;
    for(var i=0; i<parameters.length; i++){
    	pos = parameters[i].indexOf('=');
    	if(pos == -1) { continue; }
    	paraName = parameters[i].substring(0, pos);
    	paraValue = parameters[i].substring(pos + 1);
    	if(paraName == name) return unescape(paraValue.replace(/\+/g, " "));
    }
   	return '';
}
function TS(URL){
	var charset = GetCookie("charset");
	if(charset && charset=='gb2312'){
		if(document.location.href.indexOf("big5.php")>=0 && getQueryString("URL")!=''){
			document.location = unescape(getQueryString("URL"));
		}
		return ;
	}
	SetCookie('charset','gb2312');
	if(URL) document.location = URL;
	else{
		document.location = unescape(getQueryString("URL"));
	}
}
function ST(){
	var charset = GetCookie("charset");
	if(charset && charset=='big5') return ;
	SetCookie('charset','big5');
	document.location = "/big5.php?URL="+escape(document.location);
}
var charset = GetCookie("charset");
if(charset && charset=='big5'){
	if(document.location.href.indexOf("big5.php")==-1 && getQueryString("URL")==''){
		document.location = "/big5.php?URL="+escape(document.location);
	}
}
document.onkeydown = keyDown;