package im.lich.gdms.core.web.admin;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.security.ShiroDbRealm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminClearCacheController extends BaseController {

	@RequestMapping("/clear")
	public String index(ModelMap modelMap) {
		//清空Shiro权限缓存
		ShiroDbRealm shiroDbRealm = applicationContext.getBean(ShiroDbRealm.class);
		shiroDbRealm.clearAllCachedAuthorizationInfo();

		return "admin/index";
	}
}
