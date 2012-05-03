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

	/**
	 * 添加或更新学生班级列表
	 * @param studentClass
	 * @return
	 */
	public StudentClass addOrUpdateStudentClass(StudentClass studentClass);

	/**
	 * 删除学生班级信息
	 * @param studentClassId
	 * @return
	 */
	public StudentClass delStudentClass(Long studentClassId);
}
