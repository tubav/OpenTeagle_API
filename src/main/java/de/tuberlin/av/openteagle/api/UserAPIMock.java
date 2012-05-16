package de.tuberlin.av.openteagle.api;

import java.util.LinkedList;
import java.util.List;

public class UserAPIMock implements UserAPI {
	private List<String> vcts = new LinkedList<String>();

	@Override
	public List<String> getListOfVCTs() {
		return this.vcts;
	}

	@Override
	public void createVCT(String vct) {
		this.vcts.add(vct);
	}

	@Override
	public void deleteVCT(String vct) {
		this.vcts.remove(vct);
	}

	@Override
	public int getNumberOfVCTs() {
		return this.vcts.size();
	}
}
