package org.evolution.util;

import java.util.Random;

import org.apache.log4j.Logger;

public final class Utils {
	private static final Logger log = Logger.getLogger(Utils.class);

	private static final Random random = new Random();

	public static double getRandom() {
		return random.nextDouble();
	}

	public static double getRandom(double min, double max) {
		return random.nextDouble() * (max - min) + min;
	}

	public static int getRandomInt() {
		return random.nextInt();
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
