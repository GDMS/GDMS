package im.lich.gdms.core.model.student;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import im.lich.gdms.base.model.IdEntity;
import im.lich.gdms.core.model.generic.User;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Student extends IdEntity implements User {
	private static final long serialVersionUID = -1349146988009388680L;

	@Column(length = 20, nullable = false, unique = true)
	private String loginName;//学号

	@Column(length = 20, nullable = false)
	private String name;//姓名

	@Column(length = 20, nullable = false)
	private String gender;//性别

	@Column(length = 20, nullable = false)
	private Double credit;//绩点

	@Column(length = 50, nullable = false)
	private String phone;//电话

	@Column(length = 250, nullable = false)
	private String email;//邮箱

	@Column(length = 20, nullable = false)
	private String password;//密码

	@Column(length = 50, nullable = false)
	private String major;//专业班级

	@Column(length = 20, nullable = false, unique = true)
	private String thesis_id;//选中课题id

	@Column(length = 20, nullable = false)
	private String assign;//分配状态

	@Column(length = 250, nullable = false)
	private String ktup;//开题报告

	@Column(length = 250, nullable = false)
	private String rwsup;//任务书

	@Column(length = 250, nullable = false)
	private String transup;//翻译

	@Column(length = 250, nullable = false)
	private String thesisup;//论文

	@Column(length = 250, nullable = false)
	private String progress;

	@Column(length = 250, nullable = false)
	private String quality;

	@Column(length = 250, nullable = false)
	private String attitude;

	@Column(length = 250, nullable = false)
	private String duty;

	@Column(length = 250, nullable = false)
	private String remark;

	@Column(length = 250, nullable = false)
	private String warn;

	@Column(length = 250, nullable = false)
	private Integer grade;

	@Column(length = 250, nullable = false)
	private String question1;

	@Column(length = 250, nullable = false)
	private String answer1;

	@Column(length = 250, nullable = false)
	private String question2;

	@Column(length = 250, nullable = false)
	private String answer2;

	@Column(length = 250, nullable = false)
	private String question3;

	@Column(length = 250, nullable = false)
	private String answer3;

	@Column(length = 250, nullable = false)
	private String projDesc;

	@Column(length = 250, nullable = false)
	private String thesDesc;

	@Column(length = 250, nullable = false)
	private Integer zd1grade;

	@Column(length = 250, nullable = false)
	private Integer zd2grade;

	@Column(length = 250, nullable = false)
	private Integer zd3grade;

	@Column(length = 250, nullable = false)
	private Integer zd4grade;

	@Column(length = 250, nullable = false)
	private Integer py1grade;

	@Column(length = 250, nullable = false)
	private Integer py2grade;

	@Column(length = 250, nullable = false)
	private Integer db1grade;

	@Column(length = 250, nullable = false)
	private Integer db2grade;

	@Column(length = 250, nullable = false)
	private String zdpingyu;

	@Column(length = 250, nullable = false)
	private String pypingyu;

	@Column(length = 250, nullable = false)
	private String dbpingyu;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Double getCredit() {
		return credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getThesis_id() {
		return thesis_id;
	}

	public void setThesis_id(String thesis_id) {
		this.thesis_id = thesis_id;
	}

	public String getAssign() {
		return assign;
	}

	public void setAssign(String assign) {
		this.assign = assign;
	}

	public String getKtup() {
		return ktup;
	}

	public void setKtup(String ktup) {
		this.ktup = ktup;
	}

	public String getRwsup() {
		return rwsup;
	}

	public void setRwsup(String rwsup) {
		this.rwsup = rwsup;
	}

	public String getTransup() {
		return transup;
	}

	public void setTransup(String transup) {
		this.transup = transup;
	}

	public String getThesisup() {
		return thesisup;
	}

	public void setThesisup(String thesisup) {
		this.thesisup = thesisup;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getAttitude() {
		return attitude;
	}

	public void setAttitude(String attitude) {
		this.attitude = attitude;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWarn() {
		return warn;
	}

	public void setWarn(String warn) {
		this.warn = warn;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getQuestion1() {
		return question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getQuestion2() {
		return question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getQuestion3() {
		return question3;
	}

	public void setQuestion3(String question3) {
		this.question3 = question3;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getProjDesc() {
		return projDesc;
	}

	public void setProjDesc(String projDesc) {
		this.projDesc = projDesc;
	}

	public String getThesDesc() {
		return thesDesc;
	}

	public void setThesDesc(String thesDesc) {
		this.thesDesc = thesDesc;
	}

	public Integer getZd1grade() {
		return zd1grade;
	}

	public void setZd1grade(Integer zd1grade) {
		this.zd1grade = zd1grade;
	}

	public Integer getZd2grade() {
		return zd2grade;
	}

	public void setZd2grade(Integer zd2grade) {
		this.zd2grade = zd2grade;
	}

	public Integer getZd3grade() {
		return zd3grade;
	}

	public void setZd3grade(Integer zd3grade) {
		this.zd3grade = zd3grade;
	}

	public Integer getZd4grade() {
		return zd4grade;
	}

	public void setZd4grade(Integer zd4grade) {
		this.zd4grade = zd4grade;
	}

	public Integer getPy1grade() {
		return py1grade;
	}

	public void setPy1grade(Integer py1grade) {
		this.py1grade = py1grade;
	}

	public Integer getPy2grade() {
		return py2grade;
	}

	public void setPy2grade(Integer py2grade) {
		this.py2grade = py2grade;
	}

	public Integer getDb1grade() {
		return db1grade;
	}

	public void setDb1grade(Integer db1grade) {
		this.db1grade = db1grade;
	}

	public Integer getDb2grade() {
		return db2grade;
	}

	public void setDb2grade(Integer db2grade) {
		this.db2grade = db2grade;
	}

	public String getZdpingyu() {
		return zdpingyu;
	}

	public void setZdpingyu(String zdpingyu) {
		this.zdpingyu = zdpingyu;
	}

	public String getPypingyu() {
		return pypingyu;
	}

	public void setPypingyu(String pypingyu) {
		this.pypingyu = pypingyu;
	}

	public String getDbpingyu() {
		return dbpingyu;
	}

	public void setDbpingyu(String dbpingyu) {
		this.dbpingyu = dbpingyu;
	}

}
