<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
</head>
<body>

	<table class="table table-bordered" style="margin-top: 20px;">
		<thead>
			<tr>
				<th colspan="2">优秀论文</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>人生的思考</td>
				<td><a>下载</a></td>
			</tr>
			<tr>
				<td>人生的思考</td>
				<td><a>下载</a></td>
			</tr>
			<tr>
				<td>人生的思考</td>
				<td><a>下载</a></td>
			</tr>
			<tr>
				<td>人生的思考</td>
				<td><a>下载</a></td>
			</tr>
			<c:forEach items="${papers}" var="paper">
				<tr>
					<td>${paper.description}</td>
					<td><a href="${ctx}/file/paper/${paper.filename}">下载</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<ul>
		<li><p>计算机学院计算机学院计算机学院计算机学院计算机学院计算机学院计算机学院</p>
			<p>aaaaaa</p></li>
	</ul>

	<!-- Javascript -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#li-index').addClass('active');
		});
	</script>
</body>
</html>