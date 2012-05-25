package de.tuberlin.av.openteagle.api;

import java.math.BigInteger;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import de.tuberlin.av.openteagle.model.vct.jaxb.Description;
import de.tuberlin.av.openteagle.model.vct.jaxb.User;
import de.tuberlin.av.openteagle.model.vct.jaxb.Vct;
import de.tuberlin.av.openteagle.model.vct.jaxb.VctInstance;
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
	
	@Test
	public void testCreateEmptyVCT() {
		VctInstance requestedVCT = new VctInstance();
		requestedVCT.setCommonName("my new vct via API");
		requestedVCT.setShared(false);
		requestedVCT.setStateId(BigInteger.ONE);
		Description value = new Description();
		requestedVCT.setDescription(value);
		User user = new User();
		user.setId(BigInteger.ONE);
		requestedVCT.setUser(user );
		String result = this.openteagle.createVct(requestedVCT);
		Assert.assertEquals("", result);
	}
}
