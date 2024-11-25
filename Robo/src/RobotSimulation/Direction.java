package RobotSimulation;

import java.util.Random;

public enum Direction {
	NORTH, EAST, SOUTH, WEST;
	
	public static Direction getRandomDirection() { //method to return random direction
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
	
	public Direction getNextDirection() { //method to return the next following direction
		Direction[] directions = Direction.values();
		return directions[(this.ordinal() + 1) % directions.length];
	}
	
}
