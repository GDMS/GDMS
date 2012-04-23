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
			<h2 align="center">评语输入</h2>
			<p>&nbsp</p>

			<c:if test="${success!=null&&success}">
				<div class="alert alert-block alert-success">
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

			<form:form id="student" modelAttribute="student" method="post" cssClass="form-horizontal">
				<!-- ID -->
				<form:hidden path="id" />

				<div class="control-group">
					<label class="control-label">学号:</label>
					<div class="controls">
						<span type="text" id="loginName" name="loginName" class="input-xlarge uneditable-input">${student.loginName}</span>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">姓名:</label>
					<div class="controls">
						<form:input path="name" cssClass="input-xlarge" />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">课题名称:</label>
					<div class="controls">
						<span type="text" id="thesisName" name="thesisName" class="input-xlarge uneditable-input">${thesisName}</span>
					</div>
				</div>

				<div id="zhidao">
					<div class="control-group">
						<label class="control-label">指导评语:</label>
						<div class="controls">
							<form:textarea path="zdpingyu" rows="4" cssClass="input-xlarge" />
						</div>
					</div>
				</div>

				<div id="pingyue">
					<div class="control-group">
						<label class="control-label">评阅评语:</label>
						<div class="controls">
							<form:textarea path="pypingyu" rows="4" cssClass="input-xlarge" />
						</div>
					</div>
				</div>

				<div id="dabian">
					<div class="control-group">
						<label class="control-label">答辩提问1:</label>
						<div class="controls">
							<form:textarea path="question1" rows="4" cssClass="input-xlarge" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">回答简况1:</label>
						<div class="controls">
							<form:textarea path="answer1" rows="4" cssClass="input-xlarge" />
						</div>
					</div>

					<div class="control-group">
						<label class="control-label">答辩提问2:</label>
						<div class="controls">
							<form:textarea path="question2" rows="4" cssClass="input-xlarge" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">回答简况2:</label>
						<div class="controls">
							<form:textarea path="answer2" rows="4" cssClass="input-xlarge" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">答辩提问3:</label>
						<div class="controls">
							<form:textarea path="question3" rows="4" cssClass="input-xlarge" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">回答简况3:</label>
						<div class="controls">
							<form:textarea path="answer3" rows="4" cssClass="input-xlarge" />
						</div>
					</div>

					<div class="control-group">
						<label class="control-label">答辩评语:</label>
						<div class="controls">
							<form:textarea path="dbpingyu" rows="4" cssClass="input-xlarge" />
						</div>
					</div>
				</div>

				<div class="form-actions">
					<input type="submit" class="btn btn-primary" value="修改" />&nbsp;<input type="reset" class="btn" value="取消" />&nbsp;<a
						href="${ctx}/teacher/scoreInput/redirect/${type}" class="btn btn-inverse">返回</a>
				</div>

			</form:form>
		</div>
	</div>


	<div style="visibility: hidden;">
		<span id="type">${type}</span>
	</div>

	<!-- Javascript -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#li-teacher_score').addClass('active');

			$('#zhidao').hide();
			$('#pingyue').hide();
			$('#dabian').hide();

			var type = $('#type').text();
			$('#' + type).show();
		});
	</script>
</body>
</html>