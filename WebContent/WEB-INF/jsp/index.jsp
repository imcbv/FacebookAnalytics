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
						<li><a title="home" href="#home">Home</a></li>
						<li><a title="help" href="#help">Help</a></li>
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
	<!--******************** Index ********************-->

	<section id="home" class="single-page scrollblock">


	<div class="homeTitle"
		style="text-align: center; width: 100%; height: 100px;">
		<img src="resources/img/fb.png" alt="">
	</div>
	<div class="homeTitle"
		style="text-align: center; width: 100%; margin-bottom: 50px;">

		Facebook Analytics</div>
	<div class="container">

		<div class="row">
			<div style="float: left; width: 50%; text-align: center;">
				<img src="resources/img/home.jpg" alt="">
			</div>
			<div
				style="float: left; width: 50%; text-align: center; clear: right;"
				class="inside">
				<hgroup>
				<h2>Welcome to the Facebook Analytics application</h2>
				</hgroup>
				Sign in using the button below to get started!
				<div class="entry-content">

					<!--******************** Facebook form ********************-->

					<form action="connect/facebook" method="POST">
						<div class="mask2" style="padding-top: 50px">
							<input type="image" src="resources/img/signin.png">
						</div>
					</form>
				</div>
			</div>
			<!-- /.inside -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /.container --> </section>
	<hr>

	<!--******************** Help ********************-->

	<section id="help" class="single-page scrollblock">


	<div class="homeTitle"
		style="text-align: center; width: 100%; height: 100px;">
		<img src="resources/img/help.png" alt="">
	</div>
	<div class="homeTitle"
		style="text-align: center; width: 100%; margin-bottom: 50px;">

		Help</div>
	<div class="container">

		<div class="row">
			<div style="float: left; width: 50%; text-align: center;">
				<img src="resources/img/home.jpg" alt="">
			</div>
			<div
				style="float: left; width: 50%; text-align: center; clear: right;"
				class="inside">
				<hgroup>
				<h2>Metering your Facebook friends has never been so easy!</h2>
				</hgroup>

				<div class="entry-content">Facebook Analytics is an
					application that helps you in visualizing (a subset of) friends
					from Facebook on a graph and computing three different centrality
					measures: Degree centrality, Closeness centrality and Betweenness
					centrality.</div>
			</div>
			<!-- /.inside -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /.container --> </section>
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