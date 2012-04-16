package im.lich.gdms.core.service.student;

import im.lich.gdms.base.service.BaseService;
import im.lich.gdms.core.model.student.StudentClass;

import java.util.List;

public interface StudentClassService extends BaseService {
	/**
	 * 获取学生专业列表
	 * @return List<StudentMajor>
	 */
	public List<StudentClass> getStudentClasses();
}
