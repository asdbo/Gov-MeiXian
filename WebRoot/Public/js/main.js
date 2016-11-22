// 回到顶部
$("#scrollToTop").hide();
$(window).scroll(function(){
if ($(window).scrollTop()>100){
$("#scrollToTop").fadeIn(500);
}
else
{
$("#scrollToTop").fadeOut(500);
}
});
$("#scrollToTop").click(function(){
$('body,html').animate({ scrollTop:0 },100);
return false;
});
$('a.scrollToTop').click(function(){ 
$('html, body').animate({
    scrollTop:0
    }, 'slow'); 
return false; 
}); 
//-- 回到顶部

$(".tufa").slide({ trigger: "click" });
$(".xzsp_sx").slide();
// 轮播
$(".focusBox").hover(function () {
                $(this).find(".prev,.next").stop(true, true).fadeTo("show", 1)
            }, function () {
                $(this).find(".prev,.next").fadeOut()
            });

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
            $(".right_scorll_img").slide({
            	   titCell:".list",mainCell:".piclist",autoPage:true,effect:"left",autoPlay:true,vis:4, 
            	   scroll: 4, delayTime: 800, trigger: "click", easing: "easeOutCirc" });
            	  $('.tsmz .tempWrap li a img').hover(function(){
            	    $('.tsmz .tempWrap li a img').removeClass('borders');
            	    $(this).addClass('borders');
            	  })
$(".threeScroll").hover(function() {
    $(this).find(".prev,.next").stop(true, true).fadeTo("show", 1)
}, function() {
    $(this).find(".prev,.next").fadeOut()
});
$(".threeScroll").slide({
    mainCell: ".dlList",
    effect: "leftLoop",
    vis: 4,
    autoPlay: true
});            
        $(".news_img2").slide({
            mainCell:".bd ul",effect:"fold",autoPlay:true
        });
        $(".news").slide(); $(".news2").slide();
        $(".news_vertical1").slide({ titCell:"ol li",mainCell:"div",delayTime:0,triggerTime:0 });
        $(".news_vertical2").slide({ titCell:"ol li",mainCell:"div",delayTime:0,triggerTime:0 });
              $(".news3").slide();
// ···轮播

// 验证码
 $.idcode.setCode();
// --验证码

$('.news-cate li a').hover(function(){
    $('.news-cate li a').removeClass('active');
    $(this).addClass('active');
});
