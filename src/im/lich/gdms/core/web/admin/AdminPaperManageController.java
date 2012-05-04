package im.lich.gdms.core.web.admin;

import im.lich.gdms.base.web.BaseController;
import im.lich.gdms.core.model.admin.Paper;
import im.lich.gdms.core.service.admin.PaperService;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminPaperManageController extends BaseController {

	@Resource
	private PaperService paperService;

	@RequestMapping(value = { "/paperManage" })
	public String showPaperManage(Model model) {
		List<Paper> papers = paperService.getPapersURLEncoded();
		model.addAttribute("papers", papers);

		return "admin/paperManage";
	}

	@RequestMapping(value = { "/paperManage/add" }, method = RequestMethod.POST)
	public String addOrUpdatePaperManage(String description, MultipartFile uploadFile, Model model) throws IOException {
		logger.debug("获取新增的Paper: {}-{}", description, uploadFile.getOriginalFilename());

		//保存
		boolean success = false;
		if (paperService.savePaper(description, uploadFile) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return "forward:/admin/paperManage";
	}

	@RequestMapping(value = { "/paperManage/del/{id}" })
	public String deletePaperManage(@PathVariable("id") Long id, Model model) throws IOException {
		logger.debug("删除Paper: {}", id);

		//保存
		boolean success = false;
		if (paperService.delPaper(id) != null) {
			success = true;
		}
		model.addAttribute("success", success);

		return "forward:/admin/paperManage";
	}
}