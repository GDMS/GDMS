<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
</head>
<body>
	<div class="row">
		<div class="span6 offset3">
			<p>&nbsp;</p>
			<!-- 成功消息 -->
			<c:if test="${param.successMessage!=null}">
				<div class="alert alert-block alert-success">
					<a class="close" data-dismiss="alert" href="#">×</a>
					<h4 class="alert-heading">成功！</h4>
					<p>${param.successMessage}</p>
				</div>
			</c:if>
			<!-- /成功消息 -->
			<!-- 提示消息 -->
			<c:if test="${param.infoMessage!=null}">
				<div class="alert alert-block alert-info">
					<a class="close" data-dismiss="alert" href="#">×</a>
					<h4 class="alert-heading">提示消息</h4>
					<p>${param.infoMessage}</p>
				</div>
			</c:if>
			<!-- /提示消息 -->
			<!-- 警告消息 -->
			<c:if test="${param.warnMessage!=null}">
				<div class="alert alert-block">
					<a class="close" data-dismiss="alert" href="#">×</a>
					<h4 class="alert-heading">警告消息</h4>
					<p>${param.warnMessage}</p>
				</div>
			</c:if>
			<!-- /警告消息 -->
			<!-- 错误消息 -->
			<c:if test="${param.errorMessage!=null}">
				<div class="alert alert-block alert-error">
					<a class="close" data-dismiss="alert" href="#">×</a>
					<h4 class="alert-heading">错误消息</h4>
					<p>${param.errorMessage}</p>
				</div>
			</c:if>
			<!-- /错误消息 -->
		</div>
	</div>
</body>
</html>