<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户登录</title>
</head>

<body>
	<div class="row">
		<div class="span6 offset3">
			<h1>&nbsp</h1>
			<!-- DEFAULT_ERROR_KEY_ATTRIBUTE_NAME = "shiroLoginFailure" -->
			<c:if test="${pageContext.request.getAttribute('shiroLoginFailure')!=null}">
				<div class="alert alert-block alert-error">
					<a class="close" data-dismiss="alert" href="#">×</a>
					<c:choose>
						<c:when
							test="${pageContext.request.getAttribute('shiroLoginFailure').toString()=='org.apache.shiro.authc.UnknownAccountException'}">
							<h4 class="alert-heading">用户不存在!</h4>
						</c:when>
						<c:when
							test="${pageContext.request.getAttribute('shiroLoginFailure').toString()=='org.apache.shiro.authc.IncorrectCredentialsException'}">
							<h4 class="alert-heading">用户名、密码错误!</h4>
						</c:when>
						<c:otherwise>
							<h4 class="alert-heading">其他错误!</h4>
						</c:otherwise>
					</c:choose>
					<p class="alert-heading">登录失败，请重试!</p>
				</div>
			</c:if>

			<c:if test="${pageContext.request.getParameter('unauthorized')!=null}">
				<div class="alert alert-block">
					<a class="close" data-dismiss="alert" href="#">×</a>
					<h4 class="alert-heading">提示!</h4>
					<p>请使用正确账户进入.</p>
				</div>
			</c:if>

			<form:form id="loginForm" action="${ctx}/login" method="post" cssClass="form-horizontal">
				<div class="control-group">
					<label class="control-label">用户名:</label>
					<div class="controls">
						<input type="text" id="username" name="username" value="${username}" class="input-xlarge" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">密码:</label>
					<div class="controls">
						<input type="password" id="password" name="password" class="input-xlarge" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">类型:</label>
					<div class="controls">
						<select id="roleType" name="roleType">
							<option value="student">学生</option>
							<option value="teacher">教师</option>
							<option value="admin">管理员</option>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">自动登录:</label>
					<div class="controls">
						<label class="checkbox"> <input type="checkbox" id="rememberMe" name="rememberMe" />记住密码
						</label>
					</div>
				</div>
				<div class="form-actions">
					<input type="submit" class="btn btn-primary" value="登录" /> <input type="reset" class="btn" value="重置" />
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>
