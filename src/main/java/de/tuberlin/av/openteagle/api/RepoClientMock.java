package de.tuberlin.av.openteagle.api;

import java.io.IOException;

import de.tuberlin.av.openteagle.utils.TeagleProperties;
import de.tuberlin.av.openteagle.utils.Utility;

public class RepoClientMock implements RestClient {

	@Override
	public String get(final String path) {
		String result = "";

		try {
			final String filename = TeagleProperties.getRepoTestInput1();
			result = Utility.readFileAsString(filename);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}

		return result;
	}
}