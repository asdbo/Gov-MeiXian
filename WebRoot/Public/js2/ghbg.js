// JavaScript Document
/*热点排行左右滑动 jquery.slide*/
	
		/*skin*/
$(document).ready(function(){
  $("#cSkin").click(function(){
	$('.skin-wrap').animate({'top':'0'},800),
	$('.skinMask').css({'display':'block'})
  });
});
 
$(document).ready(function(){
  $(".skinMask, .slideHidden").click(function(){
	$('.skin-wrap').animate({'top':'-148px'},800),
	$('.skinMask').css({'display':'none'})
  });
});
 
/*热点排行左右滑动 jquery.slide*/
(function($){
    $.fn.ckSlide = function(opts){
        opts = $.extend({}, $.fn.ckSlide.opts, opts);
        this.each(function(){
            var slidewrap = $(this).find('.ck-slide-wrapper');
            var slide = slidewrap.find('li');
            var count = slide.length;
            var that = this;
            var index = 0;
            var time = null;
            $(this).data('opts', opts);
            // next
            $(this).find('.ck-next').on('click', function(){
                if(opts['isAnimate'] == true){
                    return;
                }
                
                var old = index;
                if(index >= count - 1){
                    index = 0;
                }else{
                    index++;
                }
                change.call(that, index, old);
            });
            // prev
            $(this).find('.ck-prev').on('click', function(){
                if(opts['isAnimate'] == true){
                    return;
                }
                
                var old = index;
                if(index <= 0){
                    index = count - 1;
                }else{                                      
                    index--;
                }
                change.call(that, index, old);
            });
            $(this).find('.ck-slidebox li').each(function(cindex){
                $(this).on('click.slidebox', function(){
                    change.call(that, cindex, index);
                    index = cindex;
                });
            });
            
            // focus clean auto play
            $(this).on('mouseover', function(){
                if(opts.autoPlay){
                    clearInterval(time);
                }
                $(this).find('.ctrl-slide').css({opacity:0.9});
            });
            //  leave
            $(this).on('mouseleave', function(){
                if(opts.autoPlay){
                    startAtuoPlay();
                }
                $(this).find('.ctrl-slide').css({opacity:0.65});
            });
            startAtuoPlay();
            // auto play
            function startAtuoPlay(){
                if(opts.autoPlay){
                    time  = setInterval(function(){
                        var old = index;
                        if(index >= count - 1){
                            index = 0;
                        }else{
                            index++;
                        }
                        change.call(that, index, old);
                    }, 5000);
                }
            }
            // 修正box
            var box = $(this).find('.ck-slidebox');
            box.css({
                'margin-left':-(box.width() / 2)
            })
            // dir
            switch(opts.dir){
                case "x":
                    opts['width'] = $(this).width();
                    slidewrap.css({
                        'width':count * opts['width']
                    });
                    slide.css({
                        'float':'left',
                        'position':'relative'
                    });
                    slidewrap.wrap('<div class="ck-slide-dir"></div>');
                    slide.show();
                    break;
            }
        });
    };
    function change(show, hide){
        var opts = $(this).data('opts');
        if(opts.dir == 'x'){
            var x = show * opts['width'];
            $(this).find('.ck-slide-wrapper').stop().animate({'margin-left':-x}, function(){opts['isAnimate'] = false;});
            opts['isAnimate'] = true
        }else{
            $(this).find('.ck-slide-wrapper li').eq(hide).stop().animate({opacity:0});
            $(this).find('.ck-slide-wrapper li').eq(show).show().css({opacity:0}).stop().animate({opacity:1});
        }
       
        $(this).find('.ck-slidebox li').removeClass('current');
        $(this).find('.ck-slidebox li').eq(show).addClass('current');
    }
    $.fn.ckSlide.opts = {
        autoPlay: false,
        dir: null,
        isAnimate: false
    };
})(jQuery);

 $(function(){
	 //换肤左右切换  
    $('.skinChange').ckSlide({dir: 'x'});

	// 背景cookie	
	function switchSkin(skinName){
		$('.body_bg b').attr("class",skinName);
		$.cookie("MyCssSkin",skinName,{path:'/',expires:10});
		
	}
	
	// 换肤
        $('.imgskin a').each(function(){
            if($(this).attr('tag') != undefined){
                $(this).find('img').css({'opacity':'1','cursor':'pointer'})
                $(this).click(function(){
                    var aTag=$(this).attr('tag');
					 switchSkin(aTag);
                    if($('.body_bg b').hasClass(aTag)){}
					else{
						$('.body_bg b').stop(true).animate({"opacity":0},400,function(){$(this).removeClass().addClass(aTag)}).animate({"opacity":1},800,function(){$(this).attr("class",aTag)});
						} 
                });
				var cookie_skin = $.cookie("MyCssSkin");
				if(cookie_skin){
					switchSkin(cookie_skin);
				}
            }
        })
		
 });