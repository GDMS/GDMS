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
	<p>&nbsp;</p>
	<h2>成绩输入</h2>
	<p>&nbsp;</p>

	<table class="table table-bordered">
		<thead>
			<tr>
				<th>学号</th>
				<th>姓名</th>
				<th>课题名称</th>
				<th>成绩</th>
				<th>评阅成绩</th>
				<th>答辩成绩</th>
				<th>输入成绩</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${students}" var="student" varStatus="st">
				<tr>
					<td>${student.loginName}</td>
					<td>${student.name}</td>
					<td>${studentsThesises[st.index].name}</td>
					<td>${student.zd1grade}</td>
					<td><a href="${ctx}/teacher/scoreInput/${student.loginName}" class="btn btn-mini">输入成绩</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- Javascript -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#li-teacher_score').addClass('active');
		});
	</script>
</body>
</html>