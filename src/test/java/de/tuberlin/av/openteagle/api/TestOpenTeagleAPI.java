package de.tuberlin.av.openteagle.api;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import de.tuberlin.av.openteagle.api.model.VCT;
import de.tuberlin.av.openteagle.utils.TeagleProperties;

public class TestOpenTeagleAPI {
	private final OpenTeagleAPI openteagle = new OpenTeagleAPI(
			TeagleProperties.getRepoUrl());

	@Test
	public void getListOfExistingVCTs() {
		final int expected = 3;
		final int numberOfVCTs = this.openteagle.getNumberOfVCTs();
		Assert.assertEquals(expected, numberOfVCTs);
	}

	@Test
	public void testListOfVCTIDs() {
		final List<String> listOfVctIDs = this.openteagle.getListOfVctIDs();
		Assert.assertTrue(listOfVctIDs.contains("1"));
		Assert.assertTrue(listOfVctIDs.contains("2"));
		Assert.assertTrue(listOfVctIDs.contains("3"));
	}

	@Test
	public void getExtendedListOfExistingVCTs() {
		final VCT expectedVCT = new VCT();
		expectedVCT.id = "1";
		expectedVCT.commonName = "teagle";
		expectedVCT.description = "";
		final List<VCT> listOfVCTs = this.openteagle.getListOfVCTs();
		Assert.assertTrue(listOfVCTs.contains(expectedVCT));
	}
}
