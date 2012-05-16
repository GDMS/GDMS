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
		<div class="span12">
			<p>&nbsp</p>
			<h2 align="center">中期检查</h2>
			<p>&nbsp</p>

			<table class="table table-bordered">
				<thead>
					<tr>
						<td>学号</td>
						<td>姓名</td>
						<td>工作进度</td>
						<td>质量</td>
						<td>工作态度</td>
						<td>出勤情况</td>
						<td>警告内容</td>
						<td>提出/修改警告</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${students}" var="student" varStatus="st">
						<tr>
							<td>${student.loginName}</td>
							<td>${student.name}</td>
							<td>${student.progress}</td>
							<td>${student.quality}</td>
							<td>${student.attitude}</td>
							<td>${student.duty}</td>
							<td>${student.warn}</td>
							<!-- IF -->
							<c:if test="${student.warn!=null&&!student.warn.isEmpty()}">
								<td><a href="${ctx}/teacher/midternCheck/warn/${student.loginName}" class="btn btn-mini">修改警告</a> <a
									href="${ctx}/teacher/midternCheck/warn/${student.loginName}/delete" class="btn btn-mini">删除警告</a></td>
							</c:if>
							<!-- ELSE -->
							<c:if test="${student.warn==null||student.warn.isEmpty()}">
								<td><a href="${ctx}/teacher/midternCheck/warn/${student.loginName}" class="btn btn-mini">提出警告</a></td>
							</c:if>
							<!-- END -->
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>

	<!-- Javascript -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#li-teacher_score').addClass('active');
		});
	</script>
</body>
</html>