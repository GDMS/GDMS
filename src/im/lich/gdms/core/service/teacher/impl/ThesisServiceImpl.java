package im.lich.gdms.core.service.teacher.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.teacher.ThesisDao;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.teacher.ThesisService;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;

@Service
@Transactional(readOnly = true)
public class ThesisServiceImpl extends BaseServiceImpl implements ThesisService {

	@Resource
	private ThesisDao thesisDao;

	@Override
	public List<Thesis> getThesises() {
		List<Thesis> thesises = Lists.newArrayList(thesisDao.findAll());

		for (Thesis t : thesises) {
			String majorRestrict = t.getMajorRestrict();
			String[] ms = majorRestrict.split(",");
			List<String> majors = Arrays.asList(ms);
			t.setMajorRes(majors);
		}

		logger.debug("获取课题数量：{}", thesises.size());
		return thesises;
	}

	@Override
	public Thesis getThesis(Long thesisId) {
		Assert.notNull(thesisId);

		Thesis t = thesisDao.findOne(thesisId);
		String majorRestrict = t.getMajorRestrict();
		String[] ms = majorRestrict.split(",");
		List<String> majors = Arrays.asList(ms);
		t.setMajorRes(majors);

		return t;
	}
}
