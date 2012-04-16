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
	<h3>消息管理</h3>

	<table class="table table-bordered">
		<thead>
			<tr>
				<th style="vertical-align: middle;">消息标题</th>
				<th style="vertical-align: middle;">消息内容</th>
				<th style="vertical-align: middle;">接收对象</th>
				<th style="vertical-align: middle;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${newses}" var="news" varStatus="st">
				<tr>
					<td>${news.title}</td>
					<td>${news.message}</td>
					<td>${news.receiver}</td>
					<td><a href="${ctx}/admin/newsManage/del/${news.id}" class="btn btn-mini">删除</a></td>
				</tr>
			</c:forEach>
			<form name="news" action="${ctx}/admin/newsManage/add" method="post">
				<tr>
					<td><input type="text" class="input-small" name="title" /></td>
					<td><input type="text" class="input-xxlarge" name="message" /></td>
					<td><select class="input-small" name="receiver"><option value="all">所有人</option>
							<option value="student">学生</option>
							<option value="teacher">教师</option>
							<option value="admin">管理员</option>
					</select></td>
					<td><input type="submit" class="btn btn-primary btn-mini" value="添加" /></td>
				</tr>
			</form>
		</tbody>
	</table>

	<!-- Javascript -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#li-admin_index').addClass('active');
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