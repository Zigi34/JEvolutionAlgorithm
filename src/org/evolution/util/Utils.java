package org.evolution.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public final class Utils {
	private static final Logger log = Logger.getLogger(Utils.class);
	private static final Random random = new Random();
	private static DocumentBuilder builder;

	static {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		try {
			builder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			log.error(e);
		}
	}

	public static Object createInstance(String className)
			throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = Class.forName(className);
		Constructor<?> ctor = clazz.getConstructor();
		Object object = ctor.newInstance(new Object[] {});
		return object;
	}

	public static double getRandom() {
		return random.nextDouble();
	}

	public static double getRandom(double min, double max) {
		return random.nextDouble() * (max - min) + min;
	}

	public static int getRandomInt() {
		return random.nextInt();
	}

	public static Document loadXML(InputStream stream) {
		try {
			return builder.parse(stream);
		} catch (SAXException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}

	public static Document loadXML(File file) {
		try {
			Document document = builder.parse(new FileInputStream(file));
			return document;
		} catch (SAXException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}

	public static int getRandomInt(int max) {
		return random.nextInt(max);
	}

	/**
	 * Convert string value to integer value
	 * 
	 * @param value
	 *            string value
	 * @return integer value, else null when it´s cannot be converted to integer
	 */
	public static Integer toInteger(String value) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException nfe) {
			log.error(String.format(
					"Value '%s' cannot be converted to INTEGER", value));
		}
		return null;
	}

	public static Long toLong(String value) {
		try {
			return Long.parseLong(value);
		} catch (NumberFormatException nfe) {
			log.error(String.format("Value '%s' cannot be converted to LONG",
					value));
		}
		return null;
	}

	public static void killThread(Thread thread, long waitTime) {
		if (thread != null && thread.isAlive()) {
			// try to join thread during time
			try {
				thread.join(waitTime);
			} catch (InterruptedException e) {
				log.warn(String
						.format("Thread '%s' cannot be joined during %s ms. It must be interrupted now",
								thread.getName(), waitTime));
			}

			// it is still alive, so must be interrupted now
			if (thread.isAlive()) {
				thread.interrupt();
			}
		}
		log.info("Thread is died");
	}
}
