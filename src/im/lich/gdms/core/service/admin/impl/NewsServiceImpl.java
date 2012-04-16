package im.lich.gdms.core.service.admin.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.admin.NewsDao;
import im.lich.gdms.core.model.admin.News;
import im.lich.gdms.core.service.admin.NewsService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Service
@Transactional(readOnly = true)
public class NewsServiceImpl extends BaseServiceImpl implements NewsService {
	@Resource
	private NewsDao newsDao;

	@Override
	public List<News> getNewses() {
		List<News> newses = Lists.newArrayList(newsDao.findAll());

		logger.debug("获取News数量：{}", newses.size());
		return newses;
	}

	@Override
	public List<News> getNewses(String receiver) {
		List<News> newses = Lists.newArrayList(newsDao.findByReceiver(receiver));
		return newses;
	}

	@Override
	@Transactional(readOnly = false)
	public News addNews(News news) {
		return newsDao.save(news);
	}

	@Override
	@Transactional(readOnly = false)
	public News delNews(Long newsId) {
		News news = newsDao.findOne(newsId);
		newsDao.delete(news);
		return news;
	}
}
