package de.tuberlin.av.openteagle.api;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TestUserAPI {
	private UserAPI openteagle;

	@Before
	public void setUp() throws Exception {
		this.openteagle = new UserAPIMock();
	}
	
	@Test
	public void createAndDeleteVCT() {
		String vctDefinition = "";
		int lastNumberOfVCTs = 0;
		int currentNumberOfVCTs = 0;
		
		lastNumberOfVCTs = this.openteagle.getNumberOfVCTs();
		this.openteagle.createVCT(vctDefinition);
		currentNumberOfVCTs = this.openteagle.getNumberOfVCTs();
		Assert.assertEquals(lastNumberOfVCTs + 1, currentNumberOfVCTs);
		
		lastNumberOfVCTs = currentNumberOfVCTs;
		this.openteagle.deleteVCT(vctDefinition);
		currentNumberOfVCTs = this.openteagle.getNumberOfVCTs();
		Assert.assertEquals(lastNumberOfVCTs - 1, currentNumberOfVCTs);
	}
}
