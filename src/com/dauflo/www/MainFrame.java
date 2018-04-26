package com.dauflo.www;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	public static final int width = 500, height = 500;
	
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("Double Pendulum");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(width, height);
		myFrame.setResizable(false);
		
		Container myPanel = myFrame.getContentPane();
		myPanel.setPreferredSize(new Dimension(width, height));
		
		DisplayPanel display = new DisplayPanel();
		myPanel.add(display);
		
		myFrame.setContentPane(myPanel);
		
		myFrame.pack();
		myFrame.show();
	}
}
