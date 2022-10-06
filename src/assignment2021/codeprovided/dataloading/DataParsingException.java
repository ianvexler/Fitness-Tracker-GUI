package assignment2021.codeprovided.dataloading;

/*
 * DataParsingException.java  	2.0  22/03/2022
 *
 * Copyright (c) University of Sheffield 2022
 */

/**
 * DataParsingException.java
 *
 * Exception denoting that a Parsing error has occurred. This class should NOT
 * be modified and should be kept as it is.
 * 
 * @version 2.0  22/03/2022
 *
 * @author Ben Clegg / Islam Elgendy / Maria-Cruz Villa-Uriol
 */
public class DataParsingException extends Exception {
	/**
	 * Generated serial ID
	 */
	private static final long serialVersionUID = 3616860147116156712L;

	/**
	 * A parsing exception to throw if an error occurs when parsing a Participant in
	 * DataLoader.
	 * 
	 * @param message a message to display for the exception.
	 */
	public DataParsingException(String message) {
		super(message);
	}
}
