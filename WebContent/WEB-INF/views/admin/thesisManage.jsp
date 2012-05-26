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

	<p>&nbsp;</p>
	<h2 style="text-align: center;">课题信息管理</h2>

	<div class="row">
		<div class="span10 offset1">
			<table id="table" class="table table-bordered">
				<thead>
					<tr>
						<th style="vertical-align: middle;">课题名称</th>
						<th style="vertical-align: middle;">教师名称</th>
						<th style="vertical-align: middle;">类型</th>
						<th style="vertical-align: middle;">性质</th>
						<th style="vertical-align: middle;">方式</th>
						<th style="vertical-align: middle;" width="80px">可选专业</th>
						<th style="vertical-align: middle;" width="80px">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${thesises}" var="thesis" varStatus="st">
						<tr>
							<td>${thesis.name}</td>
							<th>${thesis.teacher.name}</th>
							<td>${thesis.type}</td>
							<td>${thesis.property}</td>
							<td>${thesis.mode}</td>
							<c:if test="${thesis.majorRestrict!=''}">
								<td><a href="#" rel="tooltip" data-original-title="${thesis.majorRestrict}">点击查看</a></td>
							</c:if>
							<c:if test="${thesis.majorRestrict==''}">
								<td>无专业</td>
							</c:if>
							<td><a href="${ctx}/admin/thesisManage/mod/${thesis.id}" class="btn btn-primary btn-mini">修改</a>&nbsp;<a
								href="${ctx}/admin/thesisManage/del/${thesis.id}" class="btn btn-danger btn-mini">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a class="btn btn-info pull-right" data-toggle="collapse" href="#add">添加课题</a>
		</div>
		<!-- /span8 offset2 -->
	</div>
	<!-- /row -->

	<div class="row">
		<div class="span8 offset2">
			<div id="add" class="collapse in">
				<form:form modelAttribute="thesis" action="${ctx}/admin/thesisManage/add" cssClass="form-horizontal">
					<div class="control-group">
						<label class="control-label">教工号:</label>
						<div class="controls">
							<input id="teacherLoginName" name="teacherLoginName" type="text" />
						</div>
					</div>

					<div class="control-group">
						<label class="control-label">课题名称:</label>
						<div class="controls">
							<form:input path="name" />
						</div>
					</div>

					<div class="control-group">
						<label class="control-label">类型:</label>
						<div class="controls">
							<form:select path="type" cssClass="input-xlarge">
								<form:option value="类型1">类型1</form:option>
							</form:select>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label">性质:</label>
						<div class="controls">
							<form:select path="property" cssClass="input-xlarge">
								<form:option value="性质1">性质1</form:option>
							</form:select>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label">方式:</label>
						<div class="controls">
							<form:select path="mode" cssClass="input-xlarge">
								<form:option value="方式1">方式1</form:option>
							</form:select>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label">可选专业:</label>
						<div class="controls">
							<label class="checkbox"><form:checkboxes items="${majors}" path="majorRes" itemLabel="name"
									itemValue="name" /><input id="majorResAll" type="checkbox">全选</input></label>
						</div>
					</div>

					<div class="form-actions">
						<input type="submit" class="btn btn-primary" value="添加" /> <input type="reset" class="btn" value="取消" />
					</div>

				</form:form>
			</div>
		</div>
		<!-- /span8 offset2 -->
	</div>
	<!-- /row -->

	<!-- Javascript -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#li-admin_thesis').addClass('active');
			$(".collapse").collapse();
			$("#majorResAll").click(function() {
						if ($(this).attr("checked")) {
							$("input[name='majorRes']").each(function() {
										$(this).attr("checked", true);
									});
						} else {
							$("input[name='majorRes']").each(function() {
										$(this).attr("checked", false);
									});
						}
					});
			// 显示tooltip
			$("a[rel='tooltip']").tooltip();
			// datatables
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