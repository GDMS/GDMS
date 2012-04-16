package im.lich.gdms.core.service.admin;

import im.lich.gdms.base.service.BaseService;
import im.lich.gdms.core.model.admin.News;

import java.util.List;

public interface NewsService extends BaseService {

	public static final String ALL = "all";
	public static final String STUDENT = "student";
	public static final String TEACHER = "teacher";
	public static final String ADMIN = "admin";

	/**
	 * 获取消息
	 * @return List<News>
	 */
	public List<News> getNewses();

	/**
	 * 根据接收者获取消息
	 * @param receiver 接收者
	 * @return List<News>
	 */
	public List<News> getNewses(String receiver);

	/**
	 * 增加消息
	 * @param news 消息
	 * @return News or null
	 */
	public News addNews(News news);

	/**
	 * 删除消息
	 * @param newsId 消息ID
	 * @return News
	 */
	public News delNews(Long newsId);
}
