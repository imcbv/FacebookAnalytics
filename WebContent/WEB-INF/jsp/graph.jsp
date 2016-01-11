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
<!-- import script for the graph -->

<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-2.1.0.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/arbor.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/graphics.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/renderer.js" />"></script>
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
		Friends Graph
	</div>

	<div class="container">

		<div class="row">

			<div style="width: 100%; text-align: center; clear: right;" class="inside">
				<div class="entry-content" >
					<div style="text-align: center; margin-top: 10px;">
						<b>Drag</b> the nodes to rearrange them, <b>doubleclick</b> on a friend to get his/her social measures
					</div>
					<div style="margin-top: 20px; text-align: center;">
						<canvas style="background: #FFFFFF;" width="1000" height="600" id="viewport"></canvas>
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

	<script language="javascript" type="text/javascript">
		var sys = arbor.ParticleSystem({
			repulsion : 200,
			stiffness : 1000,
			friction : 1,
			gravity : false,
			fps : 55,
			dt : .02,
			precision : .6
		});
		sys.renderer = Renderer("#viewport");
		sys.stop();
	</script>

	<script language="javascript" type="text/javascript">
		var userName = "${user.getName()}";
		var userPath = "${user.getPicture('square')}";
		sys.addNode(userName, {
			mass : 3000,
			shape : 'dot',
			label : userName,
			alpha : 1,
			image : userPath,
			image_w : 60,
			image_h : 60
		});
	</script>

	<c:forEach var="x" items="${user.getSelectedFriends()}">
		<script language="javascript" type="text/javascript">
			var name = "${x.getName()}";
			var friendPath = "${x.getPicture()}";
			var friendId = "${x.getUid()}"
			sys.addNode(name, {
				mass : 50,
				shape : 'dot',
				label : name,
				alpha : 1,
				image : friendPath,
				image_w : 40,
				image_h : 40,
				link : friendId
			});
			sys.addEdge(userName, name, {
				'color' : '#444444'
			});
			sys.addEdge(name, userName, {
				'color' : '#444444'
			});
		</script>
	</c:forEach>

	<c:forEach var="x" items="${user.getSelectedFriends()}">
		<c:forEach var="y" items="${x.getSelectedCommonFriends(user.getSelectedFriends())}">
			<script language="javascript" type="text/javascript">
				var name1 = "${x.getName()}";
				var name2 = "${y.getName()}";
				sys.addEdge(name1, name2, {
					'color' : '#444444'
				});
			</script>
		</c:forEach>
	</c:forEach>


</body>
</html>