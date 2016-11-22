// JavaScript Document
//function killError(){return true;}
//window.onerror = killError;

function getTopic(Idx){
	var topicId=Idx.id;
	var s=0;
	var topicCatch=new Array();
	var topics=topicList();
	for(var i=0;i<topics.length;i++){
		if (topics[i][0]==topicId){topicCatch[s]=topics[i];s++;
		if (i<topics.length-1){	if (topics[i+1][0]!=topicId){break;}}
		else{break;}
		}
	}
	return topicCatch;
}

//所属菜单,菜单名,URL,菜单前导图片,新窗口,字体色,背景色,字体加粗
function createPop(inAr){
	var tCD="";
	for (var ii=0;ii<inAr.length;ii++){
		if (inAr[ii][1]=="-"){
			if(inAr[ii][2]==""){inAr[ii][2]="#C0C0C0";}
			if(inAr[ii][3]==""){inAr[ii][3]="#FFFFFF";}
			tCD+="<div style=\"height:7px;background-color:"+inAr[ii][3]+";text-align:center\"><img src=\"\" border=\"0\" width=\"90%\" style=\"background-color:"+inAr[ii][2]+";height:1px;margin-top:3px\"></div>\n"}
		else{
			var TXT=URL=IMG=TRG=FT=FT1=BG=BG1="";
			var FTA=BGA=FT1A=FT1B=BG1A=BG1B="";
			TXT=inAr[ii][1];
			if(inAr[ii][2]!=""){URL=" href=\""+inAr[ii][2]+"\""};
			if(inAr[ii][3]!=""){IMG="<img src=\""+inAr[ii][3]+"\" border=0>"};
			if(inAr[ii][4]==1){TRG=" target=\"_blank\""};
			if(inAr[ii][5]!=""){FT="color:"+inAr[ii][5]+";";
			FTA=inAr[ii][5]};
			if(inAr[ii][6]!=""){BG="background-color:"+inAr[ii][6]+";";BGA=inAr[ii][6]};
			tCD+="<div class='topic_unit' style=\""+FT+BG+"\" onMouseover=\"this.className='pover';\" onMouseout=\"this.className='topic_unit';\">";
			tCD+=IMG+"<a"+URL+TRG+" style=\""+FT+BG+"\" onMouseover=\"this.className='pover';\" onMouseout=\"this.className='topic_unit';\" class=\"topic_unit\">";
			if(inAr[ii][7]==1){tCD+="<b>"+TXT+"</b>"}else{tCD+=TXT};
			tCD+="</a></div>\n";
		}
	}
	return tCD;
}

function ShowPop(obj){
		var tpc=getTopic(obj);
		var popLeft=parseInt(getLeft(obj),10)+5;
		var popTop=parseInt(getTop(obj),10)+20;
		//navmenu_niEasy_popwin.style.left=popLeft;
		//navmenu_niEasy_popwin.style.top=popTop;
	if(tpc.length>0){
		navmenu_niEasy_popwin.innerHTML="<div id=\"navmenu_niEasy_popwinSub\" style=\"left:"+popLeft+";top:"+popTop+";\" class=\"popmenu\" onMouseout=\"delPop()\" onMouseOver=\"clearHidePop()\">"+createPop(tpc)+"</div>";
		navmenu_niEasy_popwinSub.filters.revealTrans.apply();
		navmenu_niEasy_popwinSub.style.visibility="visible";
		navmenu_niEasy_popwinSub.filters.revealTrans.play();
		//tst.innerHTML=createPop(tpc);
	}
}
function ClearPop(){	
navmenu_niEasy_popwinSub.filters.revealTrans.apply();
navmenu_niEasy_popwinSub.style.visibility="hidden";
navmenu_niEasy_popwinSub.filters.revealTrans.play();
}
function delPop(){delayhide=setTimeout("ClearPop()",100);}
function clearHidePop(){if(window.delayhide){clearTimeout(delayhide);}}
