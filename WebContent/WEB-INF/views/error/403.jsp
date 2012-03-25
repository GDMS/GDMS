<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>403 - 用户权限不足</title>
</head>

<body>
	<div class="row">
		<div class="span6 offset3">
			<div class="alert alert-block alert-warn">
				<a class="close" data-dismiss="alert" href="#">×</a>
				<h3 class="alert-heading">用户权限不足.</h3>
				<p>管理员可能关闭了该项操作的权限.</p>
				<p>
					<a href="javascript:history.back()">点此返回上一页</a><br />
					<a href="${ctx}/">点此返回首页</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>