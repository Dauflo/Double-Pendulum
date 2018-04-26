package com.dauflo.www;

import java.awt.Component;
import java.awt.Graphics;

public class DisplayPanel extends Component implements Runnable {
	private DoublePendulum dp;

	public DisplayPanel() {
		dp = new DoublePendulum();
		new Thread(this).start();
	}

	@Override
	public void paint(Graphics g) {
		dp.draw(g);
		repaint();
	}

	@Override
	public void run() {
		while (true) {
			try {
				dp.update();
				repaint();
				Thread.sleep(10);
			} catch (Exception e) {
				break;
			}
		}
	}
}
