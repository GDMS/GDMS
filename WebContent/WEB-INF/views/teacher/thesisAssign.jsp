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
		<div class="span10 offset1">
			<c:if test="${(success!=null&&success)||(param.success!=null&&param.success)}">
				<div class="alert alert-block alert-success">
					<a class="close" data-dismiss="alert" href="#">×</a>
					<h4 class="alert-heading">操作成功</h4>
				</div>
			</c:if>
			<c:if test="${(success!=null&&!success)||(param.success!=null&&!param.success)}">
				<div class="alert alert-block alert-warn">
					<a class="close" data-dismiss="alert" href="#">×</a>
					<h4 class="alert-heading">操作失败</h4>
				</div>
			</c:if>

			<p>&nbsp;</p>
			<h2 style="text-align: center;">查看课题分配情况</h2>

			<table class="table table-bordered">
				<thead>
					<tr>
						<th style="vertical-align: middle;">课题名称</th>
						<th style="vertical-align: middle;">学生名称</th>
						<th style="vertical-align: middle;" width="100px">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${thesises}" var="thesis" varStatus="st">
						<tr>
							<td>${thesis.name}</td>
							<td>${students[st.index].name}</td>
							<td><a href="${ctx}/teacher/thesisAssign/unassign/${thesis.id}" class="btn btn-primary btn-mini">取消分配</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- /span8 offset2 -->
	</div>
	<!-- /row -->

	<div class="row">
		<div class="span10 offset1">
			<p>&nbsp;</p>
			<h3 style="text-align: center;">查看未分配课题</h3>

			<table class="table table-bordered">
				<thead>
					<tr>
						<th style="vertical-align: middle;">课题名称</th>
						<th style="vertical-align: middle;" width="100px">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${unassignedThesises}" var="thesis" varStatus="st">
						<tr>
							<td>${thesis.name}</td>
							<td><c:if test="${!isOverMaxAssign}">
									<a href="${ctx}/teacher/thesisPreview" class="btn btn-primary btn-mini">分配此课题</a>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- /span8 offset2 -->
	</div>
	<!-- /row -->

	<!-- Javascript -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#li-teacher_thesis').addClass('active');
		});
	</script>
</body>
</html>