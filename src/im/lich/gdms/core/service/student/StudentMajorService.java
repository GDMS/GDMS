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

	/**
	 * 添加或更新学生专业信息
	 * @param studentMajor
	 * @return
	 */
	public StudentMajor addOrUpdateStudentMajor(StudentMajor studentMajor);

	/**
	 * 删除学生专业信息
	 * @param studentMajorId 专业id
	 * @return
	 */
	public StudentMajor delStudentMajor(Long studentMajorId);
}
