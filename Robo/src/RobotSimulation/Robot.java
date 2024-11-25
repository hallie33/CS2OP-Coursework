package RobotSimulation;

public class Robot {
	
	private int x,y,robotId;
	private static int robotCount = 0;
	private Direction direction;
	private RobotArena arena;
	
	public Robot (int bx, int by, Direction direction, RobotArena arena) { //constructor for Robot
		this.x = bx;
		this.y = by;
		this.direction = direction;
		this.robotId = robotCount++;	
		this.arena = arena;
	}
	
	public String DisplayAsString() {
		return "Robot Id " + robotId + " is at " + x + "," + y + " and is facing " + direction;
	}
	
	public int getRobotID() {
		return robotId;
	}

	public boolean IsHere (int sx, int sy) {
		return this.x == sx && this.y == sy;
	}
	
	public void displayRobot(ConsoleCanvas c) { //calls the showIt method to display the robot by placing R
		c.showIt(x, y, 'R');
		
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void tryToMove() {
		int newX = x;
		int newY = y;
		
		switch(direction) {
		case NORTH: newY --; //decreases y therefore moves up
			break;
		case EAST: newX ++; //increases x therefore moves to the right
			break;
		case SOUTH: newY ++; //increases y therefore moves down
			break;
		case WEST: newX --; //decreases x therefore moves to the left
			break;
		}
		
		if(arena.canMoveHere(newX,newY)) {
			x = newX;
			y = newY;
		} else {
			direction = direction.getNextDirection();
		}
	}
	
	public static void main(String [] args) { // test block of code
		Robot d = new Robot(5,3, Direction.SOUTH, arena);
		System.out.println(d.DisplayAsString());
	}

}
