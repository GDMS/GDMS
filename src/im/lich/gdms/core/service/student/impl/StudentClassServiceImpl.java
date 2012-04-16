package im.lich.gdms.core.service.student.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.student.StudentClassDao;
import im.lich.gdms.core.model.student.StudentClass;
import im.lich.gdms.core.service.student.StudentClassService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StudentClassServiceImpl extends BaseServiceImpl implements StudentClassService {

	@Resource
	private StudentClassDao studentClassDao;

	@Override
	public List<StudentClass> getStudentClasses() {
		return studentClassDao.findAll();
	}
}
