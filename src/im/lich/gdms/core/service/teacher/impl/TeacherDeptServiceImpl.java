package im.lich.gdms.core.service.teacher.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.teacher.TeacherDeptDao;
import im.lich.gdms.core.model.teacher.TeacherDept;
import im.lich.gdms.core.service.teacher.TeacherDeptService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TeacherDeptServiceImpl extends BaseServiceImpl implements TeacherDeptService {

	@Resource
	private TeacherDeptDao teacherDeptDao;

	@Override
	public List<TeacherDept> getTeacherDepts() {
		return teacherDeptDao.findAll();
	}
}
