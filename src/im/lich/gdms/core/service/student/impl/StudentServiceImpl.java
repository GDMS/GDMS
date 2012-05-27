package im.lich.gdms.core.service.student.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.student.StudentDao;
import im.lich.gdms.core.dao.teacher.DabianRecordDao;
import im.lich.gdms.core.dao.teacher.PingyueRecordDao;
import im.lich.gdms.core.dao.teacher.TeacherDao;
import im.lich.gdms.core.dao.teacher.ThesisDao;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.DabianRecord;
import im.lich.gdms.core.model.teacher.PingyueRecord;
import im.lich.gdms.core.model.teacher.Teacher;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.student.StudentService;
import im.lich.gdms.core.util.Message;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;

@Service
@Transactional(readOnly = true)
public class StudentServiceImpl extends BaseServiceImpl implements StudentService {

	@Resource
	private StudentDao studentDao;

	@Resource
	private ThesisDao thesisDao;

	@Resource
	private TeacherDao teacherDao;

	@Resource
	private PingyueRecordDao pingyueRecordDao;

	@Resource
	private DabianRecordDao dabianRecordDao;

	@Override
	public Student getStudent(String loginName) {
		Assert.notNull(loginName);

		Student student = studentDao.findByLoginName(loginName);
		Assert.notNull(student);

		logger.debug("查询学生信息：{}", student);
		return student;
	}

	@Override
	public Student regStudent(Student student, Message message) {
		logger.debug("注册新学生：{}", student);
		String loginName = student.getLoginName();
		if (loginName == null || studentDao.findByLoginName(loginName) != null) {
			message.setMessage("该用户已经注册");
			return null;
		}
		return studentDao.save(student);
	}

	@Override
	public Student getStudentInfo(String loginName) {
		return getStudent(loginName);
	}

	@Override
	public Thesis getStudentThesis(Student student) {
		Assert.notNull(student);
		Long thesisId = student.getThesisId();
		return thesisDao.findOne(thesisId);
	}

	@Override
	public List<Thesis> getStudentsThesises(Collection<Student> students) {
		List<Thesis> thesises = Lists.newArrayList();
		for (Student s : students) {
			Long thesisId = s.getThesisId();
			Thesis t = thesisDao.findOne(thesisId);
			Assert.notNull(t);
			Assert.hasText(t.getAssign());
			thesises.add(t);
		}
		return thesises;
	}

	@Override
	@Transactional(readOnly = false)
	public Student saveStudentInfo(Student student) {
		Assert.notNull(student);
		Assert.notNull(student.getLoginName());
		//简化名称
		Student s = student;

		//获取内部学生
		Student _s = studentDao.findByLoginName(s.getLoginName());
		Assert.notNull(_s);

		//判断是否需要修改密码
		String pass = s.getPassword();
		if (StringUtils.isNotEmpty(pass)) {
			_s.setPassword(pass);
			logger.debug("用户：{} 修改密码", _s.getLoginName());
		}

		_s.setName(s.getName());
		_s.setGender(s.getGender());
		_s.setPhone(s.getPhone());
		_s.setEmail(s.getEmail());
		_s.setMajor(s.getMajor());
		_s.setStuClass(s.getStuClass());

		logger.debug("保存学生信息：{}", _s);
		return studentDao.save(_s);
	}

	@Override
	public String getMidternCheckWarn(String loginName) {
		Assert.notNull(loginName);

		Student student = studentDao.findByLoginName(loginName);
		Assert.notNull(student);

		String warn = student.getWarn();
		logger.debug("学生：{}，中期检查警告信息：{}", loginName, warn);

		return warn;
	}

	@Override
	public Student getMidternCheckInfo(String loginName) {
		Assert.notNull(loginName);

		Student student = studentDao.findByLoginName(loginName);
		logger.debug("查询学生中期检查信息：{}", student);
		return student;
	}

	@Override
	@Transactional(readOnly = false)
	public Student saveMidternCheckInfo(Student student) {
		Assert.notNull(student);
		Assert.notNull(student.getLoginName());
		//简化名称
		Student s = student;

		//获取内部学生
		Student _s = studentDao.findByLoginName(s.getLoginName());
		Assert.notNull(_s);

		_s.setProgress(s.getProgress());
		_s.setQuality(s.getQuality());
		_s.setAttitude(s.getAttitude());
		_s.setDuty(s.getDuty());

		logger.debug("保存学生中期检查信息：{}", _s);
		return studentDao.save(_s);
	}

	@Override
	@Transactional(readOnly = false)
	public Student saveMidternCheckInfoByTeacher(Student student) {
		Assert.notNull(student);
		Assert.notNull(student.getLoginName());
		//简化名称
		Student s = student;

		//获取内部学生
		Student _s = studentDao.findByLoginName(s.getLoginName());
		Assert.notNull(_s);

		_s.setProgress(s.getProgress());
		_s.setQuality(s.getQuality());
		_s.setAttitude(s.getAttitude());
		_s.setDuty(s.getDuty());
		_s.setWarn(s.getWarn());

		logger.debug("保存学生中期检查信息：{}", _s);
		return studentDao.save(_s);
	}

	@Override
	@Transactional(readOnly = false)
	public Student delMidternCheckWarn(String studentLoginName) {
		Assert.notNull(studentLoginName);

		//获取内部学生
		Student _s = studentDao.findByLoginName(studentLoginName);
		Assert.notNull(_s);

		_s.setWarn("");

		logger.debug("保存学生中期检查信息：{}", _s);
		return studentDao.save(_s);
	}

	private static final String ZHIDAO_NOT_INPUT = "未输入";
	private static final String ZHIDAO_INPUT = "已输入";

	@Override
	public List<String> getStudentsZhidaoScoreStatuses(Collection<Student> students) {
		List<String> statuses = Lists.newArrayList();
		for (Student s : students) {
			int zd1 = s.getZd1grade();
			int zd2 = s.getZd2grade();
			int zd3 = s.getZd3grade();
			int zd4 = s.getZd4grade();
			String status = ZHIDAO_NOT_INPUT;
			if (zd1 > 0 && zd2 > 0 && zd3 > 0 && zd4 > 0)
				status = ZHIDAO_INPUT;
			statuses.add(status);
		}
		return statuses;
	}

	@Override
	public List<String> getStudentsPingyueScoreStatuses(Collection<Student> students) {
		List<String> statuses = Lists.newArrayList();
		for (Student s : students) {
			int py1 = s.getPy1grade();
			int py2 = s.getPy2grade();
			String status = "未输入";

			//根据记录查询
			PingyueRecord _py = pingyueRecordDao.findByStudentId(s.getId());
			if (_py != null && (py1 > 0 && py2 > 0)) {
				Teacher _t = teacherDao.findOne(_py.getTeacherId());
				status = "已输入：" + _t.getName();
			}

			statuses.add(status);
		}
		return statuses;
	}

	@Override
	public List<String> getStudentsDabianScoreStatuses(Collection<Student> students) {
		List<String> statuses = Lists.newArrayList();
		for (Student s : students) {
			int db1 = s.getDb1grade();
			int db2 = s.getDb2grade();
			String status = "未输入";

			//根据记录查询
			DabianRecord _db = dabianRecordDao.findByStudentId(s.getId());
			if (_db != null && (db1 > 0 && db2 > 0)) {
				Teacher _t = teacherDao.findOne(_db.getTeacherId());
				status = "已输入：" + _t.getName();
			}

			statuses.add(status);
		}
		return statuses;
	}

	@Transactional(readOnly = false)
	private void updateTotalGrade(Student student) {
		Assert.notNull(student);
		Assert.notNull(student.getLoginName());
		//简化名称
		Student s = student;

		//获取内部学生
		Student _s = studentDao.findByLoginName(s.getLoginName());
		Assert.notNull(_s);

		int zd1 = _s.getZd1grade();
		int zd2 = _s.getZd2grade();
		int zd3 = _s.getZd3grade();
		int zd4 = _s.getZd4grade();
		int py1 = _s.getPy1grade();
		int py2 = _s.getPy2grade();
		int db1 = _s.getDb1grade();
		int db2 = _s.getDb2grade();

		double total = 0;
		total += zd1 * 0.15 + zd2 * 0.05 + zd3 * 0.05 + zd4 * 0.05;
		total += py1 * 0.2 + py2 * 0.1;
		total += db1 * 0.3 + db2 * 0.1;
		logger.debug("updateTotalGrade-total: {}", total);

		int grade = (int) Math.round(total);
		logger.debug("updateTotalGrade-grade: {}", grade);
		_s.setGrade(grade);

		logger.debug("更新成绩总分：{}", _s);
	}

	@Override
	@Transactional(readOnly = false)
	public Student updateScoreInputZhidaoDetail(Student student) {
		Assert.notNull(student);
		Assert.notNull(student.getLoginName());
		//简化名称
		Student s = student;

		//获取内部学生
		Student _s = studentDao.findByLoginName(s.getLoginName());
		Assert.notNull(_s);

		_s.setZd1grade(s.getZd1grade());
		_s.setZd2grade(s.getZd2grade());
		_s.setZd3grade(s.getZd3grade());
		_s.setZd4grade(s.getZd4grade());

		updateTotalGrade(_s);

		logger.debug("保存指导教师输入成绩信息：{}", _s);
		return studentDao.save(_s);
	}

	@Override
	@Transactional(readOnly = false)
	public Student addScoreInputPingyue(Student student, String teacherLoginName) {
		Assert.notNull(student);
		Assert.notNull(student.getLoginName());
		//简化名称
		Student s = student;

		//获取内部学生
		Student _s = studentDao.findByLoginName(s.getLoginName());
		Assert.notNull(_s);

		_s.setPy1grade(s.getPy1grade());
		_s.setPy2grade(s.getPy2grade());

		updateTotalGrade(_s);

		Teacher _t = teacherDao.findByLoginName(teacherLoginName);
		Assert.notNull(_t);

		PingyueRecord py = new PingyueRecord(_s.getId(), _t.getId());
		PingyueRecord _py = pingyueRecordDao.save(py);
		Assert.notNull(_py);

		logger.debug("保存评阅教师输入成绩信息：{}", _s);
		return studentDao.save(_s);
	}

	@Override
	@Transactional(readOnly = false)
	public Student delScoreInputPingyue(String studentLoginName) {
		Student s = studentDao.findByLoginName(studentLoginName);
		Long pingyueId = pingyueRecordDao.findByStudentId(s.getId()).getId();
		pingyueRecordDao.delete(pingyueId);
		return s;
	}

	@Override
	@Transactional(readOnly = false)
	public Student addScoreInputDabian(Student student, String teacherLoginName) {
		Assert.notNull(student);
		Assert.notNull(student.getLoginName());
		//简化名称
		Student s = student;

		//获取内部学生
		Student _s = studentDao.findByLoginName(s.getLoginName());
		Assert.notNull(_s);

		_s.setDb1grade(s.getDb1grade());
		_s.setDb2grade(s.getDb2grade());

		updateTotalGrade(_s);

		Teacher _t = teacherDao.findByLoginName(teacherLoginName);
		Assert.notNull(_t);

		DabianRecord db = new DabianRecord(_s.getId(), _t.getId());
		DabianRecord _db = dabianRecordDao.save(db);
		Assert.notNull(_db);

		logger.debug("保存答辩小组输入成绩信息：{}", _s);
		return studentDao.save(_s);
	}

	@Override
	@Transactional(readOnly = false)
	public Student delScoreInputDabian(String studentLoginName) {
		Student s = studentDao.findByLoginName(studentLoginName);
		Long dabianId = dabianRecordDao.findByStudentId(s.getId()).getId();
		dabianRecordDao.delete(dabianId);
		return s;
	}

	@Override
	@Transactional(readOnly = false)
	public Student updatePingyu(Student student) {
		Assert.notNull(student);
		Assert.notNull(student.getLoginName());

		//简化名称
		Student s = student;

		//获取内部学生
		Student _s = studentDao.findByLoginName(s.getLoginName());
		Assert.notNull(_s);

		_s.setZdpingyu(s.getZdpingyu());

		_s.setPypingyu(s.getPypingyu());

		_s.setQuestionanswer(s.getQuestionanswer());
		_s.setQuestion1(s.getQuestion1());
		_s.setAnswer1(s.getAnswer1());
		_s.setQuestion2(s.getQuestion2());
		_s.setAnswer2(s.getAnswer2());
		_s.setQuestion3(s.getQuestion3());
		_s.setAnswer3(s.getAnswer3());
		_s.setDbpingyu(s.getDbpingyu());

		logger.debug("保存评语信息：{}", _s);
		return studentDao.save(_s);
	}

	@Override
	public List<Student> getStudents() {
		List<Student> students = Lists.newArrayList(studentDao.findAll());

		logger.debug("获取Student数量：{}", students.size());
		return students;
	}

	@Override
	public List<Student> getStudentsWithThesisAssigned() {
		List<Student> students = getStudents();
		Iterator<Student> it = students.iterator();
		while (it.hasNext()) {
			Student s = it.next();
			if (s.getThesisId() == 0L)
				it.remove();
		}

		return students;
	}

	@Override
	public Student addOrUpdateStudent(Student student) {
		Student _t = studentDao.findByLoginName(student.getLoginName());

		if (_t != null) {
			student.setId(_t.getId());
		}
		return studentDao.save(student);
	}

	@Override
	@Transactional(readOnly = false)
	public Student delStudent(Long studentId) {
		Student student = studentDao.findOne(studentId);

		Long thesisId = student.getThesisId();
		Thesis thesis = thesisDao.findOne(thesisId);
		thesis.setAssign("");
		//TODO 删除学生同时，检查相关课题，同步删除
		studentDao.delete(student);
		return student;
	}

	private File getDir(String folderName) throws IOException {
		Assert.notNull(folderName);
		org.springframework.core.io.Resource r = applicationContext.getResource("/file");
		Assert.isTrue(r.exists());

		org.springframework.core.io.Resource res = applicationContext.getResource("/file/" + folderName);
		if (!res.exists()) {//如果folder目录不存在，进入上级目录并创建folder
			File parent = r.getFile();
			File d = new File(parent, folderName);
			Assert.isTrue(d.mkdir());
			logger.info("文件夹{}不存在，创建成功", d.getAbsoluteFile());
		}

		File dir = res.getFile();
		Assert.isTrue(dir.isDirectory());
		logger.debug("文件夹绝对路径：{}", dir.getAbsolutePath());
		return dir;
	}

	private String saveUploadFile(MultipartFile uploadFile, String loginName, String folder) throws IOException {
		File dir = getDir(folder);
		String filename = uploadFile.getOriginalFilename();
		filename = filename.replaceAll(" ", "_");//将所有空格替换成下划线
		Assert.notNull(loginName);
		filename = loginName + '-' + filename;//添加学号
		File f = new File(dir, filename);
		if (f.exists())
			f.delete();
		Assert.isTrue(f.createNewFile());
		uploadFile.transferTo(f);

		return filename;
	}

	private void delUploadFile(String filename, String folder) throws IOException {
		File dir = getDir(folder);
		File f = new File(dir, filename);
		if (f.exists()) {
			Assert.isTrue(f.delete());
			logger.debug("删除文件：{}", f.getAbsolutePath());
		} else {
			logger.warn("找不到被删除文件：{}", f.getAbsolutePath());
		}
	}

	@Override
	@Transactional(readOnly = false)
	public Student saveKtup(MultipartFile ktup, String loginName) {
		String filename;
		try {
			filename = saveUploadFile(ktup, loginName, "ktup");
		} catch (IOException e) {
			logger.warn(e.toString());
			return null;
		}
		Student _s = studentDao.findByLoginName(loginName);
		_s.setKtup(filename);

		return studentDao.save(_s);
	}

	@Override
	@Transactional(readOnly = false)
	public Student delKtup(String loginName) {
		Student _s = studentDao.findByLoginName(loginName);
		String filename = _s.getKtup();
		try {
			delUploadFile(filename, "ktup");
		} catch (IOException e) {
			logger.warn(e.toString());
			return null;
		}
		_s.setKtup("");

		return _s;
	}

	@Override
	@Transactional(readOnly = false)
	public Student saveRwsup(MultipartFile rwsup, String loginName) {
		String filename;
		try {
			filename = saveUploadFile(rwsup, loginName, "rwsup");
		} catch (IOException e) {
			logger.warn(e.toString());
			return null;
		}
		Student _s = studentDao.findByLoginName(loginName);
		_s.setRwsup(filename);

		return studentDao.save(_s);
	}

	@Override
	@Transactional(readOnly = false)
	public Student delRwsup(String loginName) {
		Student _s = studentDao.findByLoginName(loginName);
		String filename = _s.getRwsup();
		try {
			delUploadFile(filename, "rwsup");
		} catch (IOException e) {
			logger.warn(e.toString());
			return null;
		}
		_s.setRwsup("");

		return _s;
	}

	@Override
	@Transactional(readOnly = false)
	public Student saveTransup(MultipartFile transup, String loginName) {
		String filename;
		try {
			filename = saveUploadFile(transup, loginName, "transup");
		} catch (IOException e) {
			logger.warn(e.toString());
			return null;
		}
		Student _s = studentDao.findByLoginName(loginName);
		_s.setTransup(filename);

		return studentDao.save(_s);
	}

	@Override
	@Transactional(readOnly = false)
	public Student delTransup(String loginName) {
		Student _s = studentDao.findByLoginName(loginName);
		String filename = _s.getTransup();
		try {
			delUploadFile(filename, "transup");
		} catch (IOException e) {
			logger.warn(e.toString());
			return null;
		}
		_s.setTransup("");

		return _s;
	}

	@Override
	@Transactional(readOnly = false)
	public Student saveThesisup(MultipartFile thesisup, String loginName) {
		String filename;
		try {
			filename = saveUploadFile(thesisup, loginName, "thesisup");
		} catch (IOException e) {
			logger.warn(e.toString());
			return null;
		}
		Student _s = studentDao.findByLoginName(loginName);
		_s.setThesisup(filename);

		return studentDao.save(_s);
	}

	@Override
	@Transactional(readOnly = false)
	public Student delThesisup(String loginName) {
		Student _s = studentDao.findByLoginName(loginName);
		String filename = _s.getThesisup();
		try {
			delUploadFile(filename, "thesisup");
		} catch (IOException e) {
			logger.warn(e.toString());
			return null;
		}
		_s.setThesisup("");

		return _s;
	}

	@Override
	public Thesis getFinalThesis(String loginName) {
		Thesis t = new Thesis();
		Student s = studentDao.findByLoginName(loginName);
		Long thesisId = s.getThesisId();
		if (thesisId > 0)
			t = thesisDao.findOne(thesisId);
		else
			logger.debug("学生{}没有分配到课题");
		// FIXME 未经过自己考虑
		return t;
	}
}
