package im.lich.gdms.core.util;

import java.io.File;

import javax.annotation.PostConstruct;

import im.lich.gdms.base.BaseObject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValueExample extends BaseObject {

	@Value("#{filePath.paperDir}")
	private File paperDir;

	@PostConstruct
	public void test() {
		logger.debug("@Value: {}", paperDir.getAbsolutePath());
	}
}
