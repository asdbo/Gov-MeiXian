// JavaScript Document

//��ѡ��ȫѡ������theFormΪ������
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
//�鿴��վ����
function readinvre(Path,id1,id2){location.href=Path+"/Articleinv.asp?id="+id1+"&topic="+id2;}
//�����鿴ͼƬ,ImgΪͼƬ·��
function ViewImg(Img,Folder){
	var ImgPath;
	if(typeof(Img)=="object"){ImgPath=Img.href;}
	else{ImgPath=Img;}
	window.open(Folder+'viewimg.asp?path='+ImgPath,null,'height=1,width=1,top=1,left=1,toolbar=no,menubar=no,scrollbars=no,resizable=yes,location=no, status=no');
	return false;
}
function DispObj(key,obj){if (key.checked){obj.style.display="";}else{obj.style.display="none";}}

//������ָ���X���꣨�����ҳ�棩
function getPointerX(){var x = getLeft(event.srcElement)+event.offsetX;return x;}
//������ָ���Y���꣨�����ҳ�棩
function getPointerY(){var y = getTop(event.srcElement)+event.offsetY;return y;}
//��ö����Y���꣨�����ҳ�棩
function getTop(e){var t=e.offsetTop;while(e=e.offsetParent){t+=e.offsetTop;}return t;}
//��ö����X���꣨�����ҳ�棩
function getLeft(e){var l=e.offsetLeft;while(e=e.offsetParent){l+=e.offsetLeft;}return l;}
//��ö���ĸ߶�
function getHeight(e){var t=e.offsetHeight;return t;}
//��ö���Ŀ��
function getWidth(e){var t=e.offsetWidth;return t;}

