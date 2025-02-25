package LeetCode2.BackTracking;

public class WordSearch {
    public static void main(String[] args) {
        WordSearch ws = new WordSearch();

        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','F','E','E'}
        };

        String word = "ABCCEF";
        boolean exists = ws.exist(board, word);
        System.out.println("Does the word exist? " + exists);

    }

    private boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        // Try starting from every cell in the board.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true; // Word found starting from this cell.
                }
            }
        }
        return false; // Word not found on the board.
    }

    private boolean dfs(char[][] board, String word, int i, int j, int index) {

        if(index==word.length()){
            return true;
        }

        if(i<0 ||i>=board.length ||j<0 ||j>=board.length || board[i][j] != word.charAt(index)){
            return false;
        }
        char temp = board [i][j];
        board [i][j] ='#';

        boolean found = ( dfs(board, word, i+1, j, index+1) ||
                dfs(board, word, i-1, j, index+1)||
                dfs(board, word, i, j+1, index+1)||
                dfs(board, word, i, j-1, index+1));

         board[i][j] = temp;
         return found;
    }
}
