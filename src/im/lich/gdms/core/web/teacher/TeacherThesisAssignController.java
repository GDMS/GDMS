package im.lich.gdms.core.web.teacher;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.teacher.TeacherService;
import im.lich.gdms.core.service.teacher.ThesisService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher/thesisAssign")
public class TeacherThesisAssignController extends BaseController {

	@Resource
	private TeacherService teacherService;

	@Resource
	private ThesisService thesisService;

	@RequestMapping(value = "")
	public String show(Model model) {
		logger.debug("GET-show");

		String teacherLoginName = SecurityUtils.getSubject().getPrincipal().toString();
		List<Thesis> thesises = teacherService.getAssignedStudentThesis(teacherLoginName);
		model.addAttribute("thesises", thesises);

		List<Student> students = teacherService.getAssignedStudentInfo(teacherLoginName);
		model.addAttribute("students", students);

		List<Thesis> unassignedThesises = teacherService.getUnassignedThesises(teacherLoginName);
		model.addAttribute("unassignedThesises", unassignedThesises);

		boolean isOverMaxAssign = teacherService.isOverMaxAssign(teacherLoginName);
		model.addAttribute("isOverMaxAssign", isOverMaxAssign);

		return "teacher/thesisAssign";
	}

	@RequestMapping(value = "/unassign/{thesisId}")
	public String unassign(@PathVariable("thesisId") Long thesisId, Model model) {
		logger.debug("GET-unassign");

		boolean success = false;
		if (thesisService.unassignThesis(thesisId) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return "forward:/teacher/thesisAssign";
	}
}