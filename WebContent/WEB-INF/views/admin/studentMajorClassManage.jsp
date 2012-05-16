<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生专业班级管理</title>
</head>
<body>
	<h3>学生专业班级管理</h3>

	<table class="span4 table table-bordered">
		<thead>
			<tr>
				<th style="vertical-align: middle;">编号</th>
				<th style="vertical-align: middle;">专业名</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${studentMajors}" var="studentMajor" varStatus="st1">
				<tr>
					<td>${studentMajor.id}</td>
					<td>${studentMajor.name}</td>
					<td><a href="${ctx}/admin/studentMajorManage/del/${studentMajor.id}" class="btn btn-mini">删除</a>&nbsp;<a
						class="btn btn-mini">修改</a></td>
				</tr>
			</c:forEach>
			<form name="teacherDept" action="${ctx}/admin/studentMajorManage/add" method="post">
				<tr>
					<td>&nbsp;</td>
					<td><input type="text" class="input-mini" name="name" /></td>
					<td><input type="submit" class="btn btn-primary btn-mini" value="添加" />&nbsp;<input type="submit"
						class="btn btn-primary btn-mini" value="更新" /></td>
				</tr>
			</form>
		</tbody>
	</table>

	<table class="span4 table table-bordered">
		<thead>
			<tr>
				<th style="vertical-align: middle;">编号</th>
				<th style="vertical-align: middle;">班级名</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${studentClasses}" var="studentClass" varStatus="st2">
				<tr>
					<td>${studentClass.id}</td>
					<td>${studentClass.name}</td>
					<td><a href="${ctx}/admin/studentClassManage/del/${studentClass.id}" class="btn btn-mini">删除</a>&nbsp;<a
						class="btn btn-mini">修改</a></td>
				</tr>
			</c:forEach>
			<form name="teacherDept" action="${ctx}/admin/studentClassManage/add" method="post">
				<tr>
					<td>&nbsp;</td>
					<td><input type="text" class="input-mini" name="name" /></td>
					<td><input type="submit" class="btn btn-primary btn-mini" value="添加" />&nbsp;<input type="submit"
						class="btn btn-primary btn-mini" value="更新" /></td>
				</tr>
			</form>
		</tbody>
	</table>

	<!-- Javascript -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#li-admin_people').addClass('active');
			//实现点击表格条目，将内容复制到最后一行
			$('tbody tr:not(:last)').click(function() {
				var num = $('thead tr th').size() - 1;//减去最后操作一列
				for ( var i = 0; i < num; i++) {
					var val = $(this).children(':nth(' + i + ')').text();
					$('tbody tr:last td:nth(' + i + ') input').val(val);
				}
			})
		});
	</script>
</body>
</html>