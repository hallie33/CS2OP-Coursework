package RobotSimulation;

import java.util.Scanner;
import java.io.*;

public class RobotInterface{
	private Scanner s; // scanner used for input from user
	private RobotArena myArena; // arena in which Robots are shown
	
	public RobotInterface() {
		s = new Scanner(System.in); // set up scanner for user input
		myArena = new RobotArena(20, 6); // create arena with size 20*6
		char ch = ' ';
		do {
			System.out.print("Enter (A)dd Robot, get (I)nformation, (D)isplay Arena, (M)ove Robots, A(n)imate Robots, (C)reate New Arena, (S)ave File, (L)oad File or e(X)it > ");
			ch = s.next().charAt(0);
			s.nextLine();
			
			switch (ch) {
				case 'A' :
				case 'a' :
					myArena.addRobotRandomly(); // add a new Robot to arena
					break;
					
				case 'I' :
				case 'i' :
					System.out.print(myArena.DisplayAsString());
					break;
					
				case 'D':
				case 'd':
					doDisplay(); //displays the arena 
					break;
					
				case 'M':
				case 'm':
					myArena.moveAllRobots();
					System.out.println("Robots have now moved ");
					doDisplay();
					break;
					
				case 'N':
				case 'n':
					animateRobots();
					break;
					
				case 'C':
				case 'c':
					createNewArena();
					break;
					
				case 'S':
				case 's':
					saveFile();
					break;
					
				case 'L':
				case 'l':
					loadFile();
					break;
		
				case 'x' : ch = 'X'; // when X detected program ends
					break;
			}
		} while (ch != 'X'); // test if end
		s.close(); // close scanner
	}
	
	
	private void doDisplay(){
		int width = myArena.getWidth(); // gets the width of the arena
		int height = myArena.getHeight(); //get the height of the arena
		
		ConsoleCanvas canvas = new ConsoleCanvas(width, height, "32006330"); // created a suitable sized ConsoleCanvas object
		
		//loop to call and display each robot on the canvas
		myArena.showRobots(canvas);
		
		for (Robot i : myArena.getRobots()) {
			i.displayRobot(canvas);
		}
		
		System.out.println(canvas.DisplayAsString()); // prints the arena using the method from the ConsoleCanvas class
	
	}
	
	
	private void animateRobots() {
		for (int i = 0; i <10; i++) { //moving the robots 10 times and display the results each time
			myArena.moveAllRobots();
			System.out.println("Move numer " + (i) + " of 10: ");
			doDisplay();
			
	        try {
	            Thread.sleep(200); // 200ms delay before the next move is displayed
	        } catch (InterruptedException e) {
	            e.printStackTrace(); // Handle any interruptions in thread sleep
	        }
		}
		
	}
	
	
	private void createNewArena() {
		System.out.print("Enter new arena width: ");
		int newWidth = s.nextInt();
		System.out.print("Enter new arena height: ");
		int newHeight = s.nextInt();
		myArena = new RobotArena(newWidth, newHeight);
		System.out.println("New Arena has been created: " + myArena.DisplayAsString());
	}
		
	
	private void saveFile() {
		System.out.println("Enter the name to save the file as: ");
		String filename = s.nextLine();
		
		try (PrintWriter writer = new PrintWriter(new FileWriter(filename))){
			writer.write(myArena.toString());
			System.out.println("Arena has been saved to " + filename);
		} catch (IOException e) {
			System.err.print("Error saving arena: " + e.getMessage());
		}
	}
	
	
	private void loadFile() {
		System.out.println("Enter the file name to load: ");
		String filename = s.nextLine();
		
	}
	
	
	public static void main(String[] args) {
		RobotInterface r = new RobotInterface(); // just call the interface
	}
}