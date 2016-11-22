// JavaScript Document
$(function(){
	$.ajax({
			url : 'http'+'://app.gd.gov.cn/xxts/pushinfo_json.php?s=12&d=1',
			dataType : "jsonp",
			jsonp : "pushInfoJsonpCallBack",
			jsonpCallback:"pushInfoJsonpCallBack",
			success : function(data) {
				$.each(data,function(i,json){
					var title = json.title;
					title = title.length>31?title.substring(0,30)+"...":title;
					if(i<5){
					$("#zscd").append("<li><a href='"+json.link+"' target='_blank' title='"+json.title+"'>"+title+"</a><span>"+json.pubDate+"</span></li>")
					}
				})
			},
			error:function(){
		           alert('fail');
		    }
	});
	
	var $dhlj = $('.dhlj li');
	$dhlj.click(function(){
			var index = $dhlj.index(this);
			$(this).toggleClass("viewlj").siblings().removeClass("viewlj");
			$(".mainljcon ul").eq(index).slideDown("fast").siblings().slideUp("fast");
			if(!$(this).hasClass('viewlj')){
				$(".mainljcon ul").eq(index).slideUp("fast");
			}
	})
				
	jQuery(".zdlygkzl").slide({titCell:".tit_h2_2 a", mainCell:".zdlySlm", delayTime:0,triggerTime:0,autoPage:false});
		
	jQuery(".slideBox").slide({titCell:".hd ul",mainCell:".bd ul",effect:"leftLoop",autoPlay:true,autoPage:true});
	jQuery(".slideBox2").slide({titCell:".hd ul",mainCell:".bd ul",effect:"leftLoop",autoPlay:true,autoPage:true});
	jQuery(".links_scroll").slide({mainCell:".bd ul",autoPage:true,effect:"leftLoop",autoPlay:true,vis:4});
	
	$('.lypicH li').addClass(function() {return 'child' + Number($(this).index()+1);})
	$('.lypicH2 li').addClass(function() {return 'child' + Number($(this).index()+1);})
	$(".ldzc_ldlj p a").each(function(){
		if($(this).attr("href").indexOf("#")==0){
			$(this).removeAttr("href");
		}
	})
	
	$("#qxdt").load("/sofpro/gecs/count/quxian_list1.html .news_list");
	$("#bmdt").load("/sofpro/gecs/count/bumen_list1.html .news_list");
	$("#cjwt").load("/sofpro/gzyyqt/12345web/hd_cjwt_sy.jsp .hd_cjwt_list");
	$("#bottom").load("/gzgov/2016bottom/2016_bottom.shtml .container");
	$(".wsdcbox").load("/sofpro/gzyyqt/wsdc/wsdc_index.jsp .hd_wsdc_list");
	$(".footer-nav").before("<b></b>");
})
	
var strM = document.location.href;
function go_chinese(portnum){
	var strM1 = strM.replace(/(http:\/\/[^\/]*)(:[0-9]*\/)([.^:]*)/gi,"$1:"+portnum+"/$3");
	if(portnum=="80"){var strM1 = strM.replace(/(http:\/\/[^\/^:]*)(:[^\/]*)*\/(.*)/gi,"$1\/$3"); }
	else{var strM1 = strM.replace(/(http:\/\/[^\/^:]*)(:[^\/]*)*\/(.*)/gi,"$1:"+portnum+"/$3");}
	window.location.href=strM1
}