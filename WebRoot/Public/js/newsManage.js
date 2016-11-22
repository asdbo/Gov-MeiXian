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
	if (!turn){alert("��ѡ��Ҫ����������!");return;}
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

function cmdTop(){	form1.action=mainpath+"/system/news/setTop.asp";form1.submit();}//�ö�
function cmdEli(){	form1.action=mainpath+"/system/news/setEli.asp";form1.submit();}//����
function cmdLock(){	form1.action=mainpath+"/system/news/setLock.asp";form1.submit();}//����
function cmdSetRe(){	form1.action=mainpath+"/system/news/setRe.asp";form1.submit();}//�ظ�
function cmdDel(){	form1.action=mainpath+"/system/news/setDel.asp";form1.submit();}//ɾ��
function cmdClearReview(){form1.action=mainpath+"/system/news/setClearReview.asp";form1.submit();}//����ظ�
function cmdRepealDel(){form1.action=mainpath+"/system/news/setReDel.asp";form1.submit();}//����ɾ��
function cmdTrueDel(){if(confirm("�˲��������ɻָ�,ȷ��Ҫɾ����?")){form1.action=mainpath+"/system/news/setTrueDel.asp";form1.submit();}}//����ɾ��
function cmdExamine(){form1.action=mainpath+"/system/news/setExamine.asp";form1.submit();}//���

function cmdMove(){//�ƶ�
	var tpc=showModalDialog(mainpath+"/system/topic/selectmenus.asp",null, "dialogWidth:330px;dialogHeight:345px;status:0;help:0;scroll:0");
	if(tpc!=null){
		var tArr=(tpc+"|").split("|");
		if(tArr[2]!=""){
			alert("��ǰ��Ŀ����������Ŀ,������ѡ��!");
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

function cmdMeetMove(objForm,strTable,intMenuId){//�ƶ�
	var turn=false;
	var obj=objForm.C1;
	intMenuId=intMenuId+"";
	for (var i=0;i<objForm.C1.length;i++){
		if (objForm.C1[i].checked){turn=true;break;}
	}
	if(isNaN(parseInt(objForm.C1.length))){
	  if(objForm.C1.checked) turn=true;
	}
	if (!turn){alert("��ѡ��Ҫ����������!");return;}

	var tpc=showModalDialog(mainpath+"/system/topic/selectmenus.asp",null, "dialogWidth:330px;dialogHeight:345px;status:0;help:0;scroll:0");
	if(tpc!=null){
		var tArr=(tpc+"|").split("|");
		if(tArr[2]==""){
			alert("��ǰ��Ŀ�ǹҽӱ�����Ŀ,������ѡ��!");
			cmdMeetMove(objForm,strTable,intMenuId);
		}else if(tArr[2]!=strTable){
			alert("��ѡ��һ���ҽ����ݱ�Ϊ��"+tArr[2]+"������Ŀ!");
			cmdMeetMove(objForm,strTable,intMenuId);
		}
		else if(intMenuId==tArr[1]){
			alert("���µ�ǰ��Ŀ�������ƶ�������Ŀ������ͬ!");
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


function cmdSpec(){//ר��
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

function cmdAMove(id){//�ƶ�
	var tpc=showModalDialog(mainpath+"/system/topic/selectmenus.asp",null, "dialogWidth:330px;dialogHeight:345px;status:0;help:0;scroll:0");
	if(tpc!=null){
		var tArr=(tpc+"|").split("|");
		if(tArr[2]!=""){
			alert("��ǰ��Ŀ����������Ŀ,������ѡ��!");
			cmdMove();
		}else{
			window.location=mainpath+"/system/news/setMove.asp?objID="+tArr[1]+"&objName="+tArr[0]+"&C1="+id;
		}
	}
}

function cmdASpec(id){//ר��
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