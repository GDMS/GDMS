<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Sitemesh标题 -->
<title>GDMS: <sitemesh:title /></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<!--
<link href="${ctx}/static/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${ctx}/static/bootstrap/docs.css" rel="stylesheet">

<script type="text/javascript" src="${ctx}/static/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.js"></script>
-->
<link href="${ctx}/static/core.css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/static/core.js"></script>

<!-- Sitemesh头部 -->
<sitemesh:head />
</head>

<body>
	<div class="container">
		<div id="header" class="jumbotron masthead" style="margin-top: 20px; margin-bottom: 10px;">
			<h1>毕业设计管理系统</h1>
			<p>Graduate Design Manage System</p>

			<ul id="menu" class="nav nav-pills">
				<li id="li-index"><a href="${ctx}/">首页</a></li>

				<!-- 主页链接 -->
				<shiro:hasRole name="ROLE_STUDENT">
					<li id="li-student_index"><a href="${ctx}/student/index">学生主页</a></li>
				</shiro:hasRole>
				<shiro:hasRole name="ROLE_TEACHER">
					<li id="li-teacher_index"><a href="${ctx}/teacher/index">教师主页</a></li>
				</shiro:hasRole>
				<shiro:hasRole name="ROLE_ADMIN">
					<li id="li-admin_index"><a href="${ctx}/admin/index">管理员主页</a></li>
				</shiro:hasRole>
				<!-- /主页链接 -->

				<!-- 学生菜单 -->
				<shiro:hasAnyRoles name="ROLE_STUDENT">
					<li id="li-student_info"><a href="${ctx}/student/info">个人信息</a></li>

					<li id="li-student_project" class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">选课模块<b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="${ctx}/student/projectChoose">选择课题</a></li>
							<li><a href="${ctx}/student/projectManage">管理预选课题</a></li>
							<li><a href="${ctx}/student/projectResult">查看课题分配结果</a></li>
						</ul></li>

					<li id="li-student_file" class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">文件提交<b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="${ctx}/student/openUp">开题报告提交</a></li>
							<li><a href="${ctx}/student/assignmentUp">任务书提交</a></li>
							<li><a href="${ctx}/student/translateUp">翻译资料提交</a></li>
							<li><a href="${ctx}/student/paperUp">论文提交</a></li>
						</ul></li>

					<li id="li-student_score" class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">成绩管理<b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="${ctx}/student/midternCheck">中期检查</a></li>
							<li><a href="${ctx}/student/replyInput">答辩信息输入</a></li>
							<li><a href="${ctx}/student/finalScore">查看最后成绩</a></li>
						</ul></li>

				</shiro:hasAnyRoles>
				<!-- /学生菜单 -->

				<!-- 教师菜单 -->
				<shiro:hasRole name="ROLE_TEACHER">
					<li id="li-teacher_info"><a href="${ctx}/teacher/info">个人信息</a></li>
					<li id="li-teacher_project" class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">课题管理<b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="${ctx}/teacher/projectAdd">注册新的课题</a></li>
							<li><a href="${ctx}/teacher/projectManage">管理您的课题信息</a></li>
							<li><a href="${ctx}/teacher/projectAssign">管理您的课题分配信息</a></li>
							<li><a href="${ctx}/teacher/projectPreview">查看学生预选情况</a></li>
							<li><a href="${ctx}/teacher/projectReassign">为学生重新分配课题</a></li>
							<li><a href="${ctx}/teacher/projectChangeName">为学生更改课题名称</a></li>
						</ul></li>

					<li id="li-teacher_file" class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">文件管理<b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="${ctx}/teacher/fileManage">文档管理</a></li>
							<li><a href="${ctx}/teacher/printManage">打印管理</a></li>
						</ul></li>

					<li id="li-teacher_score" class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">成绩管理<b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="${ctx}/teacher/midternCheck">中期检查</a></li>
							<li><a href="${ctx}/teacher/scoreInput">输入学生论文成绩和评语</a></li>
							<li><a href="${ctx}/teacher/scoreSplit">分数拆分</a></li>
						</ul></li>

				</shiro:hasRole>
				<!-- /教师菜单 -->

				<!-- 管理员菜单 -->
				<shiro:hasRole name="ROLE_ADMIN">
					<li id="li-admin_info"><a href="${ctx}/admin/info">个人信息</a></li>
					<li><a href="${ctx}/admin/newsRelease">信息发布</a></li>
					<li><a href="${ctx}/admin/teacherReg">注册新教师</a></li>
					<li><a href="${ctx}/admin/majorReg">新增专业班级</a></li>
					<li><a href="${ctx}/admin/pyManage">管理评语</a></li>
					<li><a href="${ctx}/admin/dbOperate">数据库操作</a></li>
					<li><a href="${ctx}/admin/lwManage">论文管理</a></li>
					<li><a href="${ctx}/admin/viewSelect">查看视图</a></li>
					<li><a href="${ctx}/admin/studentInfo">管理学生信息</a></li>
					<li><a href="${ctx}/admin/teacherInfo">管理教师信息</a></li>
					<li><a href="${ctx}/admin/subInfoManage">管理课题信息</a></li>
					<li><a href="${ctx}/admin/studentPreviewInfo">管理学生预选信息</a></li>
					<li><a href="${ctx}/admin/subAssignInfo">管理学生课题分配信息</a></li>
					<li><a href="${ctx}/admin/subAssignedInfo">管理已分配信息</a></li>
					<li><a href="${ctx}/admin/assignAuto">自动分配</a></li>
					<li><a href="${ctx}/admin/printManage!adminuse">打印管理</a></li>
					<li><a href="${ctx}/admin/adminPropertyQuery">后台属性管理</a></li>
				</shiro:hasRole>
				<!-- /管理员菜单 -->

				<!-- 用户信息、登录注销 -->
				<shiro:guest>
					<li style="float: right;"><a href="${ctx}/login" class="btn" style="background-color: #E6E6E6;">登录</a></li>
					<li style="float: right;"><a>用户未登录!</a></li>
				</shiro:guest>
				<shiro:user>
					<li style="float: right;"><a href="${ctx}/logout" class="btn" style="background-color: #E6E6E6;">注销</a></li>
					<li style="float: right;"><a>当前用户: <shiro:principal property="name" />&nbsp(<shiro:principal />)&nbsp
					</a></li>
				</shiro:user>
				<!-- /用户信息、登录注销 -->
			</ul>
		</div>

		<hr class="soften" style="margin-top: -10px; margin-bottom: 0px;" />

		<div class="row">
			<div class="span12">
				<!-- Sitemesh正文 -->
				<sitemesh:body />
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