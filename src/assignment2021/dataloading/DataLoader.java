package assignment2021.dataloading;

import java.util.*;

import assignment2021.codeprovided.dataloading.AbstractDataLoader;
import assignment2021.codeprovided.dataloading.DataParsingException;
import assignment2021.codeprovided.fitnesstracker.Participant;
import assignment2021.codeprovided.fitnesstracker.Tracker;
import assignment2021.codeprovided.fitnesstracker.measurements.*;

public class DataLoader extends AbstractDataLoader {

	@Override
	public Participant loadDataLines(List<String> lines) throws DataParsingException {
		String name = "";
		String gender = "";
		int age = 0;
		
		try {
			name = lines.get(0).split(",")[0];
			
			try {
				age = Integer.parseInt(lines.get(0).split(",")[1]);
			} catch(NumberFormatException e) {
				age = 0;
			}
			
			try {
				if (lines.get(0).split(",")[2].toUpperCase().equals("M") || lines.get(0).split(",")[2].toUpperCase().equals("F")) {
					gender = lines.get(0).split(",")[2];
				} else {
					gender = "Other";
				}
			} catch(Exception e) {
				gender = "Other";
			}
		} catch(Exception e) {
			
		}
		
		
		Participant participant = new Participant(name, age, gender);
		Map<String, Tracker> trackerMap = participant.getTrackersMap();
		
		//Iterates through all lines
		for(String line : lines) {
			if(!checkNextLinePI(line, name, gender, age)) {
				if(line.equals("Distance") || line.equals("Energy expenditure") || line.equals("Heart Rate") || line.equals("Steps")) {
					int index = lines.indexOf(line);
					String measurementType = line; //Stores the measurement type written in the line				
					
					//Gets the tracker names by looking at the next line
					String[] trackerNames = getTrackerNames(lines.get(index + 1), participant, amountOfTrackers(lines.get(index + 1)) );

					for(int i = 0; i < trackerNames.length; i++) {
						String trackerName = trackerNames[i];
						trackerMap.put(trackerName, addTrackerMeasurements(lines, participant, index + 2, i, trackerName, measurementType));
					}
				}
								
			}
		}
						
		return participant;
	}	
	
	/**
	 * Gets the names of all the trackers from a line input
     * @param line - The current line
     * @param p - the participant being created
     * @param numOfTrackers - the total amount of trackers
     *
     * @return A string array with all tracker names
     */
	public String[] getTrackerNames(String line, Participant p, int numOfTrackers) {
				
		String[] trackerNames = new String[numOfTrackers];
				
		if(trackerNames.length != 0) {
			for(int i = 0; i < trackerNames.length; i++) {
				trackerNames[i] = line.split(";")[i + 1];
			}
		}
		
		return trackerNames;
	}
	
	/**
	 * Gets the number of trackers
     * @param line - the current line
     *
     * @return The total amount of trackers
     */
	public int amountOfTrackers(String line){
		return (line.split(";").length - 1);
	}
	
	
	/**
	 * Adds the measurements to a tracker in a participant and returns that tracker
	 * @param lines - the lines that contain the measurements
	 * @param p - the participant
	 * @param lineIndex - the index of the line where the data examination should start
	 * @param trackerIndex - the index of the tracker so that it can be found in the line
	 * @param trackerName - the name of the tracker
	 * @measurementType - the measurement type as a string
	 * 
	 * @return - the tracker with measurements
	 */

	public Tracker addTrackerMeasurements(List<String> lines, Participant p, int lineIndex, int trackerIndex, String trackerName, String measurementType) {
		List<Measurement> measurementList = new ArrayList<Measurement>();
		MeasurementType mt = MeasurementType.fromMeasurementName(measurementType);
		int count = 1;
		
		for(String line : lines) {
			Measurement measurement;
			
			if(lines.indexOf(line) > lines.indexOf(measurementType) + 1) {
				//Makes sure that the line is not empty or invalid
				try {
					if(checkNextLineMT(line) && line != measurementType) {
						break;
					}
	
					measurement = MeasurementFactory.createMeasurement(mt, count, line.split(";")[trackerIndex + 1]);
					measurementList.add(measurement);
					count++;
				} catch(Exception e) {
					
				}
			}
			
		}
		
		//Checks if the tracker already exists
		if(p.getAllTrackerNames().contains(trackerName)) {
			for(Measurement m : measurementList) {
				p.addMeasurementToTracker(trackerName, m);
			}
			
			return p.getTracker(trackerName);
		//If not it creates a new one
		} else { 
			Tracker newTracker = new Tracker(trackerName);
			newTracker.getMeasurementsMap().put(MeasurementType.fromMeasurementName(measurementType), measurementList);
			return newTracker;
		}
	}
	
	/**
	 * Method to check if the next line is a participant information (PI) line
	 * @param line - the line that contains the tracker names
	 * 
	 * @return boolean that indicates if next line is PI
	 */
	public boolean checkNextLinePI(String line, String name, String gender, int age) {		
		if(name.equals(line.split(",")[0]) && 
			age == Integer.parseInt(line.split(",")[1]) &&
			gender.equals(line.split(",")[2].toUpperCase().equals("M") || line.split(",")[2].toUpperCase().equals("F")))
			return true;
		
		return false;
	}
	
	/**
	 * Method to check if the next line is a measurement type (MT) line
	 * @param line - the line that contains the tracker names
	 * 
	 * @return boolean that indicates if next line is MT
	 */
	public boolean checkNextLineMT(String line) {
		if(line.equals("Distance") || line.equals("Energy expenditure") || line.equals("Heart Rate") || line.equals("Steps"))
			return true;
		
		return false;
	}
	
	/**
	 * Method to check if the next line is a tracker name (TN) line
	 * @param line - the line that contains the tracker names
	 * 
	 * @return boolean that indicates if next line is TN
	 */

	public boolean checkNextLineTN(String line) {
		if(line.equals("Distance") || line.equals("Energy expenditure") || line.equals("Heart Rate") || line.equals("Steps"))
			return true;
		
		return false;
	}
	
}
