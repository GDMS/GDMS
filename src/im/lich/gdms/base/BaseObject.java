package im.lich.gdms.base;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseObject {
	// 引入日志记录器
	protected Logger logger = LoggerFactory.getLogger(getClass());

	// 重写标准的toString生成
	@Override
	public String toString() {
		return ReflectionToStringBuilder.reflectionToString(this);
	}
}
