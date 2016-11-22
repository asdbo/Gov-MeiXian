// 地图
function setMap(type) { 
	 abinhide();
theObj = document.getElementById(type).style; 
theObj.display = "inline";
}


function abinhide(){ 
  $("#map").children().each(function(){
		$(this).css('display','none')
  });
}

