package im.lich.gdms.core.web.student;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.student.StudentMajor;
import im.lich.gdms.core.service.student.StudentMajorService;
import im.lich.gdms.core.service.student.StudentService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/student")
public class StudentInfoController extends BaseController {

	@Resource
	private StudentService studentService;

	@Resource
	private StudentMajorService studentMajorService;

	@RequiresPermissions("student:info:show")
	@RequestMapping(value = "/info")
	public String showInfo(Model model) {
		logger.debug("GET-showInfo");

		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
		Student student = studentService.getStudentInfo(loginName);
		model.addAttribute("student", student);

		//添加专业列表
		List<StudentMajor> majors = studentMajorService.getStudentMajors();
		model.addAttribute("majors", majors);

		return "student/info";
	}

	@RequiresPermissions("student:info:mod")
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public String modInfo(Student student, Model model) {
		logger.debug("POST-postInfo");

		logger.debug("网页获取信息：{}", student);
		//强制指定用户登录名
		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
		student.setLoginName(loginName);

		//保存
		boolean success = false;
		if (studentService.saveStudentInfo(student) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		//添加专业列表
		List<StudentMajor> majors = studentMajorService.getStudentMajors();
		model.addAttribute("majors", majors);

		return "student/info";
	}
}
