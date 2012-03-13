package im.lich.gdms.core.model.teacher;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import im.lich.gdms.base.model.IdEntity;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DabianTeacher extends IdEntity {
	private static final long serialVersionUID = -6626250567951247734L;

	private String teacher_id;//答辩老师id

	private String thesis_id;//答辩课题

	private String student_id;//答辩学生

	public String getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getThesis_id() {
		return thesis_id;
	}

	public void setThesis_id(String thesis_id) {
		this.thesis_id = thesis_id;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

}
