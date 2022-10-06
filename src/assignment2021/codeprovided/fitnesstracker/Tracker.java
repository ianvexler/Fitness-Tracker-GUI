package assignment2021.codeprovided.fitnesstracker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import assignment2021.codeprovided.fitnesstracker.measurements.*;

/*
 * Tracker.java  	2.0  22/03/2022
 *
 * Copyright (c) University of Sheffield 2022
 */

/**
 * Tracker.java
 *
 * A class to represent a Tracker of the Fitness Tracker study.
 * 
 * @version 2.0  22/03/2022
 *
 * @author Ben Clegg / Islam Elgendy / Maria-Cruz Villa-Uriol
 */
public class Tracker {

	public static final String FILTER_ANY = "ANY";

	private Map<MeasurementType, List<Measurement>> measurementsMap;
	String name;

	public Tracker(String name) {
		this.measurementsMap = new HashMap<>();
		this.name = name;
	}

	/**
	 * Adds a Measurement to the Tracker's Map. The MeasurementType is automatically
	 * used as a key.
	 * 
	 * @param measurement the Measurement to add
	 */
	public void addMeasurement(Measurement measurement) {
		final MeasurementType type = measurement.getMeasurementType();

		// Create a list if none exists for the given type.
		if (!measurementsMap.containsKey(type))
			measurementsMap.put(type, new ArrayList<>());

		// Add the measurement for the appropriate type.
		measurementsMap.get(type).add(measurement);
	}

	/**
	 * Get the Tracker's Measurements of the specified type
	 * 
	 * @param type the type of Measurement to get
	 * @return the specified Measurements.
	 */
	public List<Measurement> getMeasurementsForType(MeasurementType type) {
		if (!measurementsMap.containsKey(type))
			// Return empty List if no key present; prevents NullPointerException
			return Collections.emptyList();
		return measurementsMap.get(type);
	}

	public String getName() {
		return name;
	}

	/**
	 * Filters the provided Trackers so only those of the specified name are
	 * included.
	 * 
	 * @param trackers   the Trackers to filter
	 * @param filterName the name to include. Can be the same value as
	 *                   Tracker.FILTER_ANY to return all Trackers.
	 * @return the filtered Trackers
	 */
	public static Collection<Tracker> filterTrackersByName(Collection<Tracker> trackers, String filterName) {
		// Any name (no filter)
		if (filterName.equals(FILTER_ANY))
			return trackers;

		// Filter by names that are equal
		return trackers.stream().filter(t -> t.getName().equals(filterName)).collect(Collectors.toList());
	}

	/**
	 * Get all of the Measurements for all MeasurementTypes.
	 * 
	 * @return all Measurements of the Tracker.
	 */
	public Collection<Measurement> getAllMeasurements() {
		Collection<Measurement> all = new ArrayList<>();
		for (List<Measurement> mList : measurementsMap.values())
			all.addAll(mList);
		return all;
	}

	public Map<MeasurementType, List<Measurement>> getMeasurementsMap() {
		return measurementsMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((measurementsMap == null) ? 0 : measurementsMap.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Tracker other = (Tracker) obj;
		if (measurementsMap == null) {
			if (other.measurementsMap != null)
				return false;
		} else if (!measurementsMap.equals(other.measurementsMap))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
