package im.lich.gdms.core.web.teacher;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.teacher.TeacherScoreInputService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/teacher")
public class TeacherScoreInputController extends BaseController {
	@Resource
	private TeacherScoreInputService teacherScoreInputService;

	@RequestMapping(value = "/scoreInput")
	public String showScoreInput(Model model) {
		logger.debug("GET-showScoreInput");

		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
		//获取学生信息
		List<Student> students = teacherScoreInputService.getStudents(loginName);
		model.addAttribute("students", students);
		logger.debug("学生:{}", StringUtils.join(students, ','));

		//获取学生课题
		List<Thesis> studentsThesises = teacherScoreInputService.getStudentsThesises(students);
		model.addAttribute("studentsThesises", studentsThesises);

		//获取指导教师成绩输入状态
		List<String> studentsZhidaoScoreStatuses = teacherScoreInputService.getStudentsZhidaoScoreStatuses(students);
		model.addAttribute("studentsZhidaoScoreStatuses", studentsZhidaoScoreStatuses);

		//获取评阅教师成绩输入状态
		List<String> studentsPingyueScoreStatuses = teacherScoreInputService.getStudentsPingyueScoreStatuses(students);
		model.addAttribute("studentsPingyueScoreStatuses", studentsPingyueScoreStatuses);

		//获取答辩小组成绩输入状态
		List<String> studentsDabianScoreStatuses = teacherScoreInputService.getStudentsDabianScoreStatuses(students);
		model.addAttribute("studentsDabianScoreStatuses", studentsDabianScoreStatuses);

		return "teacher/scoreInput";
	}

	@RequestMapping(value = "/scoreInput/zhidao/{studentLoginName}")
	public String showScoreZhidaoInputDetail(@PathVariable("studentLoginName") String studentLoginName, Model model) {
		logger.debug("GET-showScoreZhidaoInputDetail");

		Student student = teacherScoreInputService.getStudent(studentLoginName);
		model.addAttribute("student", student);
		logger.debug("学生:{}", student);

		return "teacher/scoreInputZhidaoDetail";
	}

	@RequestMapping(value = "/scoreInput/zhidao/{studentLoginName}", method = RequestMethod.POST)
	public String saveScoreInputZhidaoDetail(@PathVariable("studentLoginName") String studentLoginName,
			Student student, Model model) {
		logger.debug("POST-saveScoreZhidaoInputDetail");

		logger.debug("网页获取信息：{}", student);

		//强制指定学生登录名
		student.setLoginName(studentLoginName);

		//保存
		boolean success = false;
		if (teacherScoreInputService.saveScoreInputZhidaoDetail(student) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return "teacher/scoreInputZhidaoDetail";
	}
}
