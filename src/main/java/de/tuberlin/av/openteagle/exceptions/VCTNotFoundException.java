package de.tuberlin.av.openteagle.exceptions;

public class VCTNotFoundException extends Exception {

	private static final long serialVersionUID = 3906020995021012536L;

	public VCTNotFoundException() {
		super();
	}

	public VCTNotFoundException(String message) {
		super(message);
	}

	public VCTNotFoundException(Throwable cause) {
		super(cause);
	}

	public VCTNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public VCTNotFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
