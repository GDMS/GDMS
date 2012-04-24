package im.lich.gdms.core.web.root;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.admin.News;
import im.lich.gdms.core.model.admin.Paper;
import im.lich.gdms.core.service.admin.NewsService;
import im.lich.gdms.core.service.admin.PaperService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，

 * 真正登录的POST请求由Filter完成,
 * 
 * @author calvin
 */
@Controller
public class IndexController extends BaseController {

	@Resource
	private PaperService paperService;

	@Resource
	private NewsService newsService;

	@RequestMapping("/index")
	public String index(Model model) {
		//获取消息列表
		List<News> allNewses = newsService.getNewses(NewsService.ALL);
		model.addAttribute("allNewses", allNewses);
		logger.debug("allNewses:{}", allNewses.size());

		List<News> studentNewses = newsService.getNewses(NewsService.STUDENT);
		model.addAttribute("studentNewses", studentNewses);
		logger.debug("studentNewses:{}", studentNewses.size());

		List<News> teacherNewses = newsService.getNewses(NewsService.TEACHER);
		model.addAttribute("teacherNewses", teacherNewses);
		logger.debug("teacherNewses:{}", teacherNewses.size());

		List<News> adminNewses = newsService.getNewses(NewsService.ADMIN);
		model.addAttribute("adminNewses", adminNewses);
		logger.debug("adminNewses:{}", adminNewses.size());
		//获取优秀论文列表
		List<Paper> papers = paperService.getPapersURLEncoded();
		model.addAttribute("papers", papers);
		return "index";
	}
}
