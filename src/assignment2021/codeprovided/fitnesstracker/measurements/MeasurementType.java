package assignment2021.codeprovided.fitnesstracker.measurements;

/**
 * Representation of the possible types of Measurements in the dataset.
 *
 */
public enum MeasurementType {

	DISTANCE("Distance"), 
	HEART_RATE("Heart Rate"),
	STEPS("Steps"),
	ENERGY_EXPENDITURE("Energy expenditure")
	;

	private String measurementName;

	MeasurementType(String measurementName) {
		this.measurementName = measurementName;
	}
	
	public String getMeasurementName() {
		return measurementName;
	}

	/**
	 * Get a MeasurementType from the specified name.
	 * @param measurementName the name of the MeasurementType to get. These are the values in the brackets next to the enum value declared above.
	 * Note: these are the same names used as section headings in the data files.  
	 * @return The corresponding MeasurementType
	 */
	public static MeasurementType fromMeasurementName(String measurementName) {
		
		for (MeasurementType t : MeasurementType.values()) {
			if (t.getMeasurementName().toLowerCase().equals(measurementName.toLowerCase()))
				return t;
		}
		return null;
	}
}
