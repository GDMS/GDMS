<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师部门管理</title>
</head>
<body>
	<div class="row">
		<div class="span6 offset3">
			<h3 style="text-align: center;">教师部门管理</h3>

			<table class="table table-bordered">
				<thead>
					<tr>
						<th style="vertical-align: middle;">部门名称</th>
						<th style="vertical-align: middle;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${teacherDepts}" var="teacherDept" varStatus="st">
						<tr>
							<td>${teacherDept.name}</td>
							<td><a href="${ctx}/admin/teacherDeptManage/del/${teacherDept.id}" class="btn btn-mini">删除</a></td>
						</tr>
					</c:forEach>
					<form name="teacherDept" action="${ctx}/admin/teacherDeptManage/add" method="post">
						<tr>
							<td><input type="text" class="input-mini" name="name" /></td>
							<td><input type="submit" class="btn btn-primary btn-mini" value="添加" /></td>
						</tr>
					</form>
				</tbody>
			</table>

		</div>
	</div>

	<!-- Javascript -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#li-admin_people').addClass('active');
		});
	</script>
</body>
</html>