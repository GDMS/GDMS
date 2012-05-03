package im.lich.gdms.core.service.student.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.student.StudentMajorDao;
import im.lich.gdms.core.model.student.StudentMajor;
import im.lich.gdms.core.service.student.StudentMajorService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StudentMajorServiceImpl extends BaseServiceImpl implements StudentMajorService {

	@Resource
	private StudentMajorDao studentMajorDao;

	@Override
	public List<StudentMajor> getStudentMajors() {
		return studentMajorDao.findAll();
	}

	@Override
	public StudentMajor addOrUpdateStudentMajor(StudentMajor studentMajor) {
		StudentMajor _t = studentMajorDao.findByName(studentMajor.getName());
		if(_t != null){
			studentMajor.setId(_t.getId());
		}
		return studentMajorDao.save(studentMajor);
	}

	@Override
	@Transactional(readOnly = false)
	public StudentMajor delStudentMajor(Long studentMajorId) {
		StudentMajor studentMajor = studentMajorDao.findOne(studentMajorId);
		studentMajorDao.delete(studentMajor);
		
		return studentMajor;
	}

}
