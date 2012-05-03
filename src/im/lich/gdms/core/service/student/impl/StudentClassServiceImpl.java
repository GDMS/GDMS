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

	@Override
	public StudentClass addOrUpdateStudentClass(StudentClass studentClass) {
		StudentClass _t = studentClassDao.findByName(studentClass.getName());
		
		if(_t != null){
			studentClass.setId(_t.getId());
		}
		return studentClassDao.save(studentClass);
	}

	@Override
	@Transactional(readOnly = false)
	public StudentClass delStudentClass(Long studentClassId) {
		StudentClass studentClass = studentClassDao.findOne(studentClassId);
		studentClassDao.delete(studentClass);
		
		return studentClass;
	}
}
