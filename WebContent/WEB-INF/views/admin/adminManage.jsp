<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员管理</title>
</head>
<body>
	<h3>管理员管理</h3>

	<table class="table table-bordered">
		<thead>
			<tr>
				<th style="vertical-align: middle;">登录名</th>
				<th style="vertical-align: middle;">姓名</th>
				<th style="vertical-align: middle;">密码</th>
				<th style="vertical-align: middle;">启用</th>
				<th style="vertical-align: middle;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${admins}" var="admin" varStatus="st">
				<tr>
					<td>${admin.loginName}</td>
					<td>${admin.name}</td>
					<td><span style="display: none;">${admin.password}</span></td>
					<td>${admin.enable}</td>
					<td><a href="${ctx}/admin/adminManage/del/${admin.id}" class="btn btn-mini">删除</a>&nbsp;<a
						class="btn btn-mini">修改</a></td>
				</tr>
			</c:forEach>
			<form name="admin" action="${ctx}/admin/adminManage/add" method="post">
				<tr>
					<td><input type="text" class="input-medium" name="loginName" /></td>
					<td><input type="text" class="input-small" name="name" /></td>
					<td><input type="text" class="input-medium" name="password" /></td>
					<td><select name="enable" class="input-small">
							<option value="true">启用</option>
							<option value="false">禁用</option>
					</select></td>
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
			});
		});
	</script>
</body>
</html>