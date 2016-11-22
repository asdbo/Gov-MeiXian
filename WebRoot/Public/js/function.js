// JavaScript Document

//复选框全选涵数，theForm为表单对象
function SelectAll(theForm)
{
	var theObjName=event.srcElement.name;
	var theObj=event.srcElement;
	var turn;
	if (theObj.checked){turn=true;}else{turn=false;}
	var length=theForm.elements[theObjName].length;
	var i;
	if(length+""=="undefined")
	{
		theForm.elements[theObjName].checked=turn;
	}
	else
	{
		for(i=0;i<length;i++)
		{
			theForm.elements[theObjName][i].checked=turn;
		}
	}	
}
//查看网站调查
function readinvre(Path,id1,id2){location.href=Path+"/Articleinv.asp?id="+id1+"&topic="+id2;}
//用来查看图片,Img为图片路径
function ViewImg(Img,Folder){
	var ImgPath;
	if(typeof(Img)=="object"){ImgPath=Img.href;}
	else{ImgPath=Img;}
	window.open(Folder+'viewimg.asp?path='+ImgPath,null,'height=1,width=1,top=1,left=1,toolbar=no,menubar=no,scrollbars=no,resizable=yes,location=no, status=no');
	return false;
}
function DispObj(key,obj){if (key.checked){obj.style.display="";}else{obj.style.display="none";}}

//获得鼠标指针的X坐标（相对于页面）
function getPointerX(){var x = getLeft(event.srcElement)+event.offsetX;return x;}
//获得鼠标指针的Y坐标（相对于页面）
function getPointerY(){var y = getTop(event.srcElement)+event.offsetY;return y;}
//获得对象的Y坐标（相对于页面）
function getTop(e){var t=e.offsetTop;while(e=e.offsetParent){t+=e.offsetTop;}return t;}
//获得对象的X坐标（相对于页面）
function getLeft(e){var l=e.offsetLeft;while(e=e.offsetParent){l+=e.offsetLeft;}return l;}
//获得对象的高度
function getHeight(e){var t=e.offsetHeight;return t;}
//获得对象的宽度
function getWidth(e){var t=e.offsetWidth;return t;}

