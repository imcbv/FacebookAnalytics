<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Facebook Analytics</title>

<!-- Styles -->
<link href="resources/css/bootstrap.css" rel="stylesheet">
<link href="resources/css/style.css" rel="stylesheet">
<link rel='stylesheet' id='prettyphoto-css'
	href="resources/css/prettyPhoto.css" type='text/css' media='all'>
<link href="resources/css/fontello.css" type="text/css" rel="stylesheet">
<link href="resources/css/FacebookAnalytics.css" rel="stylesheet">

<!-- Google Web fonts -->
<link href='http://fonts.googleapis.com/css?family=Quattrocento:400,700'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Patua+One'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>
<style>
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>
<link href="resources/css/bootstrap-responsive.css" rel="stylesheet">
<!-- Favicon -->
<link rel="shortcut icon" href="resources/img/favicon.ico">
<!-- JQuery -->
<script type="text/javascript" src="resources/js/jquery.js"></script>
<!-- Load ScrollTo -->
<script type="text/javascript"
	src="resources/js/jquery.scrollTo-1.4.2-min.js"></script>
<!-- Load LocalScroll -->
<script type="text/javascript"
	src="resources/js/jquery.localscroll-1.2.7-min.js"></script>
<!-- prettyPhoto Initialization -->
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$("a[rel^='prettyPhoto']").prettyPhoto();
	});
</script>
</head>
<body>



	<!--******************** NAVBAR ********************-->
	<div class="navbar-wrapper">
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container">
					<!-- Responsive Navbar Part 1: Button for triggering responsive navbar (not covered in tutorial). Include responsive CSS to utilize. -->
					<a class="btn btn-navbar" data-toggle="collapse"
						data-target=".nav-collapse"> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
					</a>
					<h1 class="brand">
						<a href="/FacebookAnalytics">Facebook Analytics</a>
					</h1>
					<!-- Responsive Navbar Part 2: Place all navbar contents you want collapsed withing .navbar-collapse.collapse. -->
					<nav class="pull-right nav-collapse collapse">
					<ul id="menu-main" class="nav">
						<li><a title="home" href="/FacebookAnalytics">Home</a></li>
					</ul>
					</nav>
				</div>
				<!-- /.container -->
			</div>
			<!-- /.navbar-inner -->
		</div>
		<!-- /.navbar -->
	</div>
	<!-- /.navbar-wrapper -->
	<div id="top"></div>
	<!--******************** User Page ********************-->

	<section id="home" class="single-page scrollblock">


	<div class="homeTitle" style="text-align: center; width: 100%;">
		<div class="circular" style="height: 100px;">
			<img src="${user.getFriend(friendId).getPic_big()}"
				style="width: 100px" alt="">
		</div>
	</div>
	<div class="homeTitle"
		style="text-align: center; width: 100%; margin-bottom: 50px;">

		${user.getFriend(friendId).getName()}</div>
	<div class="container">

		<div class="row">
			<div
				style="float: left; width: 50%; text-align: left; overflow: hidden;">
				<hgroup>
				<h2>User data</h2>
				</hgroup>
				Degree: ${user.getDegreeCentrality(friendId)} <br> Normalized
				Degree: ${user.getNormalizedDegreeCentrality(friendId)} <br>
				Betweenness: ${user.getBetweennessCentrality(friendId)} <br>
				Normalized Betweenness:
				${user.getNormalizedBetweennessCentrality(friendId)} <br>
				Closeness: ${user.getClosenessCentrality(friendId)} <br>
				Normalized Closeness:
				${user.getNormalizedClosenessCentrality(friendId)} <br>
			</div>
			<div style="float: left; width: 50%; text-align: left; clear: right;"
				class="inside">
				<hgroup>
				<h2>Start metering your friends</h2>
				</hgroup>
				Get you friend list with the button below
				<div class="entry-content">

					<!--******************** Get friends and Disconnect forms ********************-->

					<div class="mask2"
						style="width: 50%; text-align: center; float: left; clear: left; padding-top: 10px">
						<a href="/FacebookAnalytics/user/commonFriends/graph"> <img
							type="image" src="resources/img/back.png"></a>
					</div>

				</div>
				<!-- /.inside -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container -->
	</section>
	<hr>

	<div class="footer-wrapper">
		<div class="container">
			<footer> <small>&copy; 2013 Inbetwin Network. All
				rights reserved.</small> </footer>
		</div>
		<!-- ./container -->
	</div>



	<!-- Loading the javaScript at the end of the page -->
	<script type="text/javascript" src="resources/js/bootstrap.js"></script>
	<script type="text/javascript" src="resources/js/jquery.prettyPhoto.js"></script>
	<script type="text/javascript" src="resources/js/site.js"></script>

</body>
</html>