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
			url: urlprefix_l + 'g/uniqueCheck',
			success:function(data){
				if(data.success == 0){
					returndata = 0; //未存在
				}else if (data.error == 102) {
					returndata = 1; //已存在
				}else if(data.error == 201){
					returndata = 1;
				}
			}
		});
		return returndata;
	};

	// TODO
	$.fn.vcaptimg = function(capt){
		var success;
		$.ajax({
			type:"post",
			url: urlprefix_l + "g/verifyCaptcha",
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
		var reboolean = /^[a-zA-Z0-9\u4e00-\u9fa5_#\.\,\s]{2,128}$/.test(address);
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

	// TODO
	$.fn.captimg = function(){
		$("#captimg").attr("src",urlprefix_l + 'g/getKaptchaImage?' + Math.floor(Math.random()*100));
	};

	$.fn.stringvalidate = function(string){
		var reboolean = /^[a-zA-Z0-9\u4e00-\u9fa5\。\，\、\？\！\（\）\%\￥_.\s-]{1,300}$/.test(string);
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

	$.fn.ddcartget = function(){
		$.ajax({
			type:"GET",
			data:"",
			async:false,
			cache:false,
			url: urlprefix_l + "sale/cart",
			error:function(){
				//window.location.href="error.html";
			},
			success:function(data){
				if (data.success==0) {
					//$(".hcl .hcartlist .list table").empty();
					if (data.length) {
						var tp = 0;
						for (var i = 0; i < data.length; i++) {
							tp += parseFloat(data.datas[i].saleItemSpec.discountPrice)*parseFloat(data.datas[i].quantity);
						}
						$(".cartnav .cartn").text(data.length);
						$(".hcl .hcartlist .b .ttn span").text(data.length);
						$(".hcl .hcartlist .b .ttp span").html("&yen;"+tp);
					}else{
						$(".cartnav cartn").text(0);
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
				itemtp += parseFloat(item[i].children(".td3").children("span").text());
			}
		}else{
			itemqtt = 0;
		}
		$(".hcl .hcartlist .b .ttn span").text(itemqtt);
		$(".hcl .hcartlist .b .ttp span").html("&yen;"+itemtp);
	};

	$.fn.mcart = function(){
		$.ajax({
			type:"GET",
			data:"",
			async:false,
			cache:false,
			url: urlprefix_l + "sale/cart",
			error:function(){
				//window.location.href="error.html";
			},
			success:function(data){
				if (data.success == 0) {
					if (data.length) {
						var tp = 0;
						for (var i = 0; i < data.length; i++) {
							tp += parseFloat(data.datas[i].saleItemSpec.discountPrice)*parseFloat(data.datas[i].quantity);
						}
						$(".cartnav .cartn").text(data.length);
						$(".cartnav .p").text(tp);
					}else{
						$(".cartnav cartn").text(0);
						$(".cartnav .p").text(0);
					}
				}
			}
		});
	};

	// $.fn.switch2PhoneOrPC = function(url){		
	// 	var url_array = url.split('cn');
	// 	var final_url = url_array[1];
	// 	console.log(url_array);
	// 	//平台、设备和操作系统   
	// 	var system ={  
	// 		win : false,  
	// 		mac : false,  
	// 		xll : false  
	// 	};  
	// 	//检测平台   
	// 	var p = navigator.platform;  
	// 	//console.log(p);  
	// 	system.win = p.indexOf("Win") == 0;
	// 	system.mac = p.indexOf("Mac") == 0;
	// 	system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);  
	// 	//跳转语句
	// 	if(system.win || system.mac || system.xll) { //转向电脑端
	// 		if(final_url.length == 0) {
	// 			console.log(url_array[0] + "cn/m/index.html");
	// 			window.location.href = url_array[0] + "cn/m/index.html";
	// 		} else if(final_url.indexOf("/m/") == 0) {
	// 			final_url = final_url.split('m/')[1];
	// 			console.log(url_array[0] + "cn/" + final_url);
	// 			window.location.href = url_array[0] + "cn/" + final_url;
	// 		}
	// 	} else {  // 转向手机端
	// 		if(final_url.indexOf("/m/") == -1) {
	// 			console.log(url_array[0] + "cn/m" + final_url);
	// 			window.location.href = url_array[0] + "cn/m" + final_url;
	// 		}
	// 	}
	// };
})(jQuery);

var loginData;
var cartlist = $().cartget();
var accountid;
//online
//var urlprefix = "../";
//var urlprefix_l = "../../";
//development
var urlprefix = "../";
var urlprefix_l = "../";
$(document).ready(function(){
	$("header").prepend('<nav class="box">'
			+'<div class="userinfo">'
				+'<span>ra****ps@yahoo.cn</span><button id="logout">登出</button>'
			+'</div>'
			+'<div class="search">'
				+'<div class="searchbtn">'
					+'<img src="./img/search.png" alt="search">'
				+'</div>'
				+'<input type="text" maxlength="20">'
			+'</div>'
			+'<ul>'
			+'</ul>'
			+'<div class="clear"></div>'
		+'</nav>');

	if (!$("header").hasClass("removecart")) {
		$("body").prepend('<div class="cartnav">'
			+'<div>'
				+'<img src="img/cart.png" alt="cart">'
				+'<div class="cartn">0</div>'
				+'<div class="tp">总价<span class="p"></span>元</div>'
				+'<a href="cart.html">'
					+'<button class="red">结算</button>'
				+'</a>'
			+'</div>'
		+'</div>');
	}

	if ($("header .stick").attr('data-back') === 'true') {
		$("header .stick").prepend('<div class="licon" isback="1"><img src="img/back.png" alt="menu"></div>' + '<div class="ricon">' + '<img src="img/profile.png" alt="profile">' + '</div>');
	} else {
		$("header .stick").prepend('<div class="licon"><img src="img/menu.png" alt="menu"></div>' + '<div class="ricon">' + '<img src="img/profile.png" alt="profile">' + '</div>');
	}

	/*$("header .stick").prepend('<div class="licon"><img src="img/menu.png" alt="menu"></div>'
		+'<div class="ricon">'
			+'<img src="img/profile.png" alt="profile">'
		+'</div>');*/

	// $("nav ul").empty().append('<li><a href="index.html">首页</a></li>'
	// 		+'<li><a href="culture.html">山茶文化</a></li>'
	// 		+'<li><a href="brand.html">品牌故事</a></li>'
	// 		+'<li><a href="../bbs/forum.html">论坛</a></li>'
	// 		+'<li><a href="item_list.html">商品系列</a></li>'
	// 		+'<li><a href="contact.html">联系我们</a></li>'
	// 		+'<li><a href="c_orderlist.html">我的订单</a></li>');

	$("nav ul").empty().append('<li><a href="index.html">首页</a></li>'
			+'<li><a href="culture.html">山茶文化</a></li>'
			+'<li><a href="brand.html">品牌故事</a></li>'
			+'<li><a href="../bbs/forum.html">论坛</a></li>'
			+'<li><a href="item_list.html">商品系列</a></li>'
			+'<li><a href="contact.html">联系我们</a></li>');

	$.ajax({
		type:"GET",
		data:JSON.stringify(cartlist),
		contentType: 'application/json',
		dataType: 'json',
		async:false,
		cache:false,
		url: urlprefix_l + "g/loginStatus",
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
				$("nav .userinfo").empty().append('<span>'+username+'</span><button id="logout">登出</button>').show();
				$(".ricon").removeClass("order");
			}
		}
	});
	
	$.ajax({
		type: 'GET',
		url: '../s/info',
		success: function(data) {
			console.log(data);
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
			$('#site_2dcode').attr('src', data.datas.twoCode);
			$('#site_record').html(data.datas.copyright + '<br>' + data.datas.record + '<br>' + 'Powered by <a href="http://www.nutin.me/">nutin.me</a>');

			$('title').append(" - " + data.datas.seoTitle);
			$('head').prepend('<meta name="Keywords" content="' + data.datas.seoKeywords + '"/>');
			$('head').prepend('<meta name="Description" content="' + data.datas.seoDescription + '"/>');
			//console.log($('title').html());

			$('#site_phone2').text(data.datas.phone);
			$('#site_address2').html(address_html);
			$('#site_email2').html("<a href='mailto:" + data.datas.email + "'>" + data.datas.email + "</a>");
			$('#site_2dcode2').attr('src', data.datas.twoCode);
		}
	});
	
	$("#logout").live('click',function(){
		$.ajax({
			type:"GET",
			data:"",
			dataType: 'json',
			async:false,
			cache:false,
			url: urlprefix_l + "passport/logout",
			success:function(data){
				if (data.success == 0) {
					$("nav .userinfo").empty().hide();
					$(".ricon").addClass("order");
					$.cookie("cartlist","");
					accountid = "";
					$().mcart();
					if ($("header").hasClass("logoutredirect")) {
						window.location.href="index.html";
					}
				}
			}
		});
	});

	$(".licon").click(function(){
		if($(this).attr('isback') == "1") {
			window.history.go(-1);
		} else {
			$("nav").toggle();
		}
	});

	// $(".ricon .visitor").click(function(){
	// 	window.location.href="login.html";
	// });

	$(".ricon").click(function(){
		window.location.href="c_orderlist.html";
	});

	$().mcart();
});