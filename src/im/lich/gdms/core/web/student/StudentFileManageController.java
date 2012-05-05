package im.lich.gdms.core.web.student;

import static org.apache.commons.lang3.StringUtils.stripToEmpty;
import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.service.student.StudentService;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/student")
public class StudentFileManageController extends BaseController {

	@Resource
	private StudentService studentService;

	@RequestMapping(value = { "/fileManage" })
	public String showFileManage(Model model) {
		String studentLoginName = SecurityUtils.getSubject().getPrincipal().toString();
		Student s = studentService.getStudent(studentLoginName);
		model.addAttribute("kt", stripToEmpty(s.getKtup()));//开题报告
		model.addAttribute("rws", stripToEmpty(s.getRwsup()));//任务书
		model.addAttribute("trans", stripToEmpty(s.getTransup()));//翻译
		model.addAttribute("thesis", stripToEmpty(s.getThesisup()));//论文

		logger.debug("开题报告：{}，任务书：{}，翻译：{}，论文：{}",
				new Object[] { s.getKtup(), s.getRwsup(), s.getTransup(), s.getThesisup() });

		return "student/fileManage";
	}

	@RequestMapping(value = { "/fileManage/add/kt" }, method = RequestMethod.POST)
	public String addKtup(MultipartFile ktup, Model model) {
		String studentLoginName = SecurityUtils.getSubject().getPrincipal().toString();
		logger.debug("添加学生：{}开题报告", studentLoginName);

		//保存
		boolean success = false;
		if (studentService.saveKtup(ktup, studentLoginName) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return "forward:/student/fileManage";
	}

	@RequestMapping(value = { "/fileManage/del/kt" })
	public String delKtup(Model model) {
		String studentLoginName = SecurityUtils.getSubject().getPrincipal().toString();
		logger.debug("删除学生：{}开题报告", studentLoginName);

		//保存
		boolean success = false;
		if (studentService.delKtup(studentLoginName) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return "forward:/student/fileManage";
	}
}
