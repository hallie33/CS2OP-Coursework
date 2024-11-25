package RobotSimulation;

public class ConsoleCanvas {
	private char[][] canvas;
	
	public ConsoleCanvas(int width, int height, String StudentNo) {
		canvas = new char[height + 2][width + 2]; //initialises the canvas with enough space for the border
		
		for (int i = 0; i < canvas.length; i++) {
			for(int j = 0; j < canvas[i].length; j++) {
				if(i == 0 || i == height + 1 || j == 0 || j == width + 1 ) { //identifying where the border is
					canvas[i][j] = '#';
				}else {
					canvas[i][j] = ' '; //if not the border means it's the inner canvas so is an empty space
				}
			}
		}
		int BeginStudentNo = (canvas[0].length - StudentNo.length()) / 2; //calculating the centre of the top line to put student number
		for(int i = 0; i < StudentNo.length(); i++) { //loop repeats for length of the student number
			canvas[0][BeginStudentNo + i] = StudentNo.charAt(i); //retrieves the character at index i and places into the corresponding column of top row
		}
	}
	
	
	public void showIt(int x, int y, char symbol) { //method to place robot on the canvas
		canvas[y + 1][x + 1] = symbol; //+1 due to the border
		
	}
	
	public int getWidth() { //getter for inner width of canvas, excludes borders
		return canvas[0].length - 2;
	}
	
	public int getHeight() { //getter for inner height of canvas, excludes borders
		return canvas.length - 2;
	}
	
	public String DisplayAsString() { //converts canvas to a string for display
		StringBuilder CanvasDisplay = new StringBuilder();
		for (char[] row : canvas) {
			for (char c : row) {
				CanvasDisplay.append(c);
			}
			CanvasDisplay.append("\n"); //move to the next line after each row for correct display
		}
		return CanvasDisplay.toString();
	}
	
	
	public static void main(String[] args) { //main method to test
		ConsoleCanvas c = new ConsoleCanvas (10, 5, "32006330"); // create a canvas
		c.showIt(4,3,'R'); //add a Robot at 4,3
		System.out.println(c.DisplayAsString());
		
		
	}

}
