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
	<h2>查看学生上传文件</h2>
	<p>&nbsp;</p>

	<table class="table table-bordered">
		<thead>
			<tr>
				<th style="vertical-align: middle;">学号</th>
				<th style="vertical-align: middle;">姓名</th>
				<th style="vertical-align: middle;">课题名称</th>
				<th style="vertical-align: middle;">开题报告</th>
				<th style="vertical-align: middle;">任务书</th>
				<th style="vertical-align: middle;">翻译</th>
				<th style="vertical-align: middle;">论文</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${students}" var="student" varStatus="st">
				<tr>
					<td>${student.loginName}</td>
					<td>${student.name}</td>
					<td>${studentsThesises[st.index].name}</td>
					<td><a href="${ctx}/file/ktup/${student.ktup}">${student.ktup}</a></td>
					<td><a href="${ctx}/file/ktup/${student.rwsup}">${student.rwsup}</a></td>
					<td><a href="${ctx}/file/ktup/${student.transup}">${student.transup}</a></td>
					<td><a href="${ctx}/file/ktup/${student.thesisup}">${student.thesisup}</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- Javascript -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#li-teacher_file').addClass('active');
		});
	</script>
</body>
</html>