package im.lich.gdms.core.service.root.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.admin.PaperDao;
import im.lich.gdms.core.model.admin.Paper;
import im.lich.gdms.core.service.root.IndexService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Service
@Transactional(readOnly = false)
public class IndexServiceImpl extends BaseServiceImpl implements IndexService {
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
