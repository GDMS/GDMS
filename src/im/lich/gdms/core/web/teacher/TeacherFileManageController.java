package im.lich.gdms.core.web.teacher;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.student.StudentService;
import im.lich.gdms.core.service.teacher.TeacherService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherFileManageController extends BaseController {

	@Resource
	private StudentService studentService;

	@Resource
	private TeacherService teacherService;

	@RequestMapping("/fileManage")
	public String showStudentFile(Model model) {
		logger.debug("GET-showStudentFile");

		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
		//获取学生信息
		List<Student> students = teacherService.getTeachingStudents(loginName);
		model.addAttribute("students", students);
		logger.debug("学生:{}", StringUtils.join(students, ','));

		//获取学生课题
		List<Thesis> studentsThesises = studentService.getStudentsThesises(students);
		model.addAttribute("studentsThesises", studentsThesises);

		return "/teacher/fileManage";
	}
}
