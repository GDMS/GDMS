package im.lich.gdms.core.web.admin;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.teacher.Teacher;
import im.lich.gdms.core.model.teacher.TeacherDept;
import im.lich.gdms.core.service.teacher.TeacherDeptService;
import im.lich.gdms.core.service.teacher.TeacherService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminTeacherManageController extends BaseController {

	@Resource
	private TeacherService teacherService;

	@Resource
	private TeacherDeptService teacherDeptService;

	@RequestMapping(value = { "/teacherManage" })
	public String showTeacherManage(Model model) {
		List<Teacher> teachers = teacherService.getTeachers();
		model.addAttribute("teachers", teachers);
		List<TeacherDept> depts = teacherDeptService.getTeacherDepts();
		model.addAttribute("depts", depts);

		return "admin/teacherManage";
	}

	@RequestMapping(value = { "/teacherManage/add" }, method = RequestMethod.POST)
	public String addOrUpdateTeacherManage(Teacher teacher, Model model) {
		logger.debug("获取新增的Administrator: {}", teacher.toString());

		boolean success = false;
		if (teacherService.addOrUpdateTeacher(teacher) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		List<Teacher> teachers = teacherService.getTeachers();
		model.addAttribute("teachers", teachers);
		List<TeacherDept> depts = teacherDeptService.getTeacherDepts();
		model.addAttribute("depts", depts);

		return "admin/teacherManage";
	}

	@RequestMapping(value = { "/teacherManage/del/{teacherId}" })
	public String delTeacherManage(@PathVariable("teacherId") Long teacherId, Model model) {
		boolean success = false;
		if (teacherService.delTeacher(teacherId) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		List<Teacher> teachers = teacherService.getTeachers();
		model.addAttribute("teachers", teachers);
		List<TeacherDept> depts = teacherDeptService.getTeacherDepts();
		model.addAttribute("depts", depts);

		return "admin/teacherManage";
	}
}