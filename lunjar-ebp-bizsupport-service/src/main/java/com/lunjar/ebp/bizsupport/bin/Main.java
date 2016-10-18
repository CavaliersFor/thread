package com.lunjar.ebp.bizsupport.bin;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;

public class Main {
	private static ExecuteThread executeThread;

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		if (executeThread == null) {
			executeThread = new ExecuteThread();
			executeThread.setDaemon(false);
		}
		logger.info(args.length + "");
		if (args != null && args.length > 0 && "STOP".equalsIgnoreCase(args[0])) {// 停止
			logger.info(args[0]);
			executeThread.shutdown();
		} else {
			executeThread.start();
		}

	}
}

class ExecuteThread extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(ExecuteThread.class);
	private String[] xmlApplicationContexts = new String[] { //
			"classpath*:spring/core-bean.xml", //
			"classpath*:spring/cache-bean.xml", //
			"classpath*:spring/bizsupport-dubbo-provider.xml", //
			"classpath*:spring/bizsupport-service-bean.xml", "classpath*:spring/store-bean.xml",
			"classpath*:spring/bps-service-core-bean.xml", // 公共服务核心包
			"classpath:spring/jms-bean.xml" //
	};

	private ClassPathXmlApplicationContext context = null;
	private volatile boolean running = true;

	public void start() {
		if (context == null || !context.isRunning()) {
			logger.info("Start ancun-bps-bizsuppert service ...");
			context = new ClassPathXmlApplicationContext(xmlApplicationContexts);
			running = true;
			context.start();
		}

		super.start();
	}

	public void shutdown() {
		if (context.isRunning()) {
			logger.info("Stop ancun-bps-bizsuppert service ...");

			synchronized (ExecuteThread.class) {
				running = false;
				context.stop();
				context.destroy();
				ExecuteThread.class.notify();
			}
		}
	}

	public void run() {
		synchronized (ExecuteThread.class) {
			while (running) {
				try {
					ExecuteThread.class.wait(2000);
					// logger.info("1");
				} catch (Throwable e) {
				}
			}
		}
	}
}
