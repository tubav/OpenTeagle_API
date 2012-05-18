package de.tuberlin.av.openteagle.api;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import de.tuberlin.av.openteagle.model.vct.jaxb.Vct;
import de.tuberlin.av.openteagle.utils.TeagleProperties;

public class TestOpenTeagleAPI {
	private final OpenTeagleAPI openteagle = new OpenTeagleAPI(
			TeagleProperties.getRepoUrl());

	@Test
	public void testGetListOfVCTs() {
		final int expected = 3;
		final int numberOfVCTs = this.openteagle.getNumberOfVCTs();
		Assert.assertEquals(expected, numberOfVCTs);
	}

	@Test
	public void testListOfVCTs() {
		boolean found = false;
		final String expected = "1";
		final List<Vct> listOfVCTs = this.openteagle.getListOfVCTs();
		for (Vct vct : listOfVCTs) {
			if (vct.getId().toString().equals(expected))
				found  = true;
		}
		Assert.assertTrue(found);
	}	
}
