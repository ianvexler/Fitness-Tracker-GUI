/**
 * 
 */
package assignment2021.codeprovided.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

/*
 * BasicGUIPlotPanel.java  	2.0  22/03/2022
 *
 * Copyright (c) University of Sheffield 2022
 */

/**
 * BasicGUIPlotPanel.java
 *
 * A class to represent the basic plot panel where the curves should be drawn.  
 * The class GUIPlotPanel should extend this class.
 * 
 * @version 2.0 22/03/2022
 *
 * @author Ben Clegg / Islam Elgendy / Maria-Cruz Villa-Uriol
 */

public class BasicGUIPlotPanel extends JPanel {

	/**
	 * generated serial version UID
	 */
	private static final long serialVersionUID = 3772295898490706209L;

	protected AbstractGUIPanel parentGUIPanel;

	/**
	 * Constructor for BasicGUIPlotPanel class - it has access to the parentPanel to
	 * be able to have access to participants and the user GUI selections in
	 * AbstractGUIPanel (combo boxes, textareas, checkboxes...)
	 */
	public BasicGUIPlotPanel(AbstractGUIPanel parentGUIPanel) {
		// TODO Auto-generated constructor stub
		this.parentGUIPanel = parentGUIPanel;

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d = getSize();
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D r = new Rectangle2D.Double(0, 0, d.width, d.height);
		g2.draw(r);
	}

}
