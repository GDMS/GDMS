package im.lich.gdms.core.web.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import im.lich.gdms.base.web.BaseController;

@Controller
@RequestMapping("/student")
public class StudentIndexController extends BaseController {

	@RequestMapping(value = { "/", "/index" })
	public String index(ModelMap modelMap) {
		return "student/index";
	}
}
