<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
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

			<ul id="menu" class="nav nav-pills">
				<li class="active"><a href="${ctx}/">首页</a></li>

				<!-- 主页链接 -->
				<shiro:hasRole name="ROLE_STUDENT">
					<li><a href="${ctx}/student/index">学生主页</a></li>
				</shiro:hasRole>
				<shiro:hasRole name="ROLE_TEACHER">
					<li><a href="${ctx}/teacher/index">教师主页</a></li>
				</shiro:hasRole>
				<shiro:hasRole name="ROLE_ADMIN">
					<li><a href="${ctx}/admin/index">管理员主页</a></li>
				</shiro:hasRole>
				<!-- /主页链接 -->

				<shiro:hasAnyRoles name="ROLE_STUDENT">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">课程资料 <b class="caret"></b></a>
						<ul id="course" class="dropdown-menu">
							<li><a href="${basePath}public/CourseInfo.jsp#fun-nav">课程介绍</a></li>
							<li><a href="${basePath}public/Course.jsp?loc=0#fun-nav">课程课件</a></li>
							<li><a href="${basePath}public/Course.jsp?loc=1#fun-nav">历年试题</a></li>
							<li><a href="${basePath}public/Course.jsp?loc=2#fun-nav">相关文献</a></li>
						</ul></li>
				</shiro:hasAnyRoles>

				<shiro:hasAnyRoles name="ROLE_TEACHER">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">教师资料<b class="caret"></b>
					</a>
						<ul id="teacher" class="dropdown-menu">
							<li><a href="${basePath}public/ManagerInfo.jsp#fun-nav">课程负责人</a></li>
							<li><a href="${basePath}public/TeacherInfo.jsp#fun-nav">教师队伍</a></li>
							<li class="divider"></li>
							<li><a href="${basePath}teacher/index.jsp#fun-nav">登录</a></li>
						</ul></li>
				</shiro:hasAnyRoles>

				<shiro:hasAnyRoles name="ROLE_ADMIN">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">学习资料<b class="caret"></b>
					</a>
						<ul id="study" class="dropdown-menu">
							<li><a href="${basePath}student/Homework.jsp#fun-nav">作业公告</a></li>
							<li class="divider"></li>
						</ul></li>
					<li><a href="${basePath}public/About.jsp#fun-nav">关于</a></li>
				</shiro:hasAnyRoles>

				<!-- 用户信息、登录注销 -->
				<shiro:guest>
					<li style="float: right;"><a href="${ctx}/login"
						class="btn" style="background-color: #E6E6E6;">登录</a></li>
					<li style="float: right;"><a>用户未登录!</a></li>
				</shiro:guest>
				<shiro:user>
					<li style="float: right;"><a href="${ctx}/logout"
						class="btn" style="background-color: #E6E6E6;">注销</a></li>
					<li style="float: right;"><a>当前用户: <shiro:principal /></a></li>
				</shiro:user>
				<!-- /用户信息、登录注销 -->
			</ul>
		</div>


		<hr class="soften" style="margin-top: -10px; margin-bottom: 0px;" />

		<div class="row">
			<div class="span12">
				<sitemesh:write property='body' />
			</div>
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