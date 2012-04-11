package im.lich.gdms.core.web.student;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.service.student.StudentService;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/student")
public class StudentMidternCheckController extends BaseController {
	@Resource
	private StudentService studentService;

	@RequestMapping(value = "/midternCheck")
	public String showMidternCheck(Model model) {
		logger.debug("GET-showMidternCheck");

		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
		//获取警告信息
		String warn = studentService.getMidternCheckWarn(loginName);
		model.addAttribute("warn", warn);

		//获取中期检查信息
		Student student = studentService.getMidternCheckInfo(loginName);
		model.addAttribute("student", student);

		return "student/midternCheck";
	}

	@RequestMapping(value = "/midternCheck", method = RequestMethod.POST)
	public String postMidternCheck(Student student, Model model) {
		logger.debug("POST-postMidternCheck");

		logger.debug("网页获取信息：{}", student);
		//强制指定用户登录名
		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
		student.setLoginName(loginName);

		//保存
		boolean success = false;
		if (studentService.saveMidternCheckInfo(student) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return "student/midternCheck";
	}
}
