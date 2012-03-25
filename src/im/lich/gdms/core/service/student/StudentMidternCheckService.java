package im.lich.gdms.core.service.student;

import im.lich.gdms.core.model.student.Student;

public interface StudentMidternCheckService {
	public String getMidternCheckWarn(String loginName);

	public Student getMidternCheckInfo(String loginName);

	public Student saveMidternCheckInfo(Student student);
}
