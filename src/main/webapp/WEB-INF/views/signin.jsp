<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册</title>
</head>
<body>
	<form>
		<input type="text" name="telephone"> <input type="password"
			name="password"> <input type="password" name="password2">
		<input type="text" name="captcha"> <input type="submit"
			value="提交"> <input type="button" value="重置">
	</form>
	<script src="js/zepto.js"></script>
	<script type="text/javascript">
		$(document).ready(function($){
			$("input[type=button]").on('click',function(){
				$("input").val(null);
			});
			$("input[type=submit]").on('click',function(){
				$.ajax({
					type:post,
					data:{
							telephone:$("input[name=telephone]"),
							password:$("input[name=password]"),
							captcha:$("input[name=captcha]")
						},
					url:'/account/post',
					success:function(data){
						alert(data.success);
					}
				})
			});
		})
	</script>
</body>
</html>