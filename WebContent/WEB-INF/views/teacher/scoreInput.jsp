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
	<ul id="tab" class="nav nav-tabs">
		<li><a href="#zhidao" data-toggle="tab">指导教师成绩输入</a></li>
		<li><a href="#pingyue" data-toggle="tab">评阅教师成绩输入</a></li>
		<li><a href="#dabian" data-toggle="tab">答辩小组成绩输入</a></li>
	</ul>
	<div id="tab-content" class="tab-content">
		<div id="zhidao" class="tab-pane fade active in">
			<h2>当前成绩状态</h2>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>学号</th>
						<th>姓名</th>
						<th>课题名称</th>
						<th>指导教师输入成绩状态</th>
						<th>评阅教师输入成绩状态</th>
						<th>答辩小组输入成绩状态</th>
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
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<h2>输入成绩</h2>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th rowspan="2" style="vertical-align: middle;">学号</th>
						<th rowspan="2" style="vertical-align: middle;">姓名</th>
						<th rowspan="2" style="vertical-align: middle;">课题名称</th>
						<th>毕业设计(论文)质量</th>
						<th>绘图与文字表达技巧</th>
						<th>独立工作能力</th>
						<th>工作态度</th>
						<th rowspan="2" style="vertical-align: middle;">操作</th>
					</tr>
					<tr>
						<th>折合系数: 0.15</th>
						<th>折合系数: 0.05</th>
						<th>折合系数: 0.05</th>
						<th>折合系数: 0.05</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${students}" var="student" varStatus="st">
						<form name="student" action="${ctx}/teacher/scoreInput/zhidao/update/${student.loginName}" method="post">
							<tr>
								<td>${student.loginName}</td>
								<td>${student.name}</td>
								<td>${studentsThesises[st.index].name}</td>
								<td><input type="text" name="zd1grade" value="${student.zd1grade}" class="input-mini" /></td>
								<td><input type="text" name="zd2grade" value="${student.zd2grade}" class="input-mini" /></td>
								<td><input type="text" name="zd3grade" value="${student.zd3grade}" class="input-mini" /></td>
								<td><input type="text" name="zd4grade" value="${student.zd4grade}" class="input-mini" /></td>
								<td><input type="submit" class="btn btn-primary btn-mini" value="更新" /></td>
							</tr>
						</form>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div id="pingyue" class="tab-pane fade">
			<h2>评阅教师输入</h2>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th rowspan="2" style="vertical-align: middle;">学号</th>
						<th rowspan="2" style="vertical-align: middle;">姓名</th>
						<th rowspan="2" style="vertical-align: middle;">课题名称</th>
						<th>毕业设计(论文)水平</th>
						<th>图、文质量</th>
						<th rowspan="2" style="vertical-align: middle;">操作</th>
					</tr>
					<tr>
						<th>折合系数: 0.2</th>
						<th>折合系数: 0.1</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pingyueStudents}" var="student" varStatus="st">
						<tr>
							<td>${student.loginName}</td>
							<td>${student.name}</td>
							<td>${pingyueStudentsThesises[st.index].name}</td>
							<td>${student.py1grade}</td>
							<td>${student.py2grade}</td>
							<td><a href="${ctx}/teacher/scoreInput/pingyue/del/${student.loginName}" class="btn btn-mini">删除</a></td>
						</tr>
					</c:forEach>
					<form name="student" action="${ctx}/teacher/scoreInput/pingyue/add" method="post">
						<tr>
							<td><input type="text" class="input-mini" name="loginName" /></td>
							<td></td>
							<td></td>
							<td><input type="text" class="input-mini" name="py1grade" /></td>
							<td><input type="text" class="input-mini" name="py2grade" /></td>
							<td><input type="submit" class="btn btn-primary btn-mini" value="添加" /></td>
						</tr>
					</form>
				</tbody>
			</table>
		</div>

		<div id="dabian" class="tab-pane fade">
			<h2>答辩小组输入</h2>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th rowspan="2" style="vertical-align: middle;">学号</th>
						<th rowspan="2" style="vertical-align: middle;">姓名</th>
						<th rowspan="2" style="vertical-align: middle;">课题名称</th>
						<th>口头汇报</th>
						<th>回答问题正确性</th>
						<th rowspan="2" style="vertical-align: middle;">操作</th>
					</tr>
					<tr>
						<th>折合系数: 0.1</th>
						<th>折合系数: 0.3</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${dabianStudents}" var="student" varStatus="st">
						<tr>
							<td>${student.loginName}</td>
							<td>${student.name}</td>
							<td>${dabianStudentsThesises[st.index].name}</td>
							<td>${student.db1grade}</td>
							<td>${student.db2grade}</td>
							<td><a href="${ctx}/teacher/scoreInput/dabian/del/${student.loginName}" class="btn btn-mini">删除</a></td>
						</tr>
					</c:forEach>
					<form name="student" action="${ctx}/teacher/scoreInput/dabian/add" method="post">
						<tr>
							<td><input type="text" class="input-mini" name="loginName" /></td>
							<td></td>
							<td></td>
							<td><input type="text" class="input-mini" name="db1grade" /></td>
							<td><input type="text" class="input-mini" name="db2grade" /></td>
							<td><input type="submit" class="btn btn-primary btn-mini" value="添加" /></td>
						</tr>
					</form>
				</tbody>
			</table>
		</div>
	</div>

	<h3>评分标准</h3>
	<table class="table table-bordered">
		<tr>
			<td>优</td>
			<td>良</td>
			<td>中</td>
			<td>及格</td>
			<td>不及格</td>
		</tr>
		<tr>
			<td>85～100</td>
			<td>75～84.9</td>
			<td>67～74.9</td>
			<td>60～66.9</td>
			<td>＜60</td>
		</tr>
	</table>

	<div style="visibility: hidden;">
		<span id="tabChoose">${tabChoose}</span>
	</div>

	<!-- Javascript -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#li-teacher_score').addClass('active');

			var tabChoose = $('#tabChoose').text();
			if (tabChoose == 'pingyue')
				$('#tab a:nth(1)').tab('show');
			else if (tabChoose == 'dabian')
				$('#tab a:nth(2)').tab('show');
			else
				$('#tab a:nth(0)').tab('show');
		});
	</script>
</body>
</html>