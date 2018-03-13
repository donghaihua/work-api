var url = window.location.href;
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