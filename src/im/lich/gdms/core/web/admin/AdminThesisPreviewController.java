package im.lich.gdms.core.web.admin;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.teacher.TeacherService;
import im.lich.gdms.core.service.teacher.ThesisService;
import im.lich.gdms.core.util.AssignStatus;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/thesisPreview")
public class AdminThesisPreviewController extends BaseController {

	@Resource
	private TeacherService teacherService;

	@Resource
	private ThesisService thesisService;

	@RequestMapping(value = "")
	public String show(Model model) {
		logger.debug("GET-show");

		List<Thesis> _thesises = thesisService.getUnassignedThesises();

		List<Thesis> thesises = teacherService.getStudentsPreviewTheses(_thesises);
		model.addAttribute("thesises", thesises);

		List<Student> students = teacherService.getStudentsPreviewName(_thesises);
		model.addAttribute("students", students);

		List<Integer> orders = teacherService.getStudentsPreviewOrder(_thesises);
		model.addAttribute("orders", orders);

		List<String> assigns = teacherService.getStudentsPreviewAssign(_thesises);
		model.addAttribute("assigns", assigns);

		return "admin/thesisPreview";
	}

	@RequestMapping(value = "/assign/{thesisId}/{studentId}")
	public String assign(@PathVariable("thesisId") Long thesisId, @PathVariable("studentId") Long studentId, Model model) {
		logger.debug("GET-assign");

		//分配学生成功
		boolean success = false;
		if (thesisService.assignThesis(thesisId, studentId, AssignStatus.TEACHER1) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return "redirect:/admin/thesisPreview";
	}
}