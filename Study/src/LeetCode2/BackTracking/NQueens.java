package LeetCode2.BackTracking;

import java.util.*;

public class NQueens {


    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();

        // Create the board with all positions initially empty ('.').
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        // Sets to track which columns and diagonals are already occupied by queens.
        Set<Integer> cols = new HashSet<>();
        Set<Integer> diag = new HashSet<>();      // For main diagonals (row - col)
        Set<Integer> antiDiag = new HashSet<>();  // For anti-diagonals (row + col)

        // Start the backtracking process from the first row.
        backtrack(solutions, board, 0, n, cols, diag, antiDiag);
        return solutions;
    }

    private void backtrack(List<List<String>> solutions, char[][] board, int row, int n, Set<Integer> cols, Set<Integer> diag, Set<Integer> antiDiag) {

        if (row ==n){
            solutions.add(construct(board));
            return;

        }

        for(int col= 0;col<n;col++){

            if(cols.contains(col) || diag.contains(row - col) || antiDiag.contains(row+col)){
                continue;
            }
            board [row][col] = 'Q';
            cols.add(col);
            diag.add(row-col);
            antiDiag.add(row+col);

            backtrack(solutions, board, row+1, n, cols, diag, antiDiag);

            board [row][col] = '.';
            cols.remove(col);
            diag.remove(row-col);
            antiDiag.remove(row+col);
        }

    }

    private List<String> construct(char[][] board) {

        List<String> arrayList = new ArrayList<>();

        for(char[] row:board){
            arrayList.add(new String(row));
        }
        return arrayList;
    }

    public static void main(String[] args) {
        NQueens solver = new NQueens();
        int n = 4;  // For example, solve for 4 queens.
        List<List<String>> solutions = solver.solveNQueens(n);

        // Print all the valid solutions.
        for (List<String> sol : solutions) {
            for (String s : sol) {
                System.out.println(s);
            }
            System.out.println();  // Separate solutions with a blank line.
        }
    }
}
