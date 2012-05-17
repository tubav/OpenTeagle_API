package de.tuberlin.av.openteagle.model.vct;

import de.tuberlin.av.openteagle.model.vct.jaxb.Vct;

public class VctPrinter {
	public static String printDetails(Vct vct) {
		String result = "";
		result += " * ID: " + vct.getId() + "\n";
		result += " * CommonName: " + vct.getCommonName() + "\n";
		result += " * User: " + vct.getUser().getId() + "\n";
		result += " * State: " + vct.getState().getId() + "\n";
		return result;
	}

	public static String printSummary(Vct vct) {
		String result = "";
		result += " * ID: " + vct.getId() + " (" + vct.getCommonName() + ")\n";
		return result;
	}
}
