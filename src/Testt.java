import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Testt {
    public static void main(String[] args) throws ParseException {



    }
}

/*
Input:
board =
[["A","B","C","E"],
["S","F","C","S"],
["A","D","E","E"]],

word = "ABCCED"

Output: true

*/

class Solution_2024 {



    public static void main(String[] args) {
        char[][] board = new char[3][4];


        board[0][0] = 'A';
        board[0][1] = 'B';
        board[0][2] = 'C';
        board[0][3] = 'E';

        board[1][0] = 'S';
        board[1][1] = 'F';
        board[1][2] = 'C';
        board[1][3] = 'S';

        board[2][0] = 'A';
        board[2][1] = 'D';
        board[2][2] = 'E';
        board[2][3] = 'E';
        char[] word = {'A', 'B' ,'C', 'C', 'E' ,'D'};

        System.out.println(findWord(board, word));

        int[] test = getFrequency("sgsg#");

        Arrays.stream(test).forEach(a -> System.out.println(a));

    }

    public static boolean findWord(char[][] board, char[] word){
        int m = board.length;
        if(m == 0) return false;
        int n = board[0].length;



        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                boolean[][] visited = new boolean[m][n];
                int index = 0;
                if(dfs(i, j, board, visited, word, 0)) return true;
            }
        }

        return false;
    }
    public static boolean dfs(int i, int j, char[][] board, boolean[][] visited, char[] word, int index){
        if(index == word.length){
            return true;
        }

        if(board[i][j] != word[index] || visited[i][j]){
            return false;
        }



        visited[i][j] = true;

        int[] goRight = {i, (j + 1) % board[0].length};
        int[] goLeft = {i , (j - 1) < 0 ? board[0].length - 1 : (j - 1)};
        int[] goDown = {(i + 1) % board.length, j};
        int[] goUp = {(i - 1) < 0 ? board.length - 1 : i - 1 ,  j};

        return dfs(goRight[0], goRight[1], board, visited, word, index + 1) || dfs(goLeft[0], goLeft[1], board, visited, word, index + 1)
                || dfs(goDown[0], goDown[1],  board, visited, word, index + 1) || dfs(goUp[0], goUp[1],  board, visited, word, index + 1);
    }


    public static int[] getFrequency(String s){
        int len = s.length();
        int start = 0, end = 2;
        Map<String, Integer> res = new HashMap<>();
        while(start < len ){
            if(end < len && s.charAt(end) == '#'){
                String tmp = s.charAt(start) + "" + s.charAt(start + 1);
                res.put(tmp, res.getOrDefault(tmp, 0) + 1);
                start = end + 1;
                end = start + 2;
            }
            else {
                String tmp = s.charAt(start) + "";
                res.put(tmp, res.getOrDefault(tmp, 0) + 1);
                start++;
                end++;
            }
        }

        int[] ff = new int[res.size()];
        int index = 0;
        for(Map.Entry<String, Integer> tmp : res.entrySet()){
            ff[index] = tmp.getValue();
            index++;
        }

        return ff;
    }
}
