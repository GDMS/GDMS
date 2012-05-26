package im.lich.gdms.core.dao.teacher;

import im.lich.gdms.base.dao.BaseDao;
import im.lich.gdms.core.model.teacher.DabianRecord;

import java.util.List;

public interface DabianRecordDao extends BaseDao<DabianRecord, Long> {

	public List<DabianRecord> findByTeacherId(Long teacherId);

	public DabianRecord findByStudentId(Long studentId);
}
