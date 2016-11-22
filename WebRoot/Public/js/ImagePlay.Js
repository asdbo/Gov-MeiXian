// JavaScript Document
/*=========================================================================
'代码版权说明
'=========================================================================
' 文件名: ImagePlay.asp
' 版本:	  1.0
' 日期:   2006-6-29
' 代码编写:   笑咪咪
' 说明:
' 版权所有: 重庆卓越电脑网络科技公司
'=========================================================================
' Copyright (C) 2006 zydn.net All rights reserved.
' Web: http://www.zydn.net
' Need help? Contact: www.zydn.net
'=========================================================================
*/


	function ImageInfo(src,title,link,text)
	{
		this.image=new Image();
		this.image.src=src;
		this.Title=title;
		this.Link=link;
		this.Text=text;
	}
	
	function PlayImage()
	{
		this.Container={};
		this.length=-1;
		this.AddImageInfo=function(theImageInfo){
			if(theImageInfo==null)
				return ;
			this.length++;
			this.Container[this.length]=theImageInfo;
		}	
		this.ImageLinkObj=null;
		this.ImageTagObj=null;
		this.TextLinkObj=null;
		this.PlayImageObj=null;
		this.ProceCount=-1;
	}

	
	function setFilter(ImageTagObj){
		ImageTagObj.filters.revealTrans.Transition=23;
		ImageTagObj.filters.revealTrans.apply();
	}
	
	function playFilter(ImageTagObj){ImageTagObj.filters.revealTrans.play();}