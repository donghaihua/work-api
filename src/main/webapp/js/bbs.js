;(function($){
	$.fn.topictvalidate = function(topict){
		var reboolean = /^[a-zA-Z0-9\u4e00-\u9fa5_#\.\,\s\-]{1,20}$/.test(topict);
		return reboolean;
	};

	$.fn.pagegenerator = function(lastPage, page){
		page  = parseInt(page);
		lastPage = parseInt(lastPage);
		$(".page").empty();
		if (lastPage != 1) {
			if (lastPage < 7) {
				for (var i = 1; i < lastPage + 1; i++) {
					$(".page").append('<a class="pageid'+i+'">'+i+'</a>');
				};
				$(".page").append('<span class="nextpage">下一页<span class="icon-arrow-right2"></span></span>');
			}else{
				if (page + 1 <= 5) {
					for (var i = 1; i < 6; i++) {
						$(".page").append('<a class="pageid'+i+'">'+i+'</a>');
					};
					$(".page").append('<span>&hellip;</span><a class="pageid'+lastPage+'">'+lastPage+'</a><span class="nextpage">下一页<span class="icon-arrow-right2"></span></span>');
				}else if (page >= lastPage - 2) {
					$(".page").append('<a class="pageid1">1</a><span>&hellip;</span>');
					for (var i = lastPage - 4; i < lastPage + 1; i++) {
						$(".page").append('<a class="pageid'+i+'">'+i+'</a>');
					};
					$(".page").append('<span class="nextpage">下一页<span class="icon-arrow-right2"></span></span>');
				}else{
					$(".page").append('<a class="pageid1">1</a><span>&hellip;</span>');
					for (var i = page - 1; i < page + 2; i++) {
						$(".page").append('<a class="pageid'+i+'">'+i+'</a>');
					};
					$(".page").append('<span>&hellip;</span><a class="pageid'+lastPage+'">'+lastPage+'</a><span class="nextpage">下一页<span class="icon-arrow-right2"></span></span>');
				}
			}
			$(".page a.pageid"+page+"").addClass("current");
			if (page == lastPage) {
				$(".nextpage").remove();
			}
		}
	};

	$.fn.changeurl = function(str, par, value){
		var pattern = par+'=([^&]*)';
		var replaceText = par+'='+value;
		if (str.match(pattern)) {
			var tmp = '/'+par+'=[^&]*/';
			tmp = str.replace(eval(tmp), replaceText);
			return (tmp);
		}else{
			if (str.match('[\#]')){
				return str+'&'+ replaceText; 
			}else{
				return str+'#'+replaceText; 
			}
		}
		return str+'\n'+par+'\n'+value;
	};
})(jQuery);


var accountid = -1;
var loginData;
var roleId = 1;
$(document).ready(function() {
	$('#newtopic').on('click',function(){
		window.location.href="newtopic.html";
	});

	$("footer").empty().append('<p>'
			+'<a href="../index.html">有好油主页</a>&middot;'
			+'<a href="../culture.html">山茶文化</a>&middot;'
			+'<a href="../brand.html">品牌故事</a>&middot;'
			+'<a href="">私人定制</a>&middot;'
			+'<a href="../item_list.html">商品系列</a>&middot;'
			+'<a href="../contact.html">联系我们</a>'
		+'</p>'
		+'<div class="copyright">'
			+'<span class="cr">&copy;2010-2016 www.uhaou.cn 版权所有 | </span>'
			+'<a class="nutin" href="http://nutin.me">Powered by Nutin.me</a>'
		+'</div>');

	$.ajax({
		type:"GET",
		data:"",
		url:"../bbs/post/wonderful",
		success:function(data){
			if (data.success == 0) {
				$("#hotpost").empty();
				for (var i = 0; i < data.datas.length; i++) {
					$("#hotpost").append('<li><a href="topic.html?postId='+data.datas[i].id+'">'
						+data.datas[i].title+'</a></li>');
				};
			}
		}
	});

	$.ajax({
		type:"GET",
		data:"",
		contentType: 'application/json',
		dataType: 'json',
		async:false,
		cache:false,
		url:"../g/loginStatus",
		error:function(){
			//window.location.href="../error.html";
		},
		success:function(data){
			loginData = data;
			if (data.success == 0) {
				accountid = data.id;
				var username = '用户';
				if (data.userName) {
					username = data.userName;
				}
				for (var i = 0; i < data.roles.length; i++) {
					if(data.roles[i] > roleId){
						roleId = data.roles[i];
					}
				}
				
				var str = '<div class="info"><span class="un">'
					+username+'</span><span class="icon-arrow-down"></span>'
					+'<ul class="dd1"><li><a href="../c_account.html">我的账户</a></li><li id="logout">登出</li></ul>'
					+'</div><div class="ava">';
				
				if(data.avatar){
					str+='<img src="'+data.avatar+'" alt="img">';
				}else{
					str+='<img src="../img/avatar_preview_default.png" alt="img">';
				}
				
				str+='</div>';
				$(".hr").empty().append(str);
			}
		}
	});

	$("header .hl").click(function(){
		window.location.href="forum.html";
	});

	$(".info").click(function(){
		$(".dd1").toggle();
	});

	$("#logout").live("click",function(){
		$.ajax({
			type:"GET",
			data:"",
			dataType: 'json',
			async:false,
			cache:false,
			url:"../passport/logout",
			success:function(data){
				if (data.success == 0) {
					$.cookie("cartlist","");
					$(".hr").empty().append('<a href="../reg.html">注册</a>'
				+'<a href="../login.html">登录</a>');
					window.location.href="forum.html";
				}
			}
		});
	});
});