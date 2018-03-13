;(function($){
	$.fn.check = function(type,info,category){
		var returndata = null;
		if (category == "account") {
			id = 0;
		}else if(category == "info"){
			id = 1;
		}
		var data = '{type:"'+id+'",'+type+':"'+info+'"}';
		data = eval('(' + data + ')');
		$.ajax({
			type: 'post',
			data: data,
			async:false,
			url: './g/uniqueCheck',
			success:function(data){
				if(data.success == 0){
					returndata = 0; //未存在
				}else if (data.error == 102) {
					returndata = 1; //已存在
				}else if(data.error == 201){
					returndata = 1;
				}
			}
		})
		return returndata;
	};

	$.fn.vcaptimg = function(capt){
		var success;
		$.ajax({
			type:"post",
			url:"./g/verifyCaptcha",
			data:{captchaImg:capt, type:0},
			async:false,
			error:function(){
				success = 1;
			},
			success:function(data){
				success = data.success;
			}
		});
		return success;
	};

	$.fn.telvalidate = function(tel){
		var reboolean = /^1[0-9][0-9]\d{8}$/.test(tel);
		return reboolean;
	};

	$.fn.phonevalidate = function(phone){
		var reboolean = /^0(([1-9]\d)|([3-9]\d{2}))\d{7,8}$/.test(phone);
		return reboolean;
	};
	
	$.fn.unamevalidate = function(username){
		var reboolean = /^[a-zA-Z0-9\u4e00-\u9fa5_]{2,128}$/.test(username);
		return reboolean;
	};

	$.fn.nnamevalidate = function(nickname){
		var reboolean = /^[a-zA-Z0-9\u4e00-\u9fa5_\.]{2,128}$/.test(nickname);
		return reboolean;
	};

	$.fn.fnamevalidate = function(fullname){
		var reboolean = /^[a-zA-Z0-9\u4e00-\u9fa5\.\s]{2,128}$/.test(fullname);
		return reboolean;
	};

	$.fn.birthvalidate = function(birthday){
		var reboolean = /^(19|20)\d\d[-](1[0-2]|0[1-9])[-](0[1-9]|[1,2][0-9]|3[0,1])$/.test(birthday);
		return reboolean;
	};

	$.fn.addrvalidate = function(address){
		var reboolean = /^[a-zA-Z0-9\u4e00-\u9fa5_#\.\,\s\-]{2,128}$/.test(address);
		return reboolean;
	};

	$.fn.postvalidate = function(postcode){
		var reboolean = /^[1-9][0-9]{5}$/.test(postcode);
		return reboolean;
	};

	$.fn.emailvalidate = function(email){
		var reboolean = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(email);
		return reboolean;
	};

	$.fn.pwdvalidate = function(pw){
		var reboolean = /^[a-zA-Z0-9]{6,128}$/.test(pw);
		return reboolean;
	};

	$.fn.captvalidate = function(capt){
		var reboolean = /^[0-9]{6}$/.test(capt);
		return reboolean;
	};

	$.fn.captimgvalidate = function(captimg){
		var reboolean = /^[a-zA-Z0-9]{4}$/.test(captimg);
		return reboolean;
	};

	$.fn.idnumbervalidate = function(idnumber){
		var reboolean = /^[0-9xX]{18}$/.test(idnumber);
		return reboolean;
	};

	$.fn.captimg = function(){
		$("#captimg").attr("src",'./g/getKaptchaImage?' + Math.floor(Math.random()*100));
	};

	$.fn.stringvalidate = function(string){
		var reboolean = /^[a-zA-Z0-9\u4e00-\u9fa5\。\，\、\？\！\（\）\%\￥_.,\s-]{1,1000}$/.test(string);
		return reboolean;
	};

	$.fn.bbstitlevalidate = function(bbstitle){
		var reboolean = /^[a-zA-Z0-9\u4e00-\u9fa5\。\，\、\？\！\（\）\%\￥_.\s-]{1,20}$/.test(bbstitle);
		return reboolean;
	};

	$.fn.bbsvalidate = function(bbscontent){
		var reboolean = /^[a-zA-Z0-9\u4e00-\u9fa5\。\，\、\？\！\（\）\%\￥_.\s-]{1,10000}$/.test(bbscontent);
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

	$.fn.cartget = function(){
		var cart = $.cookie("cartlist");
		var jsoncart = null;
		if (cart) {
			jsoncart = JSON.parse(cart);
		}
		return jsoncart;
	};

	$.fn.cartpost = function(itemid, specid, quantity){
		var cart = $.cookie("cartlist");
		var jsoncart = null;
		var success = 0;
		if (cart) {
			jsoncart = JSON.parse($.cookie("cartlist"));
			for (var i = 0; i < jsoncart.length; i++) {
				if (jsoncart[i].lightItem.id == itemid){
					if (jsoncart[i].saleItemSpec.id == specid) {
						jsoncart[i].quantity += quantity;
						success = 1;
					}
				}
			}
			if (success == 0) {
				jsoncart.push({
						"lightItem":{"id":itemid},
						"saleItemSpec":{"id":specid},
						"quantity":quantity
					});
			}
		}else{
			jsoncart = [];
			jsoncart.push({
					"lightItem":{"id":itemid},
					"saleItemSpec":{"id":specid},
					"quantity":quantity
				});
		}
		$.cookie("cartlist",JSON.stringify(jsoncart));
	};

	$.fn.cartput = function(itemid, specid, quantity){
		var cart = $.cookie("cartlist");
		var jsoncart = null;
		if (cart) {
			jsoncart = JSON.parse(cart);

			for (var i = 0; i < jsoncart.length; i++) {
				if (jsoncart[i].lightItem.id == itemid){
					if (jsoncart[i].saleItemSpec.id == specid) {
						jsoncart[i].quantity = quantity;
					}
				}
			}

			$.cookie("cartlist",JSON.stringify(jsoncart));
		}
	};

	$.fn.cartdelete = function(itemid, specid){
		var cart = $.cookie("cartlist");
		var jsoncart = null;
		if (cart) {
			jsoncart = JSON.parse($.cookie("cartlist"));
			var newcart = [];
			for (var i = 0; i < jsoncart.length; i++) {
				if (jsoncart[i].lightItem.id != itemid){
					if (jsoncart[i].saleItemSpec.id != specid) {
						newcart.push(jsoncart[i]);
					}
				}
			};
			$.cookie("cartlist",JSON.stringify(newcart));
		}
	};

	$.fn.ddcartget = function(url_true){
		var url;
		if(url_true) {
			url = url_true;
		} else {
			url = "../sale/cart";
		}
		$.ajax({
			type:"GET",
			data:"",
			async:false,
			cache:false,
			url: url,
			error:function(){
				//window.location.href="error.html";
			},
			success:function(data){
				if (data.success==0) {
					$(".hcl .hcartlist .list table").empty();
					if (data.length) {
						var tp = 0;
						for (var i = 0; i < data.length; i++) {
							$(".hcl .hcartlist .list table").append('<tr data-quantt="'+data.datas[i].quantity+'" data-cartid="'+data.datas[i].id+'" data-itemid="'+data.datas[i].lightItem.id+'" data-specid="'+data.datas[i].saleItemSpec.id+'"><td class="td1"><a href="./item/'+data.datas[i].lightItem.id+'.html"><div class="img"><img src="'+data.datas[i].lightItem.avatar+'" alt="img"></div><p><span class="n">'+data.datas[i].lightItem.brand+data.datas[i].lightItem.name+'</span><br><span class="s">'+data.datas[i].saleItemSpec.specName+'</span></p></td><td class="td2">'+data.datas[i].quantity+'</td><td class="td3">&yen;<span>'+(parseFloat(data.datas[i].saleItemSpec.discountPrice)*parseFloat(data.datas[i].quantity))+'</span></td><td class="td4"><span class="icon-cross"></span></td></tr>');
							tp += parseFloat(data.datas[i].saleItemSpec.discountPrice)*parseFloat(data.datas[i].quantity);
						}
						$(".secart .cartn").text(data.length);
						$(".hcl .hcartlist .b .ttn span").text(data.length);
						$(".hcl .hcartlist .b .ttp span").html("&yen;"+tp);
					}else{
						$(".secart .cartn").text(0);
						$(".hcl .hcartlist .b .ttn span").text(0);
						$(".hcl .hcartlist .b .ttp span").html("&yen;0");
					}
				}
			}
		});
	};

	$.fn.ddcartlistt = function(){
		var item = $(".hcl .hcartlist .list table tr");
		var itemqtt = item.length;
		var itemtp = 0;
		if (itemqtt) {
			for (var i = 0; i < itemqtt; i++) {
				itemtp += parseFloat($(item[i]).children(".td3").children("span").text());
			}
		}else{
			itemqtt = 0;
		}
		$(".secart .cartn").text(itemqtt);
		console.log(itemqtt);
		console.log($(".secart .cartn").text());
		$(".hcl .hcartlist .b .ttn span").text(itemqtt);
		$(".hcl .hcartlist .b .ttp span").html("&yen;"+itemtp);
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
			var tmp = '/\\'+par+'=[^&]*/';
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

	$.fn.emailredirect = function(emailsuffix){
		var mailserver = "";
		if (emailsuffix) {
			if (emailsuffix =='163.com') {
		        mailserver = 'mail.163.com';
		    } else if (emailsuffix =='vip.163.com') {
		        mailserver = 'vip.163.com';
		    } else if (emailsuffix =='126.com') {
		        mailserver = 'mail.126.com';
		    } else if (emailsuffix =='qq.com' || emailsuffix =='vip.qq.com' || emailsuffix =='foxmail.com') {
		        mailserver = 'mail.qq.com';
		    } else if (emailsuffix =='gmail.com') {
		        mailserver = 'mail.google.com';
		    } else if (emailsuffix =='sohu.com') {
		        mailserver = 'mail.sohu.com';
		    } else if (emailsuffix =='tom.com') {
		        mailserver = 'mail.tom.com';
		    } else if (emailsuffix =='vip.sina.com') {
		        mailserver = 'vip.sina.com';
		    } else if (emailsuffix =='sina.com.cn' || emailsuffix =='sina.com' || emailsuffix =='sina.cn') {
		        mailserver = 'mail.sina.com.cn';
		    } else if (emailsuffix =='tom.com') {
		        mailserver = 'mail.tom.com';
		    } else if (emailsuffix =='yahoo.com.cn' || emailsuffix =='yahoo.cn') {
		        mailserver = 'mail.cn.yahoo.com';
		    } else if (emailsuffix =='tom.com') {
		        mailserver = 'mail.tom.com';
		    } else if (emailsuffix =='yeah.net') {
		        mailserver = 'www.yeah.net';
		    } else if (emailsuffix =='21cn.com') {
		        mailserver = 'mail.21cn.com';
		    } else if (emailsuffix =='hotmail.com') {
		        mailserver = 'www.hotmail.com';
		    } else if (emailsuffix =='sogou.com') {
		        mailserver = 'mail.sogou.com';
		    } else if (emailsuffix =='188.com') {
		        mailserver = 'www.188.com';
		    } else if (emailsuffix =='139.com') {
		        mailserver = 'mail.10086.cn';
		    } else if (emailsuffix =='189.cn') {
		        mailserver = 'webmail30.189.cn';
		    } else if (emailsuffix =='wo.com.cn') {
		        mailserver = 'mail.wo.com.cn/smsmail';
		    } else if (emailsuffix =='outlook.com'){
		        mailserver = 'outlook.com';
		    } else {
		    	mailserver = '';
		    }
		}
		return mailserver;
	};
	
	$.fn.switch2PhoneOrPC = function(url){
		/*var url_array = url.split('/uhaou/');
		var final_url = url_array[1];
		//平台、设备和操作系统   
		var system ={  
			win : false,  
			mac : false,  
			xll : false  
		};  
		//检测平台   
		var p = navigator.platform;  
		//console.log(p);  
		system.win = p.indexOf("Win") == 0;
		system.mac = p.indexOf("Mac") == 0;
		system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);  
		//跳转语句   
		if(system.win || system.mac || system.xll) { //转向电脑端
			if(final_url.indexOf("m/") == 0) {
				final_url = final_url.split('m/')[1];
				window.location.href = url_array[0] + "/uhaou/" + final_url;
			}
		} else {  // 转向手机端
			if(final_url.indexOf("m/") == -1) {
				window.location.href = url_array[0] + "/uhaou/m/" + final_url;  
			}
		}*/
		
		var url_array = url.split('cn');
		var final_url = url_array[1];
		console.log(url_array);
		//平台、设备和操作系统   
		var system ={  
			win : false,  
			mac : false,  
			xll : false  
		};  
		//检测平台   
		var p = navigator.platform;  
		//console.log(p);  
		system.win = p.indexOf("Win") == 0;
		system.mac = p.indexOf("Mac") == 0;
		system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);  
		//跳转语句
		if(system.win || system.mac || system.xll) { //转向电脑端
			if(final_url.length == 0) {
				console.log(url_array[0] + "cn/m/index.html");
				window.location.href = url_array[0] + "cn/m/index.html";
			} else if(final_url.indexOf("/m/") == 0) {
				final_url = final_url.split('m/')[1];
				console.log(url_array[0] + "cn/" + final_url);
				window.location.href = url_array[0] + "cn/" + final_url;
			}
		} else {  // 转向手机端
			if(final_url.indexOf("/m/") == -1) {
				console.log(url_array[0] + "cn/m" + final_url);
				window.location.href = url_array[0] + "cn/m" + final_url;
			}
		}
	};
})(jQuery);


var loginData;
var cartlist = $().cartget();
var accountid;

$(document).ready(function(){
	$(".waypoint").waypoint(function(up){
		$(".hcl, .h2").removeClass("active");
	});

	$(".waypoint").waypoint(function(down){
		$(".hcl, .h2").addClass("active");
	});

	var pagetitle = $("head title").text();

	$("head title").html(pagetitle + "&nbsp;&ndash;&nbsp;有好油");

	$("body").prepend('<div class="customservice">'
				+'<div class="cs">'
					+'<div class="csh">'
						+'<h4>欢迎咨询</h4>'
						+'<p>AM9:00--PM6:00</p>'
					+'</div>'
					+'<table class="csb">'
						+'<tr>'
							+'<td>'
								+'<a href="http://wpa.qq.com/msgrd?v=3&uin=3235417592&site=qq&menu=yes" target="_blank"><span class="icon-call37"></span>客服1'
							+'</td>'
						+'</tr>'
					+'</table>'
					+'<div class="csf">'
						+'<input type="button" value="邮件咨询">'
					+'</div>'
				+'</div>'
				+'<div class="close">'
					+'&times;'
				+'</div>'
			+'</div>'
			+'<div class="csbar">'
				+'咨<br>询'
			+'</div>');

	if ($(".acctmenu")) {
		var acctmentcontent;
		acctmentcontent = '<ul>'
			+'<li name="nav1">'
				+'<div class="menut">'
					+'<div class="icon-arrow-down3"></div>'
					+'<div class="icon-arrow-right"></div>'
					+'<span>订单管理</span>'
				+'</div>'
				+'<ul>'
					+'<li data-redirect="nav11"><a href="c_orderlist.html">我的订单</a></li>'
					+'<li data-redirect="nav12"><a href="c_commentlist.html">我的评价</a></li>'
					+'<li data-redirect="nav13"><a href="c_refundlist.html">退货列表</a></li>'
				+'</ul>'
			+'</li>'
			+'<li name="nav2">'
				+'<div class="menut">'
					+'<div class="icon-arrow-down3"></div>'
					+'<div class="icon-arrow-right"></div>'
					+'<span>我的账户</span>'
				+'</div>'
				+'<div class="buld"></div>'
				+'<ul>'
					+'<li data-redirect="nav21"><a href="c_account.html">账户信息</a></li>'
					+'<li data-redirect="nav22"><a href="c_profile.html">个人信息</a></li>'
					+'<li data-redirect="nav23"><a href="c_address.html">收货地址</a></li>'
					+'<li data-redirect="nav24"><a href="c_security.html">安全中心</a></li>'
					+'<li>推广中心</li>'
				+'</ul>'
			+'</li>'
		+'</ul>';
		$(".acctmenu").empty().append(acctmentcontent);
		if ($(".acctmenu").attr("data-index")) {
			var index = $(".acctmenu").attr("data-index");
			if (index[3]) {
				$(".acctmenu li").addClass("active");
				$(".acctmenu li[name=nav"+index[3]+"]").removeClass("active").addClass("current");
				if (index[4]) {
					$(".acctmenu li[data-redirect="+index+"]").addClass("current");
				}
			}
		}
	}
	
	$(".acctmenu .menut").click(function(){
		var v = $(this).parents("li");
		if (v.hasClass("active")) {
			v.removeClass("active");
		}else{
			v.addClass("active");
		}
	});

	$(".secart").mouseenter(function(){
		$(".hcartlist").show();
	});

	$(".secart").mouseleave(function(){
		$(".hcartlist").hide();
	});
	
	$(".hcartlist").mouseenter(function(){
		$(this).show();
	});
	
	$(".hcartlist").mouseleave(function(){
		$(this).hide();
	});

	$("#carthtml").click(function(){
		window.location.href = "cart.html";
	});

	$(".csbar").click(function(){
		$(this).hide();
		$(".customservice").show();
	});

	$(".customservice .close").click(function(){
		$(".customservice").hide();
		$(".csbar").show();
	});

	$.ajax({
		type:"GET",
		data:JSON.stringify(cartlist),
		contentType: 'application/json',
		dataType: 'json',
		async:false,
		cache:false,
		url:"../g/loginStatus",
		error:function(){
			//window.location.href="error.html";
		},
		success:function(data){
			loginData = data;
			if (data.success == 0) {
				accountid = data.id;
				var username = '用户';
				if (data.userName) {
					username = data.userName;
				}
				var avatar = "img/avatar_preview_default.png";
				if (data.avatar) {
					avatar = data.avatar;
				}
				var loginstr = '<span>'
					+username+'</span><img src="'+avatar+'" alt="avatar">'
					+'<ul class="dd1">'
					+'<li><a href="../m/c_orderlist.html">我的账户</a></li>'
					+'<li id="logout">登出</li>'
					+'</ul>';
				$(".h1 .ul1 .pinfo").empty().append(loginstr);
				$(".dd2 .r").html('<a href="c_orderlist.html">我的订单</a>'
					+'<a href="c_account.html">'+username+'</a><span id="logout">登出</span>');
			}
			$().ddcartget();
		}
	});

	$(".hcl .hcartlist .list table .td4 .icon-cross").live("click",function(){
		var	cartdelete = {
				"id":$(this).parents("tr").attr("data-cartid"),
				"lightItem":{"id":$(this).parents("tr").attr("data-itemid")},
				"saleItemSpec":{"id":$(this).parents("tr").attr("data-specid")},
			};
		var item = $(this).parents("tr");
		
		$.ajax({
			type:"delete",
			data:JSON.stringify(cartdelete),
			contentType: 'application/json',
			dataType: 'json',
			async:false,
			cache:false,
			url:"../sale/cart",
			success:function(data){
				if (data.success == 0) {
					item.remove();
					$().cartdelete($(this).parents("tr").attr("data-itemid"),$(this).parents("tr").attr("data-specid"));
				}
			}
		});
		$().ddcartlistt();
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
					$(".h1 .ul1 .pinfo").empty().append('<a href="login.html">登录</a>|<a href="reg.html">注册</a>');
					$.cookie("cartlist","");
					accountid = "";
					$().ddcartget();
					if ($("body").hasClass("logoutredirect")) {
						window.location.href="index.html";
					}
				}
			}
		});
	});

	$.ajax({
		type: 'GET',
		url: '../s/info',
		success: function(data) {
			$('#site_phone').text(data.datas.phone);
			var address_array = data.datas.address.split('$');
			var address_html = '';
			for(var i = 0; i < address_array.length; i++) {
				if(i < (address_array.length - 1)) {
					address_html += address_array[i] + '<br>';
				} else {
					address_html += address_array[i];
				}
			}
			$('#site_address').html(address_html);
			$('#site_email').text(data.datas.email);
			$('#site_2dcode').attr('src', '../' + data.datas.twoCode);
			$('#site_record').html(data.datas.copyright + '<br>' + data.datas.record + '<br>' + 'Powered by <a href="http://www.nutin.me/">nutin.me</a>');
		}
	});

	if (!$("body").hasClass("itemlist")) {
		$(".sesearch input[name=sebtn], .sesearch .icon-magnifier13").click(function(){
			var keyword = $(this).parents(".sesearch").children("input[type=text]").val();
			if (keyword) {
				window.location.href = "../item_list.html?val=" + encodeURI(encodeURI(keyword));
			}
		});
	}

	$(window).keydown(function(event){
		if (event.keyCode == 13) {
			if ($(".sesearch input[type=text]:focus").length) {
				var keyword = $(".sesearch input[type=text]:focus").val();
				if (keyword) {
					window.location.href = "../item_list.html?val=" + encodeURI(encodeURI(keyword));
				}
			}
		}
	});
});