<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
</head>
<body>
	<c:if test="${success!=null&&success}">
		<div class="alert alert-block alert-success">
			<a class="close" data-dismiss="alert" href="#">×</a>
			<h4 class="alert-heading">操作成功</h4>
		</div>
	</c:if>
	<c:if test="${success!=null&&!success}">
		<div class="alert alert-block alert-error">
			<a class="close" data-dismiss="alert" href="#">×</a>
			<h4 class="alert-heading">操作失败</h4>
		</div>
	</c:if>

	<h3 align="center">学生最终课题分配结果</h3>
	<table id="studentTable" class="table table-bordered">
		<thead>
			<tr>
				<td>课题名称</td>
				<td>指导老师</td>
				<td>题目类型</td>
				<td>题目性质</td>
				<td>承担方式</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${thesis.name}</td>
				<td>${thesis.teacher.name}</td>
				<td>${thesis.type}</td>
				<td>${thesis.property}</td>
				<td>${thesis.mode}</td>
			</tr>
		</tbody>
	</table>

	<!-- Javascript -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#li-student_thesis').addClass('active');
		});
	</script>
</body>
</html>