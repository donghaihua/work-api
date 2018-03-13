;(function($){
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
			url = "./sale/cart";
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
							$(".hcl .hcartlist .list table").append('<tr data-quantt="'+data.datas[i].quantity+'" data-cartid="'+data.datas[i].id+'" data-itemid="'+data.datas[i].lightItem.id+'" data-specid="'+data.datas[i].saleItemSpec.id+'"><td class="td1"><div class="img"><img src="'+data.datas[i].lightItem.avatar+'" alt="img"></div><p><span class="n">'+data.datas[i].lightItem.brand+data.datas[i].lightItem.name+'</span><br><span class="s">'+data.datas[i].saleItemSpec.specName+'</span></p></td><td class="td2">'+data.datas[i].quantity+'</td><td class="td3">&yen;<span>'+(parseFloat(data.datas[i].saleItemSpec.discountPrice)*parseFloat(data.datas[i].quantity))+'</span></td><td class="td4"><span class="icon-cross"></span></td></tr>');
							tp += parseFloat(data.datas[i].saleItemSpec.discountPrice)*parseFloat(data.datas[i].quantity);
						}
						$(".secart .cartn").text(data.length);
						$(".hcl .hcartlist .b .ttn span").text(data.length);
						$(".hcl .hcartlist .b .ttp span").html("&yen;"+tp);
					}else{
						$(".secart cartn").text(0);
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
})(jQuery);