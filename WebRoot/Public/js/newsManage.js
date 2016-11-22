// JavaScript Document
if(!window.$){
	window.$=function(){var elements=new Array();for(var i=0;i<arguments.length;i++){var element=arguments[i];if(typeof element=="string")
	element=document.getElementById(element);if(arguments.length==1)
	return element;elements.push(element);}
	return elements;}	
}
function appCommand(cmd){
	var turn=false;
	var obj=form1.C1;
	for (var i=0;i<form1.C1.length;i++){
		if (form1.C1[i].checked){turn=true;break;}
	}
	if(isNaN(parseInt(form1.C1.length))){
	  if(form1.C1.checked) turn=true;
	}
	if (!turn){alert("请选择要操作的文章!");return;}
	switch(cmd){
		case "top" :		cmdTop();break;
		case "eli" :		cmdEli();break;
		case "lock" :		cmdLock();break;
		case "setRe" :	cmdSetRe();break;
		case "move" :		cmdMove();break;
		case "spec" :		cmdSpec();break;
		case "del" :		cmdDel();break;
		case "clrRe" :	cmdClearReview();break;
		case "repeal" :	cmdRepealDel();break;
		case "thDel" :	cmdTrueDel();break;
		case "exa" : cmdExamine();break;
	}
}

function cmdTop(){	form1.action=mainpath+"/system/news/setTop.asp";form1.submit();}//置顶
function cmdEli(){	form1.action=mainpath+"/system/news/setEli.asp";form1.submit();}//精华
function cmdLock(){	form1.action=mainpath+"/system/news/setLock.asp";form1.submit();}//锁定
function cmdSetRe(){	form1.action=mainpath+"/system/news/setRe.asp";form1.submit();}//回复
function cmdDel(){	form1.action=mainpath+"/system/news/setDel.asp";form1.submit();}//删除
function cmdClearReview(){form1.action=mainpath+"/system/news/setClearReview.asp";form1.submit();}//清除回复
function cmdRepealDel(){form1.action=mainpath+"/system/news/setReDel.asp";form1.submit();}//撤消删除
function cmdTrueDel(){if(confirm("此操作将不可恢复,确认要删除吗?")){form1.action=mainpath+"/system/news/setTrueDel.asp";form1.submit();}}//彻底删除
function cmdExamine(){form1.action=mainpath+"/system/news/setExamine.asp";form1.submit();}//审核

function cmdMove(){//移动
	var tpc=showModalDialog(mainpath+"/system/topic/selectmenus.asp",null, "dialogWidth:330px;dialogHeight:345px;status:0;help:0;scroll:0");
	if(tpc!=null){
		var tArr=(tpc+"|").split("|");
		if(tArr[2]!=""){
			alert("当前栏目非新闻类栏目,请重新选择!");
			cmdMove();
		}else{
			var objHiObjId=document.createElement("input");
			objHiObjId.type="hidden";
			objHiObjId.name="objID";
			objHiObjId.id="objID";
			objHiObjId.value=tArr[1];
			
			var objHiObjName=document.createElement("input");
			objHiObjName.type="hidden";
			objHiObjName.name="objName";
			objHiObjName.id="objName";
			objHiObjName.value=tArr[0];
			
			form1.appendChild(objHiObjId);
			form1.appendChild(objHiObjName);
			form1.action=mainpath+"/system/news/setMove.asp";
			form1.submit();
		}
	}
}

function SelectOrReverseAll(theForm,theFieldName,Checked)
{
	if(typeof(theForm.elements[theFieldName])=="undefined")return false;
	var length=theForm.elements[theFieldName].length;
	var i;
	if(Checked)
	{
		if(length+""=="undefined")
		{
				theForm.elements[theFieldName].checked=true;
		}
		else
		{
			for(i=0;i<length;i++)
			{
					theForm.elements[theFieldName][i].checked=true;
			}
		}	
	}
	else
	{
		if(length+""=="undefined")
		{
			if(theForm.elements[theFieldName].checked)
				theForm.elements[theFieldName].checked=false;
			else
				theForm.elements[theFieldName].checked=true;
		}
		else
		{
			for(i=0;i<length;i++)
			{
				if(theForm.elements[theFieldName][i].checked)
					theForm.elements[theFieldName][i].checked=false;
				else
					theForm.elements[theFieldName][i].checked=true;
			}
		}	
	}
}

function cmdMeetMove(objForm,strTable,intMenuId){//移动
	var turn=false;
	var obj=objForm.C1;
	intMenuId=intMenuId+"";
	for (var i=0;i<objForm.C1.length;i++){
		if (objForm.C1[i].checked){turn=true;break;}
	}
	if(isNaN(parseInt(objForm.C1.length))){
	  if(objForm.C1.checked) turn=true;
	}
	if (!turn){alert("请选择要操作的文章!");return;}

	var tpc=showModalDialog(mainpath+"/system/topic/selectmenus.asp",null, "dialogWidth:330px;dialogHeight:345px;status:0;help:0;scroll:0");
	if(tpc!=null){
		var tArr=(tpc+"|").split("|");
		if(tArr[2]==""){
			alert("当前栏目非挂接表类栏目,请重新选择!");
			cmdMeetMove(objForm,strTable,intMenuId);
		}else if(tArr[2]!=strTable){
			alert("请选择一个挂接数据表为“"+tArr[2]+"”的栏目!");
			cmdMeetMove(objForm,strTable,intMenuId);
		}
		else if(intMenuId==tArr[1]){
			alert("文章当前栏目和文章移动到的栏目不能相同!");
			cmdMeetMove(objForm,strTable,intMenuId);
		}else{
			var objHiObjId=document.createElement("input");
			objHiObjId.type="hidden";
			objHiObjId.name="objID";
			objHiObjId.id="objID";
			objHiObjId.value=tArr[1];
			
			var objHiObjName=document.createElement("input");
			objHiObjName.type="hidden";
			objHiObjName.name="objName";
			objHiObjName.id="objName";
			objHiObjName.value=tArr[0];
			
			objForm.appendChild(objHiObjId);
			objForm.appendChild(objHiObjName);
			objForm.action=mainpath+"/system/extab/Mtable.asp";
			objForm.submit();
		}
	}
}


function cmdSpec(){//专题
	var spc=showModalDialog(mainpath+"/system/news/selectSpec.asp",null, "dialogWidth:200px;dialogHeight:210px;status:0;help:0;scroll:0");
	if(spc!=null){
		var objHiSpc=document.createElement("input");
		objHiSpc.type="hidden";
		objHiSpc.name="specID";
		objHiSpc.id="specID";
		objHiSpc.value=spc;
		
		form1.appendChild(objHiSpc);
		form1.action=mainpath+"/system/news/setSpec.asp";
		form1.submit();
	}
}

function cmdAMove(id){//移动
	var tpc=showModalDialog(mainpath+"/system/topic/selectmenus.asp",null, "dialogWidth:330px;dialogHeight:345px;status:0;help:0;scroll:0");
	if(tpc!=null){
		var tArr=(tpc+"|").split("|");
		if(tArr[2]!=""){
			alert("当前栏目非新闻类栏目,请重新选择!");
			cmdMove();
		}else{
			window.location=mainpath+"/system/news/setMove.asp?objID="+tArr[1]+"&objName="+tArr[0]+"&C1="+id;
		}
	}
}

function cmdASpec(id){//专题
	var spc=showModalDialog(mainpath+"/system/news/selectSpec.asp",null, "dialogWidth:200px;dialogHeight:210px;status:0;help:0;scroll:0");
	if(spc!=null){
		window.location=mainpath+"/system/news/setSpec.asp?specID="+spc+"&C1="+id;
	}
}

var hidden_ID_DIV_ManagerPanel=null;

function showManagerPanel(){
	var objManagerPanel=$("ID_DIV_ManagerPanel");
	var objButton=$("ID_A_Button");

	objManagerPanel.style.display="block";
	var objPoint=Element.getLocation(objButton);
	Element.setLocation(objManagerPanel,objPoint.x,objPoint.y+objButton.offsetHeight);
}

function closeManagerPanel(){
	var objManagerPanel=$("ID_DIV_ManagerPanel");
	objManagerPanel.style.display="none";
	stopCloseManagerPanel();
}

function stopCloseManagerPanel(){
	if(hidden_ID_DIV_ManagerPanel!=null){
		window.clearTimeout(hidden_ID_DIV_ManagerPanel);
		hidden_ID_DIV_ManagerPanel=null;
	}
}

function startCloseManagerPanel(){
	hidden_ID_DIV_ManagerPanel=window.setTimeout(closeManagerPanel,200);
}