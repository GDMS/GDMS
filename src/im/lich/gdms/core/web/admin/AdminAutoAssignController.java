package im.lich.gdms.core.web.admin;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.admin.AdminService;
import im.lich.gdms.core.service.teacher.ThesisService;
import im.lich.gdms.core.util.Message;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/autoAssign")
public class AdminAutoAssignController extends BaseController {

	@Resource
	private ThesisService thesisService;

	@Resource
	private AdminService adminService;

	@RequestMapping(value = "")
	public String show(Model model) {
		logger.debug("GET-show");

		List<Thesis> thesises = thesisService.getAssignedThesises();
		int thesisAssignedNum = thesises.size();
		model.addAttribute("thesisAssignedNum", thesisAssignedNum);

		List<Thesis> unassignedThesises = thesisService.getUnassignedThesises();
		int thesisUnassignedNum = unassignedThesises.size();
		model.addAttribute("thesisUnassignedNum", thesisUnassignedNum);

		List<Student> students = thesisService.getAssignedThesisesStudents();
		int studentAssignedNum = students.size();
		model.addAttribute("studentAssignedNum", studentAssignedNum);

		List<Student> unassignedStudents = thesisService.getUnassignedThesisesStudents();
		int studentUnassignedNum = unassignedStudents.size();
		model.addAttribute("studentUnassignedNum", studentUnassignedNum);

		return "admin/autoAssign";
	}

	@RequestMapping(value = "/start")
	public String startAutoAssign(Model model) {
		logger.debug("GET-startAutoAssign");

		Message message = new Message();
		boolean success = false;
		if (adminService.autoAssign(message)) {
			success = true;
		}
		model.addAttribute("success", success);
		model.addAttribute("message", message);

		return "forward:/admin/autoAssign";
	}
}