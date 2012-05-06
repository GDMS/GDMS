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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	@RequestMapping(value = "/thesisManage/del/{thesisId}")
	public String delThesis(@PathVariable("thesisId") Long thesisId, Model model) {
		logger.debug("GET-delThesis");
		logger.debug("删除课题ID：{}", thesisId);

		boolean success = false;
		if (teacherService.delTeacherThesis(thesisId) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return "redirect:/teacher/thesisManage";
	}

	@RequestMapping(value = "/thesisManage/mod/{thesisId}")
	public String modThesisShow(@PathVariable("thesisId") Long thesisId, Model model) {
		logger.debug("GET-modThesisShow");

		Thesis thesis = teacherService.getTeacherThesis(thesisId);
		model.addAttribute("thesis", thesis);

		List<StudentMajor> majors = studentMajorService.getStudentMajors();
		model.addAttribute("majors", majors);

		return "teacher/thesisManageMod";
	}

	@RequestMapping(value = "/thesisManage/mod/{thesisId}", method = RequestMethod.POST)
	public String modThesis(@PathVariable("thesisId") Long thesisId, Thesis thesis, Model model) {
		logger.debug("GET-modThesis");
		logger.debug("修改课题ID：{}，内容：{}", thesisId, thesis);

		boolean success = false;
		if (teacherService.modTeacherThesis(thesisId, thesis) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return modThesisShow(thesisId, model);
	}
}