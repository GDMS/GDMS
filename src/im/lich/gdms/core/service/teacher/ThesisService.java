package im.lich.gdms.core.service.teacher;

import im.lich.gdms.base.service.BaseService;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.util.AssignStatus;

import java.util.List;

public interface ThesisService extends BaseService {
	/**
	 * 获取所有课题信息
	 * @return List<Thesis>
	 */
	public List<Thesis> getThesises();

	/**
	 * 获取指定课题
	 * @param thesisId 课题ID
	 * @return Thesis
	 */
	public Thesis getThesis(Long thesisId);

	/**
	 * 获取未分配课题
	 * @return List<Thesis>
	 */
	public List<Thesis> getUnassignedThesises();

	/**
	 * 获取未分配和未选择的课题
	 * @param studentLoginName
	 * @return List<Thesis>
	 */
	public List<Thesis> getUnassignedAndUnchoosedThesises(String studentLoginName);

	/**
	 * 学生是否已被分配课题
	 * @param studentLoginName
	 * @return
	 */
	public boolean isAssigned(String studentLoginName);

	/**
	 * 获取已分配课题
	 * @return
	 */
	public List<Thesis> getAssignedThesis();

	/**
	 * 获取已分配课题学生
	 * @return
	 */
	public List<Student> getAssignedThesisStudent();

	/**
	 * 分配课题
	 * @param thesisId
	 * @param studentId
	 * @param assignStatus
	 * @return Thesis or null
	 */
	public Thesis assignThesis(Long thesisId, Long studentId, AssignStatus assignStatus);

	/**
	 * 取消课题分配
	 * @param studentId
	 * @return
	 */
	public Thesis unassignThesisByStudent(Long studentId);

	/**
	 * 取消课题分配
	 * @param thesisId
	 * @return
	 */
	public Thesis unassignThesis(Long thesisId);
}