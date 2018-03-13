/**
 * this is a jquery plugin that can rebuild html select elements:
 * make select more beautifull and more convenient
 * author:i@stou.info
 * blog:www.stou.info
 * date:2011-09-29
 */
(function($) {
	$.diyselect = function(){
		return this;
	}
	$.extend($.diyselect, {
		version:function(){
			return "DIYsElEct 2.1";
		},
		author:function(){
			return "i@stou.info";
		},
		getObject:function(pattern){
			if ( typeof(pattern) == "object" ) return pattern;
			if ( typeof(this.objectCache) == "undefined" ) this.objectCache = [];
			if ( typeof(this.objectCache[pattern]) == "undefined" ) this.objectCache[pattern] = $(pattern);
			return this.objectCache[pattern];
		},
		init:function(){
			try {
				document.execCommand('BackgroundImageCache', false, true);
			} catch (e) {};
			_thisPath = (function (script, i, me) {
				for (i in script) {
					if (script[i].src && script[i].src.indexOf('diyselect') !== -1) me = script[i];
				};
				_thisScript = me || script[script.length - 1];
				me = _thisScript.src.replace(/\\/g, '/');
				return me.lastIndexOf('/') < 0 ? '.' : me.substring(0, me.lastIndexOf('/'));
			}(document.getElementsByTagName('script')));
		},
		getPushDivCache:function(name){
			if ( typeof(PushDivCache) == "undefined" ) PushDivCache = [];
			if ( typeof(PushDivCache[name]) !='undefined' ) return PushDivCache[name];
			if ( name == "all" ) return PushDivCache;
			return null;
		},
		setPushDivCache:function(name,value){
			if ( typeof(PushDivCache) == "undefined" ) PushDivCache = [];
			if ( value=='array' ) PushDivCache[name]=[];
			else PushDivCache[name].push(value);
		},
		add:function(name,value,html,id,obj){
			this.getObject("input[name='"+name+"']").val(value);
			this.getObject("input[name='"+name+"_show']").val(html);
			this.getObject("#diyselectbox"+id).toggle();
			this.getObject("#diyselectbox"+id).find('li a').removeClass('current');
			$(obj).addClass('current');
			
			var change = this.getObject("select[name='"+name+"__bak']").attr('onChange');
			if ( change!=null && value != '' ) {
				change = change.replace('this.value',value);
				eval(change);
			}
		}, 
		make:function(obj,i) {
			var n = this.getObject(obj).attr("name");
			var d = this.getObject(obj).attr("id");
			var o = this.getObject(obj);
			o.hide();
			o.attr('name',function(i,attr){return attr+"__bak"});
			o.attr('id',  function(i,attr){return attr+"__bak"});
			o.attr('i',   i);
			
			$(o).before("<input type='hidden' value='' id='"+d+"' name='"+n+"' /><input type='text' class='input' onfocus='this.select();' value='' onkeyup=\"$.diyselect.find('"+n+"',this);\" onblur=\"$.diyselect.getObject('input[name="+n+"]').val(this.value);\"  name='"+n+"_show' /><a onfocus='this.blur()' href='javascript:;' id='diyselect_a"+i+"' class='diyselectclass' onclick=\"$.diyselect.getObject('.diyselectpush').hide();$.diyselect.getObject('#diyselectbox"+i+"').toggle();$.diyselect.scroll("+i+");\"></a>");
			this.render(n,o);
			delete o;
		},
		render:function(name,o){
			var o = this.getObject(o);
			var s = '';
			var h = o.find("option");
			var i = o.attr('i');
			var v = o.val();
			
			this.setPushDivCache(name,"array");
			h.each(function(ii,obj){
				$.diyselect.setPushDivCache(name,$(obj).html());
				s += '<li><a ';
				if ( $(obj).attr('value') == v ) s +=' class=\'current\'';
				s += 'href="javascript:;"';
				s += ' onclick="$.diyselect.add(\''+name+'\',\''+$(obj).attr('value')+'\',';
				s += '\''+$(obj).html()+'\','+i+',this);">'+$(obj).html()+'</a></li>';
			});
			var w = this.getObject("input[name='"+name+"_show']").outerWidth()-2;
			if ( document.getElementById('diyselectbox'+i) ) {
				document.getElementById('diyselectbox'+i).innerHTML="<ul>"+s+"</ul>";
			}else{
				$(o).before("<div id='diyselectbox"+i+"' style='width:"+w+"px;' class='diyselectpush'><ul>"+s+"</ul></div>");
			}
			var t = o.find("option:selected");
			if ( t ) {
				this.getObject("input[name='"+name+"_show']").val(t.eq(0).html()==null?'':t.eq(0).html());
				this.getObject("input[name='"+name+"']").val(t.eq(0).attr("value")==null?'':t.eq(0).attr("value"));
			}else{
				this.getObject("input[name='"+name+"_show']").val(h.eq(0).html()==null?'':h.eq(0).html());
				this.getObject("input[name='"+name+"_show']").val(h.eq(0).attr('value')==null?'':h.eq(0).attr('value'));
			}
			delete o,t;
		},
		find:function(name,obj){
			if ( obj.value == "" ) return;
			if ( typeof(this.getPushDivCache(name)) == null ) return;
			if ( typeof(lastValue) != "undefined" ) {
				if ( lastValue == obj.value ) return;
			}
			for(var i=0;i<this.getPushDivCache(name).length;i++){
				lastValue = obj.value;
				var rg = new RegExp(obj.value);//模糊匹配
				if ( rg.test(this.getPushDivCache(name)[i]) ) {
					var t = $(obj).next('a').next('.diyselectpush');
					var v = this.getPushDivCache(name)[i]; 
					t.find('li a').removeClass('current');
					t.find("li a").each(function(ii,obj2){
						if ( $(obj2).html() == v ) {
							t.show();
							$(obj2).addClass('current');
							var ot = $(obj2).position().top;
							t.scrollTop(ot);
							return;
						}
					});
					break;
				}
			}
		},
		addListener:function(element,e,fn){
			if(element.addEventListener){
				element.addEventListener(e,fn,false);
			} else {
				element.attachEvent("on" + e,fn);
			}
		},
		addPushListen:function(){
			$.diyselect.addListener(document,"click",function(evt){
				try{
					var evt = window.event?window.event:evt,target=evt.srcElement||evt.target;
					if(target.className == "diyselectclass"){
						return;
					}else{
						$.diyselect.getObject('.diyselectpush').hide();
					}
				}catch(e){$.diyselect.getObject('.diyselectpush').hide();}
			});			
		},
		scroll:function(i){
			this.getObject('#diyselectbox'+i).scrollTop(this.getObject('#diyselectbox'+i).find('li .current').position().top);
		}
	});
	$.fn.diyselect = function(){
		$.diyselect.init();
		var bgUrl = _thisPath+"/diyselectbg.gif";
		var css = "<style>" +
			".diyselectclass{visibility:hidden;display:block!important;width:17px;height:4px;position:absolute!important;background:url('"+bgUrl+"') center center no-repeat!important;}.diyselectclass:hover{background:url('"+bgUrl+"') #f1f1f1 center center no-repeat!important;}.diyselectpush{position:absolute;background:#fff;height:auto;overflow-x:hidden;overflow-y:auto;border:1px solid #ccc;display:none;z-index:999;}.diyselectpush ul{margin:0;padding:0;list-style:none}.diyselectpush ul li{list-style:none;width:100%;overflow:hidden;height:23px;line-height:23px!important;margin-bottom:0!important}.diyselectpush ul li a{display:block;width:100%;height:100%;color:#000;text-decoration:none;padding-left:5px;padding-left:none!important;text-indent:0!important;font-size:12px;text-decoration:none!important;}.diyselectpush ul li a:hover,.diyselectpush ul li a.current{background:#3399FF;color:#fff;}"
		+ "</style>";
		$('body').append(css);
		this.each(function(i,obj){
			$.diyselect.make($(obj),i);
		});
		$.diyselect.addPushListen();
		setTimeout(function(){
			$("input[name$='_show']").each(function(i,obj){
				var h = $(obj).outerHeight();
				var j = $(obj).innerHeight();
				var w = $(obj).outerWidth();
				var x = $(obj).offset().left;
				var y = $(obj).offset().top;
				var z = $(obj).next("a");
				var $_= z.next(".diyselectpush");
				var $h= $_.innerHeight();
				var b = document.documentElement.clientHeight;
				y    += h;
				
				if ( $h>150 ) 
				//$_.css({height:"150px"});			
				//z.css({height:j+"px",left:(x+w-20)+"px",top:(y-j-1)+"px",visibility:"visible"});
				if ( y+150>b ) y = y-$h-h;
				if ( y<0 ) y = 0;
				//$_.css({left:x+"px",top:y+"px"});
				delete h,j,w,x,y,z,$_,$h,b;
			});
		},100);
	};
}(jQuery));
