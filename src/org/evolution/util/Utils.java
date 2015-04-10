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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public final class Utils {
	private static final Logger log = Logger.getLogger(Utils.class);
	private static final Random random = new Random();
	private static DocumentBuilder builder;
	private static Transformer transformer;

	private static XPath xPath = XPathFactory.newInstance().newXPath();

	static {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		try {
			builder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			log.error(e);
		}

		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			log.error(e);
		}
	}

	public static XPath getXPath() {
		return xPath;
	}

	/**
	 * Return attribute value with name
	 * 
	 * @param node
	 *            go throught attributes
	 * @param name
	 *            name of atrribute
	 * @return
	 */
	public static String getAttributeValue(Node node, String name) {
		NamedNodeMap list = node.getAttributes();
		for (int i = 0; i < list.getLength(); i++) {
			Node attrib = list.item(i);
			if (node.getNodeName().equals(name))
				return attrib.getNodeValue();
		}
		return null;
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

	public static void saveXML(Document document, File file) {
		DOMSource source = new DOMSource(document);
		StreamResult streamResult = new StreamResult(file);
		try {
			transformer.transform(source, streamResult);
		} catch (TransformerException e) {
			log.error(e);
		}
	}

	public static Document createDocument() {
		return builder.newDocument();
	}

	public static Node getNode(Document doc, String xmlPath) {
		try {
			Node node = (Node) xPath.compile(xmlPath).evaluate(doc,
					XPathConstants.NODE);
			return node;
		} catch (XPathExpressionException e) {
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
