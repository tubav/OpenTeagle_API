package de.tuberlin.av.openteagle.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TeagleProperties {
	private static final String URL_REPO = "repo_url";
	private static final String REPO_MOCKING = "repo_mocking";
	private static final String API_PROPERTIES = "api.properties";
	private static final String TEST_FILE_REPO_1 = "test_file_vct_1";

	private TeagleProperties() {
		//Utility class
	}
	
	public static String getRepoUrl() {
		return TeagleProperties.getProperties().getProperty(
				TeagleProperties.URL_REPO);
	}

	public static String getRepoTestInput1() {
		return TeagleProperties.getProperties().getProperty(
				TeagleProperties.TEST_FILE_REPO_1);
	}

	public static boolean isRepoMocking() {
		return Boolean.parseBoolean(TeagleProperties.getProperties()
				.getProperty(TeagleProperties.REPO_MOCKING));
	}

	public static Properties getProperties() {
		final Properties properties = new Properties();
		InputStream propertyPath = null;
		try {
			propertyPath = Utility.getAsStream(TeagleProperties.API_PROPERTIES);
			properties.load(propertyPath);
		} catch (final IOException e) {
			throw new RuntimeException("Property: " + propertyPath, e);
		}
		return properties;
	}
}
