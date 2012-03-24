package im.lich.gdms.core.service.student.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.student.StudentDao;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.service.student.StudentInfoService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StudentInfoServiceImpl extends BaseServiceImpl implements StudentInfoService {

	@Resource
	private StudentDao studentDao;

	@Override
	public Student getStudentInfo(String loginName) {
		return studentDao.findByLoginName(loginName);
	}

}
