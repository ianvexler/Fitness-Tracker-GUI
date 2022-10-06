package assignment2021.codeprovided.fitnesstracker.measurements;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class Measurement {
	
	int count;
	Number value;
	
	/**
	 * Get the stored numeric value.
	 * Note: Number is an abstraction of any numeric value. To get it in the form of a primitive, use Number's xyzValue() methods.
	 * e.g. you can use aMeasurement.getValue().doubleValue(); to get the value as a double.
	 * @return
	 */
	public Number getValue() {
		return value;
	}
	
	/**
	 * The name of the units for the type of Measurement. For example, Distance is "m" (metres).
	 * @return The Measurement's units
	 */
	public abstract String getUnitsName();
	
	public abstract MeasurementType getMeasurementType();
	
	abstract String getValueAsString();
	
	/**
	 * Filters the provided Measurements so only those of the specified MeasurementType are included.
	 * @param measurements the Measurements to filter
	 * @param filterType the MeasurementType to include
	 * @return the filtered Measurements 
	 */
	public static Collection<Measurement> filterMeasurementsByType(Collection<Measurement> measurements, MeasurementType filterType) {
		return measurements.stream()
				.filter(m -> m.getMeasurementType().equals(filterType))
				.collect(Collectors.toList());
	}
	
	@Override
	public String toString() {
		return getMeasurementType().toString() + ": " + 
				getValueAsString() + " " +
				getUnitsName();
	}

	public int getCount() {
		return count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Measurement other = (Measurement) obj;
		if (count != other.count)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if(getMeasurementType() != other.getMeasurementType())
			return false;
		return true;
	}
	
	
	
	
	
}
