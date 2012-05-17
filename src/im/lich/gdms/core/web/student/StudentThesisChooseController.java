package im.lich.gdms.core.web.student;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.student.PreviewService;
import im.lich.gdms.core.service.teacher.ThesisService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student/thesisManage")
public class StudentThesisChooseController extends BaseController {

	@Resource
	private ThesisService thesisService;

	@Resource
	private PreviewService previewService;

	@RequestMapping(value = { "" })
	public String show(Model model) {
		String studentLoginName = SecurityUtils.getSubject().getPrincipal().toString();
		List<Thesis> thesises = thesisService.getUnassignedAndUnchoosedThesises(studentLoginName);
		model.addAttribute("thesises", thesises);

		List<Integer> counts = previewService.getThesisesPreviewCount(thesises);
		model.addAttribute("counts", counts);

		List<Thesis> studentThesises = previewService.getStudentPreviewThesises(studentLoginName);
		model.addAttribute("studentThesises", studentThesises);

		List<Integer> studentOrders = previewService.getStudentPreviewThesisesOrder(studentLoginName, studentThesises);
		model.addAttribute("studentOrders", studentOrders);

		List<Integer> studentCounts = previewService.getThesisesPreviewCount(studentThesises);
		model.addAttribute("studentCounts", studentCounts);

		return "student/thesisManage";
	}

	@RequestMapping(value = { "/choose/{thesisId}" })
	public String choosePreviewThesis(@PathVariable("thesisId") Long thesisId, int order, Model model) {
		String studentLoginName = SecurityUtils.getSubject().getPrincipal().toString();

		//保存
		boolean success = false;
		if (previewService.saveStudentPreview(studentLoginName, thesisId, order) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return "forward:/student/thesisManage";
	}

	@RequestMapping(value = { "/del/{thesisId}" })
	public String delPreviewThesis(@PathVariable("thesisId") Long thesisId, Model model) {
		String studentLoginName = SecurityUtils.getSubject().getPrincipal().toString();

		//保存
		boolean success = false;
		if (previewService.delStudentPreview(studentLoginName, thesisId) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return "forward:/student/thesisManage";
	}
}
