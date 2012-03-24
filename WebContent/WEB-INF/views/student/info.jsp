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
			<p>&nbsp</p>
			<h2 align="center">个人信息</h2>
			<p>&nbsp</p>

			<c:if test="${success!=null&&success}">
				<div class="alert alert-block alert-info">
					<a class="close" data-dismiss="alert" href="#">×</a>
					<h4 class="alert-heading">修改成功</h4>
				</div>
			</c:if>
			<c:if test="${success!=null&&!success}">
				<div class="alert alert-block alert-error">
					<a class="close" data-dismiss="alert" href="#">×</a>
					<h4 class="alert-heading">修改失败</h4>
				</div>
			</c:if>

			<form:form id="info" modelAttribute="student" method="post" cssClass="form-horizontal">
				<div class="control-group">
					<label class="control-label">学号:</label>
					<div class="controls">
						<span type="text" id="loginName" name="loginName" class="input-xlarge uneditable-input">${student.loginName}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">密码:</label>
					<div class="controls">
						<input type="password" id="password" name="password" class="input-xlarge" />
						<p class="help-block">不修改，请留空</p>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">姓名:</label>
					<div class="controls">
						<input type="text" id="name" name="name" value="${student.name}" class="input-xlarge" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">性别:</label>
					<div class="controls">
						<input type="text" id="gender" name="gender" value="${student.gender}" class="input-xlarge" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">电话:</label>
					<div class="controls">
						<input type="text" id="phone" name="phone" value="${student.phone}" class="input-xlarge" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">邮箱:</label>
					<div class="controls">
						<input type="text" id="email" name="email" value="${student.email}" class="input-xlarge" />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">专业班级:</label>
					<div class="controls"></div>
				</div>

				<div class="form-actions">
					<input type="submit" class="btn btn-primary" value="修改" /> <input type="reset" class="btn" value="取消" />
				</div>

			</form:form>
		</div>
	</div>

	<!-- Javascript -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#li-student_info').addClass('active');
		});
	</script>
</body>
</html>