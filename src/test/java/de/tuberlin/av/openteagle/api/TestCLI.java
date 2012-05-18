package de.tuberlin.av.openteagle.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCLI {

	private transient CLI cli;

	@Before
	public void setUp() {
		this.cli = new CLI();
	}

	@Test
	public void testWrongParameter() {
		final String[] args = { "sdf" };

		final String expected = "ERROR";
		final String cliResult = this.cli.parse(args);

		Assert.assertTrue(cliResult.startsWith(expected));
	}

	@Test
	public void testListVCTsCommand() {
		final String[] args = { "listVCTs" };

		final String unexpected = "Usage";
		final String cliResult = this.cli.parse(args);

		Assert.assertFalse(cliResult.isEmpty());
		Assert.assertFalse(cliResult.contains(unexpected));
	}
	
	@Test
	public void testShowVCTCommand() {
		final String[] args = { "showVCT", "-id=1" };

		final String unexpected = "Usage";
		final String cliResult = this.cli.parse(args);

		Assert.assertFalse(cliResult.isEmpty());
		Assert.assertFalse(cliResult.contains(unexpected));
	}	

	@Test
	public void testShowUnknownVCTCommand() {
		final String[] args = { "showVCT", "-id=afsdsadf" };

		final String expected = "not found";
		final String cliResult = this.cli.parse(args);

		Assert.assertTrue(cliResult.contains(expected));
	}	

}
