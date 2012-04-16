package im.lich.gdms.core.web.admin;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.admin.News;
import im.lich.gdms.core.service.admin.NewsService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminNewsManageController extends BaseController {

	@Resource
	private NewsService newsService;

	@RequestMapping(value = { "/newsManage" })
	public String showNewsManage(Model model) {
		List<News> newses = newsService.getNewes();
		model.addAttribute("newses", newses);

		return "admin/newsManage";
	}

	@RequestMapping(value = { "/newsManage/add" }, method = RequestMethod.POST)
	public String saveNewsManage(News news, Model model) {
		logger.debug("获取新增的News: {}", news.toString());

		boolean success = false;
		if (newsService.saveNews(news) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		List<News> newses = newsService.getNewes();
		model.addAttribute("newses", newses);

		return "admin/newsManage";
	}

	@RequestMapping(value = { "/newsManage/del/{newsId}" })
	public String saveNewsManage(@PathVariable("newsId") Long newsId, Model model) {
		boolean success = false;
		if (newsService.delNews(newsId) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		List<News> newses = newsService.getNewes();
		model.addAttribute("newses", newses);

		return "admin/newsManage";
	}
}