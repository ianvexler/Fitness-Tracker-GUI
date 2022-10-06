package assignment2021.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
import assignment2021.codeprovided.gui.AbstractGUIPanel;
import assignment2021.handoutquestions.FitnessQuestions;

public class GUIPanel extends AbstractGUIPanel {

	private static final long serialVersionUID = -1257936627636425453L;
	
	String selectedParticipant;
	String selectedTrackers;
	String selectedMeasurementType;
	
	String currentlySelected;
	Boolean allSelected = true;	//Boolean to know if "all" has been selected
	
	public GUIPanel(Collection<Participant> participants) {
		super(participants);
		
		this.selectedParticipant = participantsArray[0];
		this.selectedTrackers = "";
		this.currentlySelected = trackersArray[0]; //Sets "all" as the default selected
		this.selectedMeasurementType = measurementTypeArray[0];
		
		this.datasetSummaryTextArea.selectAll();
		this.datasetSummaryTextArea.replaceSelection(setSummaryText());
		
		this.visualisedCurvesDetailsTextArea.selectAll();
		this.visualisedCurvesDetailsTextArea.replaceSelection("Select a participant to show data");
	}
	
	@Override
	protected void loadTrackers() {		
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
	
	@Override
	public void addListeners() {
		
		addCurvesButton.addActionListener(e -> {
			if(currentlySelected != "") {
				if(currentlySelected.equals("all")) {	//Checks if The currently selected comboBox is "all"
					selectedTrackers = "";
					for(String tracker : trackersArray) {	//If so, it selects all trackers expect "all"
						if(!tracker.equals("all"))
							selectedTrackers += tracker + ";";
					}
					
					allSelected = true;
					currentlySelected = "";
				} else {
					if(allSelected) {	//If "all" was selected it starts selectedTrackers without ";"
						selectedTrackers = "";
						selectedTrackers = currentlySelected;
						allSelected = false;
						currentlySelected = "";
					} else {	//Else it adds ";" + the current selected tracker
						selectedTrackers += ";" + currentlySelected;
						currentlySelected = "";
					}
				}
			}

			visualisedCurvesPanel.repaint();
			visualisedCurvesDetailsTextArea.selectAll();
			visualisedCurvesDetailsTextArea.replaceSelection(setCuvesDetailsText());
		});
		
		removeAllCurvesButton.addActionListener(e -> {
			selectedTrackers = "";
			
			visualisedCurvesPanel.repaint();
			
			//Resets everything
			selectedTrackers = "";
			allSelected = true;
			currentlySelected = (String) comboListTrackers.getSelectedItem();
			
			visualisedCurvesDetailsTextArea.selectAll();
			visualisedCurvesDetailsTextArea.replaceSelection("Select a Participant to show data");
		});
		
		comboListParticipants.addActionListener(e -> {
			selectedParticipant = (String) comboListParticipants.getSelectedItem();
		});
		
		comboListTrackers.addActionListener(e -> {
			//Checks if the selected item is "all" or if selectedTrackers already contains that tracker
			if(((String) comboListTrackers.getSelectedItem()).equals("all")) {
				currentlySelected = (String) comboListTrackers.getSelectedItem();
			//If its not all it adds the string to currentlySelected as long as its not already there
			} else { 
				if(!selectedTrackers.contains((String) comboListTrackers.getSelectedItem())) {
					currentlySelected = (String) comboListTrackers.getSelectedItem();
				} else {
					currentlySelected = "";
				}
			}
			
		});
		
		//Sets selectedMeasurementType to the chosen one in the combo list
		comboListMeasurementType.addActionListener(e -> {
			selectedMeasurementType = (String) comboListMeasurementType.getSelectedItem();
		});
		
		//For all show buttons: update the graph with the information to be shown
		cbMaxMinValues.addActionListener(e -> {
			visualisedCurvesPanel.repaint();
			visualisedCurvesDetailsTextArea.selectAll();
			visualisedCurvesDetailsTextArea.replaceSelection(setCuvesDetailsText());
		});
		
		cbAverageValue.addActionListener(e -> {
			visualisedCurvesPanel.repaint();
			visualisedCurvesDetailsTextArea.selectAll();
			visualisedCurvesDetailsTextArea.replaceSelection(setCuvesDetailsText());
		});

		cbIncrements.addActionListener(e -> {
			visualisedCurvesPanel.repaint();
			visualisedCurvesDetailsTextArea.selectAll();
			visualisedCurvesDetailsTextArea.replaceSelection(setCuvesDetailsText());
		});
		
	}
	
	public Participant getSelectedParticipant(String participantName) {
		for(Participant p : participants) {
			if(participantName.equals(p.getName()))
				return p;
		}
		
		return null;
	}

	@Override
	public String getSelectedParticipantName() {
		return selectedParticipant;
	}

	@Override
	public String getSelectedTrackersNames() {
		return selectedTrackers;
	}

	@Override
	public MeasurementType getSelectedMeasurementType() {
		return MeasurementType.fromMeasurementName(selectedMeasurementType);
	}

	@Override //If isShowAverage is selected return true
	public boolean isShowAverageSelected() {
		if(cbAverageValue.isSelected()) {
			return true;
		}
		return false;
	}

	@Override //If showMinMax is selected return true
	public boolean isShowMinMaxSelected() {
		if(cbMaxMinValues.isSelected()) {
			return true;
		}		
		return false;
	}
	
	@Override //If showIncrement is selected return true
	public boolean isShowIncrementsSelected() {
		if(cbIncrements.isSelected()) {
			return true;
		}
		return false;
	}
	
	@Override
	public void loadParticipantsNames() {
		int i = 0;
		participantsArray = new String[participants.size()];
		
		for (Participant p : participants) {
			participantsArray[i] = p.getName();
			i++;
		}
	}
	
	/**
	 * Sets the text of the summary box
	 * 
	 * @return string that will be displayed in the summary box
	 */
	private String setSummaryText() {
		FitnessQuestions fq = new FitnessQuestions(participants);
		
		return "Total Participants: " + fq.getTotalParticipants() + "\n" +
				"Females: " + getFemaleParticipants() + "\n" +
				"Males: " + getMaleParticipants() + "\n" +
				"Age range: " + getAgeRange() + "\n" +
				"Average Female Age: " + getAvgFemaleAge() + "\n" +
				"Average Male Age: " + getAvgMaleAge() + "\n" +
				"\n" +
				"Number of Trackers: " + (trackersArray.length - 1) + "\n" +
				"Number of Measurements: " + measurementTypeArray.length + "\n";
	}
	
	//
	
	/**
	 * Sets the text of the curves details box
	 * 
	 * @return string that will be displayed in the curves details box
	 */
	private String setCuvesDetailsText() {	
		FitnessQuestions fq = new FitnessQuestions(participants);
		MeasurementType type = getSelectedMeasurementType();
		String details = "";
		
		details += "Participant ID: " + selectedParticipant + "\n" +
				"Gender: " + getParticipantGender(selectedParticipant) + "\n" +
				"Age: " + getParticipantAge(selectedParticipant) + "\n" +
				"Visualised Trackers: " + getVisualisedTrackers() + "\n" + "\n" +
				"Average " + type.getMeasurementName() + " for " + selectedParticipant + ": " + 
				fq.getAverageForTrackerForParticipant(type, selectedParticipant) + "\n" +
				"Average " + type.getMeasurementName() + " for dataset: " + fq.getAverageForDataset(type) + "\n" +
				"Max value: " + fq.getHighesTypeForParticipant(type, selectedParticipant, trackersToList()) + "\n" +
				"Min value: " + fq.getLowestTypeForParticipant(type, selectedParticipant, trackersToList()) + "\n" +
				 "\n" + "Legend: " + "\n";
		
		for(String tracker : trackersToList()) {
			details += tracker + ": " + getLineColor(tracker) + "\n";
		}
		
		//If showMinMax is selected it will show the numbers in curvesDetails
		if(isShowMinMaxSelected()) {
			details += "Max: " + getLineColor("max") + "\n" +
					"Min: " + getLineColor("min") + "\n";
		}
		
		//If showAverage is selected it will show the numbers in curvesDetails
		if(isShowAverageSelected()) {
			details += "Average: " + getLineColor("average") + "\n";
		}
				
		return details;		
	}
	
	/**
	 * Function to get the count of male participants
	 * 
	 * @return count of male participants
	 */
	public int getMaleParticipants() {
		int count = 0;
		
		//Looks in all participants for gender equal "m"
		for(Participant p : participants) {
			if(p.getGender().equals("m"))
				count++;
		}
		
		return count;
	}
	
	/**
	 * Function to get the count of female participants
	 * 
	 * @return count of female participants
	 */
	public int getFemaleParticipants() {
		int count = 0;
		
		//Looks in all participants for gender equal "f"
		for(Participant p : participants) {
			if(p.getGender().equals("f"))
				count++;
		}
		
		return count;
	}
	
	/**
	 * Returns a string with the age range
	 * 
	 * @return a string with age range
	 */
	public String getAgeRange() {
		int maxAge = 0;
		
		for(Participant p : participants) {
			if(p.getAge() > maxAge) {
				maxAge = p.getAge();
			}
		}
		
		int minAge = maxAge;

		for(Participant p : participants) {
			if(p.getAge() < minAge) {
				minAge = p.getAge();
			}
		}
		
		return minAge + "-" + maxAge;
	}

	/**
	 * Returns the average male age
	 * 
	 * @return the average male age
	 */
	public double getAvgMaleAge() {
		int totalAgeCount = 0;
		
		for(Participant p : participants) {
			if(p.getGender().equals("m")) {
				totalAgeCount += p.getAge();
			}
		}
		
		return Double.valueOf(totalAgeCount / getMaleParticipants());
	}
	
	/**
	 * Returns the average female age
	 * 
	 * @return the average female age
	 */
	public double getAvgFemaleAge() {
		int totalAgeCount = 0;

		for(Participant p : participants) {
			if(p.getGender().equals("f")) {
				totalAgeCount += p.getAge();
			}
		}
		
		return Double.valueOf(totalAgeCount / getFemaleParticipants());
	}
	
	/**
	 * Gets the current participant's gender
	 * @param selectedParticipant - the current participant's name
	 * 
	 * @return the gender of the current participant
	 */
	public String getParticipantGender(String selectedParticipant) {
		
		//Looks for the participant that matches the selectedParticipant's name
		for(Participant p : participants) {
			if(p.getName().equals(selectedParticipant)) {
				if (p.getGender().equals("m")){
					return "Male";
				} else if (p.getGender().equals("f")) {
					return "Female";
				}
			}
		}
		
		return null;
			
	}
	
	/**
	 * Returns the selectedParticipant's age
	 * @param selectedParticipant - the current participant's name
	 * 
	 * @return the age of the current participant
	 */
	public int getParticipantAge(String selectedParticipant) {
		
		//Looks for the participant that matches the selectedParticipant's name
		for(Participant p : participants) {
			if(p.getName().equals(selectedParticipant)) {
				return p.getAge();
			}
		}
		
		return 0;
	}
	
	/**
	 * Gets the visualised trackers
	 * 
	 * @return a string with the visualised trackers
	 */
	public String getVisualisedTrackers() {
		String visualisedTrackers = "";
		
		//Splits selectedTrackers on each ";"
		for (int i = 0; i < selectedTrackers.split(";").length; i++) {
			visualisedTrackers += selectedTrackers.split(";")[i] + " ";
		}
		
		return visualisedTrackers;
	}
	
	/**
	 * Turns the selected tracker string into a List
	 * Useful for other methods
	 * 
	 * @return a list of the tracker names
	 */
	public List<String> trackersToList() { 
		List<String> trackersList = new ArrayList<String>();
		
		for (int i = 0; i < selectedTrackers.split(";").length; i++) {
			trackersList.add(selectedTrackers.split(";")[i]);
		}
		
		return trackersList;
	}
	
	/**
	 * Gets the participant collection
	 * 
	 * @return a collection of participants
	 */
	public Collection<Participant> getParticipants() {
		return super.participants;
	}
	
	/**
	 * Returns a string with the colour of the line depending on the line
	 * 
	 * @return the colour of the line
	 */
	public String getLineColor(String lineName) {
		if(lineName.equals("FT0")) 
			return "Blue";
		
		if(lineName.equals("FT1")) 
			return "Red";
		
		if(lineName.equals("FT2")) 
			return "Black";
		
		if(lineName.equals("FT3")) 
			return "Pink";
		
		if(lineName.equals("FT4")) 
			return "Magenta";
		
		if(lineName.equals("FT5")) 
			return "Cyan";
		
		if(lineName.equals("max"))
			return "Red (Vertical)";
		
		if(lineName.equals("min"))
			return "Blue (Vertical)";
		
		if(lineName.equals("average"))
			return "Black (Horizontal)";
				
		return null;
	}
}