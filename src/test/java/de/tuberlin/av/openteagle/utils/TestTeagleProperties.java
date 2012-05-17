package de.tuberlin.av.openteagle.utils;

import junit.framework.Assert;

import org.junit.Test;

public class TestTeagleProperties {

	@SuppressWarnings("static-method")
	@Test
	public void testEmptyProperty() {
		final String expected = null;
		final String actual = TeagleProperties.getProperties().getProperty(
				"asdf");
		Assert.assertEquals(expected, actual);
	}

	@SuppressWarnings("static-method")
	@Test
	public void testRepoProperty() {
		final String expected = "";
		final String actual = TeagleProperties.getRepoUrl();
		Assert.assertFalse(actual.equals(expected));
	}
}
