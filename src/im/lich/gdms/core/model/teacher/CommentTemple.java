package im.lich.gdms.core.model.teacher;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import im.lich.gdms.base.model.IdEntity;

@Entity
@Table
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommentTemple extends IdEntity {
	private static final long serialVersionUID = -180477163122663159L;

	private String grade;//评语等第

	private String zdpingyu = "";//指导评语

	private String pypingyu = "";//评阅评语

	private String dbpingyu = "";//答辩评语

	public CommentTemple(String grade) {
		super();
		this.grade = grade;
	}

	public CommentTemple(String grade, String zdpingyu, String pypingyu, String dbpingyu) {
		super();
		this.grade = grade;
		this.zdpingyu = zdpingyu;
		this.pypingyu = pypingyu;
		this.dbpingyu = dbpingyu;
	}

	@Column(nullable = false)
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(nullable = false)
	public String getZdpingyu() {
		return zdpingyu;
	}

	public void setZdpingyu(String zdpingyu) {
		this.zdpingyu = zdpingyu;
	}

	@Column(nullable = false)
	public String getPypingyu() {
		return pypingyu;
	}

	public void setPypingyu(String pypingyu) {
		this.pypingyu = pypingyu;
	}

	@Column(nullable = false)
	public String getDbpingyu() {
		return dbpingyu;
	}

	public void setDbpingyu(String dbpingyu) {
		this.dbpingyu = dbpingyu;
	}

}
