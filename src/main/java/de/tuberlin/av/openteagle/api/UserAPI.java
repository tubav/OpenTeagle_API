package de.tuberlin.av.openteagle.api;

import java.util.List;

public interface UserAPI {

	public abstract List<String> getListOfVCTs();

	public abstract void createVCT(String vct);

	public abstract void deleteVCT(String vct);

	public abstract int getNumberOfVCTs();

}