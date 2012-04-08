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
			<h2 align="center">成绩输入</h2>

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

			<table class="table table-bordered">
				<tr>
					<td rowspan="2" style="vertical-align: middle;">评分标准</td>
					<td>优</td>
					<td>良</td>
					<td>中</td>
					<td>及格</td>
					<td>不及格</td>
				</tr>
				<tr>
					<td>85～100</td>
					<td>75～84.9</td>
					<td>67～74.9</td>
					<td>60～66.9</td>
					<td>＜60</td>
				</tr>
			</table>

			<form:form id="Zhidao" modelAttribute="student" method="post" cssClass="form-horizontal">
				<!-- ID -->
				<form:hidden path="id" />

				<div class="control-group">
					<label class="control-label">学生学号:</label>
					<div class="controls">
						<span type="text" id="loginName" name="loginName" class="input-xlarge uneditable-input">${student.loginName}</span>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">学生姓名:</label>
					<div class="controls">
						<span type="text" id="name" name="name" class="input-xlarge uneditable-input">${student.loginName}</span>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">毕业设计(论文)质量:</label>
					<div class="controls">
						<form:input path="zd1grade" cssClass="input-large" />
						&nbsp;<span class="help-inline"></span>
						<p class="help-block">百分制，折合系数: 0.15</p>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">绘图与文字表达技巧:</label>
					<div class="controls">
						<form:input path="zd2grade" cssClass="input-large" />
						&nbsp;<span class="help-inline"></span>
						<p class="help-block">百分制，折合系数: 0.05</p>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">独立工作能力:</label>
					<div class="controls">
						<form:input path="zd3grade" cssClass="input-large" />
						&nbsp;<span class="help-inline"></span>
						<p class="help-block">百分制，折合系数: 0.05</p>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">工作态度:</label>
					<div class="controls">
						<form:input path="zd4grade" cssClass="input-large" />
						&nbsp;<span class="help-inline"></span>
						<p class="help-block">百分制，折合系数: 0.05</p>
					</div>
				</div>

				<div class="form-actions">
					<input type="submit" class="btn btn-primary" value="修改" />&nbsp;<input type="reset" class="btn" value="取消" />
					&nbsp;<a href="${ctx}/teacher/scoreInput#zhidao" class="btn btn-inverse">返回</a>
				</div>

			</form:form>
		</div>
	</div>

	<!-- Javascript -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#li-teacher_score').addClass('active');
			//添加成绩区间显示，对于所有的
			function getDesc(score) {
				var desc = '';
				if (score >= 85 && score <= 100)
					desc = '优';
				else if (score >= 75 && score < 85)
					desc = '良';
				else if (score >= 67 && score < 75)
					desc = '中';
				else if (score >= 60 && score < 67)
					desc = '及格';
				else if (score >= 0 && score < 60)
					desc = '不及格';
				else
					desc = '输入错误';
				return desc;
			}
			$('input.input-large').keyup(function() {
				var score = $(this).val();
				var desc = getDesc(score);
				$(this).next().css('color', '#800').text(desc);
			});
		});
	</script>
</body>
</html>