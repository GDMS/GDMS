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
	<c:if test="${success!=null&&success}">
		<div class="alert alert-block alert-success">
			<a class="close" data-dismiss="alert" href="#">×</a>
			<h4 class="alert-heading">操作成功</h4>
		</div>
	</c:if>
	<c:if test="${success!=null&&!success}">
		<div class="alert alert-block alert-error">
			<a class="close" data-dismiss="alert" href="#">×</a>
			<h4 class="alert-heading">操作失败，可能是该志愿顺被已经被使用</h4>
		</div>
	</c:if>

	<c:if test="${studentOrders.size()>0}">
		<h3 align="center">学生个人预选信息</h3>
		<table id="studentTable" class="table table-bordered">
			<thead>
				<tr>
					<td>志愿顺序</td>
					<td>课题名称</td>
					<td>指导老师</td>
					<td>题目类型</td>
					<td>题目性质</td>
					<td>承担方式</td>
					<td>已被预选次数</td>
					<td style="width: 140px;">操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${studentThesises}" var="studentThesis" varStatus="st">
					<tr>
						<c:choose>
							<c:when test="${studentOrders[st.index]==1}">
								<td class="thesisOrder">第一志愿</td>
							</c:when>
							<c:when test="${studentOrders[st.index]==2}">
								<td class="thesisOrder">第二志愿</td>
							</c:when>
							<c:when test="${studentOrders[st.index]==3}">
								<td class="thesisOrder">第三志愿</td>
							</c:when>
							<c:otherwise>
								<td>志愿错误</td>
							</c:otherwise>
						</c:choose>
						<td>${studentThesis.name}</td>
						<td>${studentThesis.teacher.name}</td>
						<td>${studentThesis.type}</td>
						<td>${studentThesis.property}</td>
						<td>${studentThesis.mode}</td>
						<td>${studentCounts[st.index]}</td>
						<td><a class="btn btn-primary btn-mini" href="${ctx}/student/thesisManage/del/${studentThesis.id}">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>

	<c:if test="${studentOrders.size()<3}">
		<br />
		<h3 align="center">选择课题</h3>

		<table id="table" class="table table-bordered">
			<thead>
				<tr>
					<td>课题名称</td>
					<td>指导老师</td>
					<td>题目类型</td>
					<td>题目性质</td>
					<td>承担方式</td>
					<td>已被预选次数</td>
					<td style="width: 140px;">操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${thesises}" var="thesis" varStatus="st">
					<tr>
						<td>${thesis.name}</td>
						<td>${thesis.teacher.name}</td>
						<td>${thesis.type}</td>
						<td>${thesis.property}</td>
						<td>${thesis.mode}</td>
						<td>${counts[st.index]}</td>
						<form action="${ctx}/student/thesisManage/choose/${thesis.id}" method="POST">
							<td><select style="width: 90px" name="order"><option value="1">第一志愿</option>
									<option value="2">第二志愿</option>
									<option value="3">第三志愿</option></select><input type="submit" class="btn btn-primary btn-mini" value="选择"></input></td>
						</form>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>

	<!-- Javascript -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#li-student_thesis').addClass('active');
			$('#table').dataTable({
				"sDom" : "<'row'<'span6'l><'span6'f>r>t<'row'<'span6'i><'span6'p>>",
				"sPaginationType" : "bootstrap",
				"oLanguage" : {
					"sUrl" : "${ctx}/static/datatables-1.9.1/zh_CN.txt"
				}
			});
			$('.thesisOrder').each(function(){
				var text = $(this).text();
				if(text=="第一志愿")
					$('select').find('option[value=1]').remove();
				if(text=="第二志愿")
					$('select').find('option[value=2]').remove();
				if(text=="第三志愿")
					$('select').find('option[value=3]').remove();
			});
		});
	</script>
</body>
</html>