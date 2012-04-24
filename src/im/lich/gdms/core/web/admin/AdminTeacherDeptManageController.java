package im.lich.gdms.core.web.admin;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.teacher.TeacherDept;
import im.lich.gdms.core.service.teacher.TeacherDeptService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminTeacherDeptManageController extends BaseController {

	@Resource
	TeacherDeptService teacherDeptService;

	@RequestMapping(value = { "/teacherDeptManage" })
	public String showTeacherDeptManage(Model model) {
		List<TeacherDept> teacherDepts = teacherDeptService.getTeacherDepts();
		model.addAttribute("teacherDepts", teacherDepts);

		return "/admin/teacherDeptManage";
	}

	@RequestMapping(value = { "/teacherDeptManage/add" }, method = RequestMethod.POST)
	public String addTeacherDeptManage(TeacherDept teacherDept, Model model) {
		logger.debug("新增教师部门：{}", teacherDept.toString());

		boolean success = false;
		if (teacherDeptService.addTeacherDept(teacherDept) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return showTeacherDeptManage(model);
	}

	@RequestMapping(value = { "/teacherDeptManage/del/{teacherDeptId}" })
	public String delTeacherDeptManage(@PathVariable("teacherDeptId") Long teacherDeptId, Model model) {
		boolean success = false;
		if (teacherDeptService.delTeacherDept(teacherDeptId) != null) {
			success = true;
		}
		model.addAttribute("success", success);
		return showTeacherDeptManage(model);
	}
}
