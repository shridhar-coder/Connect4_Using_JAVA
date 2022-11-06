import java.util.Scanner;

public class Connect4 {

	public static void main(String[] args) {
	    
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter Number of Players from 2 to 4");

		Integer p = in.nextInt();

		System.out.println("Enter Number of Columns over 5");

		Integer c = in.nextInt();

		System.out.println("Enter Number of Rows over 5");

		Integer r = in.nextInt();
		
		char P[]={'R','G','B','Y'};

		String[] PN={"Red","Green","Black","Yellow"};

		char[][] grid = new char[r][c];
		
		//initialize array
		for (int row = 0; row < grid.length; row++){
			for (int col = 0; col < grid[0].length; col++){
				grid[row][col] = ' ';
			}
		}
		
		int turn = 1;
		int t=0;
		char player = 'R';
		boolean winner = false;		

		//play a turn
		while (winner == false && turn <= c*r){
			boolean validPlay;
			int play;
			do {
				display(grid,r,c);
				
				System.out.print("Player " + player + ", choose a column: ");
				play = in.nextInt();
				
				//validate play
				validPlay = validate(play,grid);
				
			}while (validPlay == false);
			
			//drop the checker
			for (int row = grid.length-1; row >= 0; row--){
				if(grid[row][play] == ' '){
					grid[row][play] = player;
					break;
				}
			}
			
			//determine if there is a winner
			winner = isWinner(player,grid);
			

			//switch players
			System.out.print("Player " + t );
			if (t<p-1){
				player = P[t+1];
			}else{
				player = P[0];
				t=-1;
			}
			
			t++;
			turn++;			
		}	
		display(grid,r,c);
		
		if (winner){
			if (player=='R'){
				System.out.println("Black won");
			}else{
				System.out.println("Red won");
			}
		}else{
			System.out.println("Tie game");
		}
		
	}
	
	public static void display(char[][] grid,int r,int c){
		for (int col = 0; col < c; col++){
			System.out.print(" ");
			System.out.print(col);
		}
		System.out.println(" ");
		for (int row = 0; row < r; row++){
			System.out.print("--");
		}
		System.out.println("-");
		for (int row = 0; row < grid.length; row++){
			System.out.print("|");
			for (int col = 0; col < grid[0].length; col++){
				System.out.print(grid[row][col]);
				System.out.print("|");
			}
			System.out.println();
			for (int row1 = 0; row1 < r; row1++){
					System.out.print("--");
				}
			System.out.println("-");
		}
		for (int col = 0; col < c; col++){
			System.out.print(" ");
			System.out.print(col);
		}
		System.out.println(" ");
		System.out.println();
	}
	
	public static boolean validate(int column, char[][] grid){
		//valid column?
		if (column < 0 || column > grid[0].length){
			return false;
		}
		
		//full column?
		if (grid[0][column] != ' '){
			return false;
		}
		
		return true;
	}
	
	public static boolean isWinner(char player, char[][] grid){
		//check for 4 across
		for(int row = 0; row<grid.length; row++){
			for (int col = 0;col < grid[0].length - 3;col++){
				if (grid[row][col] == player   && 
					grid[row][col+1] == player &&
					grid[row][col+2] == player &&
					grid[row][col+3] == player){
					return true;
				}
			}			
		}
		//check for 4 up and down
		for(int row = 0; row < grid.length - 3; row++){
			for(int col = 0; col < grid[0].length; col++){
				if (grid[row][col] == player   && 
					grid[row+1][col] == player &&
					grid[row+2][col] == player &&
					grid[row+3][col] == player){
					return true;
				}
			}
		}
		//check upward diagonal
		for(int row = 3; row < grid.length; row++){
			for(int col = 0; col < grid[0].length - 3; col++){
				if (grid[row][col] == player   && 
					grid[row-1][col+1] == player &&
					grid[row-2][col+2] == player &&
					grid[row-3][col+3] == player){
					return true;
				}
			}
		}
		//check downward diagonal
		for(int row = 0; row < grid.length - 3; row++){
			for(int col = 0; col < grid[0].length - 3; col++){
				if (grid[row][col] == player   && 
					grid[row+1][col+1] == player &&
					grid[row+2][col+2] == player &&
					grid[row+3][col+3] == player){
					return true;
				}
			}
		}
		return false;
	}
}