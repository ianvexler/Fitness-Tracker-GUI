package assignment2021.codeprovided.fitnesstracker.measurements;

public class Distance extends Measurement{
	
	public Distance(int count, double distanceInKm) {
		this.count = count;
		this.value = distanceInKm;
	}
	
	public Distance(int count, String distanceKmString) throws NumberFormatException {
		this(count, Double.parseDouble(distanceKmString));
	}

	@Override
	String getValueAsString() {
		return String.valueOf(getValue().doubleValue());
	}

	@Override
	public String getUnitsName() {
		return "m";
	}

	@Override
	public MeasurementType getMeasurementType() {
		return MeasurementType.DISTANCE;
	}
}
