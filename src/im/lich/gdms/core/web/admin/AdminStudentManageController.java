package im.lich.gdms.core.web.admin;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.student.StudentClass;
import im.lich.gdms.core.model.student.StudentMajor;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.student.StudentClassService;
import im.lich.gdms.core.service.student.StudentMajorService;
import im.lich.gdms.core.service.student.StudentService;
import im.lich.gdms.core.service.teacher.ThesisService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminStudentManageController extends BaseController {
	@Resource
	private StudentService studentService;

	@Resource
	private StudentMajorService studentMajorService;

	@Resource
	private StudentClassService studentClassService;

	@Resource
	private ThesisService thesisService;

	@RequestMapping(value = { "/studentManage" })
	public String showStudentManage(Model model) {
		return getContent(model);
	}

	@RequestMapping(value = { "/studentManage/add" }, method = RequestMethod.POST)
	public String addOrUpdateStudentManage(Student student, Model model) {
		logger.debug("获取新增的学生: {}", student.toString());

		boolean success = false;
		if (studentService.addOrUpdateStudent(student) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return getContent(model);
	}

	@RequestMapping(value = { "/studentManage/del/{StudentId}" })
	public String delStudentManage(@PathVariable("StudentId") Long studentId, Model model) {

		boolean success = false;
		if (studentService.delStudent(studentId) != null) {
			success = true;
		}
		model.addAttribute("success", success);
		return getContent(model);
	}

	private String getContent(Model model) {
		List<Student> students = studentService.getStudents();
		model.addAttribute("students", students);

		List<StudentMajor> studentMajors = studentMajorService.getStudentMajors();
		model.addAttribute("studentMajors", studentMajors);

		List<StudentClass> studentClasss = studentClassService.getStudentClasses();
		model.addAttribute("studentClasss", studentClasss);

		List<Thesis> thesises = thesisService.getThesises();
		model.addAttribute("thesises", thesises);

		return "/admin/studentManage";
	}

}
