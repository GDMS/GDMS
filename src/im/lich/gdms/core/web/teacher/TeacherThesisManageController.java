package im.lich.gdms.core.web.teacher;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.student.StudentMajor;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.student.StudentMajorService;
import im.lich.gdms.core.service.teacher.TeacherService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherThesisManageController extends BaseController {

	@Resource
	private TeacherService teacherService;

	@Resource
	private StudentMajorService studentMajorService;

	@RequestMapping(value = "/thesisManage")
	public String show(Model model) {
		logger.debug("GET-show");

		String teacherLoginName = SecurityUtils.getSubject().getPrincipal().toString();
		List<Thesis> thesises = teacherService.getTeacherThesises(teacherLoginName);
		model.addAttribute("thesises", thesises);

		model.addAttribute("thesis", new Thesis());
		List<StudentMajor> majors = studentMajorService.getStudentMajors();
		model.addAttribute("majors", majors);

		return "teacher/thesisManage";
	}

	@RequestMapping(value = "/thesisManage/add")
	public String addThesis(Thesis thesis, Model model) {
		logger.debug("GET-addThesis");
		logger.debug("添加课题：{}", thesis);

		String teacherLoginName = SecurityUtils.getSubject().getPrincipal().toString();

		boolean success = false;
		if (teacherService.saveTeacherThesis(thesis, teacherLoginName) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return "redirect:/teacher/thesisManage";
	}
}