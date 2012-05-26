package im.lich.gdms.core.web.admin;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.teacher.ThesisService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/thesisAssign")
public class AdminThesisAssignController extends BaseController {

	@Resource
	private ThesisService thesisService;

	@RequestMapping(value = "")
	public String show(Model model) {
		logger.debug("GET-show");

		List<Thesis> thesises = thesisService.getAssignedThesis();
		model.addAttribute("thesises", thesises);

		List<Student> students = thesisService.getAssignedThesisStudent();
		model.addAttribute("students", students);

		List<Thesis> unassignedThesises = thesisService.getUnassignedThesises();
		model.addAttribute("unassignedThesises", unassignedThesises);

		return "admin/thesisAssign";
	}

	@RequestMapping(value = "/unassign/{thesisId}")
	public String unassign(@PathVariable("thesisId") Long thesisId, Model model) {
		logger.debug("GET-unassign");

		boolean success = false;
		if (thesisService.unassignThesis(thesisId) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return "forward:/admin/thesisAssign";
	}
}