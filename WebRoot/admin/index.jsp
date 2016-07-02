
<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
<head>
<title>Member Profile Widget Flat Responsive Widget Template :: w3layouts</title>
<!-- for-mobile-apps -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<meta name="keywords" content="Member Profile Widget Responsive, Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<!-- //for-mobile-apps -->
<link href='//fonts.googleapis.com/css?family=Josefin+Slab:400,100,100italic,300,300italic,400italic,600,600italic,700,700italic' rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Carter+One' rel='stylesheet' type='text/css'>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />

</head>
<body>
<div class="content">
	<h1>Member Profile Widget</h1>
	<div class="main">
			<div class="profile-left">
				<h2>Login</h2>
				<form action = "${pageContext.request.contextPath }/adminUser_login.action" method = "post">
					<div class="login">
						<input type="text" class="email-icon" value="Enter your Email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Enter your Email';}" required="" name = "userName">	
						<input type="password" class="key-icon"  value="password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'password';}" required="" name = "password">	
					</div>
					<input type="submit" value="Signin" >
				</form>
				<div class="login-left">
					<ul>
						<li><a class="fb" href="#"><i></i>Facebook</a></li>
						<li><a class="goog" href="#"><i></i>Twitter</a></li>
					</ul>
				</div>
			</div>
			<div class="profile-middle">
				<div class="profile-img">
					<div class="login-pic"><img src="images/pic2.jpg" alt=" " /></div>
					<h3>Victoria</h3>
					<p>Actores</p>
				</div>
				<div class="twitt-left">
					<h5>Followers</h5>
					<p>2k</p>
				</div>
				<div class="twitt-right">
					<h5>Following</h5>
					<p>3k</p>
				</div>
				<div class="clear"></div>
			</div>
			<div class="clear"> </div>
		
	</div>	
	<p class="footer">&copy; 2016 Member Profile Widget. All Rights Reserved | Design by <a href="http://w3layouts.com/"> W3layouts</a></p>
</div>

</body>
</html>
