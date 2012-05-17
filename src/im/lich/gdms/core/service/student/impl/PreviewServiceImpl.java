package im.lich.gdms.core.service.student.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.student.PreviewDao;
import im.lich.gdms.core.dao.student.StudentDao;
import im.lich.gdms.core.dao.teacher.ThesisDao;
import im.lich.gdms.core.model.student.Preview;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.student.PreviewService;

@Service
@Transactional(readOnly = true)
public class PreviewServiceImpl extends BaseServiceImpl implements PreviewService {

	@Resource
	private ThesisDao thesisDao;

	@Resource
	private PreviewDao previewDao;

	@Resource
	private StudentDao studentDao;

	@Override
	public Integer getThesisPreviewCount(Long thesisId) {
		Assert.notNull(thesisId);
		Integer count = 0;

		Thesis t = thesisDao.findOne(thesisId);
		List<Preview> previews = previewDao.findByThesisId(thesisId);
		count = previews.size();
		logger.debug("获取课题{}预选次数：{}", t, count);

		return count;
	}

	@Override
	public List<Integer> getThesisesPreviewCount(List<Thesis> thesises) {
		List<Integer> counts = Lists.newArrayList();
		for (Thesis t : thesises) {
			Long thesisId = t.getId();
			Integer count = getThesisPreviewCount(thesisId);
			counts.add(count);
		}

		return counts;
	}

	@Override
	@Transactional(readOnly = false)
	public Preview saveStudentPreview(String studentLoginName, Long thesisId, int order) {
		Assert.notNull(studentLoginName);
		Assert.notNull(thesisId);
		if (order < 1 || order > 3)
			order = 3;

		Student student = studentDao.findByLoginName(studentLoginName);
		Long studentId = student.getId();

		List<Preview> previews = previewDao.findByThesisIdAndSubjectOrder(thesisId, order);
		if (previews.size() > 0)
			return null;

		List<Preview> previews2 = previewDao.findByStudentIdAndSubjectOrder(studentId, order);
		if (previews2.size() > 0)
			return null;

		Preview preview = new Preview(thesisId, studentId, order);
		return previewDao.save(preview);
	}

	@Override
	@Transactional(readOnly = false)
	public Preview delStudentPreview(String studentLoginName, Long thesisId) {
		Assert.notNull(studentLoginName);
		Assert.notNull(thesisId);

		Student student = studentDao.findByLoginName(studentLoginName);
		Long studentId = student.getId();

		Preview p = previewDao.findByStudentIdAndThesisId(studentId, thesisId);
		previewDao.delete(p);

		return p;
	}

	@Override
	public List<Thesis> getStudentPreviewThesises(String studentLoginName) {
		Assert.notNull(studentLoginName);

		List<Thesis> thesises = Lists.newArrayList();
		Student s = studentDao.findByLoginName(studentLoginName);
		Long studentId = s.getId();
		List<Preview> previews = previewDao.findByStudentId(studentId);
		for (Preview p : previews) {
			Long thesisId = p.getThesisId();
			Thesis t = thesisDao.findOne(thesisId);
			thesises.add(t);
		}
		logger.debug("学生已有预选课题：{}", StringUtils.join(thesises, ','));

		return thesises;
	}

	@Override
	public List<Integer> getStudentPreviewThesisesOrder(String studentLoginName, List<Thesis> studentThesises) {
		Assert.notNull(studentLoginName);

		List<Integer> orders = Lists.newArrayList();
		Student s = studentDao.findByLoginName(studentLoginName);
		Long studentId = s.getId();
		for (Thesis t : studentThesises) {
			Long thesisId = t.getId();
			Preview p = previewDao.findByStudentIdAndThesisId(studentId, thesisId);
			Integer order = p.getSubjectOrder();
			orders.add(order);
		}

		logger.debug("学生已有预选课题志愿：{}", StringUtils.join(orders, ','));

		return orders;
	}
}
