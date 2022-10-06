package assignment2021.codeprovided.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import assignment2021.codeprovided.fitnesstracker.Participant;
import assignment2021.codeprovided.fitnesstracker.measurements.MeasurementType;
import assignment2021.gui.GUIPlotPanel;

/*
 * AbstractGUIPanel.java  	2.0  22/03/2022
 *
 * Copyright (c) University of Sheffield 2022
 */

/**
 * AbstractGUIPanel.java
 *
 * Abstract class designed to be extended. It represents the main panel that
 * will be visualised. This panel contains: 
 * - At the top: the GUI controls that
 * will determine which curves should be displayed in the central panel of the
 * main GUI 
 * - At the centre: the panel where the relevant curves should be drawn
 * using Java2D and a number of controls controlling which type of measurement,
 * which additional values should be also displayed (min/max/average), and if
 * the curves should be showing the values in the original data or in increments
 * - At the bottom: the panel where general details and info about the
 * dataset and the curves plotted shown in the central panel.
 * 
 * @version 2.0 22/03/2022
 *
 * @author Ben Clegg / Islam Elgendy / Maria-Cruz Villa-Uriol
 */
public abstract class AbstractGUIPanel extends JPanel {

	/**
	 * generated serial version UID
	 */
	private static final long serialVersionUID = -2674518387270317168L;

	// defining all the labels and texts that will be shown in the GUI
	private JLabel listParticipantsLabel = new JLabel("Participant:", SwingConstants.LEFT);
	private JLabel measurementTypeLabel = new JLabel("Measurement Type:", SwingConstants.RIGHT);
	private JLabel trackerLabel = new JLabel("Tracker/s:", SwingConstants.LEFT);
	private String selectDataTitle = "PARTICIPANT AND TRACKER/S";
	private String plotResultsTitle = "CURVE/S";
	private String datasetSummaryTitle = "DATASET GENERAL SUMMARY";
	private String curveDetailsTitle = "DISPLAYED CURVE/S DETAILS";
	private String defaultTextDatasetSummaryTextArea = 
			"Default text:\n" + 
			"This panel should display general details about the dataset," + 
			" e.g. number of participants, number of female vs male," + 
			" age range, number of trackers... " + 
			"It should never change its contents. " + 
			"Feel free to innovate!";
	private String defaultTextCurveDetailsTextArea = 
			"Default text:\n" + 
			"This panel should display the details about the curves " + 
			"visualised, e.g. participant ID, female or male, age, " +
			"visualised tracker/s... The contents displayed will depend " +
			"on the selected controls in the top panel. " + 
			"Feel free to innovate!";

	// defining all the comboboxes that will be used to select the data 
	// that will be visualised
	protected String[] participantsArray;
	protected String[] trackersArray;
	protected String[] measurementTypeArray;

	protected JComboBox<String> comboListParticipants;
	protected JComboBox<String> comboListTrackers;
	protected JComboBox<String> comboListMeasurementType;

	// defining the checkboxes used to control how the data should be visualised
	protected JCheckBox cbIncrements;
	protected JCheckBox cbAverageValue;
	protected JCheckBox cbMaxMinValues;
	
	// defining the button that will force an update of the GUI based on the
	// selected settings (topControlPanel)
	protected JButton addCurvesButton = new JButton("Add");
	protected JButton removeAllCurvesButton = new JButton("Remove all");

	// defining the two JTextAreas that will show details about the dataset
	protected JTextArea datasetSummaryTextArea = new JTextArea(10, 30);
	protected JTextArea visualisedCurvesDetailsTextArea = new JTextArea(10, 30);

	// defining the panel were the curves will be displayed
	protected GUIPlotPanel visualisedCurvesPanel;

	protected Collection<Participant> participants;

	/**
	 * loadParticipantsNames method - This method load the participant names into an
	 * array of String to be used in the participant list comboBox.
	 */
	protected void loadParticipantsNames() {
		int i = 0;
		participantsArray = new String[participants.size()];
		// TODO Please override this method in GUIPanel.java and delete this piece of
		// code
		if (participants.contains(null)) {
			for (Participant p : participants) {
				participantsArray[i] = "P" + i;
				i++;
			}
			return;
		}
		i = 0;
		// TODO When you override this method in GUIPanel.Java, keep the remaining of
		// this method
		for (Participant p : participants) {
			participantsArray[i] = p.getName();
			i++;
		}
		System.out.println(Arrays.toString(participantsArray));
	}

	/**
	 * loadTrackers method - This method load the tracker names into an array of
	 * String to be used in the trackers list comboBox.
	 */
	protected void loadTrackers() {
		// TODO Please override this method in GUIPanel.java and delete this piece of
		// code
		if (participants.contains(null)) {
			trackersArray = new String[7];
			trackersArray[0] = "all";
			trackersArray[1] = "FT0";
			trackersArray[2] = "FT1";
			trackersArray[3] = "FT2";
			trackersArray[4] = "FT3";
			trackersArray[5] = "FT4";
			trackersArray[6] = "FT5";
			return;
		}
		// TODO When you override this method in GUIPanel.Java, keep the remaining of
		// this method as it will properly help you populate the combobox used to select which
		// tracker to be visualised
		Collection<String> trackerNames = ((Participant) participants.toArray()[0]).getAllTrackerNames();
		trackersArray = new String[trackerNames.size() + 1];
		trackersArray[0] = "all";
		int i = 1;
		for (String trackerName : trackerNames) {
			trackersArray[i] = trackerName;
			i++;
		}
		System.out.println(Arrays.toString(trackersArray));
	}

	/**
	 * loadMeasurementTypes method - This method load the measurement types into an
	 * array of String to be used in the measurement types list comboBox.
	 */
	private void loadMeasurementTypes() {
		measurementTypeArray = new String[MeasurementType.values().length];
		int i = 0;
		for (MeasurementType t : MeasurementType.values()) {
			measurementTypeArray[i] = t.getMeasurementName();
			i++;
		}
		System.out.println(Arrays.toString(measurementTypeArray));
	}

	/**
	 * setupComboBoxes method - This method loads the data to be shown
	 * in the combo boxes (list of participants, list of trackers, 
	 * and available measurement types.
	 * 
	 */
	private void setupComboBoxes() {

		loadParticipantsNames();
		comboListParticipants = new JComboBox<>(participantsArray);
		comboListParticipants.setName("participants");

		loadTrackers();
		comboListTrackers = new JComboBox<>(trackersArray);
		comboListTrackers.setName("trackers");

		loadMeasurementTypes();
		comboListMeasurementType = new JComboBox<>(measurementTypeArray);
		comboListMeasurementType.setName("measurementTypes");
		
		cbIncrements = new JCheckBox("Show increments");
		cbAverageValue = new JCheckBox("Show average value/s");
		cbMaxMinValues = new JCheckBox("Show min/max value/s");

	}

	/**
	 * addTitleBorder method - This method adds a title border to a JPanel
	 * 
	 * @param title - provides the text with the title for this panel
	 * @param panel - the panel to which a title border will be added
	 * 
	 */
	private void addTitleBorder(String title, JPanel panel) {
		// enclosing this panel with a titled border
		Border blackline = BorderFactory.createLineBorder(Color.black);
		TitledBorder tbPanel = BorderFactory.createTitledBorder(
				blackline, title);
		tbPanel.setTitleJustification(TitledBorder.CENTER);
		panel.setBorder(tbPanel);
	}

	/**
	 * createTitledBorderTextPanel method - This method 
	 * returns a scrollable text panel with a titled border 
	 * 
	 * @param textArea - the main textArea that will be dynamically updated 
	 * based on user interaction
	 * @param defaultText - provides the default text that will be shown
	 * in the textArea
	 * @param title - provides the label with the title for this panel
	 * @return panel - a scrollable panel with a titled border
	 * 
	 */
	private JPanel createTitledBorderTextPanel(
			JTextArea textArea, String defaultText, String title) {

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		textArea.setLineWrap(true);
		// providing a default message that should be overwritten
		// based on user GUI selections
		textArea.setText(defaultText);
		JScrollPane textScrollPane = new JScrollPane(
				textArea, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		panel.add(textScrollPane, BorderLayout.CENTER);

		// enclosing the panel with a title border
		addTitleBorder(title, panel);

		return panel;
	}

	/**
	 * createTopControlPanel method - This method creates the top part of the GUI, which
	 * contains the type and filter selectors that will control the data that will
	 * be visualised.
	 * 
	 * @return topControlPanel - the top control panel to be shown in the main GUI frame.
	 */
	private JPanel createTopControlPanel() {
		JPanel topControlPanel = new JPanel();
		topControlPanel.setLayout(new FlowLayout());

		JPanel typeSelectorPanel = new JPanel();
		typeSelectorPanel.setLayout(new FlowLayout());
		typeSelectorPanel.add(listParticipantsLabel);
		typeSelectorPanel.add(comboListParticipants);
		typeSelectorPanel.add(trackerLabel);
		typeSelectorPanel.add(comboListTrackers);
		typeSelectorPanel.add(addCurvesButton);
		typeSelectorPanel.add(removeAllCurvesButton);

		topControlPanel.add(typeSelectorPanel);

		// enclosing this panel with a title border
		addTitleBorder(selectDataTitle, topControlPanel);

		return topControlPanel;

	}

	/**
	 * createCentrePanel method - This method creates the central part of the GUI,
	 * which contains the curve plots to be drawn based on the user selections
	 * 
	 * @return centreCurvesPlotPanel where the curves will be shown
	 *  based on the user selections .
	 */
	private JPanel createCentrePanel() {
		JPanel centreCurvesPlotPanel = new JPanel();

		JPanel centreControlCurvesPanel = new JPanel();
		centreControlCurvesPanel.setLayout(new FlowLayout());
		centreControlCurvesPanel.add(measurementTypeLabel);
		centreControlCurvesPanel.add(comboListMeasurementType);
		centreControlCurvesPanel.add(cbIncrements);
		centreControlCurvesPanel.add(cbMaxMinValues);
		centreControlCurvesPanel.add(cbAverageValue);

		// setting up the curves panel where all the drawing of curves will happen
		// this is the panel that will need to be regularly updated based on
		// user selections
		visualisedCurvesPanel = new GUIPlotPanel(this);
		visualisedCurvesPanel.setName("curvesPanel");

		centreCurvesPlotPanel.setLayout(new BorderLayout());
		centreCurvesPlotPanel.add(visualisedCurvesPanel, BorderLayout.CENTER);
		centreCurvesPlotPanel.add(centreControlCurvesPanel, BorderLayout.SOUTH);

		// enclosing this panel with a title border
		addTitleBorder(plotResultsTitle, centreCurvesPlotPanel);

		return centreCurvesPlotPanel;

	}

	/**
	 * createBottomInfoPanel method - This method creates the bottom 
	 * part of the GUI, which will display the dataset summary 
	 * and details about the curves being displayed.
	 * 
	 * @return bottomInfoPanel - the bottom panel showing details about the
	 * dataset (left) and the curves being displayed (right).
	 */
	private JPanel createBottomInfoPanel() {
		// Creating the bottom panel area
		JPanel bottomInfoPanel = new JPanel();
		bottomInfoPanel.setLayout(new GridLayout(1, 2));

		// setting up the two text areas where all the data will be displayed
		datasetSummaryTextArea.setName("datasetGeneralDetails");
		visualisedCurvesDetailsTextArea.setName("visualisedDataDetails");

		// Creating bottom left and right panels
		JPanel leftInfoTextPanel = createTitledBorderTextPanel(
				datasetSummaryTextArea, 
				defaultTextDatasetSummaryTextArea, 
				datasetSummaryTitle);

		JPanel rightInfoTextPanel = createTitledBorderTextPanel(
				visualisedCurvesDetailsTextArea, 
				defaultTextCurveDetailsTextArea, 
				curveDetailsTitle);

		// Adding the left and right panels to the main text area panel
		bottomInfoPanel.add(leftInfoTextPanel);
		bottomInfoPanel.add(rightInfoTextPanel);

		return bottomInfoPanel;

	}

	/**
	 * constructor
	 */
	public AbstractGUIPanel(Collection<Participant> participants) {

		this.participants = participants;

		// setting up components in GUI, and the layout of the frame
		setupComboBoxes();


		// Creating the top, centre and bottom panel areas
		JPanel topControlPanel = createTopControlPanel();
		JPanel centreCurvesPanel = createCentrePanel();
		JPanel bottomInfoPanel = createBottomInfoPanel();

		// Setting the layout and adding the top, centre and bottom panels 
		this.setLayout(new BorderLayout());
		this.add(topControlPanel, BorderLayout.NORTH);
		this.add(centreCurvesPanel, BorderLayout.CENTER);
		this.add(bottomInfoPanel, BorderLayout.SOUTH);

		// adding the listeners, you will need to implement this method 
		// to register the events generated
		// by the GUI components that will be expecting a change 
		// in the results being displayed by the GUI
		this.addListeners();
	}

	/**
	 * addListeners method - adds relevant actionListeners to the GUI components (as
	 * required). You will need to listen perhaps to some comboboxes (?).
	 */
	public abstract void addListeners();

	/**
	 * getSelectedParticipant method - returns a String with name of the participant
	 * selected with the corresponding combo box.
	 */
	public abstract String getSelectedParticipantName();

	/**
	 * getSelectedTrackers method - returns a String indicating which trackers were
	 * selected with the corresponding combo box. Tip: The return is a String
	 * because if all trackers were to be visualised, you could use the
	 * Tracker.FILTER_ANY constant to specify that.
	 */
	public abstract String getSelectedTrackersNames();

	/**
	 * getSelectedMeasurementType method - returns the measurement type selected
	 * with the corresponding combo box.
	 */
	public abstract MeasurementType getSelectedMeasurementType();
	
	/**
	 * isShowAverageSelected method - returns if the show average checkbox
	 * has been selected or not.
	 */
	public abstract boolean isShowAverageSelected();
	
	/**
	 * isShowMinMaxSelected method - returns if the show min/max checkbox
	 * has been selected or not.
	 */
	public abstract boolean isShowMinMaxSelected();
	
	/**
	 * isShowIncrementsSelected method - returns if the show increments checkbox
	 * has been selected or not.
	 */
	public abstract boolean isShowIncrementsSelected();

}
