package im.lich.gdms.core.service.admin.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.admin.PaperDao;
import im.lich.gdms.core.model.admin.Paper;
import im.lich.gdms.core.service.admin.PaperService;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
public class PaperServiceImpl extends BaseServiceImpl implements PaperService {
	@Resource
	private PaperDao paperDao;

	@Override
	public List<Paper> getPapers() {
		//防止修改数据被持久化
		List<Paper> _papers = paperDao.findAll();
		List<Paper> papers = Lists.newArrayList(_papers);
		logger.debug("获取Paper数量：{}", papers.size());

		return papers;
	}

	@Override
	public List<Paper> getPapersURLEncoded() {
		List<Paper> papers = getPapers();
		for (Paper p : papers) {
			String filename = p.getFilename();
			try {
				String filenameURLEncoded = URLEncoder.encode(filename, "UTF-8");
				logger.debug("下载地址转码 {} - {}", filename, filenameURLEncoded);
				p.setFilename(filenameURLEncoded);
			} catch (UnsupportedEncodingException e) {
				logger.warn(e.toString());
			}
		}
		return papers;
	}

	private File getDir() throws IOException {
		return getDir("paper");
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
		return dir;
	}

	@Override
	@Transactional(readOnly = false)
	public Paper savePaper(String description, MultipartFile uploadFile) throws IOException {
		File dir = getDir();
		String filename = uploadFile.getOriginalFilename();
		filename = StringUtils.replace(filename, " ", "_");//将所有空格替换成下划线
		File f = new File(dir, filename);
		Assert.isTrue(f.createNewFile());
		uploadFile.transferTo(f);

		if (StringUtils.isEmpty(description))
			description = filename;
		Paper paper = new Paper(description, filename);
		return paperDao.save(paper);
	}

	@Override
	@Transactional(readOnly = false)
	public Paper delPaper(Long id) throws IOException {
		Paper paper = paperDao.findOne(id);

		File dir = getDir();
		String filename = paper.getFilename();
		File f = new File(dir, filename);
		if (f.exists()) {
			Assert.isTrue(f.delete());
			logger.debug("删除文件：{}", f.getAbsolutePath());
		} else {
			logger.warn("找不到被删除文件：{}", f.getAbsolutePath());
		}

		paperDao.delete(paper);
		return paper;
	}
}
