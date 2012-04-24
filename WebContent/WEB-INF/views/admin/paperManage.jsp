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
	<div class="row">
		<div class="span6 offset3">
			<h3 style="text-align: center;">管理员管理</h3>

			<table class="table table-bordered">
				<thead>
					<tr>
						<th colspan="2" style="vertical-align: middle;">文件名</th>
						<th style="vertical-align: middle;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${papers}" var="paper" varStatus="st">
						<tr>
							<form:url value="${ctx}/file/paper/${paper.filename}" var="url" htmlEncoding="true" />
							<td colspan="2"><a href="${ctx}/file/paper/${paper.filename}">${paper.description}</a></td>
							<td><a href="${ctx}/admin/paperManage/del/${paper.id}" class="btn btn-mini">删除</a></td>
						</tr>
					</c:forEach>
					<form action="${ctx}/admin/paperManage/add" method="post" enctype="multipart/form-data">
						<tr>
							<td><input type="text" class="input-medium" name="description" /></td>
							<td><input type="file" class="input-medium" name="uploadFile" /></td>
							<td><input type="submit" class="btn btn-primary btn-mini" value="添加" /></td>
						</tr>
					</form>
				</tbody>
			</table>
		</div>
		<!-- /span6 offset3 -->
	</div>
	<!-- /row -->

	<!-- Javascript -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#li-admin_material').addClass('active');
		});
	</script>
</body>
</html>