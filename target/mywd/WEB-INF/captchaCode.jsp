<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="./../js/jquery.js"></script>


<title>测试页面</title>
<script type="text/javascript">


$(function(){       
    $('#kaptchaImage').click(function () {//生成验证码
   	 $(this).hide().attr('src', './getKaptchaImage?' + Math.floor(Math.random()*100) ).fadeIn();
    });
}); 

function changeCode(event) {
	$('#kaptchaImage').hide().attr('src', './getKaptchaImage?' + Math.floor(Math.random()*100) ).fadeIn();
}
</script>
</head>
<body>
		
<div class="chknumber">
      <label>验证码:
      <input name="kaptcha" type="text" id="kaptcha" maxlength="4" class="chknumber_input" />             
      </label>
      <br />
      <img src="./getKaptchaImage" id="kaptchaImage"  style="margin-bottom: -3px"/>
      <a href="#" onclick="changeCode()">看不清?换一张</a>
</div>
</body>
</html>