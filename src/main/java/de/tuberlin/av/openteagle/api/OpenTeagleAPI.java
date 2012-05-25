package de.tuberlin.av.openteagle.api;

import java.util.LinkedList;
import java.util.List;

import de.tuberlin.av.openteagle.exceptions.VCTNotFoundException;
import de.tuberlin.av.openteagle.model.vct.JaxbHelper;
import de.tuberlin.av.openteagle.model.vct.jaxb.Vct;
import de.tuberlin.av.openteagle.model.vct.jaxb.VctInstance;
import de.tuberlin.av.openteagle.utils.TeagleProperties;

public class OpenTeagleAPI {

	private final RestClient repoClient;

	public OpenTeagleAPI(final String url) {
		this.repoClient = TeagleProperties.isRepoMocking() ? new RepoClientMock()
				: new RepoClient(url);
	}

	public int getNumberOfVCTs() {
		return this.getListOfVCTs().size();
	}

	public List<Vct> getListOfVCTs() {
		final List<Vct> result = new LinkedList<Vct>();

		final String list = this.repoClient.get("/vct");
		for (Vct vct : JaxbHelper.createList(list).getVct()) {
			result.add(vct);
		}

		return result;
	}

	public Vct getVCT(String vctID) throws VCTNotFoundException {
		for (Vct vct : this.getListOfVCTs()) {
			if (vctID.equals(vct.getId().toString()))
				return vct;
		}
		throw new VCTNotFoundException("");
	}

	//todo: Alex: just a first stub
	public String createVct(VctInstance requestedVCT) {
		String request = JaxbHelper.VctInstanceToString(requestedVCT);
		System.out.println(request);
		String result = this.repoClient.post("/vct/47", request);
		return result;
	}
}
