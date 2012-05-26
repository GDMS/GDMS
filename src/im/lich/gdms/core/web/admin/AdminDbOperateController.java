package im.lich.gdms.core.web.admin;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.security.ShiroDbRealm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/dbOperate")
public class AdminDbOperateController extends BaseController {

	@RequestMapping("")
	public String index(Model model) {
		return "admin/dbOperate";
	}

	@RequestMapping("/clear")
	public String clear(Model model) {
		//清空Shiro权限缓存
		ShiroDbRealm shiroDbRealm = applicationContext.getBean(ShiroDbRealm.class);
		shiroDbRealm.clearAllCachedAuthorizationInfo();

		model.addAttribute("success", true);

		return "redirect:/admin/dbOperate";
	}
}
