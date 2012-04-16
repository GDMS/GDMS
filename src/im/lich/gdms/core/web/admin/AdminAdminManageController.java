package im.lich.gdms.core.web.admin;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.admin.Administrator;
import im.lich.gdms.core.service.admin.AdminService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminAdminManageController extends BaseController {

	@Resource
	private AdminService adminService;

	@RequestMapping(value = { "/adminManage" })
	public String showAdminManage(Model model) {
		List<Administrator> admins = adminService.getAdministrators();
		model.addAttribute("admins", admins);

		return "admin/adminManage";
	}

	@RequestMapping(value = { "/adminManage/add" }, method = RequestMethod.POST)
	public String addAdminManage(Administrator admin, Model model) {
		logger.debug("获取新增的Administrator: {}", admin.toString());

		boolean success = false;
		if (adminService.addAdministrator(admin) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		List<Administrator> admins = adminService.getAdministrators();
		model.addAttribute("admins", admins);

		return "admin/adminManage";
	}

	@RequestMapping(value = { "/adminManage/del/{administratorId}" })
	public String delAdminManage(@PathVariable("administratorId") Long administratorId, Model model) {
		boolean success = false;
		if (adminService.delAdministrator(administratorId) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		List<Administrator> admins = adminService.getAdministrators();
		model.addAttribute("admins", admins);

		return "admin/adminManage";
	}
}