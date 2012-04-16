package im.lich.gdms.core.service.admin;

import im.lich.gdms.base.service.BaseService;
import im.lich.gdms.core.model.admin.Administrator;

import java.util.List;

public interface AdminService extends BaseService {

	/**
	 * 获取管理员列表
	 * @return List<News>
	 */
	public List<Administrator> getAdministrators();

	/**
	 * 增加管理员
	 * @param news 消息
	 * @return News or null
	 */
	public Administrator addAdministrator(Administrator administrator);

	/**
	 * 删除管理员
	 * @param newsId 消息ID
	 * @return News
	 */
	public Administrator delAdministrator(Long administratorId);
}
