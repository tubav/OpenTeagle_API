package de.tuberlin.av.openteagle.api;

import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.tuberlin.av.openteagle.api.model.VCT;
import de.tuberlin.av.openteagle.utils.TeagleProperties;
import de.tuberlin.av.openteagle.utils.Utility;

public class OpenTeagleAPI {

	private final RestClient repoClient;

	public OpenTeagleAPI(final String url) {
		this.repoClient = TeagleProperties.isRepoMocking() ? new RepoClientMock()
				: new RepoClient(url);
	}

	public int getNumberOfVCTs() {
		return this.getListOfVctIDs().size();
	}

	public List<String> getListOfVctIDs() {

		final List<String> result = new LinkedList<String>();

		final NodeList listOfVCTs = this.getVCTNodeList();
		for (int i = 0; i < listOfVCTs.getLength(); i++) {
			final Node childNode = listOfVCTs.item(i);
			result.add((childNode.getAttributes().getNamedItem("id")
					.getTextContent()));
		}

		return result;
	}

	public List<VCT> getListOfVCTs() {
		final List<VCT> result = new LinkedList<VCT>();

		final NodeList vctNodeList = this.getVCTNodeList();
		for (int i = 0; i < vctNodeList.getLength(); i++) {
			final Node childNode = vctNodeList.item(i);
			final VCT vct = new VCT();
			vct.id = childNode.getAttributes().getNamedItem("id")
					.getTextContent();

			final NodeList vctParameterNodes = childNode.getChildNodes();
			for (int j = 0; j < vctParameterNodes.getLength(); j++) {
				final Node vctChildNode = vctParameterNodes.item(j);
				final String nodeName = vctChildNode.getNodeName();

				if ("commonName".equals(nodeName)) {
					vct.commonName = vctChildNode.getTextContent();
				}

				if ("description".equals(nodeName)) {
					vct.description = vctChildNode.getTextContent();
				}
			}

			result.add(vct);
		}

		return result;
	}

	protected NodeList getVCTNodeList() {

		final String result = this.repoClient.get("/vct");
		final Document resultDoc = Utility.convertToDOM(result);

		return resultDoc.getElementsByTagName("vct");
	}
}
