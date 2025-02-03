package com.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogInitializer {
	private static final Logger log = LogManager.getLogger(LogInitializer.class.getName());

	public static Logger getLogger() {
		return log;
	}

}
