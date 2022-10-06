package assignment2021.test;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestGUI {

	@Test
	public void testClassIsPresent() {
		// Only tests that classes can be loaded
		
		try {
			Class.forName("assignment2021.gui.GUIPanel");
			Class.forName("assignment2021.gui.GUIPlotPanel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			Assertions.fail("Required class not found, check that it is in the correct package.");
		}
	}
	

}

