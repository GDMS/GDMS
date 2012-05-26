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
		<div class="span6 offset3">
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
		</div>
		<!-- /span6 offset3 -->
	</div>
	<!-- /row -->

	<div class="row">
		<div class="span10 offset1">
			<p>&nbsp;</p>
			<h2 style="text-align: center;">查看学生已分配情况</h2>

			<table id="table" class="table table-bordered">
				<thead>
					<tr>
						<th style="vertical-align: middle;">学生学号</th>
						<th style="vertical-align: middle;">学生名称</th>
						<th style="vertical-align: middle;">课题名称</th>
						<th style="vertical-align: middle;">教师名称</th>
						<th style="vertical-align: middle;" width="100px">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${students}" var="student" varStatus="st">
						<tr>
							<td>${student.loginName}</td>
							<td>${student.name}</td>
							<td>${thesises[st.index].name}</td>
							<td>${thesises[st.index].teacher.name}</td>
							<td><a href="${ctx}/admin/studentAssign/unassign/${thesises[st.index].id}" class="btn btn-primary btn-mini">取消分配</a></td>
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
			<h3 style="text-align: center;">查看学生未分配课题</h3>

			<table id="table2" class="table table-bordered">
				<thead>
					<tr>
						<th style="vertical-align: middle;">学生学号</th>
						<th style="vertical-align: middle;">学生名称</th>
						<th style="vertical-align: middle;" width="300px">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${unassignedStudents}" var="student" varStatus="st">
						<tr>
							<td>${student.loginName}</td>
							<td>${student.name}</td>
							<form action="${ctx}/admin/studentAssign/assign/${student.id}" method="POST">
								<td><select style="width: 250px" name="thesisId">
										<c:forEach items="${unassignedThesises}" var="thesis" varStatus="st">
											<option value="${thesis.id}">${thesis.name}</option>
										</c:forEach>
								</select>&nbsp;<input type="submit" class="btn btn-primary btn-mini" value="选择"></input></td>
							</form>
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
		$(document)
				.ready(
						function() {
							$('#li-admin_thesis').addClass('active');
							$('#table')
									.dataTable(
											{
												"sDom" : "<'row'<'span5'l><'span5'f>r>t<'row'<'span5'i><'span5'p>>",
												"sPaginationType" : "bootstrap",
												"oLanguage" : {
													"sUrl" : "${ctx}/static/datatables-1.9.1/zh_CN.txt"
												}
											});
							$('#table2')
									.dataTable(
											{
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