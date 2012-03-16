package im.lich.gdms.core.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.admin.Paper;
import im.lich.gdms.core.service.IndexService;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，

 * 真正登录的POST请求由Filter完成,
 * 
 * @author calvin
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

	@Resource
	private IndexService indexService;

	@RequestMapping(value = "")
	public String index(ModelMap modelMap) {
		List<Paper> papers = indexService.getPapers();
		modelMap.put("papers", papers);
		return "index";
	}

}
