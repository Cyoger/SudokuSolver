import java.util.Arrays;
import java.util.Random;

/**
 * SudokoSolver
 */
public class SudokoSolver {
	static final int n = 9;
	public static void main(String[] args) {

		Random rand = new Random();

		int k = 41 + rand.nextInt(51-41)+1;
		System.out.println(k);
		int[][] mat1 = new int[n][n];
		int[][] mat2 = {{7, 0, 2, 0, 5, 0, 6, 0, 0},
        				{0, 0, 0, 0, 0, 3, 0, 0, 0},
        				{1, 0, 0, 0, 0, 9, 5, 0, 0},
        				{8, 0, 0, 0, 0, 0, 0, 9, 0},
        				{0, 4, 3, 0, 0, 0, 7, 5, 0},
        				{0, 9, 0, 0, 0, 0, 0, 0, 8},
        				{0, 0, 9, 7, 0, 0, 0, 0, 5},
        				{0, 0, 0, 2, 0, 0, 0, 0, 0},
        				{0, 0, 7, 0, 4, 0, 2, 0, 3}};

		int[][] mat3 = {{7, 0 ,0, 0, 0, 0, 0, 5, 0},
						{8, 6, 0, 0, 0, 9, 0, 0, 0},
						{0, 5, 9, 4, 0, 0, 0, 7, 0},
						{0, 0, 0, 0, 3, 0, 0, 0, 7},
						{0, 0, 3, 0, 8, 0, 5, 0, 0},
						{5, 0, 0, 0, 9, 0, 0, 0, 0},
						{0, 4, 0, 0, 0, 1, 3, 9, 0},
						{0, 0, 0, 2, 0, 0, 0, 4, 5},
						{0, 8, 0, 0, 0, 0, 0, 0, 6}};
		BoardGen bg = new BoardGen(n, k);
		bg.Fill();
		bg.printBoard();
		//insert values into mat array from Fill()
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				mat1[i][j] = bg.mat[i][j];
			}
		}

		// for(int i = 0; i < n; i++) {
		// 	System.out.println(Arrays.toString(mat1[i]));
		// }

		if(solveSudoku(mat2)){
			System.out.println("solved");
		}else{
			System.out.println("not solved");
		}

		printBoard(mat2);

		System.out.println();

		if(solveSudoku(mat1)){
			System.out.println("solved");
		}else{
			System.out.println("not solved");
		}

		printBoard(mat1);

		System.out.println();

		if(solveSudoku(mat3)){
			System.out.println("solved");
		}else{
			System.out.println("not solved");
		}

		printBoard(mat3);


	}

	private static void printBoard(int[][] board) {
		for (int row = 0; row < n; row++) {
		  if (row % 3 == 0 && row != 0) {
			System.out.println("-----------");
		  }
		  for (int column = 0; column <n; column++) {
			if (column % 3 == 0 && column != 0) {
			  System.out.print("|");
			}
			System.out.print(board[row][column]);
		  }
		  System.out.println();
		}
	}

	public static boolean isNumberinRow(int[][] grid, int num, int row){
		for(int i = 0; i < n; i++){
			if(grid[row][i] == num){
				return true;
			}
		}
		return false;
	}

	public static boolean isNumberinCol(int[][] grid, int num, int col){
		for(int i = 0; i < n; i++){
			if(grid[i][col] == num){
				return true;
			}
		}
		return false;
	}

	public static boolean isNumberinSquare(int[][] grid, int num, int col, int row){
		int squarerow = row - row % 3;
		int squarecol = col - col % 3;

		for(int i = squarerow; i < squarerow + 3; i++){
			for(int j = squarecol; j < squarecol + 3; j++){
				if(grid[i][j] == num){
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isValid(int[][] grid, int num, int row, int col){
		return !isNumberinRow(grid, num, row) && 
		!isNumberinCol(grid, num, col) && 
		!isNumberinSquare(grid, num, col, row);
	}

	private static boolean solveSudoku(int[][] board) {
		for (int row = 0; row < n; row++) {
		  for (int column = 0; column < n; column++) {
			if (board[row][column] == 0) {
			  for (int num = 1; num <= n; num++) {
				if (isValid(board, num, row, column)) {
				  board[row][column] = num;
				  
				  if (solveSudoku(board)) {
					return true;
				  }
				  else {
					board[row][column] = 0;
				  }
				}
			  }
			  return false;
			}
		  }
		}
		return true;
	  }

}