import java.util.*;

public class LeetcodeAlgorithms {

    public static void main(String[] args){
        leetcode testclass = new leetcode();
        testclass.calculate_2024("3+5 / 2");
    }
}

class leetcode{
    private final static Random random = new Random();
    private int treenode_ans;
    private TreeNode ans = null;
    private static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int count = 0;


    Map<Node,Node> cachemap = new HashMap<>();
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len <= 2) {
            return len;
        }

        int j = 2;
        for(int i = 2; i < len; i++){
            if(nums[i] != nums[j-2] ) {
                nums[j] = nums[i];
                j++;
            }

        }

        return j;

    }

    public void moveZeroes(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = 0;

        while(right < len){
            if(nums[right] != 0){
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;

                left++;
            }
            right++;
        }
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int len = nums.length;
        int max_ones = Integer.MIN_VALUE;

        int left = 0;
        int right = 0;

        while(right < len){
            while (right < len && nums[left] == 1 && nums[right] == 1){
                right++;
            }

           max_ones = Math.max(max_ones,right-left);
            left = right + 1;
            right++;
        }

        return max_ones;

    }


    public List<Integer> findDisappearedNumbers(int[] nums) {
        int len = nums.length;
        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < len; i++){
            int x = (nums[i] - 1) % len;
            nums[x] = nums[x] + len;
        }

        for(int i = 0; i < len; i++){
            if(nums[i] <= len){
                result.add(i+1);
            }
        }

        return result;
    }

    public List<Integer> findDuplicates(int[] nums) {
        int len = nums.length;
        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < len; i++){

            int x = (nums[i] - 1) % len;
            nums[x] += len;
        }

        for(int i = 0; i < len; i++){
            if(nums[i] > len*2){
                result.add(i+1);
            }
        }

        return result;

    }


    public int hIndex(int[] citations) {
        int len = citations.length;

        Arrays.parallelSort(citations);

        int max = 0;
        boolean flag = false;
        for(int i = 0; i < len; i++){
            if(citations[i] <= len - i){
                flag = true;
                int tmp = citations[i];
                max = Math.max(max,tmp);
            }
            else {
                int tmp = len - i;
                max = Math.max(max,tmp);
            }
        }

        return max;
    }

    public int firstMissingPositive(int[] nums) {
        int len = nums.length;

        for(int i = 0; i < len; i++){
            while(nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]){

                int tmp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
            }
        }

        for(int i = 0; i < len; i++){
            if(nums[i] != i + 1){
                return i + 1;
            }
        }

        return len + 1;
    }

    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> res = new ArrayList<>();

        for(int i = 0; i <= rowIndex; i++){

            if(i == 0){
                List<Integer> tmp = new ArrayList<>();
                tmp.add(1);
                res.add(tmp);
            }
            else {
                List<Integer> tmp_list = new ArrayList<>();
                for(int j = 0; j < i + 1; j++){
                    int tmp = 0;
                    if(j - 1 >= 0 && j - 1 < res.get(i-1).size()){
                        tmp += res.get(i-1).get(j-1);
                    }

                    if(j < res.get(i-1).size()){
                        tmp += res.get(i-1).get(j);
                    }

                    tmp_list.add(tmp);

                }
                res.add(tmp_list);
            }
        }

        return res.get(rowIndex);
    }

    public int[][] imageSmoother(int[][] img) {
        int rows = img.length;
        int cols = img[0].length;

        int[][] res = new int[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                int tmp = 0;
                int count = 0;
                if(i - 1 >= 0){
                    tmp += img[i-1][j];
                    count++;
                    if(j-1 >= 0){
                        tmp += img[i-1][j-1];
                        count++;
                    }
                    if(j+1 < cols){
                        tmp += img[i-1][j+1];
                        count++;
                    }
                }
                if(i+1 < rows){

                    tmp += img[i+1][j];
                    count++;
                    if(j-1 >= 0){

                        tmp += img[i+1][j-1];
                        count++;
                    }
                    if(j+1 < cols){
                        tmp += img[i+1][j+1];
                        count++;
                    }
                }

                tmp += img[i][j];
                count++;
                if(j-1 >= 0){
                    tmp += img[i][j-1];
                    count++;
                }
                if(j+1 < cols){
                    tmp += img[i][j+1];
                    count++;
                }

                int final_num = tmp/count;
                res[i][j] = final_num;
            }
        }

        return res;

    }

    public int minMoves(int[] nums) {
        int len = nums.length;
        Arrays.parallelSort(nums);

        if(len <= 1){
            return 0;
        }
        int count = 0;
        for(int i = 1; i < len; i++){
            count += nums[i] - nums[0];
        }

        return count;
    }

    public boolean checkPossibility(int[] nums) {
        int len = nums.length;
        if(len <= 1){
            return true;
        }

        int count = 0;
        for(int i = 0; i < len - 1; i++){
            if(nums[i] > nums[i+1]){
                count++;
                if(i-1>=0 && nums[i+1] < nums[i-1]){
                    nums[i+1] = nums[i];
                }
            }


            if(count > 1) return false;
        }

        return true;

    }

    public int countBattleships(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        boolean[][] visited = new boolean[rows][cols];
        int count = 0;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(!visited[i][j] && board[i][j] =='X'){
                    count++;
                    visited[i][j] = true;
                    int tmp_col = j + 1;
                    int tmp_row = i + 1;

                    while (tmp_col < cols && board[i][tmp_col] == 'X'){

                        visited[i][tmp_col] = true;
                        tmp_col++;
                    }

                    while(tmp_row < rows && board[tmp_row][j] == 'X'){
                        visited[tmp_row][j] = true;
                        tmp_row++;
                    }
                }
            }
        }

        return count;

    }

    public void rotate(int[] nums, int k) {
        int len = nums.length;

        if(k > len){
            return;
        }
        int[] newarray = new int[len];

        int count = 0;
        for(int i = 0; i < len; i++){
            if(k > 0){
                newarray[i] = nums[len - k];
                k--;
            }
            else {
                newarray[i] = nums[count];
                count++;
            }
        }

        for (int i = 0; i < len; i++){
            nums[i] = newarray[i];
        }

    }

    public int maxRotateFunction(int[] nums) {
        int f = 0, n = nums.length, numSum = Arrays.stream(nums).sum();
        for (int i = 0; i < n; i++) {
            f += i * nums[i];
        }
        int res = f;
        for (int i = n - 1; i > 0; i--) {
            f += numSum - n * nums[i];
            res = Math.max(res, f);
        }
        return res;

    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        List<Integer> res = new LinkedList<>();

        int left = 0;
        int right = cols - 1;
        int top = 0;
        int bottom = rows - 1;

        while(true){
            for(int i = left; i <= right; i++){
                res.add(matrix[top][i]);
            }
            top++;
            if(top > bottom){
                return res;
            }

            for(int i = top; i <= bottom; i++){
                res.add(matrix[i][right]);
            }
            right--;
            if(left > right){
                return res;
            }

            for(int i = right; i >= left; i--){
                res.add(matrix[bottom][i]);
            }
            bottom--;
            if(top > bottom){
                return res;
            }

            for(int i = bottom; i >= top; i--){
                res.add(matrix[i][left]);
            }
            left++;
            if(left > right){
                return res;
            }

        }
    }

    public int[][] generateMatrix(int n) {
        int rows = n;
        int cols = n;
        int[][] res = new int[n][n];

        int left = 0;
        int right = cols - 1;
        int top = 0;
        int bottom = rows - 1;

        int count = 1;
        while(true){
            for(int i = left; i <= right; i++){
                res[top][i] = count;

                count++;

            }
            top++;
            if(top > bottom){
                return res;
            }

            for(int i = top; i <= bottom; i++){
                res[i][right] = count;
                count++;
            }
            right--;
            if(left > right){
                return res;
            }

            for(int i = right; i >= left; i--){
                res[bottom][i] = count;
                count++;
            }
            bottom--;
            if(top > bottom){
                return res;
            }

            for(int i = bottom; i >= top; i--){
                res[i][left] = count;
                count++;
            }
            left++;
            if(left > right){
                return res;
            }

        }

    }

    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int rows = mat.length;
        int cols = mat[0].length;

        if(rows * cols != r * c){
            return mat;
        }

        int[][] res = new int[r][c];
        int[] tmp = new int[rows * cols];

        int count = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                tmp[count] = mat[i][j];
                count++;
            }
        }

        count = 0;
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                res[i][j] = tmp[count];
                count++;
            }
        }

        return res;
    }

    public int[] findDiagonalOrder(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        int[] res = new int[rows * cols];

        int cur_row = 0;
        int cur_col = 0;
        int count = 0;
        res[count] = mat[cur_row][cur_col];
        count++;
        int jus_reverse = 1;

        while(count < rows * cols){
            if(cur_row + 1 < rows){
                jus_reverse++;
                cur_row++;
                int tmp_row = cur_row;
                int tmp_col = cur_col;
                List<Integer> tmp = new ArrayList<>();

                while(tmp_row < rows && tmp_col < cols && tmp_row >= 0 && tmp_col >= 0){
                    //res[count] = mat[tmp_row][tmp_col];
                    //count++;
                    tmp.add(mat[tmp_row][tmp_col]);
                    tmp_row--;
                    tmp_col++;
                }
                if(jus_reverse % 2 == 0){
                    int cot = tmp.size() - 1;
                    while(cot >= 0){
                        res[count] = tmp.get(cot);

                        cot--;
                        count++;
                    }
                }
                else {
                    int cot = 0;
                    while (cot < tmp.size()){
                        res[count] = tmp.get(cot);
                        cot++;
                        count++;
                    }
                }
            }
            else if(cur_col + 1 < cols){
                cur_col++;
                int tmp_row = cur_row;
                int tmp_col = cur_col;
                jus_reverse++;
                List<Integer> tmp = new ArrayList<>();

                while(tmp_row < rows && tmp_col < cols && tmp_row >= 0 && tmp_col >= 0){
                    //res[count] = mat[tmp_row][tmp_col];
                    //count++;
                    tmp.add(mat[tmp_row][tmp_col]);
                    tmp_row--;
                    tmp_col++;
                }

                if(jus_reverse % 2 == 0){
                    int cot = tmp.size() - 1;
                    while(cot >= 0){
                        res[count] = tmp.get(cot);
                        cot--;
                        count++;
                    }

                }
                else {
                    int cot = 0;
                    while (cot < tmp.size()){
                        res[count] = tmp.get(cot);
                        cot++;
                        count++;
                    }
                }
            }

        }

        return res;
    }

    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if(matrix[i][j] == 0){
                    matrix[i][j] = (int) 10000000000L;
                    for(int tmp = 0; tmp < rows; tmp++){
                        if(matrix[tmp][j] != 0 && matrix[tmp][j] != (int) 10000000000L){
                            matrix[tmp][j] = (int) 10000000000L;
                        }
                    }

                    for(int tmp = 0; tmp < cols; tmp++){
                        if(matrix[i][tmp] != 0 && matrix[i][tmp] != (int) 10000000000L){
                            matrix[i][tmp] = (int) 10000000000L;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if(matrix[i][j] == (int) 10000000000L){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public void gameOfLife(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j ++){
                if(board[i][j] == 0){
                    int count_live = 0;
                    for(int tmp_row = i - 1; tmp_row <= i + 1; tmp_row++){
                        for(int tmp_col = j -1; tmp_col <= j + 1; tmp_col++){
                            if(tmp_row >= 0 && tmp_row < rows && tmp_col >= 0 && tmp_col < cols){
                                if(tmp_row == i && tmp_col == j){
                                    continue;
                                }
                                else {
                                    if(board[tmp_row][tmp_col] == 1 || board[tmp_row][tmp_col] == 3 ){
                                        count_live++;
                                    }
                                }
                            }
                        }
                    }
                    if(count_live == 3){
                        board[i][j] += 2;
                    }

                }
                else if(board[i][j] == 1){
                    int count_live = 0;
                    for(int tmp_row = i - 1; tmp_row <= i + 1; tmp_row++){
                        for(int tmp_col = j -1; tmp_col <= j + 1; tmp_col++){
                            if(tmp_row >= 0 && tmp_row < rows && tmp_col >= 0 && tmp_col < cols){

                                if(tmp_row == i && tmp_col == j){
                                    continue;
                                }
                                else {
                                    if(board[tmp_row][tmp_col] == 1 || board[tmp_row][tmp_col] == 3 ){
                                        count_live++;
                                    }
                                }
                            }
                        }
                    }

                    if(count_live < 2){
                        board[i][j] += 2;
                    }
                    else if(count_live > 3){
                        board[i][j] += 2;
                    }
                }



            }
        }

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(board[i][j] == 2){
                    board[i][j] = 1;
                }
                else if(board[i][j] == 3){
                    board[i][j] = 0;
                }
            }
        }

    }

    public void rotate(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int top_row = 0;
        int left_col = 0;
        int bottom_row = rows - 1;
        int right_col = cols - 1;
        int right = right_col;
        while(top_row < bottom_row && left_col < right_col){

            for(int j = left_col; j < right_col; j++){
                int tmp1 = matrix[top_row][j];
                int tmp2 = matrix[j][right_col];
                int tmp3 = matrix[bottom_row][right - j];
                int tmp4 = matrix[right - j][left_col];

                matrix[top_row][j] = tmp4;
                matrix[j][right_col] = tmp1;
                matrix[bottom_row][right - j] = tmp2;
                matrix[right - j][left_col] = tmp3;
            }
            top_row++;
            bottom_row--;
            left_col++;
            right_col--;
        }
    }

    public boolean isPalindrome(String s) {
        int len = s.length();
        s = s.toLowerCase();
        int left = 0;
        int right = len - 1;

        while(left < right){
            if(Character.isLetterOrDigit(s.charAt(left)) && Character.isLetterOrDigit(s.charAt(right))){
                if(s.charAt(left) != s.charAt(right)){
                    return false;
                }
                left++;

                right--;
            }
            else {
                if (!Character.isLetterOrDigit(s.charAt(left))){

                    left++;
                }
                if (!Character.isLetterOrDigit(s.charAt(right))){
                    right--;
                }
            }
        }
        return true;
    }

    public boolean detectCapitalUse(String word) {
        int len = word.length();
        boolean flag_lower = false;
        boolean flag_upper = false;

        for(int i = 0; i < len; i++){
            if(Character.isLowerCase(word.charAt(i))){

                flag_lower = true;
            }
            if(Character.isUpperCase(word.charAt(i)) && i != 0){
                flag_upper = true;
            }
        }

        if(flag_lower == true && flag_upper == true){
            return false;
        }

        return true;

    }

    public String longestCommonPrefix(String[] strs) {
        int count = 0;
        int len = strs.length;
        if(len <= 1){
            return strs[0];
        }

        while (true){
            for(int i = 0; i < len - 1; i++){
                if(count < strs[i].length() && count < strs[i+1].length() && strs[i].charAt(count) != strs[i+1].charAt(count)){
                    return strs[i].substring(0,count);
                }

                if(count >= strs[i].length()){
                    return strs[i];
                }

                if(count >= strs[i+1].length()){
                    return strs[i+1];
                }
            }


            count++;
        }

    }

    public String reverseWords(String s) {
        s = s.trim();
        int len = s.length();

        List<String> tmp = new ArrayList<>();
        String res = "";
        int begin = 0;
        for(int i = 0; i < len; i++){
            if(s.charAt(i) ==' '){
                String tmp_string = s.substring(begin,i);
                tmp_string = tmp_string.trim();
                tmp.add(tmp_string);
                int tmp_begin =  i + 1;
                while(s.charAt(tmp_begin) ==' ' && tmp_begin < len){
                    tmp_begin++;
                }
                begin = tmp_begin;
                i = begin;
            }
        }
        String tmp_string = s.substring(begin,len);
        System.out.println(tmp_string);
        tmp_string = tmp_string.trim();
        System.out.println(tmp_string);
        tmp.add(tmp_string);

        int len_tmp = tmp.size();
        for(int i = len_tmp - 1; i >=0; i--){
            res +=" ";
            res += tmp.get(i);
        }

        res = res.trim();

        return res;
    }

    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] L = new int[len];
        int[] R = new int[len];

        L[0] = 1;
        for(int i = 1; i < len; i++){
            L[i] = L[i-1] * nums[i - 1];
        }

        R[len - 1] = 1;
        for(int i = len - 2; i >= 0; i--){
            R[i] = R[i + 1] * nums[i + 1];
        }

        int[] res = new int[len];

        for(int i = 0; i < len; i++){
            res[i] = L[i] * R[i];
        }

        return res;
    }

    public int firstUniqChar(String s) {
        int len = s.length();
        if(len <= 1){
            return 0;
        }

        Map<Character,Integer> tmp_map = new HashMap<>();
        for(int i = 0; i < len; i++){
            tmp_map.put(s.charAt(i),tmp_map.getOrDefault(s.charAt(i),0) + 1);
        }

        for(int i = 0; i < len; i++){
            if(tmp_map.get(s.charAt(i)) == 1){
                return i;
            }
        }

        return -1;
    }

    public char findTheDifference(String s, String t) {
        char[] s1 =  s.toCharArray();
        char[] t1 = t.toCharArray();
        Arrays.parallelSort(s1);
        Arrays.parallelSort(t1);
        int len = s1.length;
        if(len == 0){
            return t.charAt(0);
        }

        for(int i = 0; i < len; i++){
            if(s1[i] != t1[i]){
                return t1[i];
            }
        }

        return t1[len];
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        int len = strs.length;
        List<List<String>> res = new LinkedList<>();
        Set<Integer> index_set = new HashSet<>();
        for(int i = 0; i < len; i++){
            if(index_set.contains(i)){
                continue;
            }
            List<String> tmp_list = new LinkedList<>();
            tmp_list.add(strs[i]);
            int[] counts = new int[26];
            int tmp_len_i = strs[i].length();
            for(int tmp = 0; tmp < tmp_len_i; tmp++){
                counts[strs[i].charAt(tmp) - 'a']++;
            }
            for(int j = i + 1; j < len; j++){
                if(index_set.contains(j)){
                    continue;
                }
                if(strs[j].length() != strs[i].length()){
                    continue;
                }
                int[] tmp_counts = counts.clone();
                int tmp_len_j = strs[j].length();
                boolean flag = false;
                for(int tmp = 0; tmp < tmp_len_j; tmp++){
                    tmp_counts[strs[j].charAt(tmp) - 'a']--;
                    if(tmp_counts[strs[j].charAt(tmp) - 'a'] < 0){
                        flag = true;
                    }
                }

                if(flag == false){
                    tmp_list.add(strs[j]);
                    index_set.add(j);
                }
            }


            res.add(tmp_list);
        }

        return res;

    }

    public boolean checkRecord(String s) {
        int len = s.length();

        int count_A = 0;
        int count_L = 1;
        for(int i = 0; i < len - 1; i++){
            if(s.charAt(i) == 'A'){
                count_A++;
            }

            if(s.charAt(i) == 'L' && s.charAt(i+1) == 'L'){
                count_L++;
            }

            if(s.charAt(i) == 'L' && s.charAt(i+1) != 'L'){
                count_L = 1;
            }

            if(count_A >= 2){
                return false;
            }

            if(count_L >= 3){
                return false;
            }
        }
        if(s.charAt(len-1) == 'A'){
            count_A++;
            if(count_A >= 2){
                return false;
            }

        }

        return true;

    }

    public boolean judgeCircle(String moves) {
        int len = moves.length();

        int count_left_right = 0;
        int count_up_down = 0;
        for(int i = 0; i < len; i++){
            if(moves.charAt(i) == 'U'){
                count_up_down++;
            }
            else if(moves.charAt(i) == 'D'){
                count_up_down--;
            }
            else if(moves.charAt(i) == 'R'){
                count_left_right++;
            }
            else {
                count_left_right--;
            }
        }

        if(count_left_right == 0 && count_up_down == 0){
            return true;
        }
        return false;
    }

    public String frequencySort(String s) {
        int len = s.length();
        Map<Character,Integer> char_map = new HashMap<>();

        for(int i = 0; i < len; i++){
            char_map.put(s.charAt(i),char_map.getOrDefault(s.charAt(i),0) + 1);
        }

        List<Character> char_list = new ArrayList<>(char_map.keySet());
        Collections.sort(char_list,(a,b) -> char_map.get(b) - char_map.get(a));

        String res = "";
        for(Character a : char_list){
            int tmplen = char_map.get(a);
            for(int i = 0; i < tmplen; i++){
                res += a;
            }
        }

        return res;
    }

    public List<String> fizzBuzz(int n) {
        List<String> res = new LinkedList<>();
        for(int i = 1; i <= n; i++){
            if(i % 3 == 0 && i % 5 == 0){
                res.add("FizzBuzz");
            }
            else if(i % 3 == 0){
                res.add("Fizz");
            }
            else if(i % 5 == 0){
                res.add("Buzz");
            }
            else {
                res.add(""+i);
            }
        }

        return res;

    }

    public String[] findRelativeRanks(int[] score) {
        int len = score.length;
        String[] res = new String[len];
        Map<Integer,String> tmp_map = new HashMap<>();

        int[] tmp = new int[len];
        for(int i = 0; i < len; i++){
            tmp[i] = score[i];
        }
        Arrays.parallelSort(score);
        for(int i = len-1; i >= 0; i--){
            if((len - 1) - i == 0){
                tmp_map.put(score[i],"Gold Medal");
            }
            else if((len - 1) - i == 1){
                tmp_map.put(score[i],"Silver Medal");
            }
            else if((len - 1) - i == 2){
                tmp_map.put(score[i], "Bronze Medal");
            }
            else {
                tmp_map.put(score[i],""+((len - 1)-i+1));
            }
        }

        for(int i = 0; i < len; i++){
            res[i] = tmp_map.get(tmp[i]);
        }

        return res;
    }

    public String getHint(String secret, String guess) {
        int len = secret.length();
        int x = 0;
        int y = 0;

        char[] se = secret.toCharArray();
        char[] gu = guess.toCharArray();
        Map<Character,Integer> tmp_tmp = new HashMap<>();

        for(int i = 0; i < len; i++){
            if(se[i] == gu[i]){
                x++;
                se[i] = 'z';
                gu[i] = 'z';
            }
        }

        len = se.length;
        for(int i = 0; i < len; i++){
            if(se[i] != 'z'){
                tmp_tmp.put(se[i],tmp_tmp.getOrDefault(se[i],0)+1);
            }

        }

        for(int i = 0; i < len; i++){
            if(tmp_tmp.get(gu[i]) != null && tmp_tmp.get(gu[i]) != 0){
                y++;
                tmp_tmp.put(gu[i],tmp_tmp.get(gu[i])-1);
            }
        }

        return ""+x+"A"+y+"B";
    }

    public String originalDigits(String s) {
        String res = "";
        int len = s.length();
        Map<Character,Integer> tmp_map = new HashMap<>();

        for(int i = 0; i < len; i++){
            tmp_map.put(s.charAt(i),tmp_map.getOrDefault(s.charAt(i),0)+1);
        }

        Character z = 'z';
        Character e = 'e';
        Character r = 'r';
        Character o = 'o';
        Character t = 't';
        Character w = 'w';
        Character h = 'h';
        Character f = 'f';
        Character u = 'u';
        Character i = 'i';
        Character v = 'v';
        Character ss = 's';
        Character x = 'x';
        Character n = 'n';
        Character g = 'g';

        int count = 0;
        while(count < len){
            if(tmp_map.get(z) != null && tmp_map.get(z) != 0
                    && tmp_map.get(e) != null && tmp_map.get(e) != 0
                    && tmp_map.get(r) != null && tmp_map.get(r) != 0
                    && tmp_map.get(o) != null && tmp_map.get(o) != 0){

                res += "0";
                count += 4;
                tmp_map.put(z,tmp_map.get(z)-1);
                tmp_map.put(e,tmp_map.get(e)-1);
                tmp_map.put(r,tmp_map.get(r)-1);
                tmp_map.put(o,tmp_map.get(o)-1);
            }

            else if(tmp_map.get(o) != null && tmp_map.get(o) != 0
                    && tmp_map.get(e) != null && tmp_map.get(e) != 0
                    && tmp_map.get(n) != null && tmp_map.get(n) != 0){
                res += "1";
                count += 3;

                tmp_map.put(n,tmp_map.get(n)-1);
                tmp_map.put(e,tmp_map.get(e)-1);
                tmp_map.put(o,tmp_map.get(o)-1);
            }

            else if(tmp_map.get(o) != null && tmp_map.get(o) != 0
                    && tmp_map.get(t) != null && tmp_map.get(t) != 0
                    && tmp_map.get(w) != null && tmp_map.get(w) != 0){
                res += "2";
                count += 3;

                tmp_map.put(w,tmp_map.get(w)-1);
                tmp_map.put(t,tmp_map.get(t)-1);
                tmp_map.put(o,tmp_map.get(o)-1);
            }
        }
        return res;
    }

    public boolean isSubsequence(String s, String t) {
        int len_s = s.length();
        int len_t = t.length();
        if(len_s == 0) return true;
        if(len_s > len_t) return false;

        int pointer_s = 0;
        int pointer_t = 0;

        while(pointer_t < len_t){
            if(s.charAt(pointer_s) == t.charAt(pointer_t)){
                pointer_s++;
                pointer_t++;
            }
            else{
                pointer_t++;
            }

            if(pointer_s == len_s){
                return true;
            }

        }

        return false;
    }

    public int findLUSlength_help(String a, String b) {
        int len_a = a.length();
        int len_b = b.length();

        if(len_a == len_b){
            return a.equals(b) ? -1 : len_a;
        }

        return len_a < len_b ? len_b : len_a;

    }

    public int findLUSlength(String[] strs) {
        int n = strs.length;
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            boolean check = true;
            for (int j = 0; j < n; ++j) {
                if (i != j && isSubseq(strs[i], strs[j])) {
                    check = false;
                    break;
                }
            }
            if (check) {
                ans = Math.max(ans, strs[i].length());
            }
        }
        return ans;
    }

    public boolean isSubseq(String s, String t) {
        int ptS = 0, ptT = 0;
        while (ptS < s.length() && ptT < t.length()) {
            if (s.charAt(ptS) == t.charAt(ptT)) {
                ++ptS;
            }
            ++ptT;
        }
        return ptS == s.length();
    }

    public String fractionAddition(String expression) {
        int len = expression.length();
        List<String> nums = new LinkedList<>();
        List<String> symbols = new LinkedList<>();

        int count = 0;
        for(int i = 0; i < len; i++){
            if(expression.charAt(i) == '+'){
                nums.add(expression.substring(count,i));
                symbols.add(expression.substring(i,i+1));
                count = i + 1;
            }
            else if(expression.charAt(i) == '-' && i != 0 && (expression.charAt(i-1) != '-' ||expression.charAt(i-1) != '+')){
                nums.add(expression.substring(count,i));
                symbols.add(expression.substring(i,i+1));
                count = i + 1;
            }
        }

        nums.add(expression.substring(count,len));
        if(nums.size() == 1){
            return nums.get(0);
        }


        int denominator = 1;
        int[] numerators = new int[nums.size()];
        int count_num = 0;
        for(String s : nums){
            String[] tmp = s.split("/");
            denominator = denominator * Integer.parseInt(tmp[1]);
        }

        for(String s : nums){
            String[] tmp = s.split("/");
            numerators[count_num] = denominator / Integer.parseInt(tmp[1]) * Integer.parseInt(tmp[0]);
            count_num++;
        }

        int cot = 0;
        int res = 0;
        for(String s : symbols){
            if(cot == 0){
                if(s.equals("+")){
                    res = numerators[cot] + numerators[cot+1];
                    cot += 2;
                }
                else {
                    res = numerators[cot] - numerators[cot+1];
                    cot += 2;
                }
            }
            else {
                if(s.equals("+")){
                    res = res + numerators[cot];
                    cot++;
                }
                else {
                    res = res - numerators[cot];
                    cot++;
                }
            }
        }

        if(res == 0){
            return "0/1";
        }

        int chu = Math.abs(res);
        int ress = Math.abs(denominator);

        int remainer = gcd(chu,ress);
        return ""+res/remainer+"/"+denominator/remainer;
    }

    public int gcd(int a, int b) {
        int remainder = a % b;
        while (remainder != 0) {
            a = b;
            b = remainder;
            remainder = a % b;
        }
        return b;
    }

    public String addBinary(String a, String b) {
        StringBuffer ans = new StringBuffer();

        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            ans.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        if (carry > 0) {
            ans.append('1');
        }
        ans.reverse();

        return ans.toString();
    }

    public int strStr(String haystack, String needle) {
        int len_h = haystack.length();
        int len_n = needle.length();


        if(len_h < len_n) return -1;
        int start_h = 0;
        int start_n = 0;

        int tmp = 0;
        while(start_h < len_h){
            if(haystack.charAt(start_h) == needle.charAt(start_n)){
                tmp++;
                start_h++;
                start_n++;
                if(start_n == len_n){
                    return start_h - len_n;
                }
            }
            else {
                if(tmp == 0){
                    start_h++;
                    start_n = 0;
                }
                else {
                    start_h = start_h - (tmp - 1);
                    start_n = 0;
                    tmp = 0;
                }
            }
        }

        return -1;

    }

    public String convert(String s, int numRows) {
        if(numRows == 1){
            return s;
        }
        int len = s.length();
        int nums = numRows * 2 - 2;
        int remain = len % nums == 0 ? len / nums : len / nums + 1;
        int cols = remain * numRows - (remain - 1);
        int rows = numRows;
        char[][] tmp = new char[rows][cols];

        int count = 0;
        int row = 0;
        int col = 0;
        while(count < len){
            while(row < rows){
                tmp[row][col] = s.charAt(count);
                count++;
                row++;
                if(count >= len) break;
            }
            if(count >= len) break;
            row = rows - 2;
            col++;
            while(row >= 0){
                tmp[row][col] = s.charAt(count);
                count++;
                row--;
                col++;
                if(count >= len) break;
            }

            if(count >= len) break;

            row = 1;
            col--;
        }

        StringBuilder res = new StringBuilder();

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(tmp[i][j] != '\0'){
                    res.append(tmp[i][j]);
                }
            }
        }
        return res.toString();
    }

    public boolean isPalindrome(int x) {
        if(x < 0) return false;
        StringBuilder res = new StringBuilder();

        while(x >= 10){
            int tmp = x % 10;
            res.append((char)(tmp + '0'));
            x /= 10;
        }
        res.append((char)(x + '0'));

        return res.toString().equals(res.reverse().toString());
    }

    public String longestPalindrome(String s) {
        int len = s.length();
        if(len < 2) return s;

        boolean[][] dp = new boolean[len][len];
        for(int i = 0; i < len; i++){
            dp[i][i] = true;
        }

        int maxLen = 1;
        int begin = 0;
        for(int L = 2; L <= len; L++){
            for(int i = 0; i < len; i++){
                int j = i + L - 1;
                if(j >= len) break;

                if(s.charAt(i) != s.charAt(j)) dp[i][j] = false;
                else {
                    if(j - i < 3) dp[i][j] = true;
                    else dp[i][j] = dp[i+1][j-1];
                }

                if(dp[i][j] && j - i + 1 > maxLen){
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }

        return s.substring(begin,begin + maxLen);

    }

    public int countSubstrings(String s) {
        int len = s.length(), res = 0;
        for (int i = 0; i < len; i++) {
            int left = i, right = i;
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                res++;
            }

            left = i;
            right = i+1;
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                res++;
            }
        }
        return res;
    }

    public String multiply(String num1, String num2) {
        int len_num1 = num1.length();
        int len_num2 = num2.length();
        if(num1.equals("0") || num2.equals("0")) return "0";

        LinkedList<String> tmp_list = new LinkedList<>();

        for(int i = len_num1 - 1; i >= 0; i--){
            int carry = 0;
            StringBuilder tmp = new StringBuilder();
            for(int j = len_num2 - 1; j >= 0; j--){
                carry = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + carry;
                tmp.append((char)((carry % 10) + '0'));
                carry /= 10;
            }
            if(carry > 0){
                tmp.append((char)((carry) + '0'));
            }
            tmp = tmp.reverse();
            int remain = (len_num1 - 1 - i);

            while(remain > 0){
                tmp.append('0');
                remain--;
            }
            tmp_list.add(tmp.toString());
        }


        if(tmp_list.size() == 1){
            return tmp_list.get(0);
        }


        String total = tmp_list.get(0);
        for(int i = 1; i < tmp_list.size(); i++){
            StringBuilder tmp_res = new StringBuilder();
            String tmp = tmp_list.get(i);
            int index_total = total.length() - 1;
            int index_tmp = tmp.length() - 1;
            int carry = 0;
            while(index_total >= 0 || index_tmp >= 0){
                carry += index_tmp >= 0 ? (tmp.charAt(index_tmp) - '0') : 0;
                carry += index_total >= 0 ? (total.charAt(index_total) - '0') : 0;
                tmp_res.append((char)((carry % 10) + '0'));
                carry /= 10;
                index_total--;
                index_tmp--;
            }
            if(carry > 0){
                tmp_res.append((char)((carry) + '0'));
            }

            total = tmp_res.reverse().toString();
        }

        return total;
    }

    public String convertToBase7(int num) {
        if(num < 7 && num >= 0) return String.valueOf(num);
        char flag = ' ';
        if(num < 0){
            flag = '-';
            num = Math.abs(num);
        }

        StringBuilder res = new StringBuilder();
        while(num > 0){
            int tmp = num % 7;
            res.append((char)(tmp + '0'));
            num /= 7;
        }

        if(flag == '-') res.append(flag);

        return res.reverse().toString();
    }

    public int divide(int dividend, int divisor) {
        boolean flag1 = false;
        boolean flag2 = false;
        if(dividend == Integer.MIN_VALUE){
            if(divisor == 1) return dividend;
            if(divisor == -1) return Integer.MAX_VALUE;
        }

        if(dividend < 0) {
            flag1 = true;
            dividend = Math.abs(dividend);
        }
        if(divisor < 0) {
            flag2 = true;
            divisor = Math.abs(divisor);
        }

        int count = 0;
        int total = 0;

        while(total < dividend){
            total += divisor;
            count++;
        }

        if(total > dividend) count--;

        return flag1 == flag2 ? count : count - count * 2;
    }

    public int reverse(int x) {
        if(x >=0 && x < 10) return x;

        int res = 0;
        while(x != 0){
            if(res > Integer.MAX_VALUE/10 || res < Integer.MIN_VALUE/10) return 0;
            res *= 10;
            res += (x % 10);
            x /= 10;
        }

        return res;
    }

    public double myPow(double x, int n) {
        return n < 0 ? 1.0 / myPow_help(x,n) : myPow_help(x,n);
    }

    public double myPow_help(double x, int n){
        if(n == 0) return 1;

        double y = myPow_help(x,n/2);

        return n % 2 == 0 ? y * y : y * y * x;
    }

    public int evalRPN(String[] tokens) {
        int len = tokens.length;
        Stack<String> stack = new Stack<>();

        for(String a : tokens){
            if(Character.isDigit(a.charAt(0))||(a.charAt(0) == '-'&&a.length() > 1)) stack.push(a);

            else if(a.charAt(0) == '+'){
                int tmpa = Integer.parseInt(stack.pop());
                int tmpb = Integer.parseInt(stack.pop());

                stack.push(String.valueOf(tmpb + tmpa));
            }
            else if(a.charAt(0) == '-'){
                int tmpa = Integer.parseInt(stack.pop());
                int tmpb = Integer.parseInt(stack.pop());

                stack.push(String.valueOf(tmpb - tmpa));
            }
            else if(a.charAt(0) == '*'){
                int tmpa = Integer.parseInt(stack.pop());
                int tmpb = Integer.parseInt(stack.pop());

                stack.push(String.valueOf(tmpb * tmpa));
            }
            else if(a.charAt(0) == '/'){
                int tmpa = Integer.parseInt(stack.pop());
                int tmpb = Integer.parseInt(stack.pop());

                stack.push(String.valueOf(tmpb / tmpa));
            }
        }

        return Integer.parseInt(stack.pop());

    }

    public int calculate(String s) {
        int len = s.length();
        Stack<Integer> stack = new Stack<>();
        char preSign = '+';
        int num = 0;

        for(int i = 0; i < len; i++){
            if(Character.isDigit(s.charAt(i))) {
                num = 10 * num + (s.charAt(i) - '0');
            }

            if(!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == len - 1){
                switch (preSign){
                    case '+' : {
                        stack.push(num);
                        break;
                    }
                    case '-' : {
                        stack.push(-num);
                        break;
                    }
                    case '*' : {
                        stack.push(stack.pop() * num);
                        break;
                    }
                    default : {
                        stack.push(stack.pop() / num);
                    }
                }

                preSign = s.charAt(i);
                num = 0;
            }

        }

        int res = 0;
        while(!stack.isEmpty()){
            res += stack.pop();
        }

        return res;
    }


    public String simplifyPath(String path) {
        String[] ls = path.split("/");
        Stack<String> stack = new Stack();
        for(String tmp : ls){
            if(tmp.equals("..")){
                if(!stack.isEmpty()) stack.pop();
            }
            else if(!tmp.equals(".") &&!tmp.isEmpty()){
                stack.push(tmp);
            }
        }

        StringBuilder res = new StringBuilder();
        for(String tmp : stack){
            res.append('/');
            res.append(tmp);
        }
        if(res.length() == 0) res.append("/");

        return res.toString();
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        for(int i = 0; i < len; i++){
            if(s.charAt(i) == '('||s.charAt(i) == '{' || s.charAt(i) == '['){
                stack.push(s.charAt(i));
            }
            else if(s.charAt(i) == ')'){
                if(!stack.isEmpty() && stack.peek() == '(') stack.pop();
                else return false;
            }
            else if(s.charAt(i) == '}'){
                if(!stack.isEmpty() && stack.peek() == '{') stack.pop();
                else return false;
            }
            else if(s.charAt(i) == ']'){
                if(!stack.isEmpty() && stack.peek() == '[') stack.pop();
                else return false;
            }
        }
        return stack.isEmpty();
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode tmp = pre;

        while(head != null){
            while(head != null && head.val == val){
                head = head.next;
            }
            pre.next = head;
            pre = pre.next;
            if(head != null) head = head.next;
        }

        return tmp.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode tmp = pre;
        int cot = 0;
        while(head != null){
            cot++;
            head = head.next;
        }
        int real = cot - n;

        int count = 0;
        while(pre.next != null){
            if(count != real){
                pre = pre.next;
                count++;
            }
            else {
                pre.next = pre.next.next;
                count++;
            }
        }

        return tmp.next;
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public String decodeString(String s) {
        int len = s.length();
        int index = 0;
        LinkedList<String> string_list = new LinkedList<>();
        while(index < len){
            if(Character.isDigit(s.charAt(index))){
                StringBuilder tmp = new StringBuilder();
                while(Character.isDigit(s.charAt(index))){
                    tmp.append(s.charAt(index));
                    index++;
                }
                string_list.addLast(tmp.toString());
            }
            else if(Character.isLetter(s.charAt(index)) || s.charAt(index) == '['){
                string_list.addLast(String.valueOf(s.charAt(index)));
                index++;
            }
            else {
                index++;
                LinkedList<String> tmp = new LinkedList<>();
                while(!string_list.isEmpty() && !string_list.peekLast().equals("[")){
                    tmp.addLast(string_list.removeLast());
                }

                Collections.reverse(tmp);
                string_list.removeLast();

                StringBuilder newstring = new StringBuilder();
                int count = Integer.parseInt(string_list.removeLast());
                String o = getString(tmp);//convert list to string;
                while(count > 0){
                    newstring.append(o);
                    count--;
                }

                string_list.addLast(newstring.toString());
            }
        }

        return getString(string_list);
    }

    public String getString(LinkedList<String> ls){
        StringBuilder tmp = new StringBuilder();
        for(String s : ls){
            tmp.append(s);
        }
        return tmp.toString();
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }

        return pre;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        //get the node behind the leftnode(pre) and the leftnode;
        ListNode pre = dummy;
        for(int i = 0; i < left - 1; i++){
            pre = pre.next;
        }
        ListNode leftnode = pre.next;

        //get the rightnode and the node after the rightnode
        ListNode rightnode = pre.next;
        for(int i = 0; i < right - left; i++){
            rightnode = rightnode.next;
        }
        ListNode after = rightnode.next;

        //cut the list,in order to reverse and joint.
        pre.next = null;
        rightnode.next = null;

        reverseList(leftnode);
        pre.next = rightnode;
        leftnode.next = after;


        return dummy.next;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return head;
        ListNode pre = new ListNode(-1);
        pre.next = head;
        int count = 1;
        ListNode tmp = head;
        while(tmp.next != null){
            count++;
            tmp = tmp.next;
        }
        int remain = k % count;
        if(remain == 0 || count == 1) return head;
        tmp.next = head;

        int move_forward = count - remain;

        for(int i = 0; i < move_forward; i++){
            pre = pre.next;
        }

        ListNode newhead = pre.next;
        pre.next = null;


        return newhead;
    }

    public ListNode swapPairs(ListNode head) {
        //递归！！！！！！！！！！！！
        if(head == null || head.next == null) return head;

        ListNode newhead = head.next;
        head.next = swapPairs(newhead.next);
        newhead.next = head;

        return newhead;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        ListNode dummy = res;

        int carry = 0;
        while(l1 != null || l2 != null){
            int tmp = 0;
            tmp += carry;
            tmp += l1 == null ? 0 : l1.val;
            tmp += l2 == null ? 0 : l2.val;

            carry = tmp / 10;
            tmp = tmp % 10;

            res.next = new ListNode(tmp);
            res = res.next;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }

        if(carry > 0) res.next = new ListNode(carry);

        return dummy.next;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode res = new ListNode(-1);
        ListNode dummy = res;

        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                res.next = new ListNode(list1.val);
                res = res.next;
                list1 = list1.next;
            }
            else {
                res.next = new ListNode(list2.val);
                res = res.next;
                list2 = list2.next;
            }
        }

        while(list1 != null){
            res.next = new ListNode(list1.val);
            res = res.next;
            list1 = list1.next;
        }

        while(list2 != null){
            res.next = new ListNode(list2.val);
            res = res.next;
            list2 = list2.next;
        }

        return dummy.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        while(l1 != null || l2 != null){
            if(l1 != null){
                s1.push(l1.val);
                l1 = l1.next;
            }
            if(l2 != null){
                s2.push(l2.val);
                l2 = l2.next;
            }
        }

        ListNode res = null;
        int carry = 0;
        while(!s1.isEmpty() || !s2.isEmpty()){
            int tmp = 0;
            tmp += carry;
            tmp += s1.isEmpty() ? 0 : s1.pop();
            tmp += s2.isEmpty() ? 0 : s2.pop();
            carry = tmp / 10;
            tmp = tmp % 10;
            ListNode cur = new ListNode(tmp);
            cur.next = res;
            res = cur;
        }
        if(carry > 0){
            ListNode cur = new ListNode(carry);
            cur.next = res;
            res = cur;
        }

        return res;
    }

    public int longestConsecutive(int[] nums) {
        if(nums.length <= 1){
            return nums.length;
        }

        Set<Integer> set = new HashSet<>();
        for(int a : nums){
            set.add(a);
        }

        int max = Integer.MIN_VALUE;

        for(int num : nums){
            if(!set.contains(num-1)){
                int count = 1;
                int cur = num;
                while(set.contains(cur+1)){
                    count++;
                    cur++;
                }

                max = Math.max(max,count);
            }
        }

        return max;
    }

    public boolean judgeSquareSum(int c) {
        int left = 0;
        int right = (int)Math.sqrt(c);

        while (left <= right){
            long sum = left*left + (long)right*right;
            if(sum == c) return true;
            else if(sum > c) right--;
            else left++;
        }

        return false;
    }

    public String fractionToDecimal(int numerator, int denominator) {
        long numeratorLong = (long) numerator;
        long denominatorLong = (long) denominator;
        if (numeratorLong % denominatorLong == 0) {
            return String.valueOf(numeratorLong / denominatorLong);
        }

        StringBuffer sb = new StringBuffer();
        if (numeratorLong < 0 ^ denominatorLong < 0) {
            sb.append('-');
        }

        // 整数部分
        numeratorLong = Math.abs(numeratorLong);
        denominatorLong = Math.abs(denominatorLong);
        long integerPart = numeratorLong / denominatorLong;
        sb.append(integerPart);
        sb.append('.');

        // 小数部分
        StringBuffer fractionPart = new StringBuffer();
        Map<Long, Integer> remainderIndexMap = new HashMap<Long, Integer>();
        long remainder = numeratorLong % denominatorLong;
        int index = 0;
        while (remainder != 0 && !remainderIndexMap.containsKey(remainder)) {
            remainderIndexMap.put(remainder, index);
            remainder *= 10;
            fractionPart.append(remainder / denominatorLong);
            remainder %= denominatorLong;
            index++;
        }
        if (remainder != 0) { // 有循环节
            int insertIndex = remainderIndexMap.get(remainder);
            fractionPart.insert(insertIndex, '(');
            fractionPart.append(')');
        }
        sb.append(fractionPart.toString());

        return sb.toString();
    }

    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        int front = 0;
        int end = numbers.length - 1;

        while(front < end){
            if(numbers[front] + numbers[end] == target){
                res[0] = front + 1;
                res[1] = end + 1;
                break;
            }
            else if(numbers[front] + numbers[end] < target){
                front++;
            }
            else {
                end--;
            }
        }

        return res;
    }

    public int findPairs(int[] nums, int k) {
        int len = nums.length;
        Set<Integer> visited = new HashSet<>();
        Set<Integer> res = new HashSet<>();

        for(int a : nums){
            if(visited.contains(a - k)){
                res.add(a-k);
            }
            if(visited.contains(a + k)){
                res.add(a);
            }

            visited.add(a);
        }



        return res.size();

    }

    /**
    * 也可以直接用hashmap存老节点和新节点，再二次遍历设置新节点的指针。
    * */
    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }

        if(!cachemap.containsKey(head)){
            Node headnew = new Node(head.val);
            cachemap.put(head,headnew);
            headnew.next = copyRandomList(head.next);
            headnew.random = copyRandomList(head.random);
        }

        return cachemap.get(head);
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        int len = nums.length;
        for(int i = 0; i < len; i++){
            if(map.containsKey(nums[i])){
                if(Math.abs(i - map.get(nums[i])) <= k){
                    return true;
                }
                map.put(nums[i],i);
            }
            else {
                map.put(nums[i],i);
            }
        }

        return false;
    }

    /**
     * HashMAP 记录最多的缝隙，最后总数 - 缝隙 就是穿过的砖的数量
     */
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (List<Integer> widths : wall) {
            int n = widths.size();
            int sum = 0;
            for (int i = 0; i < n - 1; i++) {
                sum += widths.get(i);
                cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
            }
        }
        int maxCnt = 0;
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            maxCnt = Math.max(maxCnt, entry.getValue());
        }
        return wall.size() - maxCnt;
    }

    //双指针 + 剪枝
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int len = nums.length;
        List<List<Integer>> res = new LinkedList<>();
        if(nums == null || len < 4) return res;
        Arrays.sort(nums);
        int first = 0;
        int second = 0;

        for(int i = 0; i < len - 3; i++){
            if((long)nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target) break;
            if((long)nums[i] + nums[len-1] + nums[len-2] + nums[len-3] < target) continue;
            if(i > 0 && nums[i] == nums[i-1]) continue;

            for(int j = i + 1; j < len - 2; j++){
                if((long)nums[i] + nums[j] + nums[j+1] + nums[j+2] > target) break;
                if((long)nums[i] + nums[j] + nums[len-1] + nums[len-2] < target) continue;
                if(j > i + 1 && nums[j] == nums[j-1]) continue;

                first = i;
                second = j;
                int front = j + 1;
                int end = len - 1;

                while(front < end){
                    long sum = (long) nums[first] + nums[second] + nums[front] + nums[end];
                    if(sum == target){
                        res.add(Arrays.asList(nums[first],nums[second],nums[front],nums[end]));
                        while (front < end && nums[front] == nums[front + 1]) {
                            front++;
                        }
                        front++;
                        while (front < end && nums[end] == nums[end - 1]) {
                            end--;
                        }
                        end--;
                    }
                    else if(sum > target){
                        end--;
                    }
                    else {
                        front++;
                    }

                }
            }

        }

        return res;
    }

    //Use HashMap, traverse the first two arrays and sum up, put the results in the map
    //Traverse the remained two arrays and use get function(O(1)) in hashmap to find.
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer,Integer> map = new HashMap<>();
        int res = 0;
        for(int num1 : nums1){
            for(int num2 : nums2){
                int tmp = num1 + num2;
                map.put(tmp,map.getOrDefault(tmp,0) + 1);
            }
        }

        for(int num3 : nums3){
            for(int num4 : nums4){
                int tmp = num3 + num4;
                if(map.containsKey(-tmp)){
                    res += map.get(-tmp);
                }
            }
        }

        return res;
    }

    //one-dimensional DP
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(len <= 1) return 0;

        int[] dp = new int[len+1];
        dp[0] = 0;
        dp[1] = 0;

        int max = Integer.MIN_VALUE;
        for(int i = 2; i < len + 1; i++){
            dp[i] = Math.max(0, dp[i-1] + (prices[i-1] - prices[i - 2]));
            max = Math.max(max,dp[i]);
        }

        return max;
    }

    //two-dimensional DP
    public int maxProfit_two(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for(int i = 1; i < len; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }

        return dp[len - 1][0];
    }

    //Store the character in the hashmap;
    //judge odd or even.
    public int longestPalindrome2(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int len = s.length();
        for(int i = 0; i < len; i++){
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        }

        int res = 0;
        for(int i = 0; i < len; i++){
            if(map.containsKey(s.charAt(i)) && map.get(s.charAt(i)) % 2 == 0){
                res += map.get(s.charAt(i));
                map.remove(s.charAt(i));
            }
            else if(map.containsKey(s.charAt(i)) && map.get(s.charAt(i)) > 1){
                int tmp = map.get(s.charAt(i)) / 2;
                res += tmp*2;

                map.put(s.charAt(i),1);
            }
        }
        if(!map.isEmpty()) res++;

        return res;
    }


    //Store the prefix sum in the HashMap; Search for (prefix sum - k);
    //(From 0 to length)gi
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        int pre = 0, res = 0;
        map.put(0,1);

        for(int i = 0; i < len; i++){
            pre += nums[i];
            if(map.containsKey(pre- k)){
                res += map.get(pre-k);
            }

            map.put(pre,map.getOrDefault(pre,0)+1);
        }

        return res;
    }

    //Sort first, and then merge.
    public int[][] merge(int[][] intervals) {
        int len = intervals.length;
        if(len == 0) return new int[0][2];
        List<int[]> res = new ArrayList<>();

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for(int i = 0; i < len; i++){
            int left = intervals[i][0];
            int right = intervals[i][1];

            if(res.size() == 0 || left > res.get(res.size() - 1)[1]){
                res.add(new int[]{left,right});
            }
            else {
                int tmp = Math.max(right,res.get(res.size() - 1)[1]);
                res.get(res.size() - 1)[1] = tmp;

            }

        }

        return res.toArray(new int[res.size()][]);
    }


    //Greedy method for evaluating the relative position between two nums.
    //a+b > b+a, a  b; a+b < b+a b  a;
    //证明：若得到结果非最大，则有数字的相对顺序需要调换，但这与排序逻辑相悖。所以结果必然是最大。
    public String largestNumber(int[] nums) {
        int len = nums.length;
        String[] numsarr = new String[len];
        for(int i = 0; i < len; i++){
            numsarr[i] = ""+nums[i];
        }

        Arrays.sort(numsarr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String a = o1 + o2;
                String b = o2 + o1;

                return b.compareTo(a);
            }
        });

        StringBuilder res = new StringBuilder();
        for(String a : numsarr){
            res.append(a);
        }

        int index = 0;
        while(index < res.length() - 1 && res.charAt(index) == '0'){
            index++;
        }

        return res.substring(index);
    }

    //Greedy method + sorting based on the first item.
    public int findLongestChain(int[][] pairs) {
        int len = pairs.length;
        if(len <= 1) return len;

        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int max = 1;
        int index = 0;
        for(int i = 0; i < len - 1; i++){
            if(pairs[index][1] < pairs[i+1][0]){
                index = i + 1;
                max++;
            }
            else {
                int tmp_index = pairs[index][1] <= pairs[i+1][1] ? index : i + 1;
                index = tmp_index;
            }
        }

        return max;
    }


    //Greedy method + sorting based on the first item.
    public int eraseOverlapIntervals(int[][] intervals) {
        int len = intervals.length;
        if(len <= 1) return 0;

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int left = intervals[0][0];
        int right = intervals[0][1];

        int res = 0;
        for(int i = 1; i < len; i++){
            if(intervals[i][0] >= left && intervals[i][0] < right){
                res++;
                right = Math.min(right,intervals[i][1]);
            }
            else {
                left = intervals[i][0];
                right = intervals[i][1];
            }
        }

        return res;
    }

    //Simulation!!Remember to skip the waiting time and just traverse the tasks.
    public int leastInterval(char[] tasks, int n) {
        int len = tasks.length;
        if(len <= 1) return len;

        Map<Character,Integer> freq_map = new HashMap<>();
        for(int i = 0; i < len; i++){
            freq_map.put(tasks[i],freq_map.getOrDefault(tasks[i],0) + 1);
        }

        int size = freq_map.size();

        List<Integer> validTime = new ArrayList<>();
        List<Integer> remain = new ArrayList<>();

        for(char a : freq_map.keySet()){
            validTime.add(1);
            remain.add(freq_map.get(a));
        }

        int res = 0;
        for(int i = 0; i < len; i++){
            res++;
            //skip the waiting time
            int minNext = Integer.MAX_VALUE;
            for(int j = 0; j < size; j++){
                if(remain.get(j) != 0){
                    minNext = Math.min(minNext,validTime.get(j));
                }
            }
            res = Math.max(res,minNext);

            int index = -1;
            for(int j = 0; j < size; j++){
                if(remain.get(j) != 0 && validTime.get(j) <= res){
                    if(index == -1 || remain.get(j) > remain.get(index)){
                        index = j;
                    }
                }
            }

            remain.set(index,remain.get(index) - 1);
            validTime.set(index,res + n + 1);
        }

        return res;
    }

    //Two stacks, one for ( and one for *;
    public boolean checkValidString(String s) {
        int len = s.length();
        Stack<Integer> left = new Stack<>();
        Stack<Integer> symbol = new Stack<>();

        for(int i = 0; i < len; i++){
            if(s.charAt(i) == '(') left.push(i);
            else if(s.charAt(i) == '*') symbol.push(i);
            else {
                if(!left.isEmpty()) left.pop();
                else if(!symbol.isEmpty()) symbol.pop();
                else return false;
                }
        }
        while(!left.isEmpty() && !symbol.isEmpty()){
            if(left.peek() < symbol.peek()){
                left.pop();
                symbol.pop();
            }
            else return false;
        }

        return left.isEmpty();
    }

    //move 0 to the front, and then move 1 to the front.
    //use pointer to determine where to move.
    public void sortColors(int[] nums) {
        int left = 0;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                int tmp = nums[left];
                nums[left] = nums[i];
                nums[i] = tmp;
                left++;
            }
        }

        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 1 ){
                int tmp = nums[left];
                nums[left] = nums[i];
                nums[i] = tmp;
                left++;
            }
        }
    }

    //Quick Selection algorithm, based on quick sort.
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        int left = 0;
        int right = nums.length - 1;
        while(true){
            int par_num = partition(nums, left, right);
            if(par_num == len - k) return nums[par_num];
            else if(par_num < len - k){
                left = par_num + 1;
            }
            else {
                right = par_num - 1;
            }
        }
    }

    public int partition(int[] nums, int left, int right){
        int random_num = left + random.nextInt(right - left + 1);
        swap(nums,left,random_num);

        int prviot = nums[left];
        int front = left + 1;
        int end = right;
        while(true){
            while(front <= end && nums[front] <= prviot) front++;
            while(front <= end && nums[end] > prviot) end--;

            if(front > end) break;

            swap(nums, front, end);
            front++;
            end--;
        }
        swap(nums, left, end);

        return end;
    }
    public void swap(int[] nums, int a, int b){
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }


    //one-dimensional DP
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        if(len == 1) return nums[0];
        int[] dp = new int[len+1];
        dp[0] = nums[0];

        int res = dp[0];
        for(int i = 1; i < len; i++){
            dp[i] = Math.max(dp[i-1] + nums[i],nums[i]);
            res = Math.max(res,dp[i]);
        }

        return res;
    }

    //我们首先检查第 00 个加油站，并试图判断能否环绕一周；如果不能，就从第一个无法到达的加油站开始继续检查。
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0;
        while (i < n) {
            int sumOfGas = 0, sumOfCost = 0;
            int cnt = 0;
            while (cnt < n) {
                int j = (i + cnt) % n;
                sumOfGas += gas[j];
                sumOfCost += cost[j];
                if (sumOfCost > sumOfGas) {
                    break;
                }
                cnt++;
            }
            if (cnt == n) {
                return i;
            } else {
                i = i + cnt + 1;
            }
        }
        return -1;
    }

    //先找到升序和降序的边缘。
    //遍历中间数组，更新边缘值(当中间数组有<左边缘 or >右边缘)
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        if(len <= 1) return 0;

        int front = 0;
        int end = len - 1;

        while(front < end && nums[front] <= nums[front+1]) front++;
        while(front < end && nums[end] >= nums[end-1]) end--;
        int l = front, r = end;

        int min = nums[l], max = nums[r];

        for(int i = l; i <= r; i++){
            if(nums[i] < min){
                while(front >= 0 && nums[i] < nums[front]) front--;
                min = front >= 0 ? nums[front] : Integer.MIN_VALUE;
            }

            if(nums[i] > max){
                while(end < len && nums[i] > nums[end]) end++;
                max = end < len ? nums[end] : Integer.MAX_VALUE;
            }

        }

        return front == end ? 0 : (end-1) - (front+1) + 1;
    }


    //根据身高降序，身高相同第二个值升序。(让前面人多的尽量排在后面),排序后索引值就是前面比自己高的人的个数
    //当 当前位置 == people[i][1], 直接插入。 否则插入到people[i][1]的位置。
    public int[][] reconstructQueue(int[][] people) {
        int len = people.length;
        if(len <= 1) return people;

        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(!(o1[0] == o2[0])) return o2[0] - o1[0];
                else return o1[1] - o2[1];
            }
        });

        List<int[]> res = new ArrayList<>();

        for(int i = 0; i < len; i++){
            if(people[i][1] == i){
                res.add(people[i]);
            }
            else {
                res.add(people[i][1],people[i]);
            }
        }


        return res.toArray(new int[len][]);
    }

    //two-dimensiontal DP
    public int maxProduct(int[] nums) {
        int len = nums.length;
        if(len == 1) return nums[0];

        int[][] dp = new int[len][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        int max = dp[0][0];

        for(int i = 1; i < len; i++){
            if(nums[i] > 0){
                dp[i][0] = Math.max(dp[i-1][0] * nums[i], nums[i]);
                dp[i][1] = Math.min(dp[i-1][1] * nums[i], nums[i]);
            }
            else {
                dp[i][0] = Math.max(dp[i-1][1] * nums[i], nums[i]);
                dp[i][1] = Math.min(dp[i-1][0] * nums[i], nums[i]);
            }

            max = Math.max(dp[i][0],max);
        }

        return max;
    }


    //单调栈 + HashMap(从底到顶递减)
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int len = nums2.length;
        Map<Integer,Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for(int i = len - 1; i >= 0; i--){
            while(!stack.isEmpty() && stack.peek() <= nums2[i]) stack.pop();

            map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());

            stack.push(nums2[i]);
        }

        int[] res = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++){
            res[i] = map.get(nums1[i]);
        }

        return res;
    }


    //维护单调递减的栈，栈内存index;
    //While:当前元素 > 栈顶时，找到了nextGreater,弹出; Otherwise push into the stack;
    //Traverse twice of the array because of the loop.
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        Arrays.fill(res,-1);
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < len * 2; i++){
            while(!stack.isEmpty() && nums[i%len] > nums[stack.peek()]){
                int tmp = stack.pop();
                res[tmp] = nums[i%len];
            }

            stack.push(i % len);
        }

        return res;
    }


    //Math, always break the Integer into several 3, and then add 2 or 4;
    public int integerBreak(int n) {
        if(n <= 3) return n -1;

        int tmp = n / 3;
        int remain = n % 3;

        if(remain == 0) return (int)Math.pow(3,tmp);
        else if(remain == 1) return (int)Math.pow(3,tmp-1) * 4;
        else return (int)Math.pow(3,tmp) * 2;
    }

    //双指针
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for(int i=0;i<nums.length;i++) {
            int start = i+1, end = nums.length - 1;
            while(start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if(Math.abs(target - sum) < Math.abs(target - ans))
                    ans = sum;
                if(sum > target)
                    end--;
                else if(sum < target)
                    start++;
                else
                    return ans;
            }
        }
        return ans;
    }

    //头尾指针
    public int maxArea(int[] height) {
        int len = height.length;
        int start = 0;
        int end = len - 1;


        int res = Integer.MIN_VALUE;
        while (start < end){
            int total = Math.min(height[start],height[end]) * (end - start);
            if(total > res) res = total;

            if(height[start] < height[end]) start++;
            else if(height[start] > height[end]) end--;
            else {
                start++;
                end--;
            }
        }

        return res;
    }

    //单调栈解法。
    //我们还遗漏了一个要求：原字符串 ss 中的每个字符都需要出现在新字符串中，且只能出现一次。为了让新字符串满足该要求，之前讨论的算法需要进行以下两点的更改。
    //
    //在考虑字符 s[i]s[i] 时，如果它已经存在于栈中，则不能加入字符 s[i]s[i]。为此，需要记录每个字符是否出现在栈中。
    //
    //在弹出栈顶字符时，如果字符串在后面的位置上再也没有这一字符，则不能弹出栈顶字符。为此，需要记录每个字符的剩余数量，当这个值为 00 时，就不能弹出栈顶字符了。
    public String removeDuplicateLetters(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int len = s.length();
        for(int i  = 0; i < len; i++){
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0) + 1);
        }

        Stack<Character> stack = new Stack<>();
        int[] is_contain = new int[26];
        for(int i = 0;i < len; i++){
            while(!stack.isEmpty() && stack.peek() > s.charAt(i)
                    && map.get(stack.peek()) > 1 && is_contain[s.charAt(i) - 'a'] == 0){
                char tmp = stack.pop();
                is_contain[tmp - 'a'] = 0;
                map.put(tmp,map.get(tmp) - 1);
            }

            if(is_contain[s.charAt(i) - 'a'] == 0){
                stack.push(s.charAt(i));
                is_contain[s.charAt(i) - 'a'] = 1;
            }
            else {
                map.put(s.charAt(i),map.get(s.charAt(i)) - 1);
            }


        }

        StringBuilder res = new StringBuilder();
        while(!stack.isEmpty()){
            res.append(stack.pop());
        }

        return res.reverse().toString();
    }


    //Two pointers, 0 and 1;
    public int removeDuplicates2(int[] nums) {
        int len = nums.length;
        if(len <= 1) return len;
        int first = 0;
        int second = 1;

        while(second < len){
            if(nums[first] != nums[second]){
                nums[first+1] = nums[second];
                first++;
            }

            second++;
        }

        return first + 1;
    }

    public int triangleNumber(int[] nums) {
        int len = nums.length;
        if(len < 3) return 0;

        Arrays.sort(nums);
        int res = 0;
        for(int i = 0; i < len - 2; i++){
            for(int j = i + 1; j < len - 1; j++){

                int second = j+1;

                while(second < len && nums[second] < nums[i] + nums[j]){
                    second++;
                }

                res += Math.max(second - (j+1),0);
            }

        }

        return res;
    }


    //create dummy node and dummy.next = head;
    //when cur.next.val == cur.next.next.val, 节点重复，删除所有 val == cur.next.val的节点;
    //traverse until to the end.
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;

        while(cur.next != null && cur.next.next != null){
            if(cur.next.val == cur.next.next.val){
                int tmp = cur.next.val;
                while(cur.next != null && cur.next.val == tmp){
                    cur.next = cur.next.next;
                }
            }
            else {
                cur = cur.next;
            }
        }

        return dummy.next;
    }


    //单调递增栈！
    public String removeKdigits(String num, int k) {
        int len = num.length();
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < len; i++){
            while(!stack.isEmpty() && stack.peek() > num.charAt(i) && k > 0){
                stack.pop();
                k--;
            }

            stack.push(num.charAt(i));
        }

        while(!stack.isEmpty() && k > 0){
            stack.pop();
            k--;
        }

        if(stack.isEmpty()) return "0";
        if(stack.size() == 1) return stack.pop().toString();
        StringBuilder res = new StringBuilder();
        while(!stack.isEmpty()){
            res.append(stack.pop());
        }

        res = res.reverse();
        int index = 0;
        while(index < res.length() - 1 && res.charAt(index) == '0') index++;

        return res.substring(index).toString();
    }

    //
    public int findLengthOfLCIS(int[] nums) {
        int len = nums.length;
        if(len < 2) return len;


        int start = 0, end = 1;
        int res = 0;
        int tmp = 1;
        while(end < len){
            if(nums[start] < nums[end]){
                start++;
                end++;
                tmp++;
            }
            else {
                res = Math.max(tmp,res);
                tmp = 1;
                start = end;
                end++;
            }
        }

        return Math.max(res,tmp);
    }


    //Two pointer both start from 0;
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        if(len < 2) return nums[0] >= target ? 1 : 0;

        int start = 0, end = 0;
        int tmp = nums[start];
        if(tmp >= target) return 1;
        int res = Integer.MAX_VALUE;
        while(start <= end && end < len){
            if(tmp >= target){
                int leng = end - start + 1;
                res = Math.min(leng,res);
                if(res == 1) return res;

                tmp -= nums[start];
                start++;
            }
            else {
                end++;
                if(end < len)  tmp += nums[end];
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }


    //Two pointers both starts from 0;
    public int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int len = s.length();
        if(len < 2) return len;

        int res = 0;
        int tmp = 0;
        int start = 0, end = 0;
        while(end < len){
            if(map.isEmpty() || !map.containsKey(s.charAt(end))){
                map.put(s.charAt(end),end);
                tmp++;
                end++;
            }
            else {
                res = Math.max(res,tmp);
                int index = map.get(s.charAt(end));
                while(start <= index){
                    map.remove(s.charAt(start));
                    start++;
                }
                tmp = end - start;
            }
        }

        return Math.max(res,tmp);
    }


    //固定滑动窗口大小为p.length;
    //使用数组存每个字符的出现次数;
    //初始把p的字符出现次数存入，只需要存一次;存入s的前p个字符并判断是否相同;
    //窗口起始点滑动，每次滑动加入末尾字符，删去起始字符，然后比较即可。
    public List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        if (sLen < pLen) {
            return new ArrayList<Integer>();
        }

        List<Integer> ans = new ArrayList<Integer>();
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < pLen; ++i) {
            ++sCount[s.charAt(i) - 'a'];
            ++pCount[p.charAt(i) - 'a'];
        }

        if (Arrays.equals(sCount, pCount)) {
            ans.add(0);
        }

        for (int i = 0; i < sLen - pLen; ++i) {
            --sCount[s.charAt(i) - 'a']; //删去起始字符
            ++sCount[s.charAt(i + pLen) - 'a']; //加入末尾字符

            //比较字符频率
            if (Arrays.equals(sCount, pCount)) {
                ans.add(i + 1);
            }
        }

        return ans;
    }

    //固定窗口长度 = s1.length(), 滑动！
    public boolean checkInclusion(String s1, String s2) {
        int s1len = s1.length();
        int s2len = s2.length();

        if(s2len < s1len) return false;

        int[] s1count = new int[26];
        int[] s2count = new int[26];

        for(int i  = 0; i < s1len; i++){
            s1count[s1.charAt(i) - 'a']++;
            s2count[s2.charAt(i) - 'a']++;
        }
        if(Arrays.equals(s1count,s2count)) return true;

        for(int i = 0; i < s2len - s1len; i++){
            s2count[s2.charAt(i) - 'a']--;
            s2count[s2.charAt(i+s1len) - 'a']++;

            if(Arrays.equals(s1count,s2count)) return true;
        }


        return false;
    }


    //建立2个链表，再把small.next = largeHead.next;
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }


    //保存奇节点末尾与偶节点头部，遍历修改.next = .next.next;
    //最后把奇节点末尾与偶节点头部拼接;
    public ListNode oddEvenList(ListNode head) {
        ListNode dummy = new ListNode(-1,head);
        int count = 1;


        ListNode tmp_odd = head;
        if(tmp_odd == null) return head;
        ListNode tmp_even = head.next;
        if(tmp_even == null) return head;
        while(head.next != null){
            ListNode tmp_node = head.next;
            head.next = head.next.next;
            head = tmp_node;
            count++;


            if(count % 2 != 0) {
                tmp_odd = head;
            }
        }

        tmp_odd.next = tmp_even;

        return dummy.next;
    }


    //end++, until end - start + 1 > maxCount + k; start++后更新res;
    //继续滑动右端点;
    public int characterReplacement(String s, int k) {
        int len = s.length();
        if(len < 2) return len;

        int[] freqs = new int[26];
        int start = 0, end = 0;
        int res = 0;
        int maxCount = 0;

        while (end < len){
            freqs[s.charAt(end) - 'A']++;
            maxCount = Math.max(maxCount, freqs[s.charAt(end) - 'A']);

            /**start++后，maxCount不需要更新，对结果不影响！！
             * 因为start++后的长度 = start++前且end++前的长度，而该长度满足len <= maxCount + k;
             * 除非出现一个比导致左端点移动的历史最大重复次数字符出现次数更大的字符，否则滑动窗口就会一直以当前长度一直滑动下去。
             * 所以maxCount只会保持不变和增长，减小是没有意义的，故不需要重新遍历滑动窗口维护。
             * **/
            if(end - start + 1 > maxCount + k){
                freqs[s.charAt(start) - 'A']--;
                start++;
            }

            res = Math.max(res, end - start + 1);
            end++;

        }

        return res;
    }

    public boolean isPalindrome(ListNode head) {
        List<Integer> vals = new ArrayList<Integer>();

        // 将链表的值复制到数组中
        ListNode currentNode = head;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }

        // 使用双指针判断是否回文
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }

    //Slow pointer and Fast pointer
    /**
     * 根据题意，任意时刻，\textit{fast}fast 指针走过的距离都为 \textit{slow}slow 指针的 2 倍。因此，我们有
     *
     * a+(n+1)b+nc=2(a+b) a=c+(n-1)(b+c)
     * a+(n+1)b+nc=2(a+b)⟹a=c+(n−1)(b+c)
     *
     * 有了 a=c+(n-1)(b+c)a=c+(n−1)(b+c) 的等量关系，我们会发现：从相遇点到入环点的距离加上 n-1n−1 圈的环长，恰好等于从链表头部到入环点的距离。
     *
     * 因此，当发现 \textit{slow}slow 与 \textit{fast}fast 相遇时，我们再额外使用一个指针 \textit{ptr}ptr。
     * 起始，它指向链表头部；随后，它和 \textit{slow}slow 每次向后移动一个位置。最终，它们会在入环点相遇。
     *
     */
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) break;
        }

        if(slow == fast){
            slow = head;
            while(slow != fast){
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
        else {
            return null;
        }

    }


    //Similar to find the loop in the linked-list!;
    /**
     * 我们对 nums 数组建图，每个位置 i 连一条 i → nums[i] 的边。
     * 由于存在的重复的数字 \textit{target}target，因此 \textit{target}target 这个位置一定有起码两条指向它的边，
     * 因此整张图一定存在环，且我们要找到的 \textit{target}target 就是这个环的入口，那么整个问题就等价于 142. 环形链表 II。
     */
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;

        slow = nums[slow];
        fast = nums[nums[fast]];

        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = 0;
        while (slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }


    //先找到中间节点，后半部分reverse，再依次拼接head与reversehead!
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;
        l2 = reverseList2(l2);
        mergeList(l1, l2);
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.next;
            l2_tmp = l2.next;

            l1.next = l2;
            l1 = l1_tmp;

            l2.next = l1;
            l2 = l2_tmp;
        }
    }


    //Use recursion!
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;

        return p.val == q.val && isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }

    //Recursion for the binary tree!
    public boolean isSymmetric(TreeNode root) {
        TreeNode p = root;
        TreeNode q = root;

        return isSymmetric_help(p,q);
    }
    public boolean isSymmetric_help(TreeNode p, TreeNode q){
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val != q.val) return false;

        return isSymmetric_help(p.left,q.right) &&  isSymmetric_help(p.right,q.left);
    }

    //Recursion for the binary tree
    public TreeNode invertTree(TreeNode root) {
/*      if(root == null) return null;

        TreeNode tmp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(tmp);

        return root;*/
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    //Recursion for the binary tree! Length = MaxDepth(left) + MaxDepth(right) + 1
    //Recursion for every node and calculate the Length!
    public int diameterOfBinaryTree(TreeNode root) {
        treenode_ans = 1;
        diameter_help(root);
        return treenode_ans - 1;
    }
    public int diameter_help(TreeNode root){
        if(root == null) return 0;

        int L = diameter_help(root.left);
        int R = diameter_help(root.right);

        treenode_ans = Math.max(treenode_ans,L+R+1);
        return Math.max(L,R)+1;
    }

    //Recursion for the binary tree!
    //利用完全二叉树性质可以给节点编号，编号的二进制可以代表到达节点的路径，判断最后一层的最大节点编号可以得到整个树的节点个数！！
    //在最后一层根据节点编号范围使用二分查找，根据路径判断节点是否为NULL.
    public int countNodes(TreeNode root) {
        if(root == null) return 0;

        int L = countNodes(root.left);
        int R = countNodes(root.right);

        return L + R + 1;
    }

    //先入队列，记住队列长度，然后poll出队列长度的Node就是这一层所有的node！
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> tmp_list = new LinkedList<>();
            for(int i = 0; i < size; i++){
                TreeNode tmp = queue.poll();
                tmp_list.add(tmp.val);
                if(tmp.left != null) queue.offer(tmp.left);
                if(tmp.right != null) queue.offer(tmp.right);
            }
            res.add(tmp_list);
        }


        return res;
    }

    //用Pair<k,v>数据结构，给节点编号；
    //注意溢出问题！
    public int widthOfBinaryTree(TreeNode root) {
        long res = 0;
        if(root == null) return 0;

        Queue<AbstractMap.SimpleEntry<TreeNode,Long>> queue = new LinkedList<AbstractMap.SimpleEntry<TreeNode,Long>>();

        queue.offer(new AbstractMap.SimpleEntry<>(root,1L));

        while(!queue.isEmpty()){
            int size = queue.size();
            long min = 0;
            long max = 0;
            boolean flag = false;
            for(int i = 0; i < size; i++){
                AbstractMap.SimpleEntry<TreeNode,Long> tmp_pair = queue.poll();
                if(flag == false){
                    min = tmp_pair.getValue();
                    flag = true;
                }
                max = Math.max(max,tmp_pair.getValue());
                if(tmp_pair.getKey().left != null) {
                    queue.offer(new AbstractMap.SimpleEntry<>(tmp_pair.getKey().left, tmp_pair.getValue()*2));
                }
                if(tmp_pair.getKey().right != null){
                    queue.offer(new AbstractMap.SimpleEntry<>(tmp_pair.getKey().right, tmp_pair.getValue()*2+1));
                }
            }

            res = Math.max(res,max-min+1);
        }

        return (int)res;
    }

    //入队列，先右再左，保证最后一个节点就是最后一层最左边的节点！！
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int res = root.val;
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode tmp = queue.poll();
            res = tmp.val;
            if(tmp.right != null) queue.offer(tmp.right);
            if(tmp.left != null) queue.offer(tmp.left);


        }

        return res;
    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if(root == null) return res;

        int countLevel = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> tmpList = new LinkedList<>();
            for(int i = 0; i < size; i++){
                TreeNode tmp = queue.poll();
                tmpList.add(tmp.val);
                if(tmp.left != null) queue.offer(tmp.left);
                if(tmp.right != null) queue.offer(tmp.right);
            }
            if(countLevel % 2 == 0) Collections.reverse(tmpList);
            countLevel++;
            res.add(tmpList);
        }

        return res;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> tmp_list = new LinkedList<>();
            for(int i = 0; i < size; i++){
                TreeNode tmp = queue.poll();
                tmp_list.add(tmp.val);
                if(tmp.left != null) queue.offer(tmp.left);
                if(tmp.right != null) queue.offer(tmp.right);
            }
            res.add(0,tmp_list);
        }

        return res;
    }

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        Queue<TreeNode> queue = new LinkedList<>();
        int countLevel = 1;
        TreeNode res = new TreeNode();
        if(depth == 1){
            res.val = val;
            res.left = root;
            return res;
        }

        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i = 0; i < size; i++){
                if(countLevel < depth - 1){
                    TreeNode tmp = queue.poll();
                    if(tmp.left != null) queue.offer(tmp.left);
                    if(tmp.right != null) queue.offer(tmp.right);
                }
                else if(countLevel == depth - 1){
                    TreeNode tmp = queue.poll();
                    TreeNode Left = tmp.left;
                    TreeNode Right = tmp.right;
                    TreeNode newLeft = new TreeNode(val);
                    TreeNode newRight = new TreeNode(val);
                    tmp.left = newLeft;
                    tmp.right = newRight;
                    newLeft.left = Left;
                    newRight.right = Right;

                }
                else{
                    return root;
                }
            }
            countLevel++;
        }

        return root;
    }

    public int maxDepth(TreeNode root) {
        if(root == null) return 0;

        int maxleft = maxDepth(root.left);
        int maxright = maxDepth(root.right);

        return Math.max(maxleft,maxright) + 1;
    }

    /**
     * 另外这道题的关键是搞清楚递归结束条件
     *
     * 叶子节点的定义是左孩子和右孩子都为 null 时叫做叶子节点
     * 当 root 节点左右孩子都为空时，返回 1
     * 当 root 节点左右孩子有一个为空时，返回不为空的孩子节点的深度
     * 当 root 节点左右孩子都不为空时，返回左右孩子较小深度的节点值
     * :叶子节点是指没有子节点的节点，这句话的意思是仅当left,right 都为null时才算数。！！
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。!
     */
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;

        int minleft = minDepth(root.left);
        int minright = minDepth(root.right);

        if(root.left == null || root.right == null) return minleft + minright;

        return Math.min(minleft,minright) + 1;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode tmp = queue.poll();
                if(i == 0) res.add(tmp.val);
                if(tmp.right != null) queue.offer(tmp.right);
                if(tmp.left != null) queue.offer(tmp.left);
            }

        }


        return res;
    }

    //recursion preorder
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();

        preorderHelp(res, root);

        return res;
    }
    public void preorderHelp(List<Integer> res, TreeNode root){
        if(root == null) return;
        res.add(root.val);
        preorderHelp(res, root.left);
        preorderHelp(res, root.right);
    }


    //iteration preorder, use stack push root, push left, push right;
    public List<Integer> preorderTraversal_iter(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return res;
    }

    //递归实现，递归传递上层结果，在本层新建Stringbuilder，append上层结果 --> 防止重复叠加已有node！！
    public int sumNumbers(TreeNode root) {
        StringBuilder res = new StringBuilder();

        int nums = sumHelp(res, root);
        return nums;
    }
    public int sumHelp(StringBuilder res, TreeNode root){
        if(root == null) return 0;

        StringBuilder tmp = new StringBuilder(res);
        tmp.append(root.val);

        if(root.left == null && root.right == null){
            return Integer.parseInt(tmp.toString());
        }
        else {
            return sumHelp(tmp, root.left) + sumHelp(tmp, root.right);
        }

    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        postorder_help(res, root);
        return res;
    }
    public void postorder_help(List<Integer> res, TreeNode root){
        if(root == null) return;

        postorder_help(res, root.left);
        postorder_help(res, root.right);
        res.add(root.val);
    }


    //postorder iteration, add left, add right, add root;
    //use prev to record the previous node!
    //中序遍历中，从栈中弹出的节点，其左子树是访问完了，可以直接访问该节点，然后接下来访问右子树。
    //后序遍历中，从栈中弹出的节点，我们只能确定其左子树肯定访问完了，但是无法确定右子树是否访问过。
    //因此，我们在后序遍历中，引入了一个prev来记录历史访问记录。
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            //现在需要确定的是是否有右子树，或者右子树是否访问过
            //如果没有右子树，或者右子树访问完了，也就是上一个访问的节点是右子节点时
            //说明可以访问当前节点
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                //更新历史访问记录，这样回溯的时候父节点可以由此判断右子树是否访问完成
                prev = root;
                root = null;
            } else {
                //如果右子树没有被访问，那么将当前节点压栈，访问右子树
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }



    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        inorder_help(res, root);
        return res;
    }
    public void inorder_help(List<Integer> res, TreeNode root){
        if(root == null) return;

        inorder_help(res, root.left);
        res.add(root.val);
        inorder_help(res, root.right);
    }

    //inorder iteration!
    //
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<TreeNode> stk = new LinkedList<TreeNode>();
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }

            // 此时root==null，说明上一步的root没有左子树
            // 1. 执行左出栈。因为此时root==null，导致root.right一定为null
            // 2. 执行下一次外层while代码块，根出栈。此时root.right可能存在
            // 3a. 若root.right存在，右入栈，再出栈
            // 3b. 若root.right不存在，重复步骤2
            root = stk.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }


    //二叉搜索树，root > 左子树最大， root < 右子树最小. Recursion!!
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        if(root.left == null && root.right == null) return true;

        if(root.left == null){
            return isValidBST(root.right) && root.val < finSmallest(root.right);
        }

        if(root.right == null){
            return isValidBST(root.left) && root.val > findLargest(root.left);
        }

        return isValidBST(root.left) && isValidBST(root.right)
                && root.val < finSmallest(root.right) && root.val > findLargest(root.left);
    }
    public int findLargest(TreeNode root){
        while(root != null && root.right != null){
            root = root.right;
        }
        return root.val;
    }
    public int finSmallest(TreeNode root){
        while(root != null && root.left != null){
            root = root.left;
        }
        return root.val;
    }

    //利用二叉树性质二分查找节点
    //找到之后根据节点是否有左右子树分别处理，若左右子树都存在 则将左子树放到右子树最小节点下面。
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if(root.val < key) root.left = deleteNode(root.left, key);
        else if(root.val > key) root.right = deleteNode(root.right, key);
        else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            TreeNode t = root.right;
            while (t.left != null) t = t.left;
            t.left = root.left;

            return root.right;
        }
        return root;
    }


    //找到节点不满足就删除，删除后接着递归的找，有点笨.
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root == null) return null;
        if(root.val >= low && root.val <= high){
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
        }
        else {
            if(root.left == null && root.right == null){
                root = null;
            }
            else if(root.left == null){
                root = root.right;
                root = trimBST(root, low, high);

            }
            else if(root.right == null){
                root = root.left;
                root = trimBST(root, low, high);
            }
            else{
                TreeNode tmp = root.right;
                while(tmp.left != null) tmp = tmp.left;
                tmp.left = root.left;
                root = root.right;
                root = trimBST(root, low, high);

            }
        }

        return root;
    }

    //clever solution
    //root < low,则root和左子树都要删除。 root > high, 则root和右子树都要删除。
    public TreeNode trimBST2(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        } else if (root.val > high) {
            return trimBST(root.left, low, high);
        } else {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }

    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        if(Math.abs(getDepth(root.left) - getDepth(root.right)) > 1) return false;

        return isBalanced(root.left) && isBalanced(root.right);
    }
    public int getDepth(TreeNode root){
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;

        int left = getDepth(root.left);
        int right = getDepth(root.right);

        return Math.max(left,right) + 1;
    }


    //总选择中间的数作为根节点，遍历;
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 总是选择中间位置左边的数字作为根节点
        int mid = (left + right) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }


    //递归+回溯！！
    public List<TreeNode> generateTrees(int n) {
        return generate_help(1, n);
    }
    public List<TreeNode> generate_help(int start, int end){
        List<TreeNode> allTrees = new LinkedList<>();
        if(start > end){
            allTrees.add(null);
            return allTrees;
        }

        for(int i = start; i <= end; i++){
            List<TreeNode> leftlist = generate_help(start, i - 1);
            List<TreeNode> rightlist = generate_help(i + 1, end);

            for(TreeNode left : leftlist){
                for(TreeNode right : rightlist){
                    //obj类型，每次赋值要新建，不然赋值会覆盖！！;
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    allTrees.add(root);
                }
            }
        }

        return allTrees;
    }

    //根据前序找到根，根据根在中序位置确定左右子树个数
    //递归构造左右子树！！
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < len; i++){
            map.put(inorder[i], i);
        }

        return buildTree_help(map, preorder,0, len-1, inorder,0, len-1);
    }
    public TreeNode buildTree_help(Map<Integer, Integer> map, int[] preorder,
                                   int preLeft, int preRight, int[] inorder, int inLeft, int inRight){
        if (preLeft > preRight) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preLeft]);

        int pIndex = map.get(root.val);

        root.left = buildTree_help(map, preorder, preLeft+1, pIndex-inLeft+preLeft, inorder, inLeft, pIndex - 1);
        root.right = buildTree_help(map, preorder, pIndex-inLeft+preLeft+1, preRight, inorder, pIndex+1, inRight);

        return root;
    }

    //same logic as the previous one.
    //Recursion!!!
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        int len = postorder.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < len; i++){
            map.put(inorder[i], i);
        }

        return buildTree_help2(map,postorder,0,len-1,inorder,0,len-1);
    }
    public TreeNode buildTree_help2(Map<Integer, Integer> map, int[] postorder,
                                   int postLeft, int postRight, int[] inorder, int inLeft, int inRight){
        if(postLeft > postRight) return null;

        TreeNode root = new TreeNode(postorder[postRight]);
        int pIndex = map.get(root.val);

        root.left = buildTree_help2(map, postorder, postLeft, pIndex-inLeft+postLeft-1, inorder, inLeft, pIndex - 1);
        root.right = buildTree_help2(map, postorder, pIndex-inLeft+postLeft, postRight - 1, inorder, pIndex+1, inRight);

        return root;
    }


    //前序遍历结果保存在list里面，然后遍历list得到cur和next，cur.left=null,cur.right = next;
    public void flatten(TreeNode root) {
        List<TreeNode> list = new LinkedList<>();
        inorderTra(root, list);

        int len = list.size();
        for(int i = 0; i < len -1; i++){
            TreeNode cur = list.get(i);
            TreeNode next = list.get(i+1);

            cur.left = null;
            cur.right = next;
        }
    }

    public void inorderTra(TreeNode root, List<TreeNode> list){
        if(root == null) return;
        list.add(root);
        inorderTra(root.left, list);
        inorderTra(root.right, list);
    }


    //Recursion!!
    // 定义F(x) 表示 x 节点的子树中是否包含 p 节点或 q 节点，如果包含为 true，否则为 false。
    // 代码中lson和rson就是F(x)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        commonAncestor_help(root, p, q);

        return this.ans;
    }
    public boolean commonAncestor_help(TreeNode root, TreeNode p, TreeNode q){
        if(root == null) return false;

        boolean lson = commonAncestor_help(root.left, p, q);
        boolean rson = commonAncestor_help(root.right, p, q);

        if((lson && rson) || (root.val == p.val || root.val == q.val) &&(lson || rson)){
            ans = root;
        }

        return lson || rson || root.val == p.val || root.val == q.val;
    }

    //利用搜索树性质找到P、Q节点并记录路径，路径上最后一个公共节点就是最近公共祖先！！
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathp = getPath(root, p);
        List<TreeNode> pathq = getPath(root, q);

        TreeNode res = null;
        int len = Math.min(pathp.size(), pathq.size());
        for(int i = 0; i < len; i++){
            if(pathp.get(i) == pathq.get(i)) res = pathp.get(i);
            else break;
        }
        return res;
    }
    public List<TreeNode> getPath(TreeNode root, TreeNode node){
        List<TreeNode> res = new LinkedList<>();
        while(root != node){
            res.add(root);
            if(root.val < node.val) root = root.right;
            else if(root.val > node.val) root = root.left;
        }

        res.add(root);
        return res;
    }

    //利用搜索树中序遍历递增的性质，找到错误的两个节点，然后将节点的值互换！;
    //搜索树不存在重复值;
    public void recoverTree(TreeNode root) {
        List<Integer> nums = new ArrayList<Integer>();
        inorder(root, nums);
        int[] swapped = findTwoSwapped(nums);
        recover(root, 2, swapped[0], swapped[1]);
    }
    public void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) {
            return;
        }
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }
    public int[] findTwoSwapped(List<Integer> nums) {
        int n = nums.size();
        int index1 = -1, index2 = -1;
        for (int i = 0; i < n - 1; ++i) {
            if (nums.get(i + 1) < nums.get(i)) {
                index2 = i + 1;
                if (index1 == -1) {
                    index1 = i;
                } else {
                    break;
                }
            }
        }
        int x = nums.get(index1), y = nums.get(index2);
        return new int[]{x, y};
    }
    public void recover(TreeNode root, int count, int x, int y) {
        if (root != null) {
            if (root.val == x || root.val == y) {
                root.val = root.val == x ? y : x;
                if (--count == 0) {
                    return;
                }
            }
            recover(root.right, count, x, y);
            recover(root.left, count, x, y);
        }
    }


    //DFS深搜，遍历数组，从 i 向nums[i] 连边，我们可以得到一张有向图。
    //记录visited，由于图必然有环，前一次访问过的节点不用再次访问因为长度必然小于前一次！
    public int arrayNesting(int[] nums) {
        int res = 0;
        int len = nums.length;
        boolean[] visited = new boolean[len];
        for(int i = 0; i < len; i++){
            int count = 0;
            while(!visited[i]){
                visited[i] = true;
                count++;
                i = nums[i];
            }

            res = Math.max(res, count);
        }

        return res;
    }

    //回溯算法用于寻找所有的可行解，如果发现一个解不可行，则会舍弃不可行的解。(回溯基于递归实现)
    //在这道题中，由于每个数字对应的每个字母都可能进入字母组合，因此不存在不可行的解，直接穷举所有的解即可。
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }
    public void backtrack(List<String> combinations,Map<Character, String> phoneMap
            ,String digits, int index, StringBuffer combination){
        if(index == digits.length()){
            combinations.add(combination.toString());
        }
        else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }


    //回溯 + 剪枝，当长度不足够到达k时 直接返回;
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> tmplist = new LinkedList<>();

        combine_help(res, tmplist, 1, n, k);
        return res;
    }
    public void combine_help(List<List<Integer>> res, List<Integer> tmplist, int begin, int n, int k){
        if(tmplist.size() == k){
            res.add(new LinkedList<>(tmplist));
            return;
        }

        if(n - begin + 1 + tmplist.size() < k) return;

        for(int i = begin; i <= n; i++){
            tmplist.add(i);
            combine_help(res, tmplist, i+1, n, k);
            tmplist.remove(tmplist.size()-1);
        }
    }

    //由于数字是1 - 9 所以可以使用数组代替哈希表，遍历统计 行 列 3*3格子;
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] subboxes = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '0' - 1;
                    rows[i][index]++;
                    columns[j][index]++;
                    //用二维数组代表小方格; 用i/3 j/3标识;
                    subboxes[i / 3][j / 3][index]++;
                    if (rows[i][index] > 1 || columns[j][index] > 1 || subboxes[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }


    //回溯+剪枝！！
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> tmplist = new LinkedList<>();
        int len = candidates.length;
        Arrays.sort(candidates);
        combinationSum_help(0,len, candidates, target, tmplist, res);
        return res;
    }
    public void combinationSum_help(int begin, int len, int[] candidates, int target, List<Integer> tmplist, List<List<Integer>> res){
        if(target < 0){
            return;
        }
        if(target == 0){
            res.add(new LinkedList<>(tmplist));
            return;
        }

        for(int i = begin; i < len; i++){
            if(target - candidates[i] < 0) break;
            tmplist.add(candidates[i]);
            combinationSum_help(i,len, candidates, target-candidates[i],tmplist,res);
            tmplist.remove(tmplist.size()-1);
        }
    }


    //回溯法遍历，用visited数组避免遍历已遍历过的元素！;
    //也可以不用visited数组，将遍历过的元素放在左边，未遍历的放在右边，回溯的时候只遍历右边元素;
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> tmplist = new LinkedList<>();

        int len = nums.length;
        if(len == 0) return res;
        boolean[] visited = new boolean[len];

        permute_help(0,len,visited,tmplist,res,nums);
        return res;
    }
    public void permute_help(int index, int len, boolean[] visited, List<Integer> tmplist, List<List<Integer>> res, int[] nums){
        if(index == len){
            res.add(new LinkedList<>(tmplist));
            return;
        }

        for(int i = 0; i < len; i++){
            if(!visited[i]){
                tmplist.add(nums[i]);
                visited[i] = true;
                permute_help(index+1,len,visited,tmplist,res,nums);
                tmplist.remove(tmplist.size()-1);
                visited[i] = false;
            }
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> tmplist = new LinkedList<>();
        Arrays.sort(nums);

        int len = nums.length;
        if(len == 0) return res;
        boolean[] visited = new boolean[len];

        permuteUnique_help(0,len,visited,tmplist,res,nums);
        return res;
    }
    public void permuteUnique_help(int index, int len, boolean[] visited, List<Integer> tmplist, List<List<Integer>> res, int[] nums){
        if(index == len){
            res.add(new LinkedList<>(tmplist));
            return;
        }

        for(int i = 0; i < len; i++){
            if(!visited[i]){
                //加上 !vis[i - 1]来去重主要是通过限制一下两个相邻的重复数字的访问顺序
                //举个栗子，对于两个相同的数11，我们将其命名为1a1b, 1a表示第一个1，1b表示第二个1； 那么，不做去重的话，会有两种重复排列 1a1b, 1b1a，
                //我们只需要取其中任意一种排列； 为了达到这个目的，限制一下1a, 1b访问顺序即可。
                //比如我们只取1a1b那个排列的话，只有当visit nums[i-1]之后我们才去visit nums[i]， 也就是如果!visited[i-1]的话则continue
                if(i > 0 && nums[i] == nums[i-1] &&!visited[i-1]) continue;
                tmplist.add(nums[i]);
                visited[i] = true;
                permuteUnique_help(index+1,len,visited,tmplist,res,nums);
                tmplist.remove(tmplist.size()-1);
                visited[i] = false;
            }
        }
    }

    //1. 从尾到头遍历找到第一个不为升序的元素i;
    //2. 从尾到头找到第一个>i的元素j(此时必是最小的>i的元素因为元素i之后的元素都是降序)
    //3. swap i j;
    //4. 将i+1到j的元素reverse
    //根据上面步骤就可以构建出下一个字典序的更大数字;
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            nextPermutation_swap(nums, i, j);
        }
        nextPermutation_reverse(nums, i + 1);
    }

    public void nextPermutation_swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void nextPermutation_reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            nextPermutation_swap(nums, left, right);
            left++;
            right--;
        }
    }

    //回溯
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> tmp = new LinkedList<>();
        res.add(new LinkedList<>());

        subsets_help(0, nums, res, tmp);
        return res;
    }
    public void subsets_help(int index, int[] nums, List<List<Integer>> res, List<Integer> tmp){
        if(tmp.size() > 0){
            res.add(new LinkedList<>(tmp));
        }

        for(int i = index; i < nums.length; i++){
            tmp.add(nums[i]);
            subsets_help(i+1, nums, res, tmp);
            tmp.remove(tmp.size()-1);
        }
    }


    //回溯 + hashset去重
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> tmp = new LinkedList<>();
        Set<List<Integer>> visited = new HashSet<>();

        finSubsequences_help(0, nums, res, tmp, visited);
        return res;
    }
    public void finSubsequences_help(int index, int[] nums, List<List<Integer>> res, List<Integer> tmp, Set<List<Integer>> visited){
        if(tmp.size() > 1){
            if(!visited.contains(tmp)){
                visited.add(new LinkedList<>(tmp));
                res.add(new LinkedList<>(tmp));
            }
        }

        for(int i = index; i < nums.length; i++){
            if(tmp.size() == 0) {
                tmp.add(nums[i]);
            }
            else {
                if(nums[i] >= tmp.get(tmp.size()-1)){
                    tmp.add(nums[i]);
                }
                else continue;
            }

            finSubsequences_help(i+1, nums, res, tmp, visited);
            tmp.remove(tmp.size()-1);
        }
    }


    //回溯，注意回溯后将visited重置！！！！！
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(exist_help(i, j, board, word, 0, visited)) return true;
            }
        }

        return false;
    }
    public boolean exist_help(int row, int col, char[][] board, String word, int index, boolean[][] visited){
        if(row < 0 || col < 0 || row >= board.length || col >= board[0].length) return false;
        if(visited[row][col]) return false;
        else {
            visited[row][col] = true;
            if(board[row][col] != word.charAt(index)) {
                visited[row][col] = false;
                return false;
            }
            if(index == word.length() - 1) return true;

            boolean tmp1 = exist_help(row+1,col,board,word,index+1,visited);
            boolean tmp2 = exist_help(row-1,col,board,word,index+1,visited);
            boolean tmp3 = exist_help(row,col+1,board,word,index+1,visited);
            boolean tmp4 = exist_help(row,col-1,board,word,index+1,visited);
            visited[row][col] = false;

            return tmp1||tmp2||tmp3||tmp4;
        }

    }


    //回溯法，当‘(’ < n 是可以加入之; 当’）‘数量<'（'数量时，可以加入之;
    //从而避免暴力构造再判断
     public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        generateParenthesis_help(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }
    public void generateParenthesis_help(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            generateParenthesis_help(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            generateParenthesis_help(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }


    public int numIslands(char[][] grid) {
        int res = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(!visited[i][j] && grid[i][j] == '1'){
                    res++;
                    numIslands_help(visited, i, j, grid);
                }
            }
        }
        return res;
    }
    public void numIslands_help(boolean[][] visited, int row, int col, char[][] grid){
        if(row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) return;

        if(grid[row][col] == '0') return;

        if(visited[row][col]) return;

        visited[row][col] = true;
        numIslands_help(visited, row+1,col,grid);
        numIslands_help(visited, row-1,col,grid);
        numIslands_help(visited, row,col+1,grid);
        numIslands_help(visited, row,col-1,grid);
    }


    //回溯 这题本质上是个分治，其实就是大问题划分为左右两个子问题，然后不断通过递归的方式去求解
    //根据op(运算符)将式子分为两部分，这两部分又可以继续分所以要递归求解;
    //分出来的两部分都是List,list包含了该部分所有可能的运算结果；然后将两部分拼起来就是最终答案.
    public List<Integer> diffWaysToCompute(String expression) {
        char[] cs = expression.toCharArray();

        return diffWaysToCompute_help(0, cs.length-1, cs);
    }
    public List<Integer> diffWaysToCompute_help(int left, int right, char[] cs){
        List<Integer> res = new LinkedList<>();
        for(int i = left; i <= right; i++){
            if(cs[i] >= '0' && cs[i] <= '9') continue;

            List<Integer> l1 = diffWaysToCompute_help(left, i-1, cs);
            List<Integer> l2 = diffWaysToCompute_help(i+1,right,cs);
            for(int a : l1){
                for(int b : l2){
                    if(cs[i] == '+') res.add(a+b);
                    else if(cs[i] == '-') res.add(a-b);
                    else res.add(a*b);
                }
            }
        }

        //递归出口，当式子只剩下数字了 就直接把数字加进res然后返回;
        if(res.isEmpty()){
            int cur = 0;
            for (int i = left; i <= right; i++) cur = cur * 10 + (cs[i] - '0');
            res.add(cur);
        }


        return res;
    }


    //回溯算法 利用visited[][]记录已访问节点
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(!visited[i][j] && grid[i][j] == 1){
                    res = Math.max(res, maxAreaOfIsland_help(visited, i, j, grid));
                }
            }
        }
        return res;
    }
    public int maxAreaOfIsland_help(boolean[][] visited, int row, int col, int[][] grid){
        int ans = 0;
        if(row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) return ans;

        if(grid[row][col] == 0) return ans;

        if(visited[row][col]) return ans;

        visited[row][col] = true;
        ans++;
        int tmp1 = maxAreaOfIsland_help(visited, row+1,col,grid);
        int tmp2 = maxAreaOfIsland_help(visited, row-1,col,grid);
        int tmp3 = maxAreaOfIsland_help(visited, row,col+1,grid);
        int tmp4 = maxAreaOfIsland_help(visited, row,col-1,grid);
        return ans + tmp1 + tmp2 + tmp3 + tmp4;
    }


    //广度优先遍历，将所有的0加入队列，将其看作一个整体，然后进行广度优先遍历, 遍历一轮值加1;
    //也可以这样理解，有一个超级0连接了所有的0，那么1到最近的0的距离就是到超级0的距离 - 1;
    //我们需要对于每一个 11 找到离它最近的 0。如果只有一个 0 的话，我们从这个 0 开始广度优先搜索就可以完成任务了；
    //
    //但在实际的题目中，我们会有不止一个 0。
    // 我们会想，要是我们可以把这些 0 看成一个整体好了。
    // 有了这样的想法，我们可以添加一个「超级零」，它与矩阵中所有的 0相连，这样的话，任意一个 1到它最近的 0的距离，会等于这个 1到「超级零」的距离减去一。

    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dist = new int[m][n];
        boolean[][] seen = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<int[]>();
        // 将所有的 0 添加进初始队列中
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    seen[i][j] = true;
                }
            }
        }

        // 广度优先搜索
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int i = cell[0], j = cell[1];
            for (int d = 0; d < 4; ++d) {
                //dirs是二维数组，记录上下左右四个方向
                int ni = i + dirs[d][0];
                int nj = j + dirs[d][1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && !seen[ni][nj]) {
                    dist[ni][nj] = dist[i][j] + 1;
                    queue.offer(new int[]{ni, nj});
                    seen[ni][nj] = true;
                }
            }
        }

        return dist;
    }


    //从边缘的O开始，深搜，搜索所有与边缘O邻接的所有O，标记;
    //遍历，将标记过的还原为O，将未标记的改为X；
    public void solve(char[][] board) {
        int n = board.length;
        if (n == 0) {
            return;
        }
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            dfs(board, i, 0,n,m);
            dfs(board, i, m - 1,n,m);
        }
        for (int i = 1; i < m - 1; i++) {
            dfs(board, 0, i,n,m);
            dfs(board, n - 1, i,n,m);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
    public void dfs(char[][] board, int x, int y, int n, int m) {
        if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] != 'O'||board[x][y]=='A') {
            return;
        }
        board[x][y] = 'A';
        dfs(board, x + 1, y,n,m);
        dfs(board, x - 1, y,n,m);
        dfs(board, x, y + 1,n,m);
        dfs(board, x, y - 1,n,m);
    }

    //经典有向图，拓扑排序 可以使用广度优先遍历求解
    //我们考虑拓扑排序中最前面的节点，该节点一定不会有任何入边，也就是它没有任何的先修课程要求。
    //当我们将一个节点加入答案中后，我们就可以移除它的所有出边，代表着它的相邻节点少了一门先修课程的要求。
    //如果某个相邻节点变成了「没有任何入边的节点」，那么就代表着这门课可以开始学习了。
    //按照这样的流程，我们不断地将没有入边的节点加入答案，直到答案中包含所有的节点（得到了一种拓扑排序）或者不存在没有入边的节点（图中包含环）。
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //由于numCourses门课编号为0-numCourses - 1, 可以利用数组下标表示每一门课的信息;
        List<List<Integer>> edges = new LinkedList<>();//记录每门课的出边，即该课是多少其他课的先修课程;
        int[] remains = new int[numCourses];//记录每个课程剩余先修课程数量

        for (int i = 0; i < numCourses; ++i) {
            edges.add(new LinkedList<Integer>());
        }
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            remains[info[0]]++;
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; ++i) {
            if (remains[i] == 0) {
                queue.offer(i);
            }
        }

        int visited = 0;
        while (!queue.isEmpty()) {
            ++visited;
            int u = queue.poll();
            for (int v: edges.get(u)) {
                --remains[v];
                if (remains[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        return visited == numCourses;
    }

    //方法同上
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges = new LinkedList<>();
        int[] remains = new int[numCourses];

        for(int i = 0; i < numCourses; i++){
            edges.add(new LinkedList<>());
        }
        for(int[] tmp : prerequisites){
            edges.get(tmp[1]).add(tmp[0]);
            remains[tmp[0]]++;
        }

        Queue<Integer> bfsq = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(remains[i] == 0) bfsq.offer(i);
        }

        int[] res = new int[numCourses];
        int count = 0;
        while(!bfsq.isEmpty()){
            int tmp = bfsq.poll();
            res[count++] = tmp;
            for(int a : edges.get(tmp)){
                remains[a]--;
                if(remains[a] == 0) bfsq.offer(a);
            }
        }

        if (count != numCourses) {
            return new int[0];
        }
        return res;

    }


    //依次判断检查即可
    public String validIPAddress(String queryIP) {
        boolean isIPv4 = queryIP.contains("."), isIPv6 = queryIP.contains(":");
        String IPv6_CHARS = "0123456789abcdefABCDEF";
        if(isIPv4 && !isIPv6) {
            if (checkIPv4(queryIP)) {
                return "IPv4";
            }
        } else if(isIPv6 && !isIPv4) {
            if (checkIPv6(queryIP, IPv6_CHARS)) {
                return "IPv6";
            }
        }
        return "Neither";
    }
    private boolean checkIPv4(String ip) {
        String[] splits = ip.split("\\.");
        if(splits.length != 4 || ip.charAt(0) == '.' || ip.charAt(ip.length() - 1) == '.') {
            return false;
        }
        for(String s: splits) {
            if (s.length() > 3 || s.length() == 0 || (s.length() > 1 && s.charAt(0) == '0')) {
                return false;
            }
            int cur = 0;
            for(int i = 0; i < s.length(); i++) {
                if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                    cur = 10 * cur + s.charAt(i) - '0';
                } else {
                    return false;
                }
            }
            if (cur > 255) {
                return false;
            }
        }
        return true;
    }
    private boolean checkIPv6(String ip, String IPv6_CHARS) {
        String[] splits = ip.split(":");
        if(splits.length != 8 || ip.charAt(0) == ':' || ip.charAt(ip.length() - 1) == ':') {
            return false;
        }
        for(String s:splits) {
            if (s.length() > 4 || s.length() == 0) {
                return false;
            }
            for(int i = 0; i < s.length(); i++) {
                if(IPv6_CHARS.indexOf(s.charAt(i)) == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    //binary search
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int start = 0;
        int end = len - 1;

        while(start <= end){
            int mid = (end - start)/2 + start;
            if(nums[mid] == target) return mid;
            else if(nums[mid] > target) end = mid - 1;
            else start = mid + 1;
        }

        return start;
    }

    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0) return new int[]{-1,-1};

        int l = 0, r = nums.length - 1; //二分范围
        while( l < r)			        //查找元素的开始位置
        {
            int mid = (l + r )/2;
            if(nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        if( nums[r] != target) return new int[]{-1,-1}; //查找失败
        int L = r;
        l = 0; r = nums.length - 1;     //二分范围
        while( l < r)			        //查找元素的结束位置
        {
            int mid = (l + r + 1)/2;
            if(nums[mid] <= target ) l = mid;
            else r = mid - 1;
        }
        return new int[]{L,r};
    }

    //由于给定数组有序 且 常规元素总是两两出现，因此如果不考虑“特殊”的单一元素的话，
    //我们有结论：成对元素中的第一个所对应的下标必然是偶数，成对元素中的第二个所对应的下标必然是奇数。
    //然后再考虑存在单一元素的情况，假如单一元素所在的下标为 x，那么下标 x 之前（左边）的位置仍满足上述结论，
    //而下标 x 之后（右边）的位置由于 x 的插入，导致结论翻转。
    //根据上述性质可以利用二分查找思想实现logn复杂度
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (mid % 2 == 0) {
                if (mid + 1 < n && nums[mid] == nums[mid + 1]) l = mid + 1;
                else r = mid;
            } else {
                if (mid - 1 >= 0 && nums[mid - 1] == nums[mid]) l = mid + 1;
                else r = mid;
            }
        }
        return nums[r];
    }


    //先排序再二分
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[][] startIntervals = new int[n][2];
        for (int i = 0; i < n; i++) {
            startIntervals[i][0] = intervals[i][0];
            startIntervals[i][1] = i;
        }
        Arrays.sort(startIntervals, (o1, o2) -> o1[0] - o2[0]);

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            int target = -1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (startIntervals[mid][0] >= intervals[i][1]) {
                    target = startIntervals[mid][1];
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            ans[i] = target;
        }
        return ans;
    }

    //n*n复杂度的dp,每次dp[i]需要遍历i之前的数找到一个nums[j]  < nums[i];
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = 1;

        int res = 1;
        for(int i = 1; i < len; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    //二分+双指针
    //先通过「二分」找到与 x 差值最小的位置 idx 然后双指针向外扩展
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int right = binary_help(arr, x);
        int left = right - 1;
        while (k > 0) {
            k--;
            if (left < 0) {
                right++;
            } else if (right >= arr.length) {
                left--;
            } else if (x - arr[left] <= arr[right] - x) {
                left--;
            } else {
                right++;
            }
        }
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = left + 1; i < right; i++) {
            ans.add(arr[i]);
        }

        return ans;
    }
    public int binary_help(int[] arr, int x){
        int low = 0, high = arr.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= x) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    //二分查找, 先找到所在行，在行内再二分
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int row = 0;
        for(int i = 0; i < rows; i++){
            if(target >= matrix[i][0]) row = i;
        }

        int left = 0;
        int right = cols - 1;
        while(left <= right){
            int mid = (left + right)/2;
            if(matrix[row][mid] == target) return true;
            else if(matrix[row][mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return false;
    }

    //基于mid拆分后，总有一边是有序的，根据有序的那边判断判断是否要在有序的那边搜索;
    //变种二分
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }


    //直接二分判断当前是否大于下一个点，若大于则可能是峰值，r = mid，否则 l = mid + 1;
    //根据题意，我们有「数据长度至少为 1」、「越过数组两边看做负无穷」和「相邻元素不相等」的起始条件。
    //条件保证了数组必然有峰值，可以用二分;
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1]) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    //dp, 用for循环代替dp数组
    public int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    //贪心法，遍历数组，若当前位置可到达，判断最远能走多远，若能走到边界返回true;
    //否则false
    public boolean canJump(int[] nums) {
        int len = nums.length;
        int max = 0;

        for(int i = 0; i < len; i++){
            if(i <= max){
                max = Math.max(max, i + nums[i]);
                if(max >= len - 1) return true;
            }
        }

        return false;
    }

    //如果我们「贪心」地进行正向查找，每次找到可到达的最远位置，就可以在线性时间内得到最少的跳跃次数。
    //如:初始位置是下标 0，从下标 0 出发，最远可到达下标 2。下标 0 可到达的位置中，下标 1 的值是 3，从下标 1 出发可以达到更远的位置，因此第一步到达下标 1。
    //在具体的实现中，我们维护当前能够到达的最大下标位置，记为边界。我们从左到右遍历数组，到达边界时，更新边界并将跳跃次数增加 1。
    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    //二分思想，当当前元素满足有>=k个值小于它时,right = mid, 即有可能是答案或者还能找到更小的值满足条件
    //else left = mid + 1
    //输出的left必然在矩阵中因为算法找的是最小的满足条件的值，若不在矩阵中值不会是最小的！！
    //比如1 3 10， 找第二大; 只有3满足 4，5也满足但不够小; 只有找到一个矩阵中的值 让 k+1才满足最小！
    public int kthSmallest(int[][] matrix, int k) {
        int len = matrix.length;

        int left = matrix[0][0];
        int right = matrix[len-1][len-1];

        while(left < right){
            int mid = left + ((right - left) >> 1);
            if(kthSmallest_help(matrix, mid, k, len)){
                right = mid;
            }
            else left = mid + 1;
        }

        return left;
    }
    //我们可以看到，矩阵中大于 mid的数就和不大于 mid的数分别形成了两个板块
    //沿着一条锯齿线将这个矩形分开。其中左上角板块的大小即为矩阵中不大于 mid的数的数量。
    //我们只要沿着这条锯齿线走一遍即可计算出这两个板块的大小，也自然就统计出了这个矩阵中不大于 mid的数的个数了。
    //即:从左下角开始，若其值 <= mid 则一整列都 <= mid; 右移继续判断，当不满足<=mid时 上移(i--);
    public boolean kthSmallest_help(int[][] matrix, int mid, int k, int len){
        int i = len - 1;
        int j = 0;
        int num = 0;
        while (i >= 0 && j < len) {
            if (matrix[i][j] <= mid) {
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }

        return num >= k;
    }

    //一维dp
    public int rob(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        if(len < 2) return dp[0];
        dp[1] = Math.max(nums[0], nums[1]);
        int max = dp[1];
        for(int i = 2; i < len; i++){
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
            max = Math.max(max, dp[i]);
        }

        return max;
    }


    //由于房屋首位相连，用2次dp，先算0 - n-2 的最大值，再算 1- n-1 的最大值， 然后两个取最大！！
    public int rob2(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robRange(nums, 0, length - 2), robRange(nums, 1, length - 1));
    }

    public int robRange(int[] nums, int start, int end) {
        int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }


    //一维dp dp[i] = dp[i-1] + dp[i-2] 当 当前位置单独构成item且可以与前一个值共同构成item
    public int numDecodings(String s) {
        int n = s.length();
        s = " " + s;
        char[] cs = s.toCharArray();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            // a : 代表「当前位置」单独形成 item
            // b : 代表「当前位置」与「前一位置」共同形成 item
            int a = cs[i] - '0', b = (cs[i - 1] - '0') * 10 + (cs[i] - '0');
            // 如果 a 属于有效值，那么 f[i] 可以由 f[i - 1] 转移过来
            if (1 <= a && a <= 9) f[i] += f[i - 1];
            // 如果 b 属于有效值，那么 f[i] 可以由 f[i - 2] 或者 f[i - 1] & f[i - 2] 转移过来
            if (10 <= b && b <= 26) f[i] += f[i - 2];
        }
        return f[n];
    }

    //下一个丑数是当前指针*2/3/5 的最小值;
    //以此得到状态转移方程，从而使用dp求解
    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }

        return dp[n];
    }

    //二维dp
    //我们目前持有一支股票，对应的「累计最大收益」记为 f[i][0]f[i][0]；
    //我们目前不持有任何股票，并且处于冷冻期中，对应的「累计最大收益」记为 f[i][1]f[i][1]；
    //我们目前不持有任何股票，并且不处于冷冻期中，对应的「累计最大收益」记为 f[i][2]f[i][2]。
    //!!！！!这里的「处于冷冻期」指的是在第 i天结束之后的状态。也就是说：如果第 i天结束之后处于冷冻期，那么第 i+1天无法买入股票。！！!!!
    public int maxProfit_withfrozen(int[] prices) {
        int len = prices.length;
        if(len == 0) return 0;
        int[][] dp = new int[len][3];
        dp[0][0] = -prices[0];

        for(int i = 1; i < len; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2] - prices[i]);
            dp[i][1] = dp[i-1][0] + prices[i];
            dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1]);
        }


        return Math.max(dp[len-1][1], dp[len-1][2]);
    }

    //二维dp求解
    //其中dp[i][j]表示从数组的 [0,i]下标范围内选取若干个正整数（可以是 0 个），是否存在一种选取方案使得被选取的正整数的和等于j
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        boolean[][] dp = new boolean[n][target + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][target];
    }

    //一维dp, dp[i]表示最少需要多少个数的平方来表示整数i。
    //dp[i] = 1 + min(dp[i - j*j]), j = (1, 根号i);
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for(int i = 1; i < n + 1; i++){
            int min = Integer.MAX_VALUE;
            for(int j = 1; j * j <= i; j++){
                min = Math.min(min, dp[i- j*j]);
            }
            dp[i] = min + 1;
        }

        return dp[n];
    }

    //一维dp, dp[i]表示最少需要多少个数的coin凑齐i。
    public int coinChange(int[] coins, int amount) {
        int len = coins.length;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for(int i = 1; i < amount + 1; i++){
            for(int j = 0; j < len; j++){
                if(coins[j] <= i){
                    dp[i] = Math.min(dp[i], dp[i-coins[j]] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }


    //回溯遍历所有可能的组合，若成立 count++;
    public int findTargetSumWays(int[] nums, int target) {
        backtrack(nums, target, 0, 0);
        return count;
    }

    public void backtrack(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) {
                count++;
            }
        } else {
            backtrack(nums, target, index + 1, sum + nums[index]);
            backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }


    //二维dp！
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i = 0; i < n; i++){
            dp[0][i] = 1;
        }
        for(int i = 0; i < m; i++){
            dp[i][0] = 1;
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }

        // 定义 dp 数组并初始化第 1 行和第 1 列。
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
            dp[0][j] = 1;
        }

        // 根据状态转移方程 dp[i][j] = dp[i - 1][j] + dp[i][j - 1] 进行递推。
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    //二维dp求解
    //dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j]; dp[i][j] 表示到达当前点的最小cost
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] dp = new int[rows][cols];
        dp[0][0] = grid[0][0];
        for(int i = 1; i < cols; i++){
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        for(int i = 1; i < rows; i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        for(int i = 1; i < rows; i++){
            for (int j = 1; j < cols; j++){
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[rows-1][cols-1];
    }

    //经典二维dp
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[n][n];
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            f[i][0] = f[i - 1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; ++j) {
                f[i][j] = Math.min(f[i - 1][j - 1], f[i - 1][j]) + triangle.get(i).get(j);
            }
            f[i][i] = f[i - 1][i - 1] + triangle.get(i).get(i);
        }
        int minTotal = f[n - 1][0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[n - 1][i]);
        }
        return minTotal;
    }

    //dp[i][j]表示以i j为右下角的正方形的最大边长！
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        //状态转移！
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }

    //dp G(n) += G(i-i) * G(n - i), 累加，当 i=1到n;
    //G(n) 表示 长度为 n的序列能构成的不同二叉搜索树的个数。
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }


    //二维dp，dp[i][j] = dp[i+1][j-1] + 2, when s[i] = s[j];
    //dp[i][j] represent the max length of the string from i to j;
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            char c1 = s.charAt(i);
            for (int j = i + 1; j < n; j++) {
                char c2 = s.charAt(j);
                if (c1 == c2) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    //思路类似寻找两个字符最长公共子串;
    //dp[i][j] 表示，word1[0:i]和word2[0：j]的最长公共子序列的长度;
    //求出最长子序列后，将两个字符串长度-最长序列再相加，就是操作的次数！！(len1 - dp[len1][len2]) + (len2 - dp[len1][len2]);
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        int[][] dp = new int[len1+1][len2+1];
        dp[0][0] = 0;
        for(int i = 1; i <= len1; i++){
            char tmp1 = word1.charAt(i-1);
            for(int j = 1; j <= len2; j++){
                char tmp2 = word2.charAt(j-1);
                if(tmp1 == tmp2){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        return (len1 - dp[len1][len2]) + (len2 - dp[len1][len2]);
    }

    //二维dp,dp[i][j]表示s1前i个和s2前j个是否能构成s3前i+j个
    public boolean isInterleave(String s1, String s2, String s3) {
        int lens1 = s1.length();
        int lens2 = s2.length();
        int lens3 = s3.length();
        if(lens1 + lens2 != lens3) return false;
        boolean[][] dp = new boolean[lens1+1][lens2+1];
        dp[0][0] = true;

        for(int i = 0; i <= lens1; i++){
            for(int j = 0; j <= lens2; j++){
                int index = i + j - 1;
                //状态转移, 当dp[i-1][j]为真且s1第i个元素与s3第i+j-1个元素相等，则真
                //同理j也是一样！！！
                if(i > 0) dp[i][j] = dp[i][j] || (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(index));
                if(j > 0) dp[i][j] = dp[i][j] || (dp[i][j-1] && s2.charAt(j-1) == s3.charAt(index));
            }
        }

        return dp[lens1][lens2];
    }


    //一维dp dp[i]表示s的前i个字符能否被wordDict里的字符组成;
    //j用于分割i,只要有一个dp[j]满足且j - i-1的字符串也在myset内 则证明其可以被wordDict中单词组成!
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> myset = new HashSet<>(wordDict);
        int len = s.length();
        boolean[] dp = new boolean[len+1];
        dp[0] = true;
        for(int i = 1; i <= len; i++){
            for(int j = 0; j < i; j++){
                if (dp[j] && myset.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len];
    }

    //dp预处理字符串，使判断是否回文复杂度为O(1)，然后回溯
    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<List<String>>();
        List<String> ans = new ArrayList<String>();
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        // 边界1: 对角线，即单个字符，都是回文
        for(int i = 0; i < n; i++){
            dp[i][i] = true;
        }
// 边界2:对角线上侧紧邻斜线，即两个字符，判断是否相等，相等则为回文
        for(int i = 0; i < n - 1; i++){
            dp[i][i + 1] = s.charAt(i) == s.charAt(i+ 1);
        }
// 从下到上，边界1和边界2确定了两条斜线，所以只需要从倒数第三行开始往上补全右上三角
        for(int i = n - 3; i >= 0; i--){
            for(int j = i + 2; j < n; j++){
                dp[i][j] = dp[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
            }
        }

        dfs(s, 0,n,dp,ret,ans);
        return ret;
    }

    public void dfs(String s, int i, int n, boolean[][] f, List<List<String>> ret, List<String> ans) {
        if (i == n) {
            ret.add(new ArrayList<String>(ans));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1,n,f,ret,ans);
                ans.remove(ans.size() - 1);
            }
        }
    }

    public int rob(TreeNode root) {
        Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
        Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();

        rob_dfs(root, f, g);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }
    public void rob_dfs(TreeNode node, Map<TreeNode, Integer> f, Map<TreeNode, Integer> g) {
        if (node == null) {
            return;
        }
        rob_dfs(node.left, f, g);
        rob_dfs(node.right, f, g);
        //我们可以用 f(o)表示选择o 节点的情况下，o节点的子树上被选择的节点的最大权值和；g(o)表示不选择o节点的情况下，o节点的子树上被选择的节点的最大权值和；
        // l和 r代表 o的左右孩子。
        //当 o被选中时，o的左右孩子都不能被选中，故 o被选中情况下子树上被选中点的最大权值和为 l和 r不被选中的最大权值和相加，即 f(o) = g(l) + g(r)
        //当 o不被选中时，oo 的左右孩子可以被选中，也可以不被选中。对于 o的某个具体的孩子x，它对 o的贡献是 x被选中和不被选中情况下权值和的较大值.
        //故g(o) = Max(f(l) , g(l)) + Max(f(r) + g(r));
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }


    //暴力搜索， 回溯 + 合理剪纸 可以通过所有case！！
    //从球的视角出发，每个球都必然属于某一个桶，总共k个桶，回溯遍历所有可能情况即可
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int len = nums.length;
        int sum = 0;
        for(int i = 0; i < len; i++){
            sum += nums[i];
        }
        if(sum % k != 0) return false;
        int target = sum / k;
        //如果我们让 nums[] 内的元素递减排序，先让值大的元素选择桶，这样可以增加剪枝的命中率，从而降低回溯的概率
        Arrays.sort(nums);
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }
        if(nums[0] > target) return false;
        int[] bucket = new int[k];
        return canPartitionKSubsets_dfs(nums, k, bucket, target, 0);
    }
    public boolean canPartitionKSubsets_dfs(int[] nums, int k, int[] bucket, int target, int index){
        // 结束条件：已经处理完所有球
        //因为当 index == num.length 时，所有球已经按要求装入所有桶，所以肯定是一个满足要求的解 即：每个桶内球的和一定为 target
        if(index == nums.length) return true;

        for(int i = 0; i < k; i++){
            if(bucket[i] + nums[index] > target) continue;
            //原因：如果元素和相等，那么 nums[index] 选择上一个桶和选择当前桶可以得到的结果是一致的,可以跳过
            if(i > 0 && bucket[i-1] == bucket[i]) continue;
            bucket[i] += nums[index];
            if(canPartitionKSubsets_dfs(nums, k, bucket, target, index+1)) return true;
            bucket[i] -= nums[index];
        }

        return false;
    }







//  new -- 2024
    public int numDecodings_2024(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for(int i = 1; i <= n; i++){
            if(s.charAt(i - 1) != '0') dp[i] += dp[i-1];

            if(i > 1 && s.charAt(i-2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26))
                dp[i] += dp[i-2];
        }

        return dp[n];
    }


    public List<String> letterCombinations_2024(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        StringBuilder res = new StringBuilder();
        letterCombinations_backtrack(combinations, phoneMap, 0,  res, digits);
        return combinations;
    }
    void letterCombinations_backtrack(List<String> combinations, Map<Character, String> phoneMap, int index, StringBuilder res, String digits)
    {
        if(index == digits.length()){
            combinations.add(new String(res));
            return;
        }

        char cur = digits.charAt(index);
        String curS = phoneMap.get(cur);

        for(int i = 0; i < curS.length(); i++){
            char tmp = curS.charAt(i);
            res.append(tmp);
            letterCombinations_backtrack(combinations, phoneMap, index+1, res, digits);
            res.deleteCharAt(res.length()-1);
        }
    }

    public String validIPAddress_2024(String queryIP) {
        return isIpv4(queryIP)?"IPv4":isIpv6(queryIP)?"IPv6":"Neither";
    }
    boolean isIpv4(String queryIp){
        String[] res = queryIp.split("\\.", -1);
        if(res.length != 4) return false;

        for(int i = 0; i < 4; i++){
            if(res[i].length() == 0 || res[i].length() > 3 || res[i].length() > 1 && res[i].charAt(0) == '0')
                return false;

            int sum = 0;
            for(char c:res[i].toCharArray()){
                if(!(c >= '0' && c<='9')){return false;}
                sum = sum*10 + (c -'0');
            }
            if(sum > 255){return false;}
        }

        return true;
    }
    boolean isIpv6(String queryIp){
        queryIp = queryIp.toLowerCase();
        String res[] = queryIp.split(":",-1);
        if(res.length!=8){return false;}
        for(int i=0;i<8;i++){
            if(res[i].length()==0||res[i].length()>4){return false;}
            for(char c:res[i].toCharArray()){
                if(!(c>='0'&&c<='9')&&!(c>='a'&&c<='f')) return false;
            }
        }
        return true;
    }


    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        List<String> res = new ArrayList<>();
        // 如果长度不够，不搜索
        if (len < 4 || len > 12) {
            return res;
        }

        List<String> path = new ArrayList<>(4);
        restoreIpAddresses_backtrack(s, len, 0, 0, path, res);
        return res;

    }
    /**
     * 判断 s 的子区间 [left, right] 是否能够成为一个 ip 段
     * 判断的同时顺便把类型转了
     */
    private int judgeIfIpSegment(String s, int left, int right) {
        int len = right - left + 1;

        // 大于 1 位的时候，不能以 0 开头
        if (len > 1 && s.charAt(left) == '0') {
            return -1;
        }

        // 转成 int 类型
        int res = 0;
        for (int i = left; i <= right; i++) {
            res = res * 10 + s.charAt(i) - '0';
        }

        if (res > 255) {
            return -1;
        }
        return res;
    }
    private void restoreIpAddresses_backtrack(String s, int len, int split, int start, List<String> path, List<String> res)
    {
        if(start == len && split == 4){
            res.add(String.join(".", path));
            return;
        }

        if (len - start < (4 - split) || len - start > 3 * (4 - split)) {
            return;
        }

        for(int i = 0; i < 3; i++){
            if(start + i >= len) break;

            int ipSegment = judgeIfIpSegment(s, start, start + i);
            if(ipSegment != -1){
                path.add(ipSegment + "");
                restoreIpAddresses_backtrack(s, len, split + 1, start + i + 1, path, res);
                path.remove(path.size()-1);
            }
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0;

        int[] sorted = new int[m + n];
        int index = 0;
        while(p1 < m && p2 < n){
            if(nums1[p1] <= nums2[p2]){
                sorted[index] = nums1[p1];
                p1++;
                index++;
            }
            else {
                sorted[index] = nums2[p2];
                p2++;
                index++;

            }
        }
        while (p1 < m){
            sorted[index] = nums1[p1];
            p1++;
            index++;
        }
        while(p2 < n){
            sorted[index] = nums2[p2];
            p2++;
            index++;
        }

        for (int i = 0; i < m + n; ++i) {
            nums1[i] = sorted[i];
        }
    }


    public int[][] merge_2024(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();

        for(int i = 0; i < intervals.length; i++){
            int left = intervals[i][0], right = intervals[i][1];
            if(merged.size() == 0 || merged.get(merged.size() - 1)[1] < left){
                merged.add(new int[]{left, right});
            }

            else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], right);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public int uniquePaths_2024(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; ++j) {
            dp[0][j] = 1;
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1) {
            return -1;
        }
        int n = grid.length;
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        Queue<int[]> queue = new ArrayDeque<int[]>();

        queue.offer(new int[]{0, 0});
        dist[0][0] = 1;

        while(!queue.isEmpty()){
            int[] tmp = queue.poll();
            int x = tmp[0], y = tmp[1];

            if(x == n - 1 && y == n - 1){
                return dist[x][y];
            }

            for(int dx = -1; dx < 2; dx++){
                for(int dy = -1; dy < 2; dy++){
                    if (x + dx < 0 || x + dx >= n || y + dy < 0 || y + dy >= n) {
                        // 越界
                        continue;
                    }
                    if (grid[x + dx][y + dy] == 1 || dist[x + dx][y + dy] != Integer.MAX_VALUE ) {
                        // 单元格值不为 0 或已被访问
                        continue;
                    }

                    dist[x+dx][y+dy] = dist[x][y] + 1;
                    queue.offer(new int[]{x+dx, y+dy});
                }
            }
        }
        return -1;
    }

    public boolean isConvex(List<List<Integer>> points) {
        int n = points.size();
        if(n==3){
            return true;
        }
        int temp = 0;
        for(int i = 0; i < n; i++){
            int x1 = points.get((i+1)%n).get(0)-points.get(i).get(0);
            int y1 = points.get((i+1)%n).get(1)-points.get(i).get(1);
            int x2 = points.get((i+2)%n).get(0)-points.get((i+1)%n).get(0);
            int y2 = points.get((i+2)%n).get(1)-points.get((i+1)%n).get(1);
            if(temp==0){
                temp = x1*y2-x2*y1;
            }
            else if(temp < 0 && x1*y2-x2*y1>0 || (temp > 0 && x1*y2-x2*y1 < 0)){
                return false;
            }
        }
        return true;
    }

    public int calculate_2024(String s) {
        Stack<Integer> stack = new Stack<>();
        int len = s.length();
        char preSign = '+';

        int num = 0;
        for(int i = 0; i < len; i++){
            if(Character.isDigit(s.charAt(i))){
                num = num * 10 + s.charAt(i) - '0';
            }
            if(!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == len -1){
                switch (preSign){
                    case '+' :
                        stack.push(num);
                        break;

                    case '-' :
                        stack.push(-num);
                        break;

                    case '*' :
                        stack.push(stack.pop() * num);
                        break;

                    default :
                        stack.push(stack.pop() / num);
                        break;
                }
                preSign = s.charAt(i);
                num = 0;
            }
        }

        int res = 0;
        while(!stack.isEmpty()){
            res += stack.pop();
        }

        return res;
    }

    public int getNumberOfBacklogOrders(int[][] orders) {
        final int MOD = 1000000007;
        PriorityQueue<int[]> buyOrders = new PriorityQueue<int[]>((a, b) -> b[0] - a[0]);
        PriorityQueue<int[]> sellOrders = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);

        for(int[] order : orders){
            int price = order[0], amount = order[1], orderType = order[2];
            if(orderType == 0){
                while (amount > 0 && !sellOrders.isEmpty() && sellOrders.peek()[0] <= price){
                    int[] sellOrder = sellOrders.poll();
                    int sellAmount = Math.min(amount, sellOrder[1]);
                    amount -= sellAmount;
                    sellOrder[1] -= sellAmount;
                    if (sellOrder[1] > 0) {
                        sellOrders.offer(sellOrder);
                    }
                }

                if (amount > 0) {
                    buyOrders.offer(new int[]{price, amount});
                }
            }
            else {
                while (amount > 0 && !buyOrders.isEmpty() && buyOrders.peek()[0] >= price){
                    int[] buyOrder = buyOrders.poll();
                    int buyAmount = Math.min(amount, buyOrder[1]);
                    amount -= buyAmount;
                    buyOrder[1] -= buyAmount;
                    if(buyOrder[1] > 0) buyOrders.offer(buyOrder);
                }

                if(amount > 0){
                    sellOrders.add(new int[]{price, amount});
                }
            }
        }

        int res = 0;
        while(!buyOrders.isEmpty()){
            int[] tmp = buyOrders.poll();
            res = (res + tmp[1]) % MOD;
        }
        while(!sellOrders.isEmpty()){
            int[] tmp = sellOrders.poll();
            res = (res + tmp[1]) % MOD;
        }
        return res;
    }


}

class TicTacToe {
    int[] rows_one;
    int[] rows_two;
    int[] cols_one;
    int[] cols_two;
    int diagonal_one;
    int diagonal_two;
    int antiDiagonal_one;
    int antiDiagonal_two;

    public TicTacToe(int n) {
        rows_one = new int[n];
        cols_one = new int[n];
        rows_two = new int[n];
        cols_two = new int[n];
    }

    public int move(int row, int col, int player) {
        int currentPlayer = (player == 1) ? 1 : 2;

        if(currentPlayer == 1){
            // 更新 rows 和 cols 数组中的当前用户
            rows_one[row] += 1;
            cols_one[col] += 1;
            // 更新 diagonal
            if (row == col) {
                diagonal_one += 1;
            }
            // 更新 antiDiagonal
            if (row == (cols_one.length - 1 - col)) {
                antiDiagonal_one += 1;
            }
            int n = rows_one.length;
            // 检查玩家是否赢得游戏
            if ((rows_one[row]) == n ||
                    (cols_one[col]) == n ||
                    (diagonal_one) == n ||
                    (antiDiagonal_one) == n) {
                return player;
            }

        }else {
            // 更新 rows 和 cols 数组中的当前用户
            rows_two[row] += 1;
            cols_two[col] += 1;
            // 更新 diagonal
            if (row == col) {
                diagonal_two += 1;
            }
            // 更新 antiDiagonal
            if (row == (cols_two.length - 1 - col)) {
                antiDiagonal_two += 1;
            }
            int n = rows_two.length;
            // 检查玩家是否赢得游戏
            if ((rows_two[row]) == n ||
                    (cols_two[col]) == n ||
                    (diagonal_two) == n ||
                    (antiDiagonal_two) == n) {
                return player;
            }

        }

        // 没有人赢
        return 0;
    }
}

class MouseAndCat {
    static final int MOUSE_WIN = 1;
    static final int CAT_WIN = 2;
    static final int DRAW = 0;
    int n;
    int[][] graph;
    int[][][] dp;

    public int catMouseGame(int[][] graph) {
        this.n = graph.length;
        this.graph = graph;
        this.dp = new int[n][n][2 * n * (n - 1)];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return getResult(1, 2, 0);
    }

    public int getResult(int mouse, int cat, int turns) {
        if (turns == 2 * n * (n - 1)) {
            return DRAW;
        }
        if (dp[mouse][cat][turns] < 0) {
            if (mouse == 0) {
                dp[mouse][cat][turns] = MOUSE_WIN;
            } else if (cat == mouse) {
                dp[mouse][cat][turns] = CAT_WIN;
            } else {
                getNextResult(mouse, cat, turns);
            }
        }
        return dp[mouse][cat][turns];
    }

    public void getNextResult(int mouse, int cat, int turns) {
        int curMove = turns % 2 == 0 ? mouse : cat;
        int defaultResult = curMove == mouse ? CAT_WIN : MOUSE_WIN;
        int result = defaultResult;
        int[] nextNodes = graph[curMove];
        for (int next : nextNodes) {
            if (curMove == cat && next == 0) {
                continue;
            }
            int nextMouse = curMove == mouse ? next : mouse;
            int nextCat = curMove == cat ? next : cat;
            int nextResult = getResult(nextMouse, nextCat, turns + 1);
            if (nextResult != defaultResult) {
                result = nextResult;
                if (result != DRAW) {
                    break;
                }
            }
        }
        dp[mouse][cat][turns] = result;
    }
}

// Definition for a Node with random point.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
/**
 * Definition for singly-linked list.*/
class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class NumArray {
    private int[] sums;

    public NumArray(int[] nums) {
        int len = nums.length;
        sums = new int[len+1];

        for(int i = 0; i < len; i++){
            sums[i + 1] = sums[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return sums[right + 1] - sums[left];
    }
}

class NumMatrix {
    private int[][] matrix;
    public NumMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        this.matrix = matrix;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int res = 0;
        for(int i = row1; i <= row2; i++){
            for(int j = col1; j <= col2; j++){
                res += matrix[i][j];
            }
        }
        return res;
    }
}


class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
}





