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
	<h2 style="text-align: center;">修改课题信息</h2>
	<p>&nbsp;</p>

	<div class="row">
		<div class="span8 offset2">
			<form:form modelAttribute="thesis" cssClass="form-horizontal">

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
					<input type="submit" class="btn btn-primary" value="修改" />&nbsp;<input type="reset" class="btn" value="重置" />&nbsp;<a
						href="${ctx}/teacher/thesisManage" class="btn btn-inverse">返回</a>
				</div>

			</form:form>
		</div>
		<!-- /span8 offset2 -->
	</div>
	<!-- /row -->

	<!-- Javascript -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#li-teacher_thesis').addClass('active');
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
		});
	</script>
</body>
</html>