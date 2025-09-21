public class SolutionA {
    public static void main(String[] args) {
        int[][] test = new int[3][3];



    }
}

class SolutionB{
    public boolean isAside = false;

    public int getIslands(int[][] input){
        int res = 0;
        int row = input.length;
        int col = input[0].length;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(input[i][j] == 1){
                    dfs(i, j, row, col, input);
                    if(!isAside) res++;
                    isAside = false;
                }
            }
        }


        return res;
    }
    public void dfs(int i, int j, int row, int col, int[][] input){
        if(i < 0 || i >= row || j < 0 || j >= col || input[i][j] == 0)
            return;

        if(i == 0 || j == 0 || i == row -1 || j == row - 1){
             isAside =  true;
        }

        input[i][j] = 0;

        dfs(i + 1, j, row, col, input);
        dfs(i - 1, j, row, col, input);
        dfs(i, j + 1, row, col, input);
        dfs(i, j - 1, row, col, input);
    }

}
