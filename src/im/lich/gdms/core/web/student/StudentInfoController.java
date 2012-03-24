package im.lich.gdms.core.web.student;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.service.student.StudentInfoService;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/student")
public class StudentInfoController extends BaseController {

	@Resource
	private StudentInfoService studentInfoService;

	@RequestMapping(value = "/info")
	public String showInfo(ModelMap modelMap) {
		logger.debug("GET-showInfo");
		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
		Student student = studentInfoService.getStudentInfo(loginName);
		modelMap.put("student", student);
		return "student/info";
	}

	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public String postInfo(Student student, Model model) {
		logger.debug("POST-postInfo");

		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
		student.setLoginName(loginName);

		boolean success = true;
		model.addAttribute("success", success);
		boolean b = model.containsAttribute("student");
		logger.debug("has student:{}", b);
		return "student/info";
	}
}
