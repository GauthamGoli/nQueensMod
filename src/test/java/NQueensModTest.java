import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NQueensModTest {

    @Test
    public void testNQueensMod() {
        int[][] board = new int[4][4];
        NQueensMod nQueens = new NQueensMod(board);
        nQueens.solve();
        assertEquals(board[0][0], 1);
        assertEquals(board[1][1], 1);
        assertEquals(board[2][3], 1);
        assertEquals(board[3][2], 1);
    }
}
