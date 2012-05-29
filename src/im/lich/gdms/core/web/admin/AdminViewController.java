package im.lich.gdms.core.web.admin;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.teacher.ThesisService;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/view")
public class AdminViewController extends BaseController {

	private static byte[] lock = new byte[0];

	@Resource
	private ThesisService thesisService;

	@RequestMapping(value = "")
	public String show(Model model) {
		logger.debug("GET-show");

		List<Thesis> thesises = thesisService.getAssignedThesises();
		List<Student> students = thesisService.getAssignedThesisesStudents();

		Iterator<Thesis> it = thesises.iterator();
		Iterator<Student> is = students.iterator();
		while (it.hasNext() & is.hasNext()) {
			synchronized (lock) {
				@SuppressWarnings("unused")
				Thesis t = it.next();
				Student s = is.next();
				if (s.getCredit() >= 1.7) {
					it.remove();
					is.remove();
				}
			}
		}

		model.addAttribute("thesises", thesises);
		model.addAttribute("students", students);

		return "admin/view";
	}

	@RequestMapping(value = "/unassign/{thesisId}")
	public String unassign(@PathVariable("thesisId") Long thesisId, Model model) {
		logger.debug("GET-unassign");

		boolean success = false;
		if (thesisService.unassignThesis(thesisId) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return "forward:/admin/view";
	}
}