package im.lich.gdms.core.web.admin;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.teacher.ThesisService;
import im.lich.gdms.core.util.AssignStatus;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/studentAssign")
public class AdminStudentAssignController extends BaseController {

	@Resource
	private ThesisService thesisService;

	@RequestMapping(value = "")
	public String show(Model model) {
		logger.debug("GET-show");

		List<Student> students = thesisService.getAssignedThesisesStudents();
		model.addAttribute("students", students);

		List<Thesis> thesises = thesisService.getAssignedThesises();
		model.addAttribute("thesises", thesises);

		List<Student> unassignedStudents = thesisService.getUnassignedThesisesStudents();
		model.addAttribute("unassignedStudents", unassignedStudents);

		List<Thesis> unassignedThesises = thesisService.getUnassignedThesises();
		model.addAttribute("unassignedThesises", unassignedThesises);

		return "admin/studentAssign";
	}

	@RequestMapping(value = "/assign/{studentId}", method = RequestMethod.POST)
	public String assign(Long thesisId, @PathVariable("studentId") Long studentId, Model model) {
		logger.debug("POST-assign");

		boolean success = false;
		if (thesisService.assignThesis(thesisId, studentId, AssignStatus.ADMIN) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return "forward:/admin/studentAssign";
	}

	@RequestMapping(value = "/unassign/{thesisId}")
	public String unassign(@PathVariable("thesisId") Long thesisId, Model model) {
		logger.debug("POST-assign");

		boolean success = false;
		if (thesisService.unassignThesis(thesisId) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return "forward:/admin/studentAssign";
	}
}