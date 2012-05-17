package im.lich.gdms.core.service.student;

import java.util.List;

import im.lich.gdms.base.service.BaseService;
import im.lich.gdms.core.model.student.Preview;
import im.lich.gdms.core.model.teacher.Thesis;

public interface PreviewService extends BaseService {
	/**
	 * 获取课题被预选次数
	 * @param thesisId Long
	 * @return Integer
	 */
	public Integer getThesisPreviewCount(Long thesisId);

	/**
	 * 获取课题被预选次数
	 * @param thesises List<Thesis>
	 * @return List<Integer>
	 */
	public List<Integer> getThesisesPreviewCount(List<Thesis> thesises);

	/**
	 * 学生保存预选课题
	 * @param studentId
	 * @param thesisId
	 * @param order
	 * @return
	 */
	public Preview saveStudentPreview(String studentLoginName, Long thesisId, int order);

	/**
	 * 学生删除预选课题
	 * @param studentLoginName
	 * @param thesisId
	 * @return
	 */
	public Preview delStudentPreview(String studentLoginName, Long thesisId);

	/**
	 * 获取学生预选课题
	 * @param studentLoginName
	 * @return List<Preview>
	 */
	public List<Thesis> getStudentPreviewThesises(String studentLoginName);

	/**
	 * 获取学生预选课题的志愿顺序
	 * @param studentPreviews
	 * @return
	 */
	public List<Integer> getStudentPreviewThesisesOrder(String studentLoginName, List<Thesis> studentThesises);
}
