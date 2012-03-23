<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>用户登录</title>
</head>

<body>
	<div class="row">
		<div class="span12">
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
								<input type="text" id="username" name="username" size="40" value="${username}" class="input-xlarge" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">密码:</label>
							<div class="controls">
								<input type="password" id="password" name="password" size="40" class="input-xlarge" />
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
							<button type="submit" class="btn btn-primary">登录</button>
							<button type="reset" class="btn">取消</button>
						</div>

					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
