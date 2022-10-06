package assignment2021.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.*;

import javax.swing.JOptionPane;

import assignment2021.codeprovided.fitnesstracker.Participant;
import assignment2021.codeprovided.fitnesstracker.Tracker;
import assignment2021.codeprovided.fitnesstracker.measurements.Measurement;
import assignment2021.codeprovided.fitnesstracker.measurements.MeasurementType;
import assignment2021.codeprovided.gui.AbstractGUIPanel;
import assignment2021.codeprovided.gui.BasicGUIPlotPanel;
import assignment2021.handoutquestions.FitnessQuestions;

/**
 * A class to represent the GUI panel where the selected curves will be plot
 * using Java 2D. You are expected to write this class.
 *
 */

public class GUIPlotPanel extends BasicGUIPlotPanel {

	/**
	 * Generated Serial version UID
	 */
	private static final long serialVersionUID = -1482643158587603732L;
	
	private final int PADDING = 40;

	public GUIPlotPanel(AbstractGUIPanel parentGUIPanel) {
		super(parentGUIPanel);
	}

	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		String selectedTrackerNames = parentGUIPanel.getSelectedTrackersNames();
		
		System.out.println("Is display average/s selected? " + 
				this.parentGUIPanel.isShowAverageSelected());
				
		Dimension d = getSize();
		Graphics2D g2 = (Graphics2D) g;
		
		try {
			//If selctedTrackerName contains something draw the graph
			if(!selectedTrackerNames.equals("")) {
				String[] trackerNames = trackerNamesToArray(selectedTrackerNames);
				
				//Depending on the measurementType draw a line or bar graph
				for(String trackerName : trackerNames) {
					if(!parentGUIPanel.getSelectedMeasurementType().equals(MeasurementType.DISTANCE)) {
						drawLine(g2, d, trackerName);
					} else {
						drawBar(g2, d, trackerName, getYAxisLabels(trackerNamesToArray(selectedTrackerNames)));
					}
				}
				
				//Calls to method to draw each axis
				drawXAxis(g2, d, trackerNamesToArray(selectedTrackerNames));
				drawYAxis(g2, d, trackerNamesToArray(selectedTrackerNames));
			}
		} catch (ArithmeticException e) {

		}
	}
	
	/**
	 * Method that draws the X axis
	 * @param g2 - the graphics being used
	 * @param d - the dimensions of the graph
	 * @param selectedTrackerNames - the tracker names selected
	 */
	public void drawXAxis(Graphics g2, Dimension d, String[] trackerNames) {
		int xLine = 0;
		
		int labelCount;
		int count = 1;
		
		String participantName = parentGUIPanel.getSelectedParticipantName();
				
		//If MeasureType is Distance, the labels are tracker names, else its the count
		if(parentGUIPanel.getSelectedMeasurementType().equals(MeasurementType.DISTANCE)) {
			//Sets the amount of points to the amount of trackers
			labelCount = trackerNames.length;
			
			//Draw some labels in the axis which are the tracker names
			for(String trackerName : trackerNames) {
				//A formula used for a good axis scale
				int xAxisPosition = ((d.width / (labelCount + 1)) * count) + PADDING;
				
				g2.setColor(Color.BLACK);
				g2.drawString(trackerName, xAxisPosition, d.height - (PADDING / 2));
				
				xLine = xAxisPosition;
				count++;
			}	
		} else {
			labelCount = getXAxisLabels(trackerNames);
			
			//If type equals Heart Rate divide the label count by 6
			if(parentGUIPanel.getSelectedMeasurementType().equals(MeasurementType.HEART_RATE)) {
				for(int i = 1; i <= labelCount; i += 6) {
					//A formula used for a good axis scale
					int xAxisPosition = ((d.width / ((labelCount / 6) + 1)) * count) + PADDING;
					
					g2.setColor(Color.BLACK);
					g2.drawString(Integer.toString(i), xAxisPosition, d.height - (PADDING / 2));
					
					xLine = xAxisPosition;
					count++;
				}
				
				if(parentGUIPanel.isShowMinMaxSelected()) {
					showLineMax(g2, d, ((d.width / ((labelCount / 6) + 1)) * getCountForHighest(participantName)));
					showLineMin(g2, d, ((d.width / ((labelCount / 6) + 1)) * getCountForLowest(participantName)));
				}
			}
			
			if(parentGUIPanel.getSelectedMeasurementType().equals(MeasurementType.STEPS) ||
					parentGUIPanel.getSelectedMeasurementType().equals(MeasurementType.ENERGY_EXPENDITURE)) {
				for(int i = 1; i <= labelCount; i++) {
					//A formula used for a good axis scale
					int xAxisPosition = ((d.width / (labelCount + 1)) * count) + PADDING;
					
					g2.setColor(Color.BLACK);
					g2.drawString(Integer.toString(i), xAxisPosition, d.height - (PADDING / 2));
					
					xLine = xAxisPosition;
					count++;
				}
			}
			
			if(parentGUIPanel.isShowMinMaxSelected()) {
				showLineMax(g2, d, ((d.width / (labelCount + 1)) * getCountForHighest(participantName)));
				showLineMin(g2, d, ((d.width / (labelCount + 1)) * getCountForLowest(participantName)));
			}
		}
		
		//Drawas the lines depending on xLine which is equal to the last xAxisPosition
		g2.setColor(Color.BLACK);
		g2.drawLine(PADDING, d.height - PADDING, xLine, d.height - PADDING);
		

	}
	
	/**
	 * Method that draws the Y axis
	 * @param g2 - the graphics being used
	 * @param d - the dimensions of the graph
	 * @param trackerNames - the tracker names selected
	 */
	public void drawYAxis(Graphics g2, Dimension d, String[] trackerNames) {	
		//Tries to draw the y axis
		try {
			//If show increment is not selected draw the axis based on measurements
			int yPos = 0;
			MeasurementType type = parentGUIPanel.getSelectedMeasurementType();
			
			if(!parentGUIPanel.isShowIncrementsSelected()) {
				//Y axis labels are the range of measurements
				int labelCount = getYAxisLabels(trackerNames);
				int scaleCount = getYScale(d, trackerNames);
				
				//A formula used for a good axis scale
				int scaledCount = labelCount / ((labelCount / scaleCount) + 1);
				int count = 0;
				
				//Variable to print a good amount of labels
				int printLabel = (labelCount / scaledCount) / 12;
				
				for(int i = 0; i <= labelCount; i += scaledCount) {
					//A formula used for a good axis scale
					int yAxisPosition = (d.height - PADDING) - (((d.height - PADDING) / ((labelCount / scaledCount) + 1)) * count);
					
					//Makes sure that a good amount of labels are printed
					if (i % printLabel == 0) {
						g2.setColor(Color.BLACK);
						g2.drawString(Integer.toString(i), PADDING - 35, yAxisPosition);
					}
					
					yPos = yAxisPosition;
					count++;
				}
				
				g2.setColor(Color.BLACK);
				g2.drawLine(PADDING, d.height - PADDING, PADDING, yPos);
				
				if(parentGUIPanel.isShowAverageSelected()) {
					if(!type.equals(MeasurementType.DISTANCE)) {
						int avg = getAverageValue(((GUIPanel) parentGUIPanel).trackersToList());
						showAvgLine(g2, d, (d.height - PADDING) - ((d.height / ((labelCount / scaledCount) + 1)) * (avg / scaledCount)));
					}
				}
			} else {
				//If increments are selected get the max and min increment values
				int[] maxMin = getMaxMinIncrement(parentGUIPanel.getSelectedParticipantName(), trackerNames);
				//labelCount is not the range of increments
				int labelCount = maxMin[0] - maxMin[1];
				int scaleCount = getYScale(d, trackerNames);
				
				//A formula used for a good axis scale
				int scaledCount = labelCount / ((labelCount / scaleCount) + 1);
				int count = 0;
				
				//Variable to print a good amount of labels
				int printLabel = (labelCount / scaledCount) / 12;
				
				//Loop from the min increment to the max increment
				for(int i = maxMin[1]; i <= maxMin[0]; i += scaledCount) {
					//A formula used for a good axis scale
					int yAxisPosition = 0;
					yAxisPosition = (d.height - PADDING) - (((d.height - PADDING) / ((labelCount / scaledCount) + 1)) * count);
		
					//Makes sure that a good amount of labels are printed
					if (i % printLabel == 0) {
						g2.setColor(Color.BLACK);
						g2.drawString(Integer.toString(i), PADDING - 35, yAxisPosition);
					}
					
					yPos = yAxisPosition;
					count++;
				}
				
				g2.setColor(Color.BLACK);
				g2.drawLine(PADDING, d.height - PADDING, PADDING, yPos);
				
				if(parentGUIPanel.isShowAverageSelected()) {
					if(!type.equals(MeasurementType.DISTANCE)) {
						int avg = getAvgIncrementTrackers(type, trackerNames);
						showAvgLine(g2, d, avg);
					}
				}
			}
		//If there is no data and arithmetic ArithmeticException will be caught;
		} catch(ArithmeticException e) {
			System.out.println("No data for that tracker");
		}
	}
	
	/**
	 * Method that draws the graphic's line
	 * @param g2 - the graphics being used
	 * @param d - the dimensions of the graph
	 * @param trackerName - the tracker that's been graphed
	 */
	public void drawLine(Graphics g2, Dimension d, String trackerName) {
		String participantName = parentGUIPanel.getSelectedParticipantName();
		Participant p = ((GUIPanel) parentGUIPanel).getSelectedParticipant(participantName);
		MeasurementType type = parentGUIPanel.getSelectedMeasurementType();
		List<Measurement> measurements = p.getTracker(trackerName).getMeasurementsForType(type);
		
		//Gets the count of x axis
		int xAxisCount = Measurement.filterMeasurementsByType(measurements, type).size();
		
		if(xAxisCount != 0 ) {
			String [] trackerNames = trackerNamesToArray(parentGUIPanel.getSelectedTrackersNames());
			
			//An array that contains all the points to be plotted
			Integer[][] points = new Integer[xAxisCount][2];
			
			//Iterates through each count
			for(int i = 0; i < xAxisCount; i++) {
				Measurement currentMeasurement = measurements.get(i);
				
				//Sets the x coordinate to the count
				//Sets the y coordinate to the measurement
				points[i][0] = i;
				points[i][1] = currentMeasurement.getValue().intValue();
			}
			
			//If show increment is shown change the x values to the difference between them and the previous
			if(parentGUIPanel.isShowIncrementsSelected()) {	
				int[] maxMin = getMaxMinIncrement(participantName, trackerNames);

				//Array containing the original values of the points array
				Integer[] previousPoints = new Integer[xAxisCount];
				for(int i = 0; i < xAxisCount; i++) {
					previousPoints[i] = points[i][1];
				}
				
				points[0][0] = 0;
				points[0][1] = previousPoints[0];
				
				//Updates the x points values
				for(int i = 1; i < xAxisCount; i++) {
					points[i - 1][0] = i - 1;
					points[i - 1][1] = (previousPoints[i] - previousPoints[i - 1]);
					
					if(maxMin[1] < 0)
						points[i - 1][1] += (Math.abs(maxMin[1]));
				}
			}
			
			setLineColor(g2, trackerName);
			String selectedTrackers = parentGUIPanel.getSelectedTrackersNames();
			int xLabelCount = getXAxisLabels(trackerNamesToArray(selectedTrackers));
			int yLabelCount = getYAxisLabels(trackerNamesToArray(selectedTrackers));
			
			int scaleCount = getYScale(d, trackerNamesToArray(selectedTrackers));
			//A formula used for a good axis scale
			int scaledCount = yLabelCount / ((yLabelCount / scaleCount) + 1);
			
			int yAxisLine = d.height - PADDING;
			
			//Formulas used for a good axis scale
			for(int i = 0; i < points.length - 1; i++) {				
				int x1 = ((d.width / (xLabelCount + 1)) * points[i][0]) + PADDING;
				int y1 = yAxisLine - ((yAxisLine / ((yLabelCount / scaledCount) + 1)) * (points[i][1] / scaledCount));
				int x2 = ((d.width / (xLabelCount + 1)) * points[i + 1][0]) + PADDING;;
				int y2 = yAxisLine - ((yAxisLine / ((yLabelCount / scaledCount) + 1)) * (points[i + 1][1] / scaledCount));
				g2.drawLine(x1, y1, x2, y2);
			}
		}
	}
	
	/**
	 * Draws bars for a bar graph (for Distance)
	 * @param g2 - the graphics being used
	 * @param d - the dimensions of the graph
	 * @param trackerName - the tracker that's been graphed
	 * @param labelCount - the count of labels for scaling
	 */
	public void drawBar(Graphics g2, Dimension d, String trackerName, int labelCount) {
		String participantName = parentGUIPanel.getSelectedParticipantName();
		Participant p = ((GUIPanel) parentGUIPanel).getSelectedParticipant(participantName);
		MeasurementType type = parentGUIPanel.getSelectedMeasurementType();
		List<Measurement> measurements = p.getTracker(trackerName).getMeasurementsForType(type);
		
		//Gets the selectedTracker's index in the array and the total amount of selected Trackers
		String selectedTrackers = parentGUIPanel.getSelectedTrackersNames();
		int trackerIndex = getArrayIndex(trackerNamesToArray(selectedTrackers), trackerName);
		int trackersCount = parentGUIPanel.getSelectedTrackersNames().split(";").length;
		
		//The scale in order to fit large numbers
		int scaleCount = getYScale(d, trackerNamesToArray(selectedTrackers));
		int scaledCount = labelCount / ((labelCount / scaleCount) + 1);
		
		if(scaledCount == 0)
			scaledCount = 1;
		
		//Finds the measurement for this tracker and turns it to an integer
		List<Measurement> m = (List<Measurement>) Measurement.filterMeasurementsByType(measurements, type);
		int measurement = m.get(0).getValue().intValue();
		
		//If showIncrements is selected subtract the current tracker's measurement
		//to the previous'
		if(parentGUIPanel.isShowIncrementsSelected()) {
			String[] trackerNames = trackerNamesToArray(selectedTrackers);
			int[] maxMin = getMaxMinIncrement(participantName, trackerNames);
			
			//Index -1 will happen for the first item in the array therefore catch the exception
			try {
				List<Measurement> prevMeasurements = p.getTracker(trackerNames[trackerIndex - 1]).getMeasurementsForType(type);
				List<Measurement> prevM = (List<Measurement>) Measurement.filterMeasurementsByType(prevMeasurements, type);
				
				int prevMeasurement = prevM.get(0).getValue().intValue();
				
				measurement = measurement - prevMeasurement  + (Math.abs(maxMin[1]));
			} catch(ArrayIndexOutOfBoundsException e) {
				measurement = 0;
			}
		}

		//The scales and numbers and scales used to draw the bars
		int yAxisLine = d.height - PADDING;
		int rectScaleX = d.width / (trackersCount + 1);
		int rectX = ((d.width / (trackersCount + 1)) * trackerIndex) + PADDING;		
		int rectY = yAxisLine - ((yAxisLine / ((labelCount / scaledCount) + 1)) * (measurement / scaledCount));
		
		//Draws a rectangle by drawing lines.
		setLineColor(g2, trackerName);
		Path2D path = new Path2D.Double();
		path.moveTo(rectX, yAxisLine);
		path.lineTo(rectX, rectY);
		path.lineTo(rectX + rectScaleX, rectY);
		path.lineTo(rectX + rectScaleX, yAxisLine);
		path.closePath();
	  
		setLineColor(g2, trackerName);
		((Graphics2D) g2).setStroke(new BasicStroke(3));
		((Graphics2D) g2).draw(path);
		((Graphics2D) g2).fill(path);
		
		//If showMinMax selected then show the max and min lines
		if(parentGUIPanel.isShowMinMaxSelected()) {
			showMaxMinBar(g2, d, yAxisLine, labelCount, scaledCount);
		}
		
		if(parentGUIPanel.isShowAverageSelected()) {
			showAvgBar(g2, d, yAxisLine, labelCount, scaledCount);
		}
	}
		
	/**
	 * Gets the count of labels for the X axis
	 * @param trackerNames - the names of selected trackers
	 * 
	 * @return the count of the tracker
	 */
	public int getXAxisLabels(String[] trackerNames) {
		String participantName = parentGUIPanel.getSelectedParticipantName();
		Participant p = ((GUIPanel) parentGUIPanel).getSelectedParticipant(participantName);
		MeasurementType type = parentGUIPanel.getSelectedMeasurementType();
		
		int currentCount = 0;
		int maxCount = 0;
		
		//Iterates through all selected trackers looking for the biggest count
		for(String trackerName : trackerNames) {
			List<Measurement> measurements = p.getTracker(trackerName).getMeasurementsForType(type);
			currentCount = Measurement.filterMeasurementsByType(measurements, type).size();
			
			//If the current count it bigger set it as max
			if(currentCount > maxCount) {
				maxCount = currentCount;
			}
		}
		
		return maxCount;
	}
	
	/**
	 * Gets the measurements for the labels for the Y axis
	 * @param trackerNames - the names of selected trackers
	 * 
	 * @return measurements of the tracker
	 */
	public int getYAxisLabels(String[] trackerNames) {
		String participantName = parentGUIPanel.getSelectedParticipantName();
		Participant p = ((GUIPanel) parentGUIPanel).getSelectedParticipant(participantName);
		MeasurementType type = parentGUIPanel.getSelectedMeasurementType();
		
		int maxMeasurement = 0;
		
		if(parentGUIPanel.isShowIncrementsSelected()) {
			int[] maxMin = getMaxMinIncrement(participantName, trackerNames);
			return (Math.abs(maxMin[0]) + Math.abs(maxMin[1]));
		}
		
		//Iterates through all selected trackers looking for the biggest count
		for(String trackerName : trackerNames) {
			Tracker currentTracker = p.getTracker(trackerName);
			
			//Iterates through all measurements
			//If the current measurement is bigger set it as max
			for(Measurement m : currentTracker.getMeasurementsForType(type)) {
				if(m.getValue().intValue() > maxMeasurement) {
					maxMeasurement = m.getValue().intValue();
				}
			}	
		}
		
		return maxMeasurement;
	}
	
	/**
	 * Turns the selected trackers to an array
	 * @param selectedTrackerNames - the names of selected trackers
	 * 
	 * @return an array with the selected tracker names
	 */
	public String[] trackerNamesToArray(String selectedTrackerNames) {
		String[] trackerNames = new String[selectedTrackerNames.split(";").length];
		
		for(int i = 0; i < selectedTrackerNames.split(";").length; i++) {
			trackerNames[i] = selectedTrackerNames.split(";")[i];
		}
		
		return trackerNames;
	}

	/**
	 * Shows max line
	 * @param g2 - the graphics being used
	 * @param d - the dimensions of the graph
	 * @param xPos - the position on the x axis
	 */
	public void showLineMax(Graphics g2, Dimension d, int xPos) {				
		g2.setColor(Color.RED);
		g2.drawLine(PADDING + xPos, d.height - PADDING, PADDING + xPos, PADDING);
	}
	
	/**
	 * Shows min line
	 * @param g2 - the graphics being used
	 * @param d - the dimensions of the graph
	 * @param xPos - the position on the x axis
	 */
	public void showLineMin(Graphics g2, Dimension d, int xPos) {		
		g2.setColor(Color.BLUE);
		g2.drawLine(PADDING + xPos, d.height - PADDING, PADDING + xPos, PADDING);
	}
	
	/**
	 * Shows average line
	 * @param g2 - the graphics being used
	 * @param d - the dimensions of the graph
	 * @param xPos - the position on the y axis
	 */
	public void showAvgLine(Graphics g2, Dimension d, int yPos) {
		g2.setColor(Color.BLACK);
		g2.drawLine(PADDING, PADDING - 35 + yPos, d.width - PADDING + yPos, PADDING - 35 + yPos);
	}
	
	/**
	 * Shows the average line for bar graph
	 * @param g2 - the graphics being used
	 * @param d - the dimensions of the graph
	 * @param yAxisLine - where should the line start considering padding and dimensions
	 * @param labelCount - the count of labels
	 * @param scaledCount - the scale being used for plotting
	 */
	public void showAvgBar(Graphics g2, Dimension d, int yAxisLine, int labelCount, int scaledCount) {
		int avg = getAverageValue(((GUIPanel) parentGUIPanel).trackersToList());
		System.out.println(avg);
		int avgPos = yAxisLine - ((yAxisLine / ((labelCount / scaledCount) + 1)) * (avg / scaledCount));
		
		g2.setColor(Color.BLACK);
		g2.drawLine(PADDING, avgPos, d.width - PADDING + avgPos, avgPos);
	}
	
	/**
	 * Shows the max and min bars for bar graph
	 * @param g2 - the graphics being used
	 * @param d - the dimensions of the graph
	 * @param yAxisLine - where should the line start considering padding and dimensions
	 * @param labelCount - the count of labels
	 * @param scaledCount - the scale being used for plotting
	 */
	public void showMaxMinBar(Graphics g2, Dimension d, int yAxisLine, int labelCount, int scaledCount) {
		FitnessQuestions fq = new FitnessQuestions(((GUIPanel) parentGUIPanel).getParticipants());
		String participantName = parentGUIPanel.getSelectedParticipantName();
		MeasurementType type = parentGUIPanel.getSelectedMeasurementType();
		
		List<String> trackerList = ((GUIPanel) parentGUIPanel).trackersToList();
		
		int maxMeas = fq.getHighesTypeForParticipant(type, participantName, trackerList);
		int yMaxPos = yAxisLine - ((yAxisLine / ((labelCount / scaledCount) + 1)) * (maxMeas / scaledCount));
		
		int minMeas = fq.getLowestTypeForParticipant(type, participantName, trackerList);
		int yMinPos = yAxisLine - ((yAxisLine / ((labelCount / scaledCount) + 1)) * (minMeas / scaledCount));
		
		//Max
		g2.setColor(Color.RED);
		g2.drawLine(PADDING, yMaxPos, d.width - PADDING + yMaxPos, yMaxPos);

		//Min
		g2.setColor(Color.BLUE);
		g2.drawLine(PADDING, yMinPos, d.width - PADDING + yMinPos, yMinPos);
	}
	
	/**
	 * Sets the colour of the line depending on the name
	 * @param g2 - the graphics being used
	 * @param trackerName - the name of the selectedTracker or tracker being drawn
	 */
	public void setLineColor(Graphics g2, String trackerName) {
		if(trackerName.equals("FT0")) {
			g2.setColor(Color.BLUE);
		}
		
		if(trackerName.equals("FT1")) {
			g2.setColor(Color.RED);
		}
		
		if(trackerName.equals("FT2")) {
			g2.setColor(Color.DARK_GRAY);
		}
		
		if(trackerName.equals("FT3")) {
			g2.setColor(Color.PINK);
		}
		
		if(trackerName.equals("FT4")) {
			g2.setColor(Color.MAGENTA);
		}
		
		if(trackerName.equals("FT5")) {
			g2.setColor(Color.CYAN);
		}
	}
	
	/**
	 * Gets the count for the highest measurement for a participant
	 * @param participantName - the current participant's name
	 * 
	 * @returns the count for the highest measurement
	 */
	public int getCountForHighest(String participantName) {
		Participant p = ((GUIPanel) parentGUIPanel).getSelectedParticipant(participantName);
		MeasurementType type = parentGUIPanel.getSelectedMeasurementType();
		FitnessQuestions fq = new FitnessQuestions(((GUIPanel) parentGUIPanel).getParticipants());
		
		//Gets the highest of a type for a participant
		int highest = fq.getHighesTypeForParticipant(type, participantName, ((GUIPanel) parentGUIPanel).trackersToList());
		
		//When the position of the highest measurement is found it returns its count
		Collection<Measurement> measurements = Measurement.filterMeasurementsByType(p.getAllMeasurements(), type);
		for(Measurement m : measurements) {
			if(m.getValue().intValue() == highest) {
				return m.getCount() - 1;
			}
		}
		
		return 0;
	}
	
	/**
	 * Gets the count for the lowest measurement for a participant
	 * @param participantName - the current participant's name
	 * 
	 * @return the count for the lowest measurement
	 */
	public int getCountForLowest(String participantName) {
		Participant p = ((GUIPanel) parentGUIPanel).getSelectedParticipant(participantName);
		MeasurementType type = parentGUIPanel.getSelectedMeasurementType();
		FitnessQuestions fq = new FitnessQuestions(((GUIPanel) parentGUIPanel).getParticipants());

		//Gets the lowest of a type for a participant
		int lowest = fq.getLowestTypeForParticipant(type, participantName, ((GUIPanel) parentGUIPanel).trackersToList());
				
		//When the position of the lowest measurement is found it returns its count
		Collection<Measurement> measurements = Measurement.filterMeasurementsByType(p.getAllMeasurements(), type);
		for(Measurement m : measurements) {
			if(m.getValue().intValue() == lowest) {
				return m.getCount() - 1;
			}
		}
		
		return 0;
	}
	
	/**
	 * Gets the max and min increments 
	 * @param participantName - the current participant's name
	 * @param trackerNames - the selectedTrackerNames
	 * 
	 * @return the max and min increments in an array
	 */
	public int[] getMaxMinIncrement(String participantName, String[] trackerNames) {
		Participant p = ((GUIPanel) parentGUIPanel).getSelectedParticipant(parentGUIPanel.getSelectedParticipantName());
		MeasurementType type = parentGUIPanel.getSelectedMeasurementType();		
		
		int max = 0;
		int min = 0;
		Boolean valueSet = false;
		int[] increments;
		
		for(String trackerName : trackerNames) {
			//If the tracker has no measurements it will throw an exception
			try {
				if(!type.equals(MeasurementType.DISTANCE)) {
					int axisCount = Measurement.filterMeasurementsByType(p.getTracker(trackerName).getMeasurementsForType(type), type).size();
					increments = new int[axisCount];
					increments[0] = 0;
					
					//Gets all increments by subtracting to the 
					for(int i = 1; i < axisCount - 1; i++) {
						Measurement currentMeasurement = p.getTracker(trackerName).getMeasurementsForType(type).get(i);
						increments[i] = currentMeasurement.getValue().intValue() - increments[i -1];
					}
				} else {										
					increments = new int[trackerNames.length];
					increments[0] = 0;
					
					//Loops through all the trackers comparing their measurement value
					for(int i = 1; i < trackerNames.length - 1; i++) {
						Measurement currentMeasurement = null;
						Measurement previousMeasurement = null;
						
						List<Measurement> currentMeasurements = p.getTracker(trackerNames[i]).getMeasurementsForType(type);
						List<Measurement> previousMeasurements = p.getTracker(trackerNames[i-1]).getMeasurementsForType(type);
						
						for(Measurement m : currentMeasurements) {
							 currentMeasurement = m;
						}
						
						for(Measurement m : previousMeasurements) {
							previousMeasurement = m;
						}
						
						increments[i] = currentMeasurement.getValue().intValue() - previousMeasurement.getValue().intValue();
					}
				}
					
				//Loops through all increments to find the max value
				int maxForTracker = 0;
				for(int i : increments) {
					if(i > maxForTracker)
						maxForTracker = i;
				}
				
				//Loops through all increments to find the min value
				int minForTracker = maxForTracker;
				for(int i : increments) {
					if(i < minForTracker) 
						minForTracker = i;
				}
				
				//Sets the max of all selected trackers
				if (maxForTracker > max)
					max = maxForTracker;
				
				//If its the first iteration, set min to minForTracker
				if(valueSet == false) {
					min = minForTracker;
					valueSet = true;
				}
				
				//Sets the min selected trackers
				if(minForTracker < min) 
					min = minForTracker;
				
			} catch (ArrayIndexOutOfBoundsException e) {
				
			}
		}
		
		int[] maxMin = {max, min};
		return maxMin;
	}
	
	/**
	 * Gets the average increment 
	 * @param type - the MeasurementType being analysed
	 * @param trackerName - the tracker's name
	 * 
	 * @return the average increment value
	 */
	public double getAverageIncrement(MeasurementType type, String trackerName) {
		double totalMeasurements;
		double totalForAverage = 0;
		double numberOfMeasurements = 0;
		
		for(Participant p : ((GUIPanel) parentGUIPanel).getParticipants()) {
			try {
				totalMeasurements = 0;
				
				//The axis count is the total count of measurements
				List<Measurement> measurements = p.getTracker(trackerName).getMeasurementsForType(type);
				int axisCount = Measurement.filterMeasurementsByType(measurements, type).size();
				int[] increments = new int[axisCount];
	
				increments[0] = 0;
				
				//Gets all increments by subtracting to the previous
				for(int i = 1; i < axisCount - 1; i++) {
					Measurement currentMeasurement = p.getTracker(trackerName).getMeasurementsForType(type).get(i);
					increments[i] = currentMeasurement.getValue().intValue() - increments[i -1];
				}
				
				for(int i : increments) {
					totalMeasurements += i;
					numberOfMeasurements++;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				totalMeasurements = 0;
			}
			
			//Adds to a total to get the average
			totalForAverage += totalMeasurements;
			
		}
		
		return totalForAverage / numberOfMeasurements;
	}
	
	/**
	 * Gets the average increment for multiple trackers
	 * @param type - the MeasurementType being analysed
	 * @param trackerNames - the selected tracker names
	 * 
	 * @return the average increment for certain trackers
	 */
	public int getAvgIncrementTrackers(MeasurementType type, String[] trackerNames) {
		int totalForAvg = 0;
		int count = 0;
		
		for(String trackerName : trackerNames) {
			totalForAvg += (int) getAverageIncrement(type, trackerName);
			count++;
		}
		
		return totalForAvg / count;
	}
	
	/**
	 * Gets the average value for showAvg
	 * @param trackerNames - the selected tracker names
	 * 
	 * @return the average value
	 */
	public int getAverageValue(List<String> trackerNames) {
		Collection<Participant> participants = ((GUIPanel) parentGUIPanel).getParticipants();
		FitnessQuestions fq = new FitnessQuestions(participants);
		MeasurementType type = parentGUIPanel.getSelectedMeasurementType();
		
		int totalForAverage = 0;
		
		if(!parentGUIPanel.isShowIncrementsSelected()) {
			//For each tracker get the average
			for(String trackerName : trackerNames) {
				double avg = fq.getAvgOfTypeForTracker(type, trackerName);
				
				if(!Double.isNaN(avg))
					totalForAverage += avg;
			}
		} else {
			//For each tracker get the average increment
			for(String trackerName : trackerNames) {
				double avg = getAverageIncrement(type, trackerName);
				
				if(!Double.isNaN(avg))
					totalForAverage += avg;
			}
		}
		
		return totalForAverage / (trackerNames.size() - 1);
	}
	
	/**
	 * Gets the index in an array of a string
	 * @param a - the array
	 * @param s - the string to compare
	 */
	public int getArrayIndex(String[] a, String s) {
        for(int i = 0; i < a.length; i++) {
            if(a[i].equals(s)) {
                return i;
            }
        }
        
        return 0;
	}
	
	/**
	 * Returns the scale thats going to be used to plot the axis and points in the y axis
	 * @param d - the dimensions
	 * @param trackerNames - the selected tracker names
	 * 
	 * @returns - the scale to be used in the Y axis
	 */
	public int getYScale(Dimension d, String[] trackerNames) { 
		int scaleCount;
		int labelCount = 0;
		
		try{
			//labelCount depends on what is being shown
			if(!parentGUIPanel.isShowIncrementsSelected()) {
				labelCount = getYAxisLabels(trackerNames);				
			} else {
				int[] maxMin = getMaxMinIncrement(parentGUIPanel.getSelectedParticipantName(), trackerNames);
				labelCount = Math.abs(maxMin[0]) + Math.abs(maxMin[1]);
			}
			
			//Formula used for a good scale
			int yScale = (d.height - (PADDING * 2)) / labelCount;
			scaleCount = 1;
			
			//If scale is less than 10 multiply by two to make it suitable
			while(yScale < 10) {
				yScale = (d.height - (PADDING * 2)) / (labelCount / (10 * scaleCount));	
				scaleCount *= 2;
			}
		} catch(ArithmeticException e) {
			scaleCount = 0;
		}
		
		return scaleCount;
	}
}