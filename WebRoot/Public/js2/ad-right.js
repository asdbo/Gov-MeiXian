// JavaScript Document

/* jquery-scrolltofixed-min */
function b(){
        h = $(window).height()-400;
		s = $(window).height()-500;
        t = $(document).scrollTop();
		if(t > s){
			$(".right_menu").css({position: "fixed"});
			$(".right_menu").finish().animate({top:"110px"}, "slow");
			if(t > h){
            	$('#gotop').show();
				$(".right_menu").finish().animate({height: "450px"},"slow" );
        	}else{
				$('#gotop').hide();
				$(".right_menu").finish().animate({height: "400px"},"slow" );
			}
		}else{
			$(".right_menu").css({position:"absolute"});
			$(".right_menu").finish().animate({top:"258px"}, "slow");
        }
    }
$(window).scroll(function(e){
    b();
});

/*扫描二维码*/
$(function(){
  $('#gotop').click(function(){
        $(document).scrollTop(0);
		$(this).hide();
		$(".right_menu").css({height: "400px",top:"258px"});
  });
  $(".smyx").hover(function(){
    $(".smyx_con").css("display","block");
  },function(){
    $(".smyx_con").css("display","none");
  })
  $("#qrcode").hover(function(){
	$(".wx_con").css({'display':'block'})
  },
  function(){
	$(".wx_con").css({'display':'none'})
  });
  $("#qfxd").hover(function(){
	$(".fxd_con").css({'display':'block'})
  },function(){
	$(".fxd_con").css({'display':'none'})
  });
  $(".fxd_con").hover(function(){
	$(this).css({'display':'block'})
  },function(){
	$(this).css({'display':'none'})
  });
  $("#zwwb").hover(function(){
	$(".wb_con").css({'display':'block'})
  },function(){
	$(".wb_con").css({'display':'none'})
  });
  $(".wb_con").hover(function(){
	$(this).css({'display':'block'})
  },function(){
	$(this).css({'display':'none'})
  });
  $('.wx_con').append('<span>广州政府网</span><img src="images/2016_right_zwwx_gzzfw.png" width="99" height="99"  alt=""/><p>轻松一扫<br/>知晓政务<br/>建言献策<br/></p>');
  $('.wb_con dd').addClass(function() {return 'child' + Number($(this).index()+1);});

  $('.fxd_con').append('<div class="bdsharebuttonbox" data-tag="share_1"><a class="bds_weixin" title="分享到微信" data-cmd="weixin" href="#"></a> <a class="bds_qzone" data-cmd="qzone" href="#"></a> <a class="bds_tsina" title="分享到新浪微博" data-cmd="tsina" href="#"></a> <a class="bds_sqq" data-cmd="sqq"></a> <a class="bds_tqq" title="分享到腾讯微博" data-cmd="tqq"></a> </div>');
   window._bd_share_config = {share : [{"bdSize" : 32}]}
with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
});
//返回顶部
$(".wzjy").click(function(){
  $("body,html").animate({scrollTop:0},500)
})

  $(".right_scorll_img").slide({
   titCell:".list",mainCell:".piclist",autoPage:true,effect:"left",autoPlay:true,vis:4, 
   scroll: 4, delayTime: 800, trigger: "click", easing: "easeOutCirc" });
          
  $(".news_img2").slide({
      mainCell:".bd ul",effect:"fold",autoPlay:true
   });

        $(".news").slide(); $(".news2").slide();
        $(".news_vertical1").slide({ titCell:"ol li",mainCell:"div",delayTime:0,triggerTime:0 });
        $(".news_vertical2").slide({ titCell:"ol li",mainCell:"div",delayTime:0,triggerTime:0 });
        
$(".tufa").slide({ trigger: "click" });
$(".news_img").slide({
                titCell: ".num li",
                mainCell: ".pic",
                effect: "fold",
                autoPlay: true,
                trigger: "click",
                //下面startFun代码用于控制文字上下切换
                startFun: function (i) {
                 $(".news_img .txt li").eq(i).animate({ "bottom": 0 }).siblings().animate({ "bottom": -36 });
                }
            });
            $(".threeScroll2").hover(function () {
                $(this).find(".prev,.next").stop(true, true).fadeTo("show", 1)
            }, function () {
                $(this).find(".prev,.next").fadeOut()
            });
            $(".threeScroll2").slide({ mainCell: ".dlList", effect: "leftLoop", vis: 4, autoPlay: true });