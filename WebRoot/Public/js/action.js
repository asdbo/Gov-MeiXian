//缃戜笂鍔炰簨action
var wsbs_triggle = $('.bigwSectype').find('li');
var wsbs_content = $('.bigwConent').find('ul');
wsbs_triggle.click(function(){
	$(this).addClass('hot').siblings().removeClass('hot');
	wsbs_content.eq($(this).index()).fadeIn(100).siblings().fadeOut(10);
})

//鍔炰欢缁熻action
var count_triggle = $('.filter').find('a');
var count_content = $('.count_sum').find('ul');
count_triggle.click(function(){
	$(this).addClass('hot').siblings().removeClass('hot');
	count_content.eq($(this).index()).fadeIn(0).siblings().fadeOut(0);
})

//鍒囨崲table
var event_triggle = $('.event_type').find('li');
var event_content = $('.event').find('.event_table');
event_triggle.click(function(){
	$(this).addClass('hot').siblings().removeClass('hot');
	event_content.eq($(this).index()).fadeIn(0).siblings().fadeOut(0);
})

//鏁堣兘鐩戝療
var event_triggle = $('.monitor_type_ul').find('li');
var event_content = $('.monitorTb');
event_triggle.click(function(){
	$(this).addClass('hot').siblings().removeClass('hot');
	event_content.eq($(this).index()).fadeIn(0).siblings().fadeOut(0);
})


//瀵艰埅杈规鎿嶄綔
$('.tb_nav_ul').find('li').last().addClass('last');
$('.tb_nav_ul').find('.hot').prev().addClass('last');

//閮ㄩ棬鍔炰簨鍩庡競涓嬫媺妗�
$('.divisionSelector').click(function(){
	$('.city_list').css({left:$(this).offset().left,top:$(this).offset().top+27}).slideDown(100);	
	$('.city_list_left').css({left:$(this).offset().left-244,top:$(this).offset().top+27}).slideDown(100);	
	
})
$('.city_list').find('.close').click(function(){
	$(this).parents('.city_list').slideUp(100);	
})

//鎼滅储妗�
var defaultValue = '鏌ユ壘鏈嶅姟浜嬮」';
function setInputValue(){
	$('#searchKey').value = defaultValue;	
	if($('#searchKey').value==null || $('searchKey').value==''){
		$('#searchKey').value = defaultValue;
		$('#searchKey').style.color='#666';
		$('#searchKey').style.fontStyle='italic';
	}
}
		
function keyInputFocus(inputObj){
	if(inputObj.value==defaultValue){
		inputObj.value='';
		inputObj.style.color='#000000';
		inputObj.style.fontStyle='normal';
	}
}
		
function keyInputBlur(inputObj){
	if(inputObj.value==''){
		inputObj.style.color='#666';
		inputObj.style.fontStyle='italic';
		inputObj.value=defaultValue;
	}else{
		inputObj.style.color='#000000';
		inputObj.style.fontStyle='normal';
	}
}



//鍔炰簨璇︽儏涓殣钘忕殑鏈嶅姟鎸囧崡鍜屼簨浠�
$('.event_all').click(function(){
	$(this).toggleClass('event_all_up');
	$('.event_all_hidden').toggleClass('hidden');
})
$('.event_point').click(function(){
	$('.event_point_hidden').toggleClass('hidden');
})
if($('.event_all')[0]){
	$('.event_all')[0].onselectstart = function(){return false;}
	if($('.event_point')[0])
		$('.event_point')[0].onselectstart = function(){return false;}
}


//鎶曡祫瀹℃壒涓紝triggle鎿嶄綔
var invest_triggle = $('.invest_triggle_ul').find('li');
var invest_triggle_qy = $('.invest_triggle_ul_qy').find('li');
var arrow = $('.invest_triggle_arrow');

invest_triggle.click(function(){
	var middle = $(this).width()/2+parseInt($(this).css('left'))-arrow.width()/2;
	$(this).addClass('hot').siblings().removeClass('hot');
	arrow.animate({'left':middle},200);
	$('.invest_process').find('div').eq($(this).index()).show().siblings().hide();
})
invest_triggle_qy.click(function(){
	var middle = $(this).width()/2+parseInt($(this).css('left'))-arrow.width()/2;
	$(this).addClass('hot').siblings().removeClass('hot');
	arrow.animate({'left':middle},200);
	$('.invest_process').find('div').eq($(this).index()).show().siblings().hide();
})

$(function(){
	$('.invest_triggle_ul').find('li').eq(0).click();
	$('.invest_triggle_ul_qy').find('li').eq(0).click();
})

function quit(){
	$.jBox.confirm("<div class='jBox-Help'>纭畾閫�嚭缃戜笂鍔炰簨澶у巺鍚楋紵</div>", "鎻愮ず淇℃伅", function (v, h, f) {
		if(v=='ok')
			window.location.href='/portal/login/login!quit.action';
	});
}

function loadimage(yzCode) {
	document.getElementById("randImage").src = "/portal/login/login!loadCode.action?yzCode="+yzCode+"&ma=" + Math.random();
}

$(function(){
	$('.invest_triggle_ul').find('li').eq(0).click();
	//鐧婚檰涓嬫媺妗�
	if($('.login_ok').length > 0 && $('.login_pop').length > 0){
		$('.login_ok').mouseover(function(){
			$('.login_pop').slideDown(100);	
		});
		$('body').click(function(){
			$('.login_pop').slideUp(100);	
		});
		$('.content').mouseover(function(){
			$('.login_pop').slideUp(100);	
		});
	}
})

//办件统计action
var count_triggle = $('.filter').find('a');
count_triggle.click(function(){
	var par = $(this).parents('.count_tap');
	var count_content = par.find('.count_sum').find('ul');
	$(this).addClass('hot').siblings().removeClass('hot');
	count_content.eq($(this).index()).fadeIn(0).siblings().fadeOut(0);
})