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
	<p>&nbsp;</p>
	<h3>系统属性修改</h3>

	<table class="table table-bordered">
		<thead>
			<tr>
				<th style="vertical-align: middle;">ID</th>
				<th style="vertical-align: middle;">字段说明</th>
				<th style="vertical-align: middle;">数值</th>
				<th style="vertical-align: middle;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${sysProperties}" var="sysProperty" varStatus="st">
				<form name="sysProperty" action="${ctx}/admin/sysProperty/updateSysProperty" method="post">
					<tr>
						<td><input type="text" class="input-small" readonly="readonly" name="id" value="${sysProperty.id}" /></td>
						<td><span class="input-xxlarge uneditable-input">${sysProperty.description}</span></td>
						<td><input type="text" class="input-small" name="propVal" value="${sysProperty.propVal}" /></td>
						<td><input type="submit" class="btn btn-primary" value="更新" /></td>
					</tr>
				</form>
			</c:forEach>
		</tbody>
	</table>

	<p>&nbsp;</p>
	<h3>系统权限控制</h3>

	<table class="table table-bordered">
		<thead>
			<tr>
				<th style="vertical-align: middle;">ID</th>
				<th style="vertical-align: middle;">字段说明</th>
				<th style="vertical-align: middle;">数值</th>
				<th style="vertical-align: middle;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${sysPermissions}" var="sysPermission" varStatus="st">
				<form name="sysPermission" action="${ctx}/admin/sysProperty/updateSysPermission" method="post">
					<tr>
						<td><input type="text" class="input-small" readonly="readonly" name="id" value="${sysPermission.id}" /></td>
						<td><span class="input-xxlarge uneditable-input">${sysPermission.description}</span></td>
						<td><span class="select_value" style="display: none;">${sysPermission.propVal}</span><select name="propVal"
							class="input-small"><option value="true">是</option>
								<option value="false">否</option></select></td>
						<td><input type="submit" class="btn btn-primary" value="更新" /></td>
					</tr>
				</form>
			</c:forEach>
		</tbody>
	</table>

	<!-- Javascript -->
	<script type="text/javascript">
	$(document).ready(function () {
		$('#li-admin_system').addClass('active');
		$('.select_value').each(function () {
			var value = $(this).text();
			$(this).next().children('option[value=' + value + ']').attr('selected', 'selected');
		});
	});
	</script>
</body>
</html>