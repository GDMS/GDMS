<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>GDMS: <sitemesh:write property='title' /></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<link href="${ctx}/static/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${ctx}/static/bootstrap/docs.css" rel="stylesheet">

<script type="text/javascript"
	src="${ctx}/static/jquery/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="${ctx}/static/bootstrap/js/bootstrap.js"></script>

<sitemesh:write property='head' />
</head>

<body>

	<div class="container">
		<div id="header" class="jumbotron masthead"
			style="margin-top: 20px; margin-bottom: 10px;">
			<h1>毕业设计管理系统</h1>
			<p>Graduate Design Manage System</p>
		</div>

		<hr class="soften" style="margin-top: 10px; margin-bottom: 10px;" />

		<div class="row">
			<div class="span2">
				<div class="well sidebar-nav">
					<ul class="nav nav-list">
						<li class="nav-header">菜单</li>
						<li class="active"><a href="#">Link</a></li>
						<li><a href="#">Link</a></li>
						<li><a href="#">Link</a></li>
						<li><a href="#">Link</a></li>
						<li class="nav-header">Sidebar</li>
						<li><a href="#">Link</a></li>
						<li><a href="#">Link</a></li>
						<li class="nav-header">Sidebar</li>
						<li><a href="#">Link</a></li>
						<li><a href="#">Link</a></li>
						<li><a href="#">Link</a></li>
					</ul>
				</div>
				<!--/.well -->
			</div>
			<!--/span2-->
			<div class="span9">
				<sitemesh:write property='body' />
			</div>
			<!--/span9-->

			<!-- span1-->
			<!--/span1-->
		</div>
		<!--/row-->

		<hr>

		<div id="footer" style="text-align: center;">
			<p>&copy; 东华大学计算机学院 2012</p>
			<p>
				联系人:石秀金 邮箱:<a href="mailto:sxj@dhu.edu.cn">sxj@dhu.edu.cn</a>
			</p>
		</div>

	</div>
	<!--/.container-->


</body>
</html>