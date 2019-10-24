package chess;

import java.util.*;

class NQueensProblem {
	private static int N = 8;

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
			for(int j = col; j <= N - row - 1; j++) {
				if(board[i][j] == 1)
					return false;
			}
		}
		return true;
	}

	private int maxQueensOnLine(int[][] board, int x, int y) {
		Integer maxCount = 0;
		List<List<Integer>> positions = new ArrayList();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(board[i][j] == 1) {
					positions.add(Arrays.asList(i,j));
				}
			}
		}

		for(int i=0; i < positions.size() - 1; i++) {
			Integer x1 = positions.get(i).get(0);
			Integer y1 = positions.get(i).get(1);
			Integer count = 0;
			for(int j=i; j < positions.size(); j++) {
				Integer x2 = positions.get(j).get(0);
				Integer y2 = positions.get(j).get(1);
				if ( (x - x1)/(y - y1) == (x - x2)/(y - y2) )
					count++;
			}
			if(count > maxCount)
				maxCount = count;
		}

		return maxCount;
	}

	private boolean solveNQueensUtil(int[][] board, int row) {
		if(row >= N)
			return true;
		for(int col = 0; col < N; col++) {
			if(isSafe(board, row, col) && maxQueensOnLine(board, row, col) < 3) {
				board[row][col] = 1;
				if(solveNQueensUtil(board, row+1)) {
					return true;
				}
				board[row][col] = 0;
			}
		}
		return false;
	}

	private void solve() {
		int[][] board = new int[N][N];
		if(!solveNQueensUtil(board, 0)) {
			System.out.println("No solution found!");
		}
		else {
			System.out.println("Solution found!");
			//print solution(board);
		}
	}

	public static void main(String args[]) {
		NQueensProblem nQueens = new NQueensProblem();
		nQueens.solve();
	}
}
