package game;

import java.awt.Point;

import game.boats.Power;

public class Attack {
	public Point point;
	public Power power;
	
	public Attack(int x, int y, Power power) {
		this(new Point(x, y), power);
	}
	
	public Attack(int x, int y) {
		this(new Point(x, y), Power.NONE);
	}
	
	// All overloaded constructors should lead to this
	public Attack(Point point, Power power) {
		this.point = new Point(point);
		this.power = power;
	}
	
	public Attack(Point point) {
		this(point, Power.NONE);
	}
	
}
