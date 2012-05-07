package im.lich.gdms.core.web.teacher;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.student.StudentService;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/teacher")
public class TeacherPingyuInputController extends BaseController {

	@Resource
	private StudentService studentService;

	//查看指导教师评语
	@RequestMapping(value = "/pingyuInput/zhidao/{studentLoginName}")
	public String showPingyuInputZhidao(@PathVariable("studentLoginName") String studentLoginName, Model model) {
		logger.debug("GET-showPingyuInputZhidao");

		//获取学生信息、课题信息
		Student student = studentService.getStudent(studentLoginName);
		model.addAttribute("student", student);
		Thesis thesis = studentService.getStudentThesis(student);
		String thesisName = thesis.getName();
		model.addAttribute("thesisName", thesisName);

		//网页只显示指导老师部分
		model.addAttribute("type", "zhidao");

		return "/teacher/pingyuInput";
	}

	//更新指导教师评语
	@RequestMapping(value = "/pingyuInput/zhidao/{studentLoginName}", method = RequestMethod.POST)
	public String updatePingyuInputZhidao(@PathVariable("studentLoginName") String studentLoginName, Student student,
			Model model) {
		logger.debug("POST-updatePingyuInputZhidao");
		logger.debug("网页获取信息：{}", student);

		//强制指定学生登录名
		student.setLoginName(studentLoginName);

		//保存
		boolean success = false;
		if (studentService.updatePingyu(student) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return showPingyuInputZhidao(studentLoginName, model);
	}

	//查看评阅教师评语
	@RequestMapping(value = "/pingyuInput/pingyue/{studentLoginName}")
	public String showPingyuInputPingyue(@PathVariable("studentLoginName") String studentLoginName, Model model) {
		logger.debug("GET-showPingyuInputPingyue");

		//获取学生信息、课题信息
		Student student = studentService.getStudent(studentLoginName);
		model.addAttribute("student", student);
		Thesis thesis = studentService.getStudentThesis(student);
		String thesisName = thesis.getName();
		model.addAttribute("thesisName", thesisName);

		//网页只显示指导老师部分
		model.addAttribute("type", "pingyue");

		return "/teacher/pingyuInput";
	}

	//更新评阅教师评语
	@RequestMapping(value = "/pingyuInput/pingyue/{studentLoginName}", method = RequestMethod.POST)
	public String updatePingyuInputPingyue(@PathVariable("studentLoginName") String studentLoginName, Student student,
			Model model) {
		logger.debug("POST-updatePingyuInputPingyue");
		logger.debug("网页获取信息：{}", student);

		//强制指定学生登录名
		student.setLoginName(studentLoginName);

		//保存
		boolean success = false;
		if (studentService.updatePingyu(student) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return showPingyuInputPingyue(studentLoginName, model);
	}

	//查看答辩小组评语
	@RequestMapping(value = "/pingyuInput/dabian/{studentLoginName}")
	public String showPingyuInputDabian(@PathVariable("studentLoginName") String studentLoginName, Model model) {
		logger.debug("GET-showPingyuInputDabian");

		//获取学生信息、课题信息
		Student student = studentService.getStudent(studentLoginName);
		model.addAttribute("student", student);
		Thesis thesis = studentService.getStudentThesis(student);
		String thesisName = thesis.getName();
		model.addAttribute("thesisName", thesisName);

		//网页只显示指导老师部分
		model.addAttribute("type", "dabian");

		return "/teacher/pingyuInput";
	}

	//更新答辩小组评语
	@RequestMapping(value = "/pingyuInput/dabian/{studentLoginName}", method = RequestMethod.POST)
	public String updatePingyuInputDabian(@PathVariable("studentLoginName") String studentLoginName, Student student,
			Model model) {
		logger.debug("POST-updatePingyuInputDabian");
		logger.debug("网页获取信息：{}", student);

		//强制指定学生登录名
		student.setLoginName(studentLoginName);

		//保存
		boolean success = false;
		if (studentService.updatePingyu(student) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return showPingyuInputDabian(studentLoginName, model);
	}
}
