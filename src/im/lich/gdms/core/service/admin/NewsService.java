package im.lich.gdms.core.service.admin;

import im.lich.gdms.base.service.BaseService;
import im.lich.gdms.core.model.admin.News;

import java.util.List;

public interface NewsService extends BaseService {
	/**
	 * 获取消息
	 * @return List<News>
	 */
	public List<News> getNewes();

	/**
	 * 保存消息
	 * @param news 消息
	 * @return News or null
	 */
	public News saveNews(News news);

	/**
	 * 删除消息
	 * @param newsId 消息ID
	 * @return News
	 */
	public News delNews(Long newsId);
}
