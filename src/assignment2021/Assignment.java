package assignment2021;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import assignment2021.codeprovided.fitnesstracker.Participant;
import assignment2021.codeprovided.gui.GUIFrame;
import assignment2021.dataloading.DataLoader;
import assignment2021.handoutquestions.FitnessQuestions;


/*
 * Assignment.java  	2.0  22/03/2022
 *
 * Copyright (c) University of Sheffield 2022
 */

/**
 * Assignment.java
 *
 * Main assignment class that provides all the required functionality to run this
 * assignment. This class should NOT be modified and should be kept as it is.
 * 
 * @version 2.0 22/03/2022
 *
 * @author Ben Clegg / Islam Elgendy / Maria-Cruz Villa-Uriol
 */
public class Assignment {

	/**
	 * Main method
	 * 
	 * @param args should have one argument, the path to the directory containing
	 *             the data files. In Eclipse, you can run with arguments by
	 *             choosing "Run" -> "Run Configurations..." then selecting the
	 *             "Arguments" tab.
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("The path to the data folder (e.g. resources/data) is not provided.");
			System.exit(-1);
		}
		Path dataFolder = Paths.get(args[0]);
		try {
			Assignment assignment = new Assignment(dataFolder);
		} catch (IOException ioEx) {
			System.err.println("Could not list files in " + dataFolder);
			System.err.println("The provided path may be incorrect.");
			System.exit(-1);
		}
	}

	/**
	 * Run the assignment.
	 * 
	 * @param dataFolder the path the directory containing the data files.
	 */
	public Assignment(Path dataFolder) throws IOException {
		// Load participants
		DataLoader dataLoader = new DataLoader();
		Collection<Participant> participants = dataLoader.loadAllParticipants(dataFolder);

		// Questions
		FitnessQuestions questions = new FitnessQuestions(participants);
		System.out.println(questions.toString());

		// GUI
		JFrame explorerGUI = new GUIFrame(participants);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				explorerGUI.setVisible(true);
			}
		});
	}
}
