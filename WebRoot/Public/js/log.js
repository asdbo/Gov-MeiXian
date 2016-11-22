//**********************窗帘广告*************************
	function MoveLog()
	{
		this.Logs=[];
		this.length=-1;

		this.AddLog=function(ID,PageX,PageY,Speed,Align,IsMove){
			var object={};
			if(document.getElementById(ID)+""=="null")
				return ;
			object.Log=document.getElementById(ID);
			object.PageX=PageX;
			object.Align=Align.toUpperCase();
			object.PageY=PageY;
			object.IsMove=IsMove;
			object.MoveSpeed=Speed/100;
			object.Log.style.top=document.body.scrollTop+PageY;
			if(object.Align=="RIGHT")
				object.Log.style.left=document.body.clientWidth-object.Log.offsetWidth-object.PageX;
			else
				object.Log.style.left=document.body.scrollLeft+object.PageX;
			object.MoveLog=function(){
				var TopPos,scrollTop,scrollLeft;
				if(!object.IsMove)
					return ;
				scrollTop=document.body.scrollTop;
				scrollLeft=document.body.scrollLeft;
				TopPos=object.Log.offsetTop;
				
				if(TopPos!=(document.body.scrollTop+object.PageY))
				{
					var PosY=(document.body.scrollTop+object.PageY-TopPos)*object.MoveSpeed;
					PosY=(PosY>0?1:-1)*Math.ceil(Math.abs(PosY));
					object.Log.style.top=TopPos+PosY;
					//======================================================
				}
				var PrePosX=0;
				if(object.Align=="LEFT")
						PrePosX=scrollLeft+object.PageX;
				else if(object.Align=="RIGHT")
						PrePosX=document.body.clientWidth-object.Log.offsetWidth-object.PageX;
						
				if(object.Log.offsetLeft!=(document.body.scrollLeft+PrePosX))
				{							
					var PosX=(document.body.scrollLeft+PrePosX-object.Log.offsetLeft)*object.MoveSpeed;
					PosX=(PosX>0?1:-1)*Math.ceil(Math.abs(PosX));
					object.Log.style.left=object.Log.offsetLeft+PosX;
				}
			}
			this.length++;
			this.Logs[this.length]=object;
		}
		
		this.StartMove=function()
		{
			MoveLogs();
		}
		
		this.Move=function()
		{
			var i;
			for(i=0;i<=this.length;i++)
			{
				this.Logs[i].MoveLog();
			}
		}
		
	}
		
	function MoveLogs()
	{
		moveLog.Move();
		setTimeout("MoveLogs();",10);	
	}

//**********************浮动广告*************************
	function RandomizeMoveLog()
	{
		this.Logs=[];
		this.length=-1;
		
		this.AddLog=function(ID,Speed){
			var object={};
			if(document.getElementById(ID)+""=="null")
				return ;
			object.Log=document.getElementById(ID);
			object.isMove=true;
			object.Log.onmouseover=function(){
				object.isMove=false;
			}
			object.Log.onmouseout=function(){
				object.isMove=true;
			}
			object.MoveMin=2;
			//object.MoveMax=Speed;
			object.MoveMax=5;
			object.Vr=3;
			object.MoveX=object.MoveMin+object.MoveMax*Math.random();
			object.MoveY=object.MoveMin+object.MoveMax*Math.random();
			object.Px=0;
			object.Py=0;
			object.Width=object.Log.offsetWidth;
			object.Height=object.Log.offsetHeight;

	
			object.MoveLog=function(){
				if(!object.isMove)
					return ;
				var pageX=window.document.body.scrollLeft;
				var pageW=window.document.body.offsetWidth-8;
				var pageY=window.document.body.scrollTop;
				var pageH=window.document.body.offsetHeight;
				//=======================================================
				object.Px=object.Px+object.MoveX;
				object.Py=object.Py+object.MoveY;
				object.MoveX+=object.Vr*(Math.random()-0.5);
				object.MoveY+=object.Vr*(Math.random()-0.5);
				
				
				if(object.MoveX>(object.MoveMax+object.MoveMin))  object.MoveX=(object.MoveMax+object.MoveMin)*2-object.MoveX;
				if(object.MoveX<(-object.MoveMax-object.MoveMin)) object.MoveX=(-object.MoveMax-object.MoveMin)*2-object.MoveX;
				if(object.MoveY>(object.MoveMax+object.MoveMin))  object.MoveY=(object.MoveMax+object.MoveMin)*2-object.MoveY;
				if(object.MoveY<(-object.MoveMax-object.MoveMin)) object.MoveY=(-object.MoveMax-object.MoveMin)*2-object.MoveY;
				if(object.Px<=pageX)
				{
					object.Px=pageX;
					object.MoveX=object.MoveMin+object.MoveMax*Math.random();
				}
				if(object.Px>=pageX+pageW-1)
				{
					object.Px=pageX+pageW-1;
					object.MoveX=-object.MoveMin+object.MoveMax*Math.random();
				}
				//
				if(object.Px>=pageW-object.Width)
				{
					object.Px=object.Px-20;
					object.MoveX=-object.MoveMin-object.MoveMax*Math.random();
				}
				if(object.Py<=pageY)
				{
					object.Py=pageY;
					object.MoveY=object.MoveMin+object.MoveMax*Math.random();
				}
				if(object.Py>=pageY+pageH-object.Height)
				{
					object.Py=pageY+pageH-object.Height;
					object.MoveY=-object.MoveMin-object.MoveMax*Math.random();
				}
				object.Log.style.left=object.Px;
				object.Log.style.top=object.Py;	
				
			}
			this.length++;
			this.Logs[this.length]=object;
		}
		
		this.StartMove=function()
		{
			RandomizeMoveLogs();
		}
		
		this.Move=function()
		{
			var i;
			for(i=0;i<=this.length;i++)
			{
				this.Logs[i].MoveLog();
			}
		}
		
	}

	function RandomizeMoveLogs()
	{
		RmLog.Move();
		setTimeout("RandomizeMoveLogs();",80);		
	}
	
	
	function RandomizeNum()
	{
		return Math.round(Math.random()*100);
	}
	
/**********************广告点击计算*************************
功能：广告点击计算
参数：广告ID号，广告点击计算类型:1为只计算独立IP，2为计算全部
返回值：
***********************************************************/ 
	function AdClick(AdID,CliCouType)
	{
		var XMLHTTP=null;
		var Content="ID="+AdID+"&ClickType="+CliCouType+"&Ad_Type=4";
		try
		{
			if (window.XMLHttpRequest)
			{
				XMLHTTP=new XMLHttpRequest();
			}
			else
			{
				XMLHTTP=new ActiveXObject("Microsoft.XMLHTTP");
			}
			XMLHTTP.open("POST","System/hiwebad/Dispose_hiwebad.asp",true);
			XMLHTTP.setRequestHeader("Content-Length",Content.length); 
			XMLHTTP.setRequestHeader("CONTENT-TYPE","application/x-www-form-urlencoded");
			XMLHTTP.send(Content);
			XMLHTTP=null;
		}
		catch (Exception)
		{
			
		}
		
	}