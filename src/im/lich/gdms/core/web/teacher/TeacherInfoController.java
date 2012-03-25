package im.lich.gdms.core.web.teacher;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.teacher.Teacher;
import im.lich.gdms.core.model.teacher.TeacherDept;
import im.lich.gdms.core.service.teacher.TeacherInfoService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/teacher")
public class TeacherInfoController extends BaseController {

	@Resource
	private TeacherInfoService teacherInfoService;

	@RequestMapping(value = "/info")
	public String showInfo(Model model) {
		logger.debug("GET-showInfo");

		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
		Teacher teacher = teacherInfoService.getTeacherInfo(loginName);
		model.addAttribute("teacher", teacher);

		//添加部门列表
		List<TeacherDept> depts = teacherInfoService.getTeacherDepts();
		model.addAttribute("depts", depts);

		return "teacher/info";
	}

	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public String postInfo(Teacher teacher, Model model) {
		logger.debug("POST-postInfo");

		logger.debug("网页获取信息：{}", teacher);
		//强制指定用户登录名
		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
		teacher.setLoginName(loginName);

		//保存
		boolean success = false;
		if (teacherInfoService.saveTeacherInfo(teacher) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		//添加部门列表
		List<TeacherDept> depts = teacherInfoService.getTeacherDepts();
		model.addAttribute("depts", depts);

		return "teacher/info";
	}
}