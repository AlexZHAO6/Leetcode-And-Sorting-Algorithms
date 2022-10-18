import java.util.*;

public class LeetcodeAlgorithms {

    public static void main(String[] args){
        int[] test_global = {1,5,3,2,7};
        SortAlgorithms_real ah = new SortAlgorithms_real();
        leetcode testclass = new leetcode();
        int[] test2 = {4,3,2,7,8,2,3,1};
        List<Integer> reslut = testclass.findDisappearedNumbers(test2);

        String num1 = "123";
        String num2 = "456";
        String[] tmp = {"4","13","5","/","*"};
        testclass.evalRPN(tmp);
        testclass.decodeString("3[z]2[2[y]pq4[2[jk]e1[f]]]ef");
        testclass.removeDuplicateLetters("bcabc");
        System.out.println(testclass.multiply(num1,num2));
    }
}

class leetcode{
    private final static Random random = new Random();
    private int treenode_ans;


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
                tmp = end - start + 1;
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
    public int minDepth(TreeNode root) {g
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;

        int minleft = minDepth(root.left);
        int minright = minDepth(root.right);

        if(root.left == null || root.right == null) return minleft + minright;

        return Math.min(minleft,minright) + 1;
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


