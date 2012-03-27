package im.lich.gdms.core.model.student;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import im.lich.gdms.base.model.IdEntity;
import im.lich.gdms.core.model.generic.User;

@Entity
@Table
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Student extends IdEntity implements User {
	private static final long serialVersionUID = -1349146988009388680L;

	private String loginName;//学号
	private String name = "";//姓名
	private String gender = "";//性别
	private Double credit = 0d;//绩点
	private String phone = "";//电话
	private String email = "";//邮箱
	private String password = "0";//密码
	private String major = "";//专业班级
	private Long thesisId;//选中课题id
	private String assign = "";//分配状态
	private String ktup = "";//开题报告
	private String rwsup = "";//任务书
	private String transup = "";//翻译
	private String thesisup = "";//论文
	private String progress = "";//工作进度
	private String quality = "";//质量
	private String attitude = "";//工作态度
	private String duty = "";//出勤情况
	private String remark = "";
	private String warn = "";
	private Integer grade = 0;
	private String question1 = "";
	private String answer1 = "";
	private String question2 = "";
	private String answer2 = "";
	private String question3 = "";
	private String answer3 = "";
	private String projDesc = "";
	private String thesDesc = "";
	private Integer zd1grade = 0;
	private Integer zd2grade = 0;
	private Integer zd3grade = 0;
	private Integer zd4grade = 0;
	private Integer py1grade = 0;
	private Integer py2grade = 0;
	private Integer db1grade = 0;
	private Integer db2grade = 0;
	private String zdpingyu = "";
	private String pypingyu = "";
	private String dbpingyu = "";

	public Student() {
		super();
	}

	public Student(String loginName) {
		super();
		this.loginName = loginName;
	}

	public Student(String loginName, String name, String password) {
		super();
		this.loginName = loginName;
		this.name = name;
		this.password = password;
	}

	public Student(String loginName, String name, String gender, Double credit, String phone, String email,
			String password, String major, Long thesisId, String assign, String ktup, String rwsup, String transup,
			String thesisup, String progress, String quality, String attitude, String duty, String remark, String warn,
			Integer grade, String question1, String answer1, String question2, String answer2, String question3,
			String answer3, String projDesc, String thesDesc, Integer zd1grade, Integer zd2grade, Integer zd3grade,
			Integer zd4grade, Integer py1grade, Integer py2grade, Integer db1grade, Integer db2grade, String zdpingyu,
			String pypingyu, String dbpingyu) {
		super();
		this.loginName = loginName;
		this.name = name;
		this.gender = gender;
		this.credit = credit;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.major = major;
		this.thesisId = thesisId;
		this.assign = assign;
		this.ktup = ktup;
		this.rwsup = rwsup;
		this.transup = transup;
		this.thesisup = thesisup;
		this.progress = progress;
		this.quality = quality;
		this.attitude = attitude;
		this.duty = duty;
		this.remark = remark;
		this.warn = warn;
		this.grade = grade;
		this.question1 = question1;
		this.answer1 = answer1;
		this.question2 = question2;
		this.answer2 = answer2;
		this.question3 = question3;
		this.answer3 = answer3;
		this.projDesc = projDesc;
		this.thesDesc = thesDesc;
		this.zd1grade = zd1grade;
		this.zd2grade = zd2grade;
		this.zd3grade = zd3grade;
		this.zd4grade = zd4grade;
		this.py1grade = py1grade;
		this.py2grade = py2grade;
		this.db1grade = db1grade;
		this.db2grade = db2grade;
		this.zdpingyu = zdpingyu;
		this.pypingyu = pypingyu;
		this.dbpingyu = dbpingyu;
	}

	@Column(nullable = false, unique = true)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(length = 5, precision = 2, nullable = false)
	public Double getCredit() {
		return credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}

	@Column(nullable = false)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(nullable = false)
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(nullable = false, unique = true)
	public Long getThesisId() {
		return thesisId;
	}

	public void setThesisId(Long thesisId) {
		this.thesisId = thesisId;
	}

	@Column(nullable = false)
	public String getAssign() {
		return assign;
	}

	public void setAssign(String assign) {
		this.assign = assign;
	}

	@Column(nullable = false)
	public String getKtup() {
		return ktup;
	}

	public void setKtup(String ktup) {
		this.ktup = ktup;
	}

	@Column(nullable = false)
	public String getRwsup() {
		return rwsup;
	}

	public void setRwsup(String rwsup) {
		this.rwsup = rwsup;
	}

	@Column(nullable = false)
	public String getTransup() {
		return transup;
	}

	public void setTransup(String transup) {
		this.transup = transup;
	}

	@Column(nullable = false)
	public String getThesisup() {
		return thesisup;
	}

	public void setThesisup(String thesisup) {
		this.thesisup = thesisup;
	}

	@Column(nullable = false)
	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	@Column(nullable = false)
	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	@Column(nullable = false)
	public String getAttitude() {
		return attitude;
	}

	public void setAttitude(String attitude) {
		this.attitude = attitude;
	}

	@Column(nullable = false)
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	@Column(nullable = false)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(nullable = false)
	public String getWarn() {
		return warn;
	}

	public void setWarn(String warn) {
		this.warn = warn;
	}

	@Column(nullable = false)
	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	@Column(nullable = false)
	public String getQuestion1() {
		return question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	@Column(nullable = false)
	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	@Column(nullable = false)
	public String getQuestion2() {
		return question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	@Column(nullable = false)
	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	@Column(nullable = false)
	public String getQuestion3() {
		return question3;
	}

	public void setQuestion3(String question3) {
		this.question3 = question3;
	}

	@Column(nullable = false)
	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	@Column(nullable = false)
	public String getProjDesc() {
		return projDesc;
	}

	public void setProjDesc(String projDesc) {
		this.projDesc = projDesc;
	}

	@Column(nullable = false)
	public String getThesDesc() {
		return thesDesc;
	}

	public void setThesDesc(String thesDesc) {
		this.thesDesc = thesDesc;
	}

	@Column(nullable = false)
	public Integer getZd1grade() {
		return zd1grade;
	}

	public void setZd1grade(Integer zd1grade) {
		this.zd1grade = zd1grade;
	}

	@Column(nullable = false)
	public Integer getZd2grade() {
		return zd2grade;
	}

	public void setZd2grade(Integer zd2grade) {
		this.zd2grade = zd2grade;
	}

	@Column(nullable = false)
	public Integer getZd3grade() {
		return zd3grade;
	}

	public void setZd3grade(Integer zd3grade) {
		this.zd3grade = zd3grade;
	}

	@Column(nullable = false)
	public Integer getZd4grade() {
		return zd4grade;
	}

	public void setZd4grade(Integer zd4grade) {
		this.zd4grade = zd4grade;
	}

	@Column(nullable = false)
	public Integer getPy1grade() {
		return py1grade;
	}

	public void setPy1grade(Integer py1grade) {
		this.py1grade = py1grade;
	}

	@Column(nullable = false)
	public Integer getPy2grade() {
		return py2grade;
	}

	public void setPy2grade(Integer py2grade) {
		this.py2grade = py2grade;
	}

	@Column(nullable = false)
	public Integer getDb1grade() {
		return db1grade;
	}

	public void setDb1grade(Integer db1grade) {
		this.db1grade = db1grade;
	}

	@Column(nullable = false)
	public Integer getDb2grade() {
		return db2grade;
	}

	public void setDb2grade(Integer db2grade) {
		this.db2grade = db2grade;
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
