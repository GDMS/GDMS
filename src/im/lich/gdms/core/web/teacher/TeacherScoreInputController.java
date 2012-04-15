package im.lich.gdms.core.web.teacher;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.student.StudentService;
import im.lich.gdms.core.service.student.StudentThesisService;
import im.lich.gdms.core.service.teacher.DabianService;
import im.lich.gdms.core.service.teacher.PingyueService;
import im.lich.gdms.core.service.teacher.TeacherService;

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
public class TeacherScoreInputController extends BaseController {

	@Resource
	private TeacherService teacherService;

	@Resource
	private StudentService studentService;

	@Resource
	private StudentThesisService studentThesisService;

	@Resource
	private PingyueService pingyueService;

	@Resource
	private DabianService dabianService;

	@RequestMapping(value = "/scoreInput")
	public String showScoreInput(Model model) {
		logger.debug("GET-showScoreInput");

		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
		//获取学生信息
		List<Student> students = teacherService.getTeachingStudents(loginName);
		model.addAttribute("students", students);
		logger.debug("学生:{}", StringUtils.join(students, ','));

		//获取学生课题
		List<Thesis> studentsThesises = studentThesisService.getStudentsThesises(students);
		model.addAttribute("studentsThesises", studentsThesises);

		//获取指导教师成绩输入状态
		List<String> studentsZhidaoScoreStatuses = studentService.getStudentsZhidaoScoreStatuses(students);
		model.addAttribute("studentsZhidaoScoreStatuses", studentsZhidaoScoreStatuses);

		//获取评阅教师成绩输入状态
		List<String> studentsPingyueScoreStatuses = studentService.getStudentsPingyueScoreStatuses(students);
		model.addAttribute("studentsPingyueScoreStatuses", studentsPingyueScoreStatuses);

		//获取答辩小组成绩输入状态
		List<String> studentsDabianScoreStatuses = studentService.getStudentsDabianScoreStatuses(students);
		model.addAttribute("studentsDabianScoreStatuses", studentsDabianScoreStatuses);

		//评阅Tab
		//获取评阅教师输入成绩的学生
		List<Student> pingyueStudents = pingyueService.getStudents(loginName);
		model.addAttribute("pingyueStudents", pingyueStudents);
		//获取评阅教师输入成绩的学生的课题
		List<Thesis> pingyueStudentsThesises = studentThesisService.getStudentsThesises(pingyueStudents);
		model.addAttribute("pingyueStudentsThesises", pingyueStudentsThesises);

		//答辩Tab
		//获取答辩教师输入成绩的学生
		List<Student> dabianStudents = dabianService.getStudents(loginName);
		model.addAttribute("dabianStudents", dabianStudents);
		//获取答辩教师输入成绩的学生的课题
		List<Thesis> dabianStudentsThesises = studentThesisService.getStudentsThesises(pingyueStudents);
		model.addAttribute("dabianStudentsThesises", dabianStudentsThesises);

		return "/teacher/scoreInput";
	}

	//指导教师成绩更新
	@RequestMapping(value = "/scoreInput/zhidao/update/{studentLoginName}", method = RequestMethod.POST)
	public String updateScoreInputZhidao(@PathVariable("studentLoginName") String studentLoginName, Integer zd1grade,
			Integer zd2grade, Integer zd3grade, Integer zd4grade, Model model) {
		logger.debug("POST-updateScoreInputZhidao");

		Student student = new Student();
		student.setZd1grade(zd1grade);
		student.setZd2grade(zd2grade);
		student.setZd3grade(zd3grade);
		student.setZd4grade(zd4grade);
		logger.debug("网页获取信息：{}", student);

		//强制指定学生登录名
		student.setLoginName(studentLoginName);

		//保存
		boolean success = false;
		if (studentService.updateScoreInputZhidaoDetail(student) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return "forward:/teacher/scoreInput";
	}

	//评阅教师成绩输入
	@RequestMapping(value = "/scoreInput/pingyue/add", method = RequestMethod.POST)
	public String addScoreInputPingyue(String loginName, Integer py1grade, Integer py2grade, Model model) {
		logger.debug("POST-addScoreInputPingyue");

		String teacherLoginName = SecurityUtils.getSubject().getPrincipal().toString();

		Student student = new Student();
		student.setLoginName(loginName);
		student.setPy1grade(py1grade);
		student.setPy2grade(py2grade);

		boolean success = false;
		if (studentService.addScoreInputPingyue(student, teacherLoginName) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		//激活评阅Tab
		model.addAttribute("tabChoose", "pingyue");
		return "forward:/teacher/scoreInput";
	}

	//评阅教师成绩删除
	@RequestMapping(value = "/scoreInput/pingyue/del/{studentLoginName}", method = RequestMethod.GET)
	public String delScoreInputPingyue(@PathVariable("studentLoginName") String studentLoginName, Model model) {
		logger.debug("GET-delScoreInputPingyue");

		//删除
		boolean success = false;
		if (studentService.delScoreInputPingyue(studentLoginName) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		//激活评阅Tab
		model.addAttribute("tabChoose", "pingyue");
		return "forward:/teacher/scoreInput";
	}

	//答辩小组成绩输入
	@RequestMapping(value = "/scoreInput/dabian/add", method = RequestMethod.POST)
	public String addScoreInputDabian(String loginName, Integer db1grade, Integer db2grade, Model model) {
		logger.debug("POST-addScoreInputDabian");

		String teacherLoginName = SecurityUtils.getSubject().getPrincipal().toString();

		Student student = new Student();
		student.setLoginName(loginName);
		student.setDb1grade(db1grade);
		student.setDb2grade(db2grade);

		boolean success = false;
		if (studentService.addScoreInputDabian(student, teacherLoginName) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		//激活评阅Tab
		model.addAttribute("tabChoose", "dabian");
		return "forward:/teacher/scoreInput";
	}

	//答辩小组成绩删除
	@RequestMapping(value = "/scoreInput/dabian/del/{studentLoginName}", method = RequestMethod.GET)
	public String delScoreInputDabian(@PathVariable("studentLoginName") String studentLoginName, Model model) {
		logger.debug("GET-delScoreInputDabian");

		//删除
		boolean success = false;
		if (studentService.delScoreInputDabian(studentLoginName) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		//激活评阅Tab
		model.addAttribute("tabChoose", "dabian");
		return "forward:/teacher/scoreInput";
	}
}
