package im.lich.gdms.core.dao.teacher;

import im.lich.gdms.base.dao.BaseDao;
import im.lich.gdms.core.model.teacher.PingyueRecord;

import java.util.List;

public interface PingyueRecordDao extends BaseDao<PingyueRecord, Long> {

	public List<PingyueRecord> findByTeacherId(Long teacherId);

	public PingyueRecord findByStudentId(Long studnetId);

}
