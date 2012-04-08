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
	<p>&nbsp;</p>
	<h2>成绩输入页面</h2>

	<ul id="tab" class="nav nav-tabs">
		<li class="active"><a href="#zhidao" data-toggle="tab">指导教师成绩输入</a></li>
		<li><a href="#pingyue" data-toggle="tab">评阅教师成绩输入</a></li>
		<li><a href="#dabian" data-toggle="tab">答辩小组成绩输入</a></li>
	</ul>
	<div id="tab-content" class="tab-content">
		<div id="zhidao" class="tab-pane fade active in">
			<p>指导教师输入</p>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>学号</th>
						<th>姓名</th>
						<th>课题名称</th>
						<th>指导教师输入成绩状态</th>
						<th>评阅教师输入成绩状态</th>
						<th>答辩小组输入成绩状态</th>
						<th>输入成绩</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${students}" var="student" varStatus="st">
						<tr>
							<td>${student.loginName}</td>
							<td>${student.name}</td>
							<td>${studentsThesises[st.index].name}</td>
							<td>${studentsZhidaoScoreStatuses[st.index]}</td>
							<td>${studentsPingyueScoreStatuses[st.index]}</td>
							<td>${studentsDabianScoreStatuses[st.index]}</td>
							<td><a href="${ctx}/teacher/scoreInput/zhidao/${student.loginName}" class="btn btn-mini">输入、修改成绩</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div id="pingyue" class="tab-pane fade">
			<p>评阅教师输入</p>
		</div>
		<div id="dabian" class="tab-pane fade">
			<p>答辩小组输入</p>
		</div>
	</div>


	<!-- Javascript -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#li-teacher_score').addClass('active');
		});
	</script>
</body>
</html>