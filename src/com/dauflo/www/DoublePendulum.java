package com.dauflo.www;

import java.awt.Color;
import java.awt.Graphics;

public class DoublePendulum {
	// Origin
	private int xO, yO;

	// Center of the first pendulum and mass
	private double x1, y1, m1;

	// length first pendulum
	private int length1;

	// Velocity and angle
	private double a1, a1Vel, a1Acc;

	// Second pendulum
	private double x2, y2, m2;
	private int length2;
	private double a2, a2Vel, a2Acc;

	// Slow down
	private double damping;

	public DoublePendulum() {
		this.xO = 250;
		this.yO = 100;
		this.length1 = 100;
		this.m1 = 10;

		this.a1 = Math.PI / 2;
		this.a1Vel = 0;
		this.a1Acc = 0;

		this.length2 = 100;
		this.m2 = 10;

		this.a2 = Math.PI / 1.1;
		this.a2Vel = 0;
		this.a2Acc = 0;

		this.damping = 0.995;
	}

	public void update() {
		double gravity = 0.05;
		double num1 = -gravity * (2 * m1 * m2) * Math.sin(a1);
		double num2 = -m2 * gravity * Math.sin(a1 - 2 * a2);
		double num3 = -2 * Math.sin(a1 - a2) * m2
				* (a2Vel * a2Vel * length2 + a1Vel * a1Vel * length1 * Math.cos(a1 - a2));
		double den = length1 * (2 * m1 + m2 - m2 * Math.cos(2 * a1 - 2 * a2));
		a1Acc = (num1 + num2 + num3) / den;
		a1Vel += a1Acc;
		a1 += a1Vel;

		a1Vel *= damping;

		num1 = 2 * Math.sin(a1 - a2) * (a1Vel * a1Vel * length1 * (m1 + m2) + gravity * (m1 + m2) * Math.cos(a1) + a2Vel * a2Vel * length2 * m2 * Math.cos(a1 - a2));
		den = length2 * (2 * m1 + m2 - m2 * Math.cos(2 * a1 - 2 * a2));
		a2Acc = num1 / den;
		a2Vel += a2Acc;
		a2 += a2Vel;

		a2Vel *= damping;
	}

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, MainFrame.width, MainFrame.height);
		this.x1 = this.length1 * Math.sin(a1) + this.xO;
		this.y1 = this.length1 * Math.cos(a1) + this.yO;

		this.x2 = this.length2 * Math.sin(a2) + this.x1;
		this.y2 = this.length2 * Math.cos(a2) + this.y1;

		g.setColor(Color.BLACK);
		g.drawLine(xO, yO, (int) x1, (int) y1);
		g.fillOval((int) x1 - 10, (int) y1 - 10, 20, 20);

		g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
		g.fillOval((int) x2 - 10, (int) y2 - 10, 20, 20);
	}
}
