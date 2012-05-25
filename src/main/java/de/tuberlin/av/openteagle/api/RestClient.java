package de.tuberlin.av.openteagle.api;

public interface RestClient {
	public abstract String get(String path);

	public abstract String post(String path, String request);
}