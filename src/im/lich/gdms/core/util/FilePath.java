package im.lich.gdms.core.util;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import im.lich.gdms.base.BaseObject;

@Component
public class FilePath extends BaseObject {

	private static final String PAPER_PATH = "/file/paper/";

	private File paperDir;

	public File getPaperDir() {
		return paperDir;
	}

	@PostConstruct
	public void init() throws IOException {
		Resource res = applicationContext.getResource(PAPER_PATH);
		paperDir = res.getFile();
		Assert.isTrue(paperDir.isDirectory());
	}
}
