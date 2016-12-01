// 内容区域切换效果
var iLmoveRboxNow=0;
var iNowAlone=0;
function bigMoveBox(obj){
    if($(obj).hasClass('active'))return;
    var iBodyWidth = 1300;
    var oCurrent=null;
    var oObjTag=$(obj).attr('tag');  
    $(obj).parent().parent().find('a').removeClass('active')
    $(obj).addClass('active');
    $('.ind_content_wrap .contentwrap').css({'display':'none','left':iBodyWidth});
    $('.ind_content_wrap .'+oCurrent).css({'left':'0','display':'block'})
    $('.ind_content_wrap .'+oObjTag).css('display','block');
    $('.ind_content_wrap .'+oCurrent).find('.tabSwitch_list').eq(0).css({'display':'block'});
    $('.ind_content_wrap .'+oObjTag).find('.tabSwitch_list').eq(0).css({'display':'block','left':'0'});
    $('.ind_content_wrap .'+oCurrent).stop(true).animate({'left':-iBodyWidth},400,function(){
        $(this).css('display','none')
    })
    $('.ind_content_wrap .'+$(obj).attr('tag')).stop(true).animate({'left':'0'},600,function(){
        $('.ind_content_wrap .contentwrap').removeClass('active');
        $(this).addClass('active');
        // 判断是否存在左右滚动
        JudgeInfo(oObjTag)
    })    
}




function tabMove(obj,iNow,btn){
    if($(obj).hasClass('active'))return;
    var iParentNode=$(obj).parent().parent().parent();
    var iBoxWidth=iParentNode.find('.tabs_boxlist').width();
    var iCurrent=null;
    var oObjTag=$(obj).attr('tag');
    var oTabBoxHig=iParentNode.find('.tabs_boxlist').height();
    iParentNode.find('.tab_box').each(function(){
        $(this).css('position','absolute');
    })


    iParentNode.find('.tabs_boxlist').css('height',oTabBoxHig)
    $(obj).parent().find('a').each(function(){
        if($(this).hasClass('active')){
            iCurrent=$(this).index();
            
        }
    })
    $(obj).parent().find('a').removeClass('active')
    $(obj).addClass('active');
    if(iCurrent>iNow){
        iParentNode.find('.tab_box').css({'display':'none'});
        iParentNode.find('.tab_box').eq(iCurrent).css('display','block');
        iParentNode.find('.tab_box').eq(iNow).css({'display':'block','left':iBoxWidth});
        iParentNode.find('.tab_box').eq(iCurrent).stop(true).animate({'left':-iBoxWidth},400);
        iParentNode.find('.tab_box').eq(iNow).stop(true).animate({'left':'0'},400,function(){
            iNow=iCurrent;
            iParentNode.find('.tab_box').removeClass('.active');
            $(this).addClass('active');
            // 判断是否存在左右滚动
            JudgeInfo(oObjTag)

        });
    }else{
        iParentNode.find('.tab_box').css({'display':'none'});
        iParentNode.find('.tab_box').eq(iCurrent).css('display','block');
        iParentNode.find('.tab_box').eq(iNow).css({'display':'block','left':-iBoxWidth});
        iParentNode.find('.tab_box').eq(iCurrent).stop(true).animate({'left':iBoxWidth},400);
        iParentNode.find('.tab_box').eq(iNow).stop(true).animate({'left':'0'},400,function(){
            iNow=iCurrent;
            iParentNode.find('.tab_box').removeClass('.active');
            $(this).addClass('active');
            // 判断是否存在左右滚动
            JudgeInfo(oObjTag)
        });
    }
    if(btn){
        iParentNode.find('.temp01').eq(1).css({'display':'block'});
    }

}
// 判断是否存在左右滚动
function JudgeInfo(tag){
    if($('.'+tag+'.active').find('.ltor_movebox').html()!=undefined){
        var lrMoveBoxHeight=450;
        var lrMoveboxWidth=$('.'+tag+'.active').find('.ltor_movebox').outerWidth(true);
        // 在有Tab切换的情况下
        if($('.'+tag+'.active').find('.tabs_boxlist').html()!=undefined){
            $('.'+tag+'.active').find('.tabs_boxlist .tab_box').each(function(){
                $(this).find('.ltor_movebox,.ltormove_box').css('height',lrMoveBoxHeight);
                $(this).find('.ltor_movebox .lrmovebox').css({'display':'none','position':'absolute','left':lrMoveboxWidth})
                $(this).find('.ltor_movebox .lrmovebox').eq(0).css({'display':'block','left':0})
            })
        }else{
            $('.'+tag+'.active').find('.ltor_movebox,.ltormove_box').css('height',lrMoveBoxHeight);
            $('.'+tag+'.active').find('.ltor_movebox .lrmovebox').css({'position':'absolute','left':lrMoveboxWidth})
            $('.'+tag+'.active').find('.ltor_movebox .lrmovebox').eq(0).css({'display':'block','left':0})
        }
        $('.'+tag+'.active').find('.goLeft').css('display','block');
        $('.'+tag+'.active').find('.goRight').css('display','none');


    }
    iLmoveRboxNow=0;
    iNowAlone=0;
    $('.alonemove').removeAttr('style');
    $('.moveImgbox dl.hovertitle').removeAttr('style');
    if($('.'+tag+'.active').find('.ltormove_box .lrmovebox').length == 1){
        $('.'+tag+'.active').find('.ltor_btnbox').remove();
    }
}

function leftMove(obj){
    var iParentNode=$(obj).parent().parent();
    var iBoxWidth=iParentNode.eq(0).outerWidth(true);
    var aLrMovebox=iParentNode.find('.lrmovebox');
    var lrLength=iParentNode.find('.lrmovebox').length;
    $(obj).next().css('display','block')
    if(iLmoveRboxNow == lrLength-2){
        $(obj).css('display','none')
    }
    aLrMovebox.eq(iLmoveRboxNow+1).css({'display':'block','left':iBoxWidth})
    aLrMovebox.eq(iLmoveRboxNow).animate({'left':-iBoxWidth},300,function(){
        $(this).css('display','none')
    })
    aLrMovebox.eq(iLmoveRboxNow+1).animate({'left':'0'},300);
    
    iLmoveRboxNow++;
}
function rightMove(obj){
    var iParentNode=$(obj).parent().parent();
    var iBoxWidth=iParentNode.eq(0).outerWidth(true);
    var aLrMovebox=iParentNode.find('.lrmovebox');
    var lrLength=iParentNode.find('.lrmovebox').length;
    $(obj).prev().css('display','block')
    if(iLmoveRboxNow == 1){
        $(obj).css('display','none')
    }
    aLrMovebox.eq(iLmoveRboxNow-1).css({'display':'block','left':-iBoxWidth})
    aLrMovebox.eq(iLmoveRboxNow).animate({'left':iBoxWidth},300,function(){
        $(this).css('display','none')
    })
    aLrMovebox.eq(iLmoveRboxNow-1).animate({'left':'0'},300);
    iLmoveRboxNow--;
}

function lrAloneMove(obj){
    var oUl=$(obj).parent().parent().find('.business ul')
    var aLiLength=oUl.find('li').length;
    var iLiWidth=oUl.find('li').eq(0).outerWidth(true);
    oUl.css('width',iLiWidth*aLiLength)
    if($(obj).hasClass('goLeft')){
        if(iNowAlone<-(aLiLength-5))return;
        iNowAlone--;
        oUl.stop(true).animate({'left':iNowAlone*iLiWidth},300)
    }else{
        if(iNowAlone>=0) return;
        iNowAlone++;
        oUl.stop(true).animate({'left':iNowAlone*iLiWidth},300)
    }
}
function imgMove(obj){

}

$(document).ready(function(){
  
	$('.tit_h2 h2 a').each(function(){
        $(this).mouseenter(function(){
			tabMove(this,$(this).index());
        });
	});
	$('.tit_h3_2 h2 a').each(function(){
        $(this).mouseenter(function(){
			tabMove(this,$(this).index());
        });
	});
});


 