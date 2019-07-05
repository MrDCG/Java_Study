package httpserver.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志记录器
 * 
 * @author DCG
 * @version 1.0
 */
public class Logger {

	// 工具类的方法往往是静态的，直接通过类名调用，不需要区创建对象
	// 工具类的构造方法往往也是私有的，但不是必须的
	private Logger() {

	}

	public static void log(String msg) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
//		Date nowTime = new Date();
//		String nowTimeStr = dateFormat.format(nowTime);
		System.out.println("[INFO]" + DateUtil.getCurrentTime() + " " + msg);
	}
}
