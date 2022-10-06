package assignment2021.codeprovided.fitnesstracker.measurements;

public class Steps extends Measurement{

	public Steps(int count, double steps) {
		this.count = count;
		this.value = (int) Math.round(steps); // Conversion to int due to original data recording
	}
	
	public Steps(int count, String stepsString) throws NumberFormatException {
		this(count, Double.parseDouble(stepsString));
	}

	@Override
	String getValueAsString() {
		return String.valueOf(getValue().doubleValue());
	}

	@Override
	public String getUnitsName() {
		return "steps";
	}

	@Override
	public MeasurementType getMeasurementType() {
		return MeasurementType.STEPS;
	}
	
	
}
