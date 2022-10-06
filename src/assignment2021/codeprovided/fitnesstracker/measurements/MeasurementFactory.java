package assignment2021.codeprovided.fitnesstracker.measurements;

/**
 * Factory to create Measurement instances from MeasurementTypes.
 *
 */
public class MeasurementFactory {
	
	/**
	 * Create a Measurement instance according to the provided MeasurementType
	 * @param type the type of Measurement to create a corresponding instance of.
	 * @param count the count to initialise the Measurement with
	 * @param value a String representation of the value to assign to the measurement.
	 * @return an instance of the appropriate Measurement class 
	 * @throws NumberFormatException if the value String is not compatible with the chosen type of Measurement.
	 */
	public static Measurement createMeasurement(MeasurementType type, int count, String value) throws NumberFormatException {
		switch(type) {
		case DISTANCE:
			return new Distance(count, value);
		case HEART_RATE:
			return new HeartRate(count, value);
		case STEPS:
			return new Steps(count, value);
		case ENERGY_EXPENDITURE:
			return new EnergyExpenditure(count, value);
		}
		throw new IllegalStateException(type.toString() + " did not match a Measurement class.");
	}
}
