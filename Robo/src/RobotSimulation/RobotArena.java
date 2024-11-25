package RobotSimulation;

import java.util.Random;
import java.util.ArrayList;


public class RobotArena { //setting attributes
	private int xmax, ymax;
	private Random random;
	private ArrayList<Robot> ListRobots;

	public RobotArena (int xmax, int ymax) { //constructor for arena 
		this.xmax = xmax;
		this.ymax = ymax;
		this.random = new Random();
		this.ListRobots = new ArrayList<>();
	}
	
	
	public Robot IsRobotHere(int x, int y) {
		for (Robot i : ListRobots) { //for loop for all robots in the list
			if (i.IsHere(x,y)) {
				return i; //if robot is found at specified position it will be returned
			}
		}
		return null; //if no robot is found at specified position
	}
	
	public boolean canMoveHere(int x, int y) {
		if (x < 1 || x >= xmax - 1 || y < 1 || y >= ymax - 1) { //checking if the new position is in the arena
			return false; //it's not so returns false as cannot move here
		}
		
		for (Robot i : ListRobots) { //checking if there is a robot already there
			if (i.IsHere(x,y)){
				return false; //if so then returns false as cannot move here
			}
		}
		return true; //can move as position is in arena and a robot isn't already there
	}
	
	public void moveAllRobots() { //method to move all the robots at once
		for (Robot i : ListRobots) {
			i.tryToMove();
		}
	}
	
	public void addRobotRandomly() {
		int randomX;
		int randomY;
		do {
			randomX = random.nextInt(xmax); // generates random x value
			randomY = random.nextInt(ymax); // generates random y value
		} while (IsRobotHere(randomX,randomY) != null); //repeating until no robot is here/found an empty space
		Robot newRobot = new Robot(randomX,randomY, Direction.getRandomDirection(), this); // creating new robot in a random position
		ListRobots.add(newRobot); // adding the new robot to the end of the list
	}
	
	
	public void showRobots(ConsoleCanvas c) { //method to show all the robots on the canvas
		for(Robot i: ListRobots) {
			i.displayRobot(c);
		}
	}
	
	public String DisplayAsString() {
		StringBuilder Info = new StringBuilder("Arena is "+ xmax + " by " + ymax); //can build strings across multiple lines
		for (Robot i : ListRobots) { //for loop for all robots in the list
			Info.append("\n").append( i.DisplayAsString()); //creates new line using the DisplayAsString method in the Robot class
		}
		Info.append("\n");
		return Info.toString();
	}
	
	public int getWidth(){ //getter for the width to use for interface
		return xmax;
	}
	
	public int getHeight() { //getter for the height to use for interface
		return ymax;
	}
	
	public ArrayList<Robot> getRobots() { //returns the list of robots to use for interface
		return ListRobots;
	}
	
	public static void main (String [] args) {
		RobotArena a = new RobotArena(20,10);
		a.addRobotRandomly();
		a.addRobotRandomly();
		a.addRobotRandomly();
		System.out.println(a.DisplayAsString());	
	}

}

