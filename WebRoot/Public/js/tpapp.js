// JavaScript Document

var NO_CK_TIP = '你没有指定操作的项',
	PL_TIP = '你确定这样操作吗？操作后可能不能恢复！';
var lang = "zh-cn",
	ADD	=	'添加',
	EDIT	=	'编辑',
	VIEW	=	'查看',
	MANAGE = '管理',
	SAVE = '保存',
	CANCEL = '取消',
	SUBMIT = '提交',
	CONFIRM = '确定',
	TIP_INFO = '提示信息',
	CLOSE	=	'关闭';
$(function(){
	
	
	//全选
	$(".box_all").click(function(){
		if(this.checked){
		    $("input[type='checkbox'][disabled!='disabled']").each(function(){
				this.checked = true;						
			})
	    }else{
			$("input[type='checkbox'][disabled!='disabled']").each(function(){
				this.checked = false;						
			})
		}				 
	})
	
	//开关
	$(".cb-enable").click(function(){
	    $(this).find('label').addClass('selected');
		$(this).find('label').find('input').attr('checked',true);
		$(this).next('.cb-disable').find('label').find('input').attr('checked',false);
		$(this).next('.cb-disable').find('label').removeClass('selected');
    })
	$(".cb-disable").click(function(){
	    $(this).find('label').addClass('selected');
		$(this).find('label').find('input').attr('checked',true);
		$(this).prev('.cb-enable').find('label').find('input').attr('checked',false);
		$(this).prev('.cb-enable').find('label').removeClass('selected');
    })
	
	//菜单联动菜单
	  $.fn.node_select = function(options) {
        var settings = {
            field: 'J_node_id',
            top_option: '请选择'
        };
        if(options) {
            $.extend(settings, options);
        }

        var self = $(this),
            pid = self.attr('data-pid'),
            uri = self.attr('data-uri'),
            selected = self.attr('data-selected'),
            selected_arr = [];
        if(selected != undefined && selected != '0'){
        	if(selected.indexOf('|')){
        		selected_arr = selected.split('|');
        	}else{
        		selected_arr = [selected];
        	}
        }
        self.nextAll('.J_node_select').remove();
        $('<option value="">--'+settings.top_option+'--</option>').appendTo(self);
        $.getJSON(uri, {id:pid}, function(result){
            if(result.status == '1'){
                
                for(var i=0; i<result.data.length; i++){
                    $('<option value="'+result.data[i].node_id+'">'+result.data[i].node_name+'</option>').appendTo(self);
                }
            }
            if(selected_arr.length > 0){
            	//IE6 BUG
            	setTimeout(function(){
            		self.find('option[value="'+selected_arr[0]+'"]').attr("selected", true);
	        		self.trigger('change');
            	}, 1);
            }
        });

        var j = 1;
        
        $("#J_node_select").off('change', '.J_node_select').on('change', '.J_node_select', function(){
            var _this = $(this),
            _pid = _this.val();
            
            _this.nextAll('.J_node_select').remove();
            if(_pid != ''){
                $.getJSON(uri, {id:_pid}, function(result){
                    if(result.status == '1'){
                        var _childs = $('<select class="J_node_select input-sm" data-pid="'+_pid+'"><option value="">--'+settings.top_option+'--</option></select>')
                        for(var i=0; i<result.data.length; i++){
                            $('<option value="'+result.data[i].node_id+'">'+result.data[i].node_name+'</option>').appendTo(_childs);
                        }
                        _childs.insertAfter(_this);
                        if(selected_arr[j] != undefined){
                        	//IE6 BUG
                        	//setTimeout(function(){
			            		_childs.find('option[value="'+selected_arr[j]+'"]').attr("selected", true);
				        		_childs.trigger('change');
			            	//}, 1);
			            }
                        j++;
                    }
                });
                $('#'+settings.field).val(_pid);
            }else{
            	$('#'+settings.field).val(_this.attr('data-pid'));
            }
        });
    }
	
	$.fn.department_select = function(options) {
        var settings = {
            field: 'J_department_id',
            top_option: '请选择'
        };
        if(options) {
            $.extend(settings, options);
        }

        var self = $(this),
            pid = self.attr('data-pid'),
            uri = self.attr('data-uri'),
            selected = self.attr('data-selected'),
            selected_arr = [];
        if(selected != undefined && selected != '0'){
        	if(selected.indexOf('|')){
        		selected_arr = selected.split('|');
        	}else{
        		selected_arr = [selected];
        	}
        }
        self.nextAll('.J_department_select').remove();
        $('<option value="">--'+settings.top_option+'--</option>').appendTo(self);
        $.getJSON(uri, {id:pid}, function(result){
            if(result.status == '1'){
                
                for(var i=0; i<result.data.length; i++){
                    $('<option value="'+result.data[i].department_id+'">'+result.data[i].department_name+'</option>').appendTo(self);
                }
            }
            if(selected_arr.length > 0){
            	//IE6 BUG
            	setTimeout(function(){
            		self.find('option[value="'+selected_arr[0]+'"]').attr("selected", true);
	        		self.trigger('change');
            	}, 1);
            }
        });

        var j = 1;
        
        $("#J_department_select").off('change', '.J_department_select').on('change', '.J_department_select', function(){
            var _this = $(this),
            _pid = _this.val();
            
            _this.nextAll('.J_department_select').remove();
            if(_pid != ''){
                $.getJSON(uri, {id:_pid}, function(result){
                    if(result.status == '1'){
                        var _childs = $('<select class="J_department_select input-sm" data-pid="'+_pid+'"><option value="">--'+settings.top_option+'--</option></select>')
                        for(var i=0; i<result.data.length; i++){
                            $('<option value="'+result.data[i].department_id+'">'+result.data[i].department_name+'</option>').appendTo(_childs);
                        }
                        _childs.insertAfter(_this);
                        if(selected_arr[j] != undefined){
                        	//IE6 BUG
                        	//setTimeout(function(){
			            		_childs.find('option[value="'+selected_arr[j]+'"]').attr("selected", true);
				        		_childs.trigger('change');
			            	//}, 1);
			            }
                        j++;
                    }
                });
                $('#'+settings.field).val(_pid);
            }else{
            	$('#'+settings.field).val(_this.attr('data-pid'));
            }
        });
    }
	
	$.fn.menu_select = function(options) {
        var settings = {
            field: 'J_menu_id',
            top_option: '请选择'
        };
        if(options) {
            $.extend(settings, options);
        }

        var self = $(this),
            pid = self.attr('data-pid'),
            uri = self.attr('data-uri'),
            selected = self.attr('data-selected'),
            selected_arr = [];
        if(selected != undefined && selected != '0'){
        	if(selected.indexOf('|')){
        		selected_arr = selected.split('|');
        	}else{
        		selected_arr = [selected];
        	}
        }
        self.nextAll('.J_menu_select').remove();
        $('<option value="">--'+settings.top_option+'--</option>').appendTo(self);
        $.getJSON(uri, {id:pid}, function(result){
            if(result.status == '1'){
                
                for(var i=0; i<result.data.length; i++){
                    $('<option value="'+result.data[i].menu_id+'">'+result.data[i].menu_name+'</option>').appendTo(self);
                }
            }
            if(selected_arr.length > 0){
            	//IE6 BUG
            	setTimeout(function(){
            		self.find('option[value="'+selected_arr[0]+'"]').attr("selected", true);
	        		self.trigger('change');
            	}, 1);
            }
        });

        var j = 1;
        
        $("#J_menu_select").off('change', '.J_menu_select').on('change', '.J_menu_select', function(){
            var _this = $(this),
            _pid = _this.val();
            
            _this.nextAll('.J_menu_select').remove();
            if(_pid != ''){
                $.getJSON(uri, {id:_pid}, function(result){
                    if(result.status == '1'){
                        var _childs = $('<select class="J_menu_select input-sm" data-pid="'+_pid+'"><option value="">--'+settings.top_option+'--</option></select>')
                        for(var i=0; i<result.data.length; i++){
                            $('<option value="'+result.data[i].menu_id+'">'+result.data[i].menu_name+'</option>').appendTo(_childs);
                        }
                        _childs.insertAfter(_this);
                        if(selected_arr[j] != undefined){
                        	//IE6 BUG
                        	//setTimeout(function(){
			            		_childs.find('option[value="'+selected_arr[j]+'"]').attr("selected", true);
				        		_childs.trigger('change');
			            	//}, 1);
			            }
                        j++;
                    }
                });
                $('#'+settings.field).val(_pid);
            }else{
            	$('#'+settings.field).val(_this.attr('data-pid'));
            }
        });
    }
	
	var $div_li = $(".tab-menu ul li");
	$div_li.click(function() {
		$(this).addClass("current") //当前<li>元素高亮
		.siblings().removeClass("current"); //去掉其它同辈<li>元素的高亮
		var index = $div_li.index(this); // 获取当前点击的<li>元素 在 全部li元素中的索引。
		$(".tab-content .tab") //选取子节点。不选取子节点的话，会引起错误。如果里面还有div
		.eq(index).show() //显示 <li>元素对应的<div>元素
		.siblings().hide(); //隐藏其它几个同辈的<div>元素
	}).hover(function() {
		$(this).addClass("hover");
	}, function() {
		$(this).removeClass("hover");
	})
	
	//高级搜索弹框
	$('.exact').click(function() {
		$(this).toggleClass('double-arrow-up').next('.exact-search').toggle();
		var isIE6 = !! window.ActiveXObject && !window.XMLHttpRequest;
		if(isIE6) {
			if($('#es-content').height() > 280) {
				$('#es-content').height(280);
			}
		}
	});
	$(".exact-search .cancel").click(function() {
		$(".exact-search").hide().prev(".exact").toggleClass('double-arrow-up');
	});
})

//提示方法
function tip(msg){
	$(".msg span").html(msg);
	if($("#body_login #msgkong .msg").is(":hidden")){
		$("#body_login #msgkong .msg").animate({height:'toggle'}).delay(2000).animate({height:'toggle'},function(){
		    btn_is_disabled(true);				  
	    });
	}
}

//快捷菜单设置选项点击执行的方法
function ckall(obj,str1,str2,str3,len){
	if(obj.checked){
		if(len==1){
			if(str1){
		      $("."+str1).each(function(i,e){
			      e.checked = true;
		      })  
	        }
			if(str3){
		      $("."+str3).each(function(i,e){
			      e.checked = true;
		      })  
	        }
			if(str2){
		      $("."+str2).each(function(i,e){
			      e.checked = true;
		      })  
	         }
		}else if(len==2){
			if(str3){
		      $("."+str3).each(function(i,e){
				  $(e).parent('label').parent('dt').parent('dl').parent('div').parent('td').prev('th').find('label').find('input').each(function(ii,ee){
					  ee.checked = true;																					  
			      })		
			      e.checked = true;
		      })  
	        }
			if(str2){
		      $("."+str2).each(function(i,e){		
			      e.checked = true;
		      })  
	        }
	   }else if(len==3){
			if(str3){
		      $("."+str3).each(function(i,e){
				  $(e).parent('label').parent('dd').parent('dl').parent('div').parent('td').prev('th').find('label').find('input').each(function(ii,ee){
					  ee.checked = true;																					  
				  })
				  $(e).parent('label').parent('dd').prev('dt').find('label').find('input').each(function(iii,eee){
					  eee.checked = true;																					  
				  })
			      e.checked = true;
		      })  
	        }
	   }
	}else{
	   $("."+str3).each(function(i,e){
			  e.checked = false;
	   })  
	}
}

function isck(){
	 $allid = new Array();
     $(".check_box").each(function(i,o){
		    if(this.checked) {
			    $allid.push($(this).val());
		    }
	 })
	 if($allid == ''){
	     return false;	 
	 }else{
	     return $allid;	 
	 }
}
function btn_is_disabled_obj(i,obj){
	if(i==true){
		$(obj).removeClass('disabled');
		$("input[type=text]").attr('disabled', false);
	}else{
		$(obj).addClass('disabled');
		$("input[type=text]").attr('disabled', 'disabled');
	}
}
function deletes(data,url,obj,isck,isback){
	if(!isck){
	if(data ==false){
		window.top.msg(0,NO_CK_TIP);
		return false;
	}
	}
	btn_is_disabled_obj(false,obj);
		window.top.art.dialog({
    		content: PL_TIP,
    		ok: function () {
    			$.post(url,{id:data},function(msg){
					if(msg.status){
					
				    	if(!isback){ window.location.reload();}//刷新
			    	}
					window.top.msg(msg.status,msg.msg);
					btn_is_disabled_obj(true,obj);				
				})
        		return true;
    		},
			title:TIP_INFO,
			lock:true,
			background:'#FFF',
			icon:'question',
			close:function(){
	     		btn_is_disabled_obj(true,obj);		
			},
    		cancelVal: CANCEL,
    		cancel: true //为true等价于function(){}
		});
}

function orders(data, url, obj, isck, isback){
    
	if(!isck){
	if(data ==false){
		window.top.msg(0,NO_CK_TIP);
		return false;
	}
	}
	btn_is_disabled_obj(false,obj);
		window.top.art.dialog({
    		content: '<input id="post_order" type="text">',
    		ok: function () {
                        var order_num = $("#post_order").val();
                        order_num = order_num ? order_num : 0;
                        url = url + "?order_num=" + order_num;
    			$.post(url,{id:data},function(msg){
					if(msg.status){
					
				    	if(!isback){ window.location.reload();}//刷新
			    	}
					window.top.msg(msg.status,msg.msg);
					btn_is_disabled_obj(true,obj);				
				})
        		return true;
    		},
			title:TIP_INFO,
			lock:true,
			background:'#FFF',
			icon:'question',
			close:function(){
	     		btn_is_disabled_obj(true,obj);		
			},
    		cancelVal: CANCEL,
    		cancel: true //为true等价于function(){}
		});
}

function ajaxpost(data,url,obj,isck,isback,istip,returnmsg){
	if(!isck){
	if(data['id'] ==false){
		window.top.msg(0,NO_CK_TIP);
		return false;
	}
	}
	btn_is_disabled_obj(false,obj);
	if(!istip){
		window.top.art.dialog({
    		content: PL_TIP,
    		ok: function () {
    			$.post(url,data,function(msg){
					if(msg.status){
				    	if(!isback)window.location.reload();//刷新
						if(!returnmsg) window.top.msg(msg.status,msg.message);
			    	}else{
						 window.top.msg(msg.status,msg.message);
					}
				    btn_is_disabled_obj(true,obj);			
				})
        		return true;
    		},
			title:TIP_INFO,
			lock:true,
			background:'#FFF',
			icon:'question',
			close:function(){
	     		btn_is_disabled_obj(true,obj);		
			},
    		cancelVal: CANCEL,
    		cancel: true //为true等价于function(){}
		});
  }else{
	 $.post(url,data,function(msg){
					//alert(msg)
					if(msg.status){
				    	if(!isback)window.location.reload();//刷新
						if(!returnmsg) window.top.msg(msg.status,msg.message);
			    	}else{
						 window.top.msg(msg.status,msg.message);
					}
				    btn_is_disabled_obj(true,obj);					
				})
        		return true;
	 }
}

//展开与隐藏
function ishidden(is,obj){
	if(is == '1'){
	    $("."+obj).show();
	}else{
		$("."+obj).hide();
	}
}