package de.tuberlin.av.openteagle.api;

import java.net.URI;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RepoClient implements RestClient {

	private final Client client;
	private final String repositoryUrl;

	public RepoClient(final String repositoryUrl) {
		this.client = Client.create();
		this.repositoryUrl = repositoryUrl;
	}

	@Override
	public String get(final String path) {
		return this.initWebResource(path).get(String.class);
	}

	@Override
	public String post(String path, String request) {
		ClientResponse response = this.initWebResource(path).post(ClientResponse.class, request);
		return response.toString();
	}

	private WebResource initWebResource(final String path) {
		final String completeURL = RepoClient.addPathToURL(this.repositoryUrl,
				path);
		final WebResource webResource = this.client.resource(completeURL);
		return webResource;
	}

	private static String addPathToURL(final String base, final String path) {
		final String completeString = base + "/" + path;
		final URI completeURL = URI.create(completeString).normalize();
		return completeURL.toString();
	}


}
