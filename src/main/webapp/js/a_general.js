;(function($){
	$.fn.stringvalidate = function(string){
		var reboolean = /^[a-zA-Z0-9\u4e00-\u9fa5\。\，\、\？\！\(\)\（\）\%\￥_.\s-]{1,300}$/.test(string);
		return reboolean;
	};

	$.fn.numbervalidate = function(number){
		var reboolean = /^[0-9\~\.\-]{1,30}$/.test(number);
		return reboolean;
	};

	$.fn.datevalidate = function(date){
		var reboolean = /^\d{4}-\d{2}-\d{2}$/.test(date);
		return reboolean;
	};

	$.fn.telvalidate = function(tel){
		var reboolean = /^1[0-9][0-9]\d{8}$/.test(tel);
		return reboolean;
	};

	$.fn.pagegenerator = function(lastPage, page){
		//lastPage 总页数 page 当前页数
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

	$.fn.popupTip = function(str){
		if (str) {
			if($(".popuptip").length == 1){
				$(".popuptip h3").text(str);
			}else{
				$(".popuptip").remove();
				$("body").append('<div class="popuptip"><h3>'+str+'</h3></div>');
			}
			$(".popuptip").fadeIn(100).delay(2000).fadeOut(400);
		}
	};
})(jQuery);

$(document).ready(function(){
	$.ajax({
		type:"GET",
		data:{"back":1},
		async:false,
		cache:false,
		url:"./g/loginStatus",
		error:function(){
			//window.location.href="error.html";
		},
		success:function(data){
			if (data.success == 0) {
				$("header .un").text(data.userName);
			}else{
				window.location.href="login.html";
			}	
		}
	});

	$("#logout").live("click",function(){
		$.ajax({
			type:"GET",
			data:"",
			dataType: 'json',
			async:false,
			cache:false,
			url:"./passport/logout",
			success:function(data){
				if (data.success == 0) {
					window.location.href="login.html";
				}
			}
		});
	});

	$("#sidebar").empty().append('<a href="a_item_new.html"><div class="newitem">发布产品</div></a><ul>'
		+'<li data-redirect="nav0"><span class="icon-speedometer"></span>控制台</li>'
		+'<li name="nav1"><span class="icon-earth"></span>店铺管理</li>'
		+'<ul class="nav1">'
			+'<li data-redirect="nav11"><span class="icon-arrow-right2"></span>店铺设置</li>'
			+'<li data-redirect="nav12"><span class="icon-arrow-right2"></span>分类管理</li>'
			// +'<li data-redirect="nav13"><span class="icon-arrow-right2"></span>客服管理</li>'
			// +'<li data-redirect="nav14"><span class="icon-arrow-right2"></span>许可证设置</li>'
		+'</ul>'
		+'<li name="nav2"><span class="icon-three"></span>订单管理</li>'
		+'<ul class="nav2">'
			+'<li data-redirect="nav21"><span class="icon-arrow-right2"></span>订单管理</li>'
			+'<li data-redirect="nav22"><span class="icon-arrow-right2"></span>评价管理</li>'
			// +'<li data-redirect="nav23"><span class="icon-arrow-right2"></span>晒单管理</li>'
		+'</ul>'
		+'<li name="nav3"><span class="icon-file"></span>商品管理</li>'
		+'<ul class="nav3">'
			+'<li data-redirect="nav31"><span class="icon-arrow-right2"></span>货架管理</li>'
			+'<li data-redirect="nav32"><span class="icon-arrow-right2"></span>仓库管理</li>'
		+'</ul>'
		// +'<li name="nav9"><span class="icon-file"></span>分销管理</li>'
		// +'<ul class="nav9">'
		// +'<li data-redirect="nav91"><span class="icon-arrow-right2"></span>提现管理</li>'
		// +'</ul>'
		+'<li name="nav4"><span class="icon-setting"></span>网站设置</li>'
		+'<ul class="nav4">'
			+'<li data-redirect="nav41"><span class="icon-arrow-right2"></span>首页设置</li>'
			// +'<li data-redirect="nav42"><span class="icon-arrow-right2"></span>SEO优化</li>'
			// +'<li data-redirect="nav43"><span class="icon-arrow-right2"></span>信息设置</li>'
		+'</ul>'
		// +'<li name="nav6"><span class="icon-supermarket"></span>现金券管理</li>'
		// +'<ul class="nav6">'
		// 	+'<li data-redirect="nav61"><span class="icon-arrow-right2"></span>现金券列表</li>'
		// 	+'<li data-redirect="nav62"><span class="icon-arrow-right2"></span>创建现金券</li>'
		// +'</ul>'
		// +'<li name="nav7"><span class="icon-man"></span>虚拟股管理</li>'
		// +'<ul class="nav7">'
		// 	+'<li data-redirect="nav74"><span class="icon-arrow-right2"></span>公告</li>'
		// 	+'<li data-redirect="nav71"><span class="icon-arrow-right2"></span>当前设定</li>'
		// 	+'<li data-redirect="nav72"><span class="icon-arrow-right2"></span>历史数据</li>'
		// 	+'<li data-redirect="nav73"><span class="icon-arrow-right2"></span>查询分红金额</li>'
		// +'</ul>'
		+'<li name="nav8"><span class="icon-man"></span>用户管理</li>'
		+'<ul class="nav8">'
			+'<li data-redirect="nav81"><span class="icon-arrow-right2"></span>用户管理</li>'
			// +'<li data-redirect="nav83"><span class="icon-arrow-right2"></span>订阅管理</li>'
			// +'<li data-redirect="nav82"><span class="icon-arrow-right2"></span>发放现金券</li>'
		+'</ul>'
		// +'<li><span class="icon-man"></span>售后管理</li>'
		// +'<li class="lil" name="nav5" data-redirect="nav5"><span class="icon-graduate33"></span>投稿作品</li>'
	+'</ul>');
	
	if ($("#sidebar").attr("data-index")) {
		var index = $("#sidebar").attr("data-index");
		if (index[3]) {
			$("#sidebar li[name=nav"+index[3]+"]").addClass("current");
			$("#sidebar ul.nav"+index[3]).addClass("current");
			if (index[4]) {
				$("#sidebar li[data-redirect="+index+"]").addClass("current");
			}
		}
	}

	$("#sidebar li[name]").click(function(){
		$("#sidebar li[name]").removeClass("active");
		$("#sidebar ul").removeClass("active");
		$(this).addClass("active");
		$("#sidebar ul."+$(this).attr("name")).addClass("active");
	});

	$("#sidebar li[data-redirect]").click(function(){
		console.log($(this).attr("data-redirect"));
		var v = $(this).attr("data-redirect");
		switch(v){
			case "nav0":
			window.location.href="a_orderlist.html";
			break;

			case "nav11":
			window.location.href="a_shopsetting.html";
			break;
			case "nav12":
			window.location.href="a_category.html";
			break;
			case "nav13":
			window.location.href="a_service.html";
			break;
			case "nav14":
			window.location.href="a_license.html";
			break;

			case "nav21":
			window.location.href="a_orderlist.html";
			break;
			case "nav22":
			window.location.href="a_commentlist.html";
			break;
			case "nav23":
			
			break;

			case "nav31":
			window.location.href="a_stock.html";
			break;
			case "nav32":
			window.location.href="a_storage.html";
			break;

			case "nav41":
			window.location.href="a_indexsetting.html";
			break;
			case "nav42":
			window.location.href="a_seosetting.html";
			break;
			case "nav43":
			window.location.href="a_infosetting.html";
			break;

			case "nav5":
			window.location.href="a_designlist.html";
			break;

			case "nav61":
			window.location.href="a_cashcoupon.html";
			break;
			case "nav62":
			window.location.href="a_newcashcoupon.html";
			break;

			case "nav71":
			window.location.href="a_virtualstock.html";
			break;
			case "nav72":
			window.location.href="a_vstockqtt.html";
			break;
			case "nav73":
				window.location.href="a_vstockbonus.html";
				break;
			case "nav74":
				window.location.href="a_stocknotice.html";
			break;

			case "nav81":
			window.location.href="a_user.html";
			break;
			case "nav82":
			window.location.href="a_sendcoupon.html";
			break;
			case "nav83":
			window.location.href="a_rsslist.html";
			break;
			case "nav91":
				window.location.href="a_withdraw.html";
				break;
		};
	});

	$(".up").click(function(){
		scroll(0,0);
	});

	$(".ddlist").append('<li id="logout">登出</li>');

	$(".userinfo").click(function(){
		$(this).siblings(".ddlist").toggle();
	});
});