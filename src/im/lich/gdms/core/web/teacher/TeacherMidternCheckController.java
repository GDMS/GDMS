package im.lich.gdms.core.web.teacher;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.service.teacher.TeacherMidternCheckService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/teacher")
public class TeacherMidternCheckController extends BaseController {
	@Resource
	private TeacherMidternCheckService teacherMidternCheckService;

	@RequestMapping(value = "/midternCheck")
	public String showMidternCheck(Model model) {
		logger.debug("GET-showMidternCheck");

		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
		//获取中期检查信息
		List<Student> students = teacherMidternCheckService.getStudents(loginName);
		model.addAttribute("students", students);

		logger.debug("学生:{}", StringUtils.join(students, ','));

		return "teacher/midternCheck";
	}

	@RequestMapping(value = "/midternCheck/warn/{studentLoginName}")
	public String showWarn(@PathVariable("studentLoginName") String studentLoginName, Model model) {
		logger.debug("GET-showWarn");

		Student student = teacherMidternCheckService.getStudent(studentLoginName);
		model.addAttribute("student", student);

		return "teacher/midternCheckWarn";
	}

	@RequestMapping(value = "/midternCheck/warn/{studentLoginName}", method = RequestMethod.POST)
	public String setWarn(@PathVariable("studentLoginName") String studentLoginName, Student student, Model model) {
		logger.debug("GET-setWarn");

		logger.debug("网页获取信息：{}", student);

		//强制指定学生登录名
		student.setLoginName(studentLoginName);

		//保存
		boolean success = false;
		if (teacherMidternCheckService.saveMidternCheckWarn(student) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return "teacher/midternCheckWarn";
	}

	@RequestMapping(value = "/midternCheck/warn/{studentLoginName}/delete")
	public String delWarn(@PathVariable("studentLoginName") String studentLoginName, Model model) {
		logger.debug("GET-setWarn");

		//保存
		boolean success = false;
		if (teacherMidternCheckService.delMidternCheckWarn(studentLoginName) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
		//获取中期检查信息
		List<Student> students = teacherMidternCheckService.getStudents(loginName);
		model.addAttribute("students", students);

		return "teacher/midternCheck";
	}
}
