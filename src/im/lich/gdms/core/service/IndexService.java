package im.lich.gdms.core.service;

import im.lich.gdms.base.service.BaseService;
import im.lich.gdms.core.dao.admin.PaperDao;
import im.lich.gdms.core.model.admin.Paper;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Service
@Transactional(readOnly = true)
public class IndexService extends BaseService {
	@Resource
	private PaperDao paperDao;

	public List<Paper> getPapers() {
		//防止修改数据被持久化
		List<Paper> _papers = paperDao.findAll();
		List<Paper> papers = Lists.newArrayList(_papers);
		logger.debug("获取Paper数量：{}", papers.size());

		return papers;
	}
}
