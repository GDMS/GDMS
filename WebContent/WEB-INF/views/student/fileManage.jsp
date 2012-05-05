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
		<div class="span8 offset2">
			<h2 style="text-align: center;">文件管理</h2>


			<table class="table table-bordered">
				<thead>
					<tr>
						<th style="vertical-align: middle;" width="64px">类型</th>
						<th style="vertical-align: middle;">文件名</th>
						<th style="vertical-align: middle;" width="80px">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="vertical-align: middle;">开题报告</td>
						<c:choose>
							<c:when test="${kt!=''}">
								<td style="vertical-align: middle;">${kt}</td>
								<td style="vertical-align: middle;"><a href="${ctx}/student/fileManage/del/kt"
									class="btn btn-danger btn-mini">删除</a></td>
							</c:when>
							<c:otherwise>
								<form action="${ctx}/student/fileManage/add/kt" method="post" enctype="multipart/form-data">
								<td style="vertical-align: middle;"><input type="file" class="input-medium" name="ktup" /></td>
								<td style="vertical-align: middle;"><input type="submit" class="btn btn-primary btn-mini" value="上传" /></td>
								</form>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td style="vertical-align: middle;">任务书</td>
						<c:choose>
							<c:when test="${rws!=''}">
								<td style="vertical-align: middle;">${rws}</td>
								<td style="vertical-align: middle;"><a href="${ctx}/student/fileManage/del/rws"
									class="btn btn-danger btn-mini">删除</a></td>
							</c:when>
							<c:otherwise>
								<td style="vertical-align: middle;"><input type="file" class="input-medium" name="rwsup" /></td>
								<td style="vertical-align: middle;"><input type="submit" class="btn btn-primary btn-mini" value="上传" /></td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td style="vertical-align: middle;">翻译</td>
						<c:choose>
							<c:when test="${trans!=''}">
								<td style="vertical-align: middle;">${trans}</td>
								<td style="vertical-align: middle;"><a href="${ctx}/student/fileManage/del/trans"
									class="btn btn-danger btn-mini">删除</a></td>
							</c:when>
							<c:otherwise>
								<td style="vertical-align: middle;"><input type="file" class="input-medium" name="transup" /></td>
								<td style="vertical-align: middle;"><input type="submit" class="btn btn-primary btn-mini" value="上传" /></td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td style="vertical-align: middle;">论文</td>
						<c:choose>
							<c:when test="${thesis!=''}">
								<td style="vertical-align: middle;">${thesis}</td>
								<td style="vertical-align: middle;"><a href="${ctx}/student/fileManage/del/thesis"
									class="btn btn-danger btn-mini">删除</a></td>
							</c:when>
							<c:otherwise>
								<td style="vertical-align: middle;"><input type="file" class="input-medium" name="thesisup" /></td>
								<td style="vertical-align: middle;"><input type="submit" class="btn btn-primary btn-mini" value="上传" /></td>
							</c:otherwise>
						</c:choose>
					</tr>
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