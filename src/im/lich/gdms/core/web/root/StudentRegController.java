package im.lich.gdms.core.web.root;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.student.StudentClass;
import im.lich.gdms.core.model.student.StudentMajor;
import im.lich.gdms.core.service.student.StudentClassService;
import im.lich.gdms.core.service.student.StudentMajorService;
import im.lich.gdms.core.service.student.StudentService;
import im.lich.gdms.core.util.Message;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class StudentRegController extends BaseController {

	@Resource
	private StudentService studentService;

	@Resource
	private StudentMajorService studentMajorService;

	@Resource
	private StudentClassService studentClassService;

	@RequestMapping(value = "/studentReg", method = RequestMethod.GET)
	public String showInfo(Model model) {
		logger.debug("GET-show");

		model.addAttribute("student", new Student());

		//添加专业列表
		List<StudentMajor> majors = studentMajorService.getStudentMajors();
		model.addAttribute("majors", majors);

		//添加班级列表
		List<StudentClass> stuClasses = studentClassService.getStudentClasses();
		model.addAttribute("stuClasses", stuClasses);

		return "studentReg";
	}

	@RequestMapping(value = "/studentReg", method = RequestMethod.POST)
	public String addStudent(Student student, Model model) {
		logger.debug("POST-addStudent");

		logger.debug("网页获取信息：{}", student);

		//保存
		Message message = new Message();
		boolean success = false;
		if (studentService.regStudent(student, message) != null) {
			success = true;
		}

		if (success) {
			model.addAttribute("successMessage", "注册学生成功，请登录");
			return "login";
		} else {
			model.addAttribute("errorMessage", "注册学生失败，请重新尝试。" + message);
			return showInfo(model);
		}
	}
}
