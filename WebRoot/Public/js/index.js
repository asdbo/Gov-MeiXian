function showNavContent(index,sum){
		var contentArray = new Array();
		for(var i=1;i<=sum;i++)
			contentArray[contentArray.length] = document.getElementById("menu"+i);
		for(i=0;i<index-1;i++)
			contentArray[i].style.display = "none";
		contentArray[i].style.display = "block";
		for(++i;i<contentArray.length;i++)
			contentArray[i].style.display = "none";
}

function showLinkContent(index){
		var contentArray = new Array();
		var link_bar=new Array();
		for(var i=1;i<=5;i++){
			contentArray[contentArray.length] = document.getElementById("Links"+i);
			link_bar[link_bar.length] = document.getElementById("link_bar"+i);
		}
		for(i=0;i<index-1;i++){
			contentArray[i].style.display = "none";
			link_bar[i].style.color = "#000000";
			link_bar[i].style.backgroundPositionX=-118;
			link_bar[i].style.backgroundPositionY=0;
		}
		contentArray[i].style.display = "block";
		link_bar[i].style.color = "#FFFFFF";
		link_bar[i].style.backgroundPositionX=0;
		link_bar[i].style.backgroundPositionY=0;
		for(++i;i<contentArray.length;i++){
			contentArray[i].style.display = "none";
			link_bar[i].style.color = "#000000";
			link_bar[i].style.backgroundPositionX=-118;
			link_bar[i].style.backgroundPositionY=0;
		}
}
