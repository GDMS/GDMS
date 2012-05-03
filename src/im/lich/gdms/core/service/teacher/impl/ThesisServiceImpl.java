package im.lich.gdms.core.service.teacher.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.teacher.ThesisDao;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.teacher.ThesisService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Service
@Transactional(readOnly = true)
public class ThesisServiceImpl extends BaseServiceImpl implements ThesisService {

	@Resource
	private ThesisDao thesisDao;

	@Override
	public List<Thesis> getThesises() {
		List<Thesis> thesises = Lists.newArrayList(thesisDao.findAll());

		logger.debug("获取课题数量：{}", thesises.size());
		return thesises;
	}
}
