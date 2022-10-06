package assignment2021.handoutquestions;

import java.util.*;

import assignment2021.codeprovided.fitnesstracker.Participant;
import assignment2021.codeprovided.fitnesstracker.Tracker;
import assignment2021.codeprovided.fitnesstracker.measurements.Measurement;
import assignment2021.codeprovided.fitnesstracker.measurements.MeasurementType;
import assignment2021.codeprovided.handoutquestions.AbstractFitnessQuestions;


public class FitnessQuestions extends AbstractFitnessQuestions {
	
	private Collection<Participant> participants;
	
	public FitnessQuestions(Collection<Participant> participants) {
		super(participants);
	}
	
	@Override
	public String toString() {
		String questions = "\nQ1.\tTotal number of Participants: " + getTotalParticipants() +
				"\n\nQ2.\tNumber of participants with heart rate measurements: " 
				+ getParticipantsNumberWithHRM() + " out of " + getTotalParticipants() +
				"\n\nQ3.\tNumber of participants with steps measurements: " 
				+ getParticipantsNumberWithStepsM() + " out of " + getTotalParticipants()  +
				"\n\nQ4.\tNumber of participants with distance measurements: " 
				+ getParticipantsNumberWithDistanceM() + " out of " + getTotalParticipants()  +
				"\n\nQ5.\tNumber of participants with energy expenditure measurements: " 
				+ getParticipantsNumberWithEEM() + " out of " + getTotalParticipants()  +
				"\n\nQ6.\tTotal count of heart rate: " + getTotalHRMCount() +
				"\n\nQ7.\tTotal count of steps: " + getTotalStepsCount() +
				"\n\nQ8.\tTotal count of distance: " + getTotalDistanceCount() +
				"\n\nQ9.\tTotal count of energy expenditure: " + getTotalEECount() +
				"\n\nQ10.";
				
		int count = 0;
		for(int i : getHRMCountPerFT()) {
			questions += "\tTotal count of heart rate for FT" + count + ": " + i + "\n";
			count++;
		}
		
		questions += "\nQ11.\tTotal count of energy expenditure measurements for FT1: " 
				+ getEEMCountForFT1() +
				"\n\nQ12.";
		
		count = 2;
		for(int i : getStepsMCountForFT234()) {
			questions += "\tTotal count of steps for FT" + count + ": " + i + "\n";
			count++;
		}
			
		questions += "\nQ13.\tTotal count of distance for FT5: " + getDistanceMCountForFT5() +
				"\n\nQ14.\t" + getHighestNumberOfStepsParticipants().size() 
				+ " participant/s highest number of steps:\n";
		
		for(String s : getHighestNumberOfStepsParticipants()) {
			questions += "\t* Participant ID " + s + " with number of steps: " 
					+ getHighestMeasurement(MeasurementType.STEPS, s) + "\n";
		}
		
		questions += "\nQ15.\t" + getHighestWalkedDistanceParticipants().size() 
				+ " participants with highest walked distance:\n";
		
		for(String s : getHighestWalkedDistanceParticipants()) {
			questions += "\t* Participant ID " + s + " with walked distance: " 
					+ getHighestMeasurement(MeasurementType.DISTANCE, s) + "\n";
		}
		
		questions += "\nQ16.\tGlobal average number of steps for FT1: " + getGlobalAverageStepsForFT1() +
				"\n\nQ17.\t" + getAvgStepsAboveGlobalParticipantsForFT1().size() 
				+ " participant/s with number of steps above global average for FT1 (" 
				+ getGlobalAverageStepsForFT1() +  "):\n";
		
		for(String s : getAvgStepsAboveGlobalParticipantsForFT1()) {
			questions += "\t* Participant ID " + s + " with average steps: " 
					+ getAverageForSingleTrackerForParticipant(MeasurementType.STEPS, s, "FT1") + "\n";
		}
		
		questions += "\nQ18.\t" + getAvgStepsBelowGlobalParticipantsForFT1().size() + " participant/s " + 
				"with number of steps below global average for FT1 (" + getGlobalAverageStepsForFT1() + "):\n";
		
		for(String s : getAvgStepsBelowGlobalParticipantsForFT1()) {
			questions += "\t* Participant ID " + s + " with average steps: " 
					+ getAverageForSingleTrackerForParticipant(MeasurementType.STEPS, s, "FT1") + "\n";
		}		
		
		questions += "\nQ19.\tGlobal average of energy expenditure for FT2 and FT3: " 
				+ getGlobalAverageEEForFT2FT3() +
				"\n\nQ20.\t" + getAvgEEAboveGlobalParticipantsForFT2FT3().size() 
				+ " participant/s with energy expenditure above global average for FT2 and FT3 (" + 
				getGlobalAverageEEForFT2FT3() + "):\n";
		
		List<String> trackerNames = new ArrayList<String>();
		trackerNames.add("FT2");
		trackerNames.add("FT3");
		
		for(String s : getAvgEEAboveGlobalParticipantsForFT2FT3()) {
			questions += "\t* Participant ID " + s + " with average energy expenditure: " 
					+ getAverageForMultipleTrackersForParticipant(MeasurementType.ENERGY_EXPENDITURE, s, trackerNames) + "\n";
		}
		
		questions += "\nQ21.\t" + getAvgEEBelowGlobalParticipantsForFT2FT3().size() 
				+ " participant/s with energy expenditure below global average for FT2 and FT3 (" 
				+ getGlobalAverageEEForFT2FT3() + "):\n";

		
		for(String s : getAvgEEBelowGlobalParticipantsForFT2FT3()) {
			questions += "\t* Participant ID " + s + " with average energy expenditure: " 
					+ getAverageForMultipleTrackersForParticipant(MeasurementType.ENERGY_EXPENDITURE, s, trackerNames) + "\n";
		}
		
		questions += "\nQ22.\tGlobal average heart rate: " + getGlobalAverageHR() +
				"\n\nQ23.\t" + getAvgHRAboveGlobalParticipants().size() + 
				" participant/s with heart rate above global average heart rate (" 
				+ getTypeAverageGlobal(MeasurementType.HEART_RATE) + "):\n";
		
		for(String s : getAvgHRAboveGlobalParticipants()) {
			questions += "\t* Participant ID " + s + " with average heart rate: " 
		+ getAverageForParticipant(MeasurementType.HEART_RATE, s) + "\n";
		}
		
		questions += "\nQ24.\t" + getAvgHRBelowGlobalParticipants().size() + " participant/s "
				+ "with heart rate below global average heart rate (" + 
				getTypeAverageGlobal(MeasurementType.HEART_RATE) + "):\n";
		
		for(String s : getAvgHRBelowGlobalParticipants()) {
			questions += "\t* Participant ID "  + s + " with average heart rate: " + 
					getAverageForParticipant(MeasurementType.HEART_RATE, s) + "\n";
		}
		
		return questions;
	}
	
	@Override
	public int getTotalParticipants() {
		return getParticipants().size();
	}
	
	@Override
	public int getParticipantsNumberWithHRM() {
		return getParticipantsNumberWithMT(MeasurementType.HEART_RATE);
	}
	
	@Override
	public int getParticipantsNumberWithStepsM() {
		return getParticipantsNumberWithMT(MeasurementType.STEPS);
	}
	
	@Override
	public int getParticipantsNumberWithDistanceM() {
		return getParticipantsNumberWithMT(MeasurementType.DISTANCE);
	}

	@Override
	public int getParticipantsNumberWithEEM() {
		return getParticipantsNumberWithMT(MeasurementType.ENERGY_EXPENDITURE);
	}

	@Override
	public int getTotalHRMCount() {
		return getTotalMeasurementCount(MeasurementType.HEART_RATE);
	}

	@Override
	public int getTotalStepsCount() {
		return getTotalMeasurementCount(MeasurementType.STEPS);
	}

	@Override
	public int getTotalDistanceCount() {
		return getTotalMeasurementCount(MeasurementType.DISTANCE);
	}

	@Override
	public int getTotalEECount() {
		return getTotalMeasurementCount(MeasurementType.ENERGY_EXPENDITURE);

	}

	@Override
	public List<Integer> getHRMCountPerFT() {
		List<Integer> mtList = new ArrayList<Integer>();
		int numberOfTrackers;
		String ftName;
		
		numberOfTrackers = getNumberofTrackersForType(MeasurementType.HEART_RATE);
		
		for(int i = 0; i < numberOfTrackers; i++) {
			ftName = "FT" + i;
			mtList.add(getMeasurementCountForTracker(MeasurementType.HEART_RATE, ftName));
		}
		
		return mtList;
	}

	@Override
	public int getEEMCountForFT1() {
		return getMeasurementCountForTracker(MeasurementType.ENERGY_EXPENDITURE, "FT1");
	}

	@Override
	public List<Integer> getStepsMCountForFT234() {
		List<Integer> mtList = new ArrayList<Integer>();
		String ftName;
		
		for(int i = 2; i <= 4; i++) {
			ftName = "FT" + i;
			mtList.add(getMeasurementCountForTracker(MeasurementType.STEPS, ftName));
		}
		
		return mtList;
	}

	@Override
	public int getDistanceMCountForFT5() {
		return getMeasurementCountForTracker(MeasurementType.DISTANCE, "FT5");
	}

	@Override
	public Set<String> getHighestNumberOfStepsParticipants() {
		return getParticipantsWithHighestMeasurement(MeasurementType.STEPS);
	}

	@Override
	public Set<String> getHighestWalkedDistanceParticipants() {
		return getParticipantsWithHighestMeasurement(MeasurementType.DISTANCE);
	}

	@Override
	public double getGlobalAverageStepsForFT1() {
		return getAvgOfTypeForTracker(MeasurementType.STEPS, "FT1");
	}

	@Override
	public List<String> getAvgStepsAboveGlobalParticipantsForFT1() {
		
		List<String> trackerNames = new ArrayList<String>();
		trackerNames.add("FT1");
		
		return getParticipantsWithMeasurementAboveAvg(MeasurementType.STEPS, trackerNames);
	}

	@Override
	public List<String> getAvgStepsBelowGlobalParticipantsForFT1() {
		
		List<String> trackerNames = new ArrayList<String>();
		trackerNames.add("FT1");
		
		return getParticipantsWithMeasurementBelowAvg(MeasurementType.STEPS, trackerNames);
	}

	@Override
	public double getGlobalAverageEEForFT2FT3() {
		List<String> trackerNames = new ArrayList<String>();
		
		trackerNames.add("FT2");
		trackerNames.add("FT3");
		
		return getAverageForTrackers(MeasurementType.ENERGY_EXPENDITURE, trackerNames);
	}

	@Override
	public List<String> getAvgEEAboveGlobalParticipantsForFT2FT3() {
		List<String> trackerNames = new ArrayList<String>();
		
		trackerNames.add("FT2");
		trackerNames.add("FT3");
		
		return getAverageAboveForTrackers(MeasurementType.ENERGY_EXPENDITURE, trackerNames);
	}

	@Override
	public List<String> getAvgEEBelowGlobalParticipantsForFT2FT3() {
		List<String> trackerNames = new ArrayList<String>();
		
		trackerNames.add("FT2");
		trackerNames.add("FT3");
		
		return getAverageBelowForTrackers(MeasurementType.ENERGY_EXPENDITURE, trackerNames);
	}

	@Override
	public double getGlobalAverageHR() {
		return getAverageForDataset(MeasurementType.HEART_RATE);
	}

	@Override
	public List<String> getAvgHRAboveGlobalParticipants() {
		return getParticipantsWithAvgTypeAboveGlobal(MeasurementType.HEART_RATE);
	}

	@Override
	public List<String> getAvgHRBelowGlobalParticipants() {
		return getParticipantsWithAvgTypeBelowGlobal(MeasurementType.HEART_RATE);
	}
	
	/** ==== Methods used in FitnessQuestions ==== */
	
	/**
	 * Gets the number of participant of a type
	 * @param type - the measurement type 
	 * 
	 * @returns - the number of participants of a certain type
	 */
	public int getParticipantsNumberWithMT(MeasurementType type) {
		int counter = 0;
		
		//Iterates through all participants looking if they have the type
		for(Participant p : getParticipants()) {
			if(Measurement.filterMeasurementsByType(p.getAllMeasurements(), type).size() > 0) {
				counter++;
			}
		}
		
		return counter;
	}
	
	/**
	 * Gets the total measurement count
	 * @param type - the measurement type 
	 * 
	 * @returns - the total count for a type
	 */
	public int getTotalMeasurementCount(MeasurementType type) {
		int counter = 0;
		
		for(Participant p : getParticipants()) {
			counter += Measurement.filterMeasurementsByType(p.getAllMeasurements(),type).size();

		}
		
		return counter;
	}
	
	/**
	 * Gets the total measurement count for a tracker
	 * @param type - the measurement type 
	 * @param trackerName - the name of the tracker to look for
	 * 
	 * @returns - the total count for a specific tracker for a type
	 */
	public int getMeasurementCountForTracker(MeasurementType type, String trackerName) {
		int counter = 0;
		
		for(Participant p : getParticipants()) {
			Tracker tracker = p.getTracker(trackerName);
			List<Measurement> measurements = tracker.getMeasurementsForType(type);
			counter += Measurement.filterMeasurementsByType(measurements, type).size();
		}
		
		return counter;
	}
	
	/**
	 * Gets the number of trackers for a type
	 * @param type - the measurement type 
	 * 
	 * @returns - the total number of trackers for a type
	 */
	public int getNumberofTrackersForType(MeasurementType type) {	
		int counter = 0;
		int maxCounter = 0;
		
		for(Participant p : getParticipants()) {
			counter = 0;
			
			for(Tracker t : p.getAllTrackers()) {
				if(t.getMeasurementsMap().containsKey(type));
					counter++;
			}
			
			if(counter > maxCounter)
				maxCounter = counter;
		}
		
		return counter;
	}
	
	/**
	 * Gets the highest measurement of a participant
	 * @param type - the measurement type 
	 * @param participantName - the participant's name to look for
	 * 
	 * @returns - the highest measurement for a specific participant and type
	 */
	public int getHighestMeasurement(MeasurementType type, String participantName) {
		int highest = 0;
		
		//Iterates through all participants looking for the one that matches the name
		for(Participant p : getParticipants()) {
			if(p.getName().equals(participantName)) {
				for(Tracker t : p.getAllTrackers()) {
					for(Measurement m : t.getMeasurementsForType(type)) {
						if(m.getValue().intValue() > highest) {
							highest = m.getValue().intValue();
						}
					}
				}
			}
		}
		
		return highest;
	}
	
	/**
	 * Gets the participants with the highest measurement
	 * @param type - the measurement type 
	 * 
	 * @returns - the name of the participants with the highest measurement
	 */
	public Set<String> getParticipantsWithHighestMeasurement(MeasurementType type) {
		Set<String> participantsSet = new HashSet<String>();
		int highest = 0;
		
		//Iterates through all participants, all trackers and all measurements
		//looking for the highest. If there is a new highest it empties the list
		//to only include the new highest
		for(Participant p : getParticipants()) {
			for(Tracker t : p.getAllTrackers()) {
				for(Measurement m : t.getMeasurementsForType(type)) {
					if(m.getValue().intValue() > highest) {
						participantsSet.clear();
						participantsSet.add(p.getName());
						highest = m.getValue().intValue();
					}
					
					if(m.getValue().intValue() == highest) {
						participantsSet.add(p.getName());
					}
				}
			}
		}
		
		return participantsSet;
	}
	
	/**
	 * Gets the highest measurement for a participant for a certain type
	 * @param type - the measurement type 
	 * @param participantName - the name of the participant to look for
	 * @param trackerNames - the list of tracker names
	 * 
	 * @returns - the highest measurement for a participant
	 */
	public int getHighesTypeForParticipant(MeasurementType type, String participantName, List<String> trackerNames) {
		int highest = 0;
		
		for(Participant p : getParticipants()) {
			
			if(p.getName().equals(participantName)) {
				for(Tracker t : p.getAllTrackers()) {
					if(trackerNames.contains(t.getName())) {
						for(Measurement m : t.getMeasurementsForType(type)) {
							if(m.getValue().intValue() > highest) {
								highest = m.getValue().intValue();
							}
						}
					}
				}
			}
		}
		
		return highest;
	}
	
	/**
	 * Returns the lowest measurement for a participant for a type
	 * @param type - the measurement type 
	 * @param participantName - the name of the participant to look for
	 * @param trackerNames - the list of tracker names
	 * 
	 * @returns - the lowest measurement for a participant
	 */
	public int getLowestTypeForParticipant(MeasurementType type, String participantName, List<String> trackerNames) {
		int lowest = getHighesTypeForParticipant(type, participantName, trackerNames);
		
		for(Participant p : getParticipants()) {
			
			if(p.getName().equals(participantName)) {
				for(Tracker t : p.getAllTrackers()) {
					if(trackerNames.contains(t.getName())) {
						for(Measurement m : t.getMeasurementsForType(type)) {
							if(m.getValue().intValue() < lowest) {
								lowest = m.getValue().intValue();
							}
						}
					}
				}
			}
		}
		
		return lowest;
	}
	
	
	/**
	 * Gets the average of a type for a tracker
	 * @param type - the measurement type 
	 * @param trackerName - the tracker to look for
	 * 
	 * @returns - the average of a type for a tracker and type
	 */
	public double getAvgOfTypeForTracker(MeasurementType type, String trackerName) {
		double totalMeasurements = 0;
		double totalForAverage = 0;
		double numberOfMeasurements = 0;
		
		for(Participant p : getParticipants()) {
			
			totalMeasurements = 0;
			
			//If it has that tracker it adds all the measurements to totalForAverage
			if (p.getTrackersMap().containsKey(trackerName)) {
				for(Measurement m : p.getTracker(trackerName).getMeasurementsForType(type)) {
					totalMeasurements += m.getValue().doubleValue();
					numberOfMeasurements++;
				}
				
				totalForAverage += totalMeasurements;
			}
		}
		
		return 	Math.round((totalForAverage / numberOfMeasurements) * 100.0) / 100.0;
	}
	
	/**
	 * Gets the participants with measurements above average
	 * @param type - the measurement type 
	 * @param trackerNames - the list of tracker names
	 * 
	 * @returns - a list with the participants with measurements above average
	 */
	public List<String> getParticipantsWithMeasurementAboveAvg(MeasurementType type, List<String> trackerNames){
		List<String> participantsAboveAvg = new ArrayList<String>();
		
		double totalMeasurements = 0;
		double totalForAverage = 0;
		
		double averageForTrackers = 0;
		double countOfTrackers = 0;
		
		//Gets the average for each tracker and total count of them
		for(String trackerName : trackerNames) {
			averageForTrackers += getAvgOfTypeForTracker(type, trackerName);
			countOfTrackers++;
		}
		
		//Loops through all participants to find which are above average
		for(Participant p : getParticipants()) {
			totalForAverage = 0;
			totalMeasurements = 0;
			
			double avgParticipant = getAverageForMultipleTrackersForParticipant(type, p.getName(), trackerNames);
			
			if(avgParticipant > (averageForTrackers / countOfTrackers))
				participantsAboveAvg.add(p.getName());
		}
		
		return participantsAboveAvg;
	}
	
	/**
	 * Gets the participants with measurements below average
	 * @param type - the measurement type 
	 * @param trackerNames - the list of tracker names
	 * 
	 * @returns - a list with the participants with measurements below average
	 */
	public List<String> getParticipantsWithMeasurementBelowAvg(MeasurementType type, List<String> trackerNames){
		List<String> participantsAboveAvg = new ArrayList<String>();
		
		double totalMeasurements = 0;
		double totalForAverage = 0;
		
		double averageForTrackers = 0;
		double countOfTrackers = 0;
		
		//Gets the average for each tracker and total count of them
		for(String trackerName : trackerNames) {
			averageForTrackers += getAvgOfTypeForTracker(type, trackerName);
			countOfTrackers++;
		}

		//Loops through all participants to find which are below average
		for(Participant p : getParticipants()) {
			totalForAverage = 0;
			totalMeasurements = 0;
			
			double avgParticipant = getAverageForMultipleTrackersForParticipant(type, p.getName(), trackerNames);
			
			if(avgParticipant < (averageForTrackers / countOfTrackers))
				participantsAboveAvg.add(p.getName());
		}
		
		return participantsAboveAvg;
	}
	
	/**
	 * Gets the  average measurement for multiple trackers
	 * @param type - the measurement type 
	 * @param trackerNames - the list of tracker names
	 * 
	 * @returns - the average of a type for multiple trackers
	 */
	public double getAverageForTrackers(MeasurementType type, List<String> trackerNames) {
		double totalForAverage = 0;
		double numberOfMeasurements = 0;
		
		//Iterates through all trackers and adds the averages to a total
		for(String trackerName : trackerNames) {
			totalForAverage += getAvgOfTypeForTracker(type, trackerName);
			numberOfMeasurements++;
		}
		
		return Math.round((totalForAverage / numberOfMeasurements) * 100.0) / 100.0;
	}
	
	/**
	 * Gets the list of names of participants with trackers above average
	 * @param type - the measurement type 
	 * @param trackerNames - the list of tracker names
	 * 
	 * @returns - a list with the participants names
	 */
	public List<String> getAverageAboveForTrackers(MeasurementType type, List<String> trackerNames) {
		List<String> participantsAboveAvg = new ArrayList<String>();
		
		//List with participants with measurements above average
		List<String> aboveAvgList = getParticipantsWithMeasurementAboveAvg(type, trackerNames);
		
		for(String aboveAvg : aboveAvgList) {
			participantsAboveAvg.add(aboveAvg);
		}
		
		return participantsAboveAvg;
	}
	
	/**
	 * Gets the list of names of participants with trackers below average
	 * @param type - the measurement type 
	 * @param trackerNames - the list of tracker names
	 * 
	 * @returns - a list with the participants with trackers below average
	 */
	public List<String> getAverageBelowForTrackers(MeasurementType type, List<String> trackerNames) {
		List<String> participantsBelowAvg = new ArrayList<String>();
		
		//List with participants with measurements below average
		List<String> belowAvgList = getParticipantsWithMeasurementBelowAvg(type, trackerNames);
		
		for(String belowAvg : belowAvgList) {
			participantsBelowAvg.add(belowAvg);
		}
		
		
		return participantsBelowAvg;		
	}
	
	/**
	 * The global average
	 * @param type - the measurement type 
	 * 
	 * @returns - the average of a type for a whole dataset
	 */
	public double getAverageForDataset(MeasurementType type) {
		double totalMeasurements = 0;
		double totalForAverage = 0;
		double numberOfMeasurements = 0;
		
		//Iterates through all participants, trackers and measurements and adds to a total
		for(Participant p : getParticipants()) {
			for(Tracker t : p.getAllTrackers()) {
				
				totalMeasurements = 0;
				
				if(t.getMeasurementsMap().containsKey(type)) {
					for(Measurement m : t.getMeasurementsForType(type)) {
						totalMeasurements += m.getValue().doubleValue();
						numberOfMeasurements++;
					}
				}
				
				totalForAverage += totalMeasurements;
			}
		}
		
		return Math.round((totalForAverage / numberOfMeasurements) * 100.0) / 100.0;
	}

	/**
	 * The list of participant names with a average type above global average
	 * @param type - the measurement type 
	 * 
	 * @returns - the list of participants with a type above global average
	 */
	public List<String> getParticipantsWithAvgTypeAboveGlobal(MeasurementType type) {
		List<String> participantsAboveAvg = new ArrayList<String>();
		
		double totalMeasurements = 0;
		double numberOfMeasurements = 0;
		
		double datasetAverage = getAverageForDataset(type);
		
		//Iterates though all participants, trackers and measurements and adds to a total
		//If the participant is above the global average it adds to a list
		for(Participant p : getParticipants()) {
			totalMeasurements = 0;
			numberOfMeasurements = 0;
			
			for(Tracker t : p.getAllTrackers()) {
				
				if(t.getMeasurementsMap().containsKey(type)) {
					for(Measurement m : t.getMeasurementsForType(type)) {
						totalMeasurements += m.getValue().doubleValue();
						numberOfMeasurements++;
					}
				}
			}
			
			if((totalMeasurements / numberOfMeasurements) > datasetAverage)
				participantsAboveAvg.add(p.getName());
		}
		
		return participantsAboveAvg;
	}
	
	/**
	 * The list of participant names with a average type below global average
	 * @param type - the measurement type 
	 * 
	 * @returns - the list of participants with a type below global average
	 */
	public List<String> getParticipantsWithAvgTypeBelowGlobal(MeasurementType type) {
		List<String> participantsBelowAvg = new ArrayList<String>();
		
		double totalMeasurements = 0;
		double numberOfMeasurements = 0;
		
		double datasetAverage = getAverageForDataset(type);
		
		//Iterates though all participants, trackers and measurements and adds to a total
		//If the participant is below the global average it adds to a list
		for(Participant p : getParticipants()) {
			totalMeasurements = 0;
			numberOfMeasurements = 0;
			
			for(Tracker t : p.getAllTrackers()) {
				
				if(t.getMeasurementsMap().containsKey(type)) {
					for(Measurement m : t.getMeasurementsForType(type)) {
						totalMeasurements += m.getValue().doubleValue();
						numberOfMeasurements++;
					}
				}
			}
			
			if((totalMeasurements / numberOfMeasurements) < datasetAverage)
				participantsBelowAvg.add(p.getName());
		}
		
		return participantsBelowAvg;
	}
	
	/**
	 * Gets the average measurement for a tracker for a participant
	 * @param type - the measurement type 
	 * @param participantName - the participant to look for
	 * 
	 * @returns - the average for a tracker for a specific participant
	 */
	public double getAverageForTrackerForParticipant(MeasurementType type, String participantName) {
		double totalForAverage = 0;
		double numberOfMeasurements = 0;
		
		//Iterates through all participants, trackers and measurements and adds to a total
		for(Participant p : getParticipants()) {
			if(p.getName().equals(participantName)) {
				for(Tracker t : p.getAllTrackers()) {
					if(t.getMeasurementsMap().containsKey(type)) {
						for(Measurement m : t.getMeasurementsForType(type)) {
							totalForAverage += m.getValue().doubleValue();
							numberOfMeasurements++;
						}
					}
				}
			}
		}
		
		return (Math.round((totalForAverage / numberOfMeasurements) * 100.0) / 100.0);
	}
	
	/**
	 * Gets the average measurement for multiple tracker for a participant
	 * @param type - the measurement type 
	 * @param participantName - the participant to look for
	 * @param trackerNames - the list of trackerNames
	 * 
	 * @returns - the average for multiple trackers for a participant
	 */
	public double getAverageForMultipleTrackersForParticipant(MeasurementType type, String participantName, List<String> trackerNames) {
		double totalForAverage = 0;
		double numberOfMeasurements = 0;
		
		//Iterates through each participant and tracker (depending on the trackers declared as parameters)
		//adding to a total to get the average
		for(Participant p : getParticipants()) {
			if(p.getName().equals(participantName)) {
				for(String trackerName : trackerNames) {
					if(p.getTracker(trackerName).getMeasurementsMap().containsKey(type)) {
						totalForAverage += getAverageForSingleTrackerForParticipant(type, participantName, trackerName);
						numberOfMeasurements++;
					}
				}
			}
		}
			
		return (Math.round((totalForAverage / numberOfMeasurements) * 100.0) / 100.0);
	}
	
	/**
	 * Gets the average measurement for a single tracker for a participant
	 * @param type - the measurement type 
	 * @param participantName - the participant to look for
	 * @param trackerName - the tracker name to look for
	 * 
	 * @returns -  the average to a single tracker
	 */
	public double getAverageForSingleTrackerForParticipant(MeasurementType type, String participantName, String trackerName) {
		double totalForAverage = 0;
		double numberOfMeasurements = 0;
		
		for(Participant p : getParticipants()) {
			if(p.getName().equals(participantName)) {
				if(p.getTracker(trackerName).getMeasurementsMap().containsKey(type)) {
					for(Measurement m : p.getTracker(trackerName).getMeasurementsForType(type)) {
						totalForAverage += m.getValue().doubleValue();
						numberOfMeasurements++;
					}
				}

			}
		}
		
		return (Math.round((totalForAverage / numberOfMeasurements) * 100.0) / 100.0);
	}
	
	/**
	 * Gets the average measurement for a participant
	 * @param type - the measurement type 
	 * @param participantName - the participant to look for
	 * 
	 * @returns - the average for all trackers for a participant
	 */
	public double getAverageForParticipant(MeasurementType type, String participantName) {
		double totalForAverage = 0;
		double numberOfTrackers = 0;
		
		for(Participant p : getParticipants()) {
			if(p.getName().equals(participantName)) {
				for(String s : p.getAllTrackerNames()) {
					if(p.getTracker(s).getMeasurementsMap().containsKey(type)) {
						totalForAverage += getAverageForTrackerForParticipant(type, participantName);
						numberOfTrackers++;
					}
				}
			}
		}
		
		return (Math.round((totalForAverage / numberOfTrackers) * 100.0) / 100.0);
	}
	
	/**
	 * Gets the average global for a type
	 * @param type - the measurement type 
	 * 
	 * @returns - the global average of a type
	 */
	public double getTypeAverageGlobal(MeasurementType type) {
		double totalForAverage = 0;
		double numberOfTrackers = 0;
		
		//Iterates through all participants, trackers and gets the average for each adding to a total
		for(Participant p : getParticipants()) {
			for(String s : p.getAllTrackerNames()) {
				if(p.getTracker(s).getMeasurementsMap().containsKey(type)) {
					totalForAverage += getAverageForTrackerForParticipant(type, p.getName());
					numberOfTrackers++;
				}
			}
		}
		
		return (Math.round((totalForAverage / numberOfTrackers) * 100.0) / 100.0);
	}
}
