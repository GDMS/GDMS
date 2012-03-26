package im.lich.gdms.core.dao.teacher;

import im.lich.gdms.base.dao.BaseDao;
import im.lich.gdms.core.model.teacher.RemarkRecord;

import java.util.List;

public interface RemarkRecordDao extends BaseDao<RemarkRecord, Long> {

	public List<RemarkRecord> findByTeacherId(Long teacherId);

	public RemarkRecord findByStudentId(Long studnetId);

}
