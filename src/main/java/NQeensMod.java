import java.util.*;

import static java.lang.Math.abs;

class NQueensMod {

	private int maxQueensOnLine = 3;
	private int[][] board;

	NQueensMod(int[][] board) {
		this.board = board;
	}

	private boolean isSafe(int[][] board, int row, int col) {
		// Check col
		for(int i = 0; i < row; i++) {
			if(board[i][col] == 1)
				return false;
		}
		// Check upper left diagonal
		for(int i = row; row >= col && i > row - col; i--) {
			for(int j = col; j >= 0; j--) {
				if(board[i][j] == 1)
					return false;
			}
		}
		// Check upper right diagonal
		for(int i = row; i >= 0; i--) {
			for(int j = col; j <= board.length - row - 1; j++) {
				if(board[i][j] == 1)
					return false;
			}
		}
		return true;
	}

	// check if (x,y) forms a line with other queens exceeding allowed # of queens
	private boolean maxQueensOnLineExceeded(int[][] board, int x, int y) {
		List<List<Integer>> positions = new ArrayList();
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(board[i][j] == 1) {
					positions.add(Arrays.asList(i,j));
				}
			}
		}

		for(int i = 0; i < positions.size() - 1; i++) {
			Integer x1, y1, x2, y2;
			Integer count = 2; // any two points are always on a line by default
			x1 = positions.get(i).get(0);
			y1 = positions.get(i).get(1);
			for(int j = i + 1; j < positions.size(); j++) {
				x2 = positions.get(j).get(0);
				y2 = positions.get(j).get(1);
				// points on same line have same gradient
				if ( abs((x - x1) * (y - y2)) == abs((x - x2) * (y - y1)) )
					count++;
			}
			if(count >= this.maxQueensOnLine)
				return true;
		}
		return false;
	}

	private void printBoard(int[][] board) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				System.out.print(board[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	private boolean solveNQueensUtil(int[][] board, int row) {
		if(row >= board.length)
			return true;

		for(int col = 0; col < board.length; col++) {
			if(isSafe(board, row, col) && !maxQueensOnLineExceeded(board, row, col)) {
				board[row][col] = 1;
				if(solveNQueensUtil(board, row+1)) {
					return true;
				}
				board[row][col] = 0;
			}
		}
		return false;
	}


	public void solve() {
		if(!solveNQueensUtil(this.board, 0)) {
			System.out.println("No solution found!");
		}
		else {
			System.out.println("Solution found!");
			printBoard(this.board);
		}
	}

	public static void main(String args[]) {
		int[][] board = new int[8][8];
		NQueensMod nQueens = new NQueensMod(board);
		nQueens.solve();
	}
}
