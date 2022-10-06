package assignment2021.codeprovided.fitnesstracker.measurements;

public class EnergyExpenditure extends Measurement{

	public EnergyExpenditure(int count, double energy) {
		this.count = count;
		this.value = energy;
	}
	
	public EnergyExpenditure(int count, String energyString) throws NumberFormatException {
		this(count, Double.parseDouble(energyString));
	}

	@Override
	String getValueAsString() {
		return String.valueOf(getValue().doubleValue());
	}

	@Override
	public String getUnitsName() {
		return "cal";
	}

	@Override
	public MeasurementType getMeasurementType() {
		return MeasurementType.ENERGY_EXPENDITURE;
	}
	
	
}
