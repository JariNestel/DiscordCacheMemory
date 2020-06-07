package main;

public class OSNotSupportedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6440726292928936872L;
	
	public OSNotSupportedException(String os) {
		super(os+" is not supported");
	}
	
}
