package org.evolution.config;

import org.evolution.util.Utils;

public class ConfigModel {
	public String name;
	public String type;
	public String instance;
	public String model;

	public ModelFactory createFactory() {
		try {
			return (ModelFactory) Utils.createInstance(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
