package de.tuberlin.av.openteagle.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.tools.ant.filters.StringInputStream;
import org.apache.tools.ant.util.FileUtils;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Utility {
	
	private Utility() {
		//utility class
	}

	public static InputStream getAsStream(final String filename) {
		final String correctFilename = Utility.correctFilename(filename);
		final InputStream stream = Utility.class
				.getResourceAsStream(correctFilename);
		if (null == stream) {
			throw new RuntimeException(new FileNotFoundException(
					correctFilename));
		}

		return stream;
	}

	public static Document convertToDOM(final String input) {
		final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		final InputStream inputStream = new StringInputStream(input);
		Document dom;

		try {
			final DocumentBuilder docBuilder = dbf.newDocumentBuilder();
			dom = docBuilder.parse(inputStream);
			dom.getDocumentElement().normalize();
		} catch (final ParserConfigurationException e) {
			throw new RuntimeException(e);
		} catch (final SAXException e) {
			throw new RuntimeException(e);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}

		return dom;
	}

	public static String readFileAsString(final InputStream inputStream)
			throws IOException {
		final Reader reader = new InputStreamReader(inputStream, "UTF-8");
		return FileUtils.readFully(reader);
	}

	public static String readFileAsString(final String filename)
			throws IOException {
		return Utility.readFileAsString(Utility.getAsStream(filename));
	}

	private static String correctFilename(final String filename) {
		return filename.startsWith("/") ? filename : "/" + filename;
	}
}
