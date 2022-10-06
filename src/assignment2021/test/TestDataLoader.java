package assignment2021.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;

import assignment2021.codeprovided.dataloading.DataParsingException;
import assignment2021.codeprovided.fitnesstracker.Participant;
import assignment2021.codeprovided.fitnesstracker.measurements.HeartRate;
import assignment2021.codeprovided.fitnesstracker.measurements.MeasurementType;
import assignment2021.dataloading.DataLoader;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestDataLoader {

	@Test
	public void testClassIsPresent() {
		// Only tests that classes can be loaded
		
		try {
			Class.forName("assignment2021.dataloading.DataLoader");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			Assertions.fail("Required class (DataLoader) not found, check that it is in the correct package.");
		}
	}
	
	@Test
	public void testLoadAll() {
		DataLoader d = new DataLoader();
		try {
			Collection<Participant> participants = d.loadAllParticipants(Paths.get("resources/data"));
			Assertions.assertEquals(11, participants.size());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assertions.fail();
		}
		
	}
	
	@Test
	public void testLoadP2() {
		DataLoader d = new DataLoader();

		try {
			Participant p = d.loadDataFile(Paths.get("resources/data/P02.txt"));		
			Assertions.assertEquals(32, p.getAge());
			Assertions.assertTrue(p.getTracker("FT1")
					.getMeasurementsForType(MeasurementType.HEART_RATE)
					.contains(new HeartRate(21, "82.0"))
					);
			
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
			Assertions.fail();
		} catch (DataParsingException dataEx) {
			dataEx.printStackTrace();
			Assertions.fail();
		}
		
	}

}
