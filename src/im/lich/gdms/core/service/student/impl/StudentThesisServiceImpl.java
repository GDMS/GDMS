package im.lich.gdms.core.service.student.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.teacher.ThesisDao;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.student.StudentThesisService;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;

@Service
@Transactional(readOnly = true)
public class StudentThesisServiceImpl extends BaseServiceImpl implements StudentThesisService {

	@Resource
	private ThesisDao thesisDao;

	@Override
	public List<Thesis> getStudentsThesises(Collection<Student> students) {
		List<Thesis> thesises = Lists.newArrayList();
		for (Student s : students) {
			Long thesisId = s.getThesisId();
			Thesis t = thesisDao.findOne(thesisId);
			Assert.notNull(t);
			Assert.hasText(t.getAssign());
			thesises.add(t);
		}
		return thesises;
	}
}
