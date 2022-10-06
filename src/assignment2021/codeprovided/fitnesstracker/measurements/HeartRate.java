package assignment2021.codeprovided.fitnesstracker.measurements;

public class HeartRate extends Measurement{

	public HeartRate(int count, double heartRate) {
		this.count = count;
		this.value = heartRate;
	}
	
	public HeartRate(int count, String heartRateString) throws NumberFormatException {
		this(count, Double.parseDouble(heartRateString));
	}

	@Override
	String getValueAsString() {
		return String.valueOf(getValue().doubleValue());
	}

	@Override
	public String getUnitsName() {
		return "bpm";
	}

	@Override
	public MeasurementType getMeasurementType() {
		return MeasurementType.HEART_RATE;
	}
	
	
}
