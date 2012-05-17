package im.lich.gdms.core.service.teacher.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.student.PreviewDao;
import im.lich.gdms.core.dao.student.StudentDao;
import im.lich.gdms.core.dao.teacher.ThesisDao;
import im.lich.gdms.core.model.student.Preview;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.teacher.ThesisService;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;

@Service
@Transactional(readOnly = true)
public class ThesisServiceImpl extends BaseServiceImpl implements ThesisService {

	@Resource
	private ThesisDao thesisDao;

	@Resource
	private PreviewDao previewDao;

	@Resource
	private StudentDao studentDao;

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

	@Override
	public List<Thesis> getUnassignedThesises() {
		List<Thesis> thesises = Lists.newArrayList(getThesises());
		Iterator<Thesis> it = thesises.iterator();
		while (it.hasNext()) {
			Thesis t = it.next();
			if (StringUtils.isNotBlank(t.getAssign()))//如果已经分配，则去除
				it.remove();
		}
		logger.debug("获取未分配课题数量：{}", thesises.size());

		return thesises;
	}

	@Override
	public List<Thesis> getUnassignedAndUnchoosedThesises(String studentLoginName) {
		Assert.notNull(studentLoginName);

		Student s = studentDao.findByLoginName(studentLoginName);
		Long studentId = s.getId();
		List<Preview> previews = previewDao.findByStudentId(studentId);

		List<Thesis> thesises = Lists.newArrayList(getUnassignedThesises());
		Iterator<Thesis> it = thesises.iterator();
		while (it.hasNext()) {
			Thesis t = it.next();
			for (Preview p : previews) {
				if (p.getThesisId() == t.getId()) {
					it.remove();
					break;
				}
			}
		}
		logger.debug("获取未分配且未选择课题数量：{}", thesises.size());

		return thesises;
	}
}
