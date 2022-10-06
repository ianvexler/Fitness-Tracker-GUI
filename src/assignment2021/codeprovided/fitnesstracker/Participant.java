package assignment2021.codeprovided.fitnesstracker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import assignment2021.codeprovided.fitnesstracker.measurements.Measurement;

/*
 * Participant.java  	2.0  22/03/2022
 *
 * Copyright (c) University of Sheffield 2022
 */

/**
 * Participant.java
 *
 * A class to represent a Participant of the Fitness Tracker study. 
 * This corresponds to a single .txt file in the data folder.
 * 
 * @version 2.0  22/03/2022
 *
 * @author Ben Clegg / Islam Elgendy / Maria-Cruz Villa-Uriol
 */

public class Participant {

	public static final String FILTER_ANY = "ANY";
	
	private String name;
	private int age;
	private String gender;
	
	private Map<String, Tracker> trackers;
	
	public Participant(String name, int age, String gender) {
		this.name = name;
		this.age = age;
		this.gender = gender.toLowerCase();
		
		this.trackers = new HashMap<>();
	}
	
	/**
	 * Adds a Measurement to one of the Participant's trackers.
	 * @param trackerName the name of the Tracker to add the Measurement to
	 * @param measurement the Measurement to add.
	 */
	public void addMeasurementToTracker(String trackerName, Measurement measurement) {
		if (!trackers.containsKey(trackerName))
			trackers.put(trackerName, new Tracker(trackerName));
		
		Tracker t = trackers.get(trackerName);
		t.addMeasurement(measurement);
	}
	
	/**
	 * 
	 * Get the Tracker of the specified name
	 * @param trackerName the name of the Tracker to get
	 * @return the Tracker of the matching name
	 * @throws NoSuchElementExceptionif no tracker of the matching name exists.
	 */
	public Tracker getTracker(String trackerName) throws NoSuchElementException {
		if (!trackers.containsKey(trackerName))
			throw new NoSuchElementException("No tracker with the name " + trackerName + " for Participant " + getName());
		return trackers.get(trackerName);
	}
	
	/**
	 * Get all Trackers of the participant.
	 * @return all Trackers.
	 */
	public Collection<Tracker> getAllTrackers() {
		return trackers.values();
	}
	
	/**
	 * Get the names of all of the Trackers
	 * @return the Trackers' names.
	 */
	public Collection<String> getAllTrackerNames() {
		return trackers.keySet();
	}
	
	/**
	 * Get all Measurements of all Trackers belonging to the Participant.
	 * @return all Measurements of the Participant.
	 */
	public Collection<Measurement> getAllMeasurements() {
		Collection<Measurement> all = new ArrayList<>();
		for (Tracker t : getAllTrackers()) {
			all.addAll(t.getAllMeasurements());
		}
		return all;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender.toLowerCase();
	}

	public Map<String, Tracker> getTrackersMap() {
		return trackers;
	}
	
	/**
	 * Filters the provided Participants so only those of the specified name are included.
	 * @param participants the Participants to filter.
	 * @param filterName the name to include. Can be the same value as Participant.FILTER_ANY to return all Participants.
	 * @return the filtered Participants.
	 */
	public static Collection<Participant> filterParticipantsByName(Collection<Participant> participants, String filterName) {
		// Any name (no filter)
		if(filterName.equals(FILTER_ANY))
			return participants; 
		
		// Filter for names that are equal
		return participants.stream()
				.filter(p -> p.getName().equals(filterName))
				.collect(Collectors.toList());
	}
	
	/**
	 * Filters the provided Participants so only those of the specified age are included.
	 * @param participants participants the Participants to filter.
	 * @param filterAge the age to include, as a String. Can be the same value as Participant.FILTER_ANY to return all Participants. Note: otherwise this must be in the same format as an integer, i.e. only numeric characters. 
	 * @returnthe filtered Participants.
	 */
	public static Collection<Participant> filterParticipantsByAge(Collection<Participant> participants, String filterAge) {
		// Any age (no filter)
		if (filterAge.equals(FILTER_ANY))
			return participants;
		
		try {
			// Filter by age integer
			int parsedAge = Integer.parseInt(filterAge);
			return filterParticipantsByAge(participants, parsedAge);
		} catch (NumberFormatException numEx) {
			// Invalid age
			System.err.println(filterAge + " is an invalid age (not an integer).");
			return Collections.emptyList();
		}
	}

	/**
	 * Filters the provided Participants so only those of the specified age are included.
	 * @param participants participants the Participants to filter.
	 * @param filterAge the age to include, as an int.
	 * @returnthe filtered Participants.
	 */
	public static Collection<Participant> filterParticipantsByAge(Collection<Participant> participants, int filterAge) {
		// Filter for same age as provided
		return participants.stream()
				.filter(p -> p.getAge() == filterAge)
				.collect(Collectors.toList());
	}


	/**
	 * Filters the provided Participants so only those of the specified gender are included.
	 * @param participants participants the Participants to filter.
	 * @param filterGender the gender to include. Can be the same value as Participant.FILTER_ANY to return all Participants.
	 * @returnthe filtered Participants.
	 */
	public static Collection<Participant> filterParticipantsByGender(Collection<Participant> participants, String filterGender) {
		// Any gender (no filter)
		if(filterGender.equals(FILTER_ANY))
			return participants;
		
		// Filter for genders that are equal
		return participants.stream()
				.filter(p -> p.getGender().equals(filterGender.toLowerCase()))
				.collect(Collectors.toList());
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((trackers == null) ? 0 : trackers.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participant other = (Participant) obj;
		if (age != other.age)
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (trackers == null) {
			if (other.trackers != null)
				return false;
		} else if (!trackers.equals(other.trackers))
			return false;
		return true;
	}
	
	

}
