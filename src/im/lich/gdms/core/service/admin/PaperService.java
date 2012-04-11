package im.lich.gdms.core.service.admin;

import java.util.List;

import im.lich.gdms.base.service.BaseService;
import im.lich.gdms.core.model.admin.Paper;

public interface PaperService extends BaseService {
	/**
	 * 获取优秀论文
	 * @return List<Paper> 优秀论文
	 */
	public List<Paper> getPapers();
}
