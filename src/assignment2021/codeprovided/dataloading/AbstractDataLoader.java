package assignment2021.codeprovided.dataloading;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import assignment2021.codeprovided.fitnesstracker.Participant;

/*
 * AbstractDataLoader.java  	2.0  22/03/2022
 *
 * Copyright (c) University of Sheffield 2022
 */

/**
 * AbstractDataLoader.java
 *
 * Abstract class designed to be extended. Provides basic abstract methods to
 * load the data in the files provided. This class should NOT be modified and
 * should be kept as it is.
 * 
 * @version 2.0 22/03/2022
 *
 * @author Ben Clegg / Islam Elgendy / Maria-Cruz Villa-Uriol
 */

public abstract class AbstractDataLoader {

	// The character used to separate individual cells in each line of the data
	// files.
	public static final String CELL_SEPARATOR = ";";
	
	/**
	 * Load a Collection of Participants using the .txt files in the specified
	 * directory. Each .txt file is one Participant.
	 * 
	 * @param dataFileDirectory the Path to the directory containing the Participant
	 *                          .txt files.
	 * @return the parsed Participants.
	 */
	public Collection<Participant> loadAllParticipants(Path dataFileDirectory) throws IOException {
		Collection<Participant> participants = new ArrayList<>();

		Files.list(dataFileDirectory).filter(p -> p.toString().endsWith(".txt")).forEach(textFile -> {
			System.out.println("Parsing Participant from " + textFile);
			try {
				// Parse a participant from the individual text file.
				participants.add(this.loadDataFile(textFile));
			} catch (IOException ioEx) {
				ioEx.printStackTrace();
			} catch (DataParsingException parseEx) {
				parseEx.printStackTrace();
			}

		});

		return participants;
	}

	/**
	 * Load an individual Participant from a single .txt file.
	 * 
	 * @param dataFileLocation the location of the .txt file.
	 * @return the parsed Participant.
	 * @throws IOException          if the .txt file could not be read, e.g. if it
	 *                              doesn't exist.
	 * @throws DataParsingException if a parsing error occurred, e.g. if the data is
	 *                              not in the correct structure.
	 */
	public Participant loadDataFile(Path dataFileLocation) throws IOException, DataParsingException {
		// Read lines of file
		List<String> lines = Files.lines(dataFileLocation).collect(Collectors.toList());

		return loadDataLines(lines);
	}

	/**
	 * Parses the provided lines to create a Participant. This is called by
	 * loadDataFile, where the provided lines have been read directly from the file.
	 * 
	 * @param lines the lines to parse.
	 * @return the parsed Participant.
	 * @throws DataParsingException if a parsing error occurred, e.g. if the data is
	 *                              not in the correct structure.
	 */
	public abstract Participant loadDataLines(List<String> lines) throws DataParsingException;
}
