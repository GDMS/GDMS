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
	<h2>成绩评定表打印</h2>

	<table id="table" class="table table-bordered">
		<thead>
			<tr>
				<th style="vertical-align: middle;">学号</th>
				<th style="vertical-align: middle;">姓名</th>
				<th style="vertical-align: middle;">课题名称</th>
				<th style="vertical-align: middle;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${students}" var="student" varStatus="st">
				<tr>
					<td>${student.loginName}</td>
					<td>${student.name}</td>
					<td>${studentsThesises[st.index].name}</td>
					<td><a href="${ctx}/admin/printManage/print/${student.loginName}" class="btn btn-mini">下载</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- Javascript -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#li-admin_system').addClass('active');
			$('#table').dataTable({
				"sDom" : "<'row'<'span5'l><'span5'f>r>t<'row'<'span5'i><'span5'p>>",
				"sPaginationType" : "bootstrap",
				"oLanguage" : {
					"sUrl" : "${ctx}/static/datatables-1.9.1/zh_CN.txt"
				}
			});
		});
	</script>
</body>
</html>