package im.lich.gdms.core.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import im.lich.gdms.base.web.BaseController;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，

 * 真正登录的POST请求由Filter完成,
 * 
 * @author calvin
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

	@RequestMapping(value = "")
	public String index() {
		return "index";
	}
}
