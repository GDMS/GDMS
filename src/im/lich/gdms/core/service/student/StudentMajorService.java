package im.lich.gdms.core.service.student;

import java.util.List;

import im.lich.gdms.base.service.BaseService;
import im.lich.gdms.core.model.student.StudentMajor;

public interface StudentMajorService extends BaseService {
	/**
	 * 获取学生专业列表
	 * @return List<StudentMajor>
	 */
	public List<StudentMajor> getStudentMajors();
}
