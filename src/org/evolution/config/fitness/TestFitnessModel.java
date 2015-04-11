package org.evolution.config.fitness;

import javax.xml.xpath.XPathExpressionException;

import org.evolution.config.Config;
import org.evolution.config.ModelFactory;
import org.evolution.function.fitness.TestFunction;
import org.evolution.util.Utils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class TestFitnessModel implements ModelFactory {

	public Object loadConfiguration(Node xml) throws XPathExpressionException {
		TestFunction fitnessFce = new TestFunction();
		return fitnessFce;
	}

	public Node saveConfiguration(Object instance) {
		if (instance instanceof TestFunction) {
			Document document = Utils.createDocument();
			Element root = document.createElement("fitness_function");
			root.setAttribute("model",
					Config.getModelByModel(TestFitnessModel.class).name);
			return root;
		}
		return null;
	}

}
