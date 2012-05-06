package im.lich.gdms.core.web.student;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.student.StudentService;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentFinalScoreController extends BaseController {

	@Resource
	private StudentService studentService;

	@RequestMapping(value = "/finalScore")
	public String showFinalScore(Model model) {
		logger.debug("GET-showFinalScore");

		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
		Student student = studentService.getStudentInfo(loginName);
		model.addAttribute("student", student);

		Thesis thesis = studentService.getStudentThesis(student);
		model.addAttribute("thesis", thesis);

		return "student/finalScore";
	}
}
