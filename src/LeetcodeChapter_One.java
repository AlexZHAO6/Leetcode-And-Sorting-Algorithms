import java.util.*;
public class LeetcodeChapter_One {
    public static void main(String[] args){
        System.out.println("Alex GO GO GO");
    }
}


class LinkedListAlgorithms{
    ListNode successor = null;
    ListNode left = null;
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tmpA = headA;
        ListNode tmpB = headB;

        while(tmpA != tmpB){
            if(tmpA != null) tmpA = tmpA.next;
            else tmpA = headB;

            if(tmpB != null) tmpB = tmpB.next;
            else tmpB = headA;
        }

        return tmpA;
    }

    public ListNode reverse(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;

        return last;
    }

    public ListNode reverseN(ListNode head, int n){
        if(n == 1){
            successor = head.next;
            return head;
        }

        ListNode last = reverseN(head.next, n -1);

        head.next.next = head;
        head.next = successor;

        return last;
    }

    ListNode reverseBetween(ListNode head, int m, int n){
        if(m == 1) return reverseN(head, n);

        head.next = reverseBetween(head, m - 1, n - 1);

        return head;
    }

    //k个一组反转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null) return head;
        ListNode a = head, b = head;

        for(int i = 0; i < k; i++){
            if(b == null) return head;
            b = b.next;
        }

        ListNode newHead = reverseList(a, b);
        a.next = reverseKGroup(b, k);

        return newHead;
    }
    /** 反转区间 [a, b) 的元素，注意是左闭右开 */
    public ListNode reverseList(ListNode a, ListNode b){
        ListNode pre = null, cur = a;
        while (cur != b){
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }

        return pre;
    }

    public boolean isPalindrome(ListNode head) {
        left = head;

        return isPalindrome_backTraverse(head);
    }
    public boolean isPalindrome_backTraverse(ListNode right){
        if(right == null) return true;

        boolean res = isPalindrome_backTraverse(right.next);
        //back traverse
        res = res && (left.val == right.val);
        left = left.next;
        return res;
    }

}

class ArrayAlgorithm{
    public int removeDuplicates(int[] nums){
        int len = nums.length;
        int slow = 0, fast = 0;

        while(fast < len){
            if(nums[slow] != nums[fast]){
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }

    public int removeElement(int[] nums, int val){
        int len = nums.length;
        int slow = 0, fast = 0;

        while(fast < len){
            if(nums[fast] != val){
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    public void moveZeroes(int[] nums){
        int len = nums.length;
        int slow = 0, fast = 0;

        while (fast < len){
            if(nums[fast] != 0){
                int tmp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = tmp;
                slow++;
            }
            fast++;
        }
    }

    public String longestPalindrome(String s){
        String res = "";
        for (int i = 0; i < s.length(); i++){
            //the length of the palindrome can be odd or even;
            //So, we need to consider both situation!!
            String s1 = longestPalindrome_help(s, i, i);
            String s2 = longestPalindrome_help(s, i, i+1);
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }

        return res;
    }
    public String longestPalindrome_help(String s, int l, int r){
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }

        return s.substring(l + 1, r);
    }

    //prefix for reducing time complexity !!
    class NumMatrix {
        // define：preSum[i][j] records the sum of the matrix in [0, 0, i-1, j-1]!
        private int[][] preSum;
        public NumMatrix(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            preSum = new int[m+1][n+1];
            if(m == 0 || n == 0) return;

            for(int i = 1; i <= m; i++){
                for(int j = 1; j <= n; j++){
                    preSum[i][j] = preSum[i-1][j] + preSum[i][j-1] + matrix[i-1][j-1] - preSum[i-1][j-1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return preSum[row2+1][col2+1] - preSum[row1][col2+1] - preSum[row2+1][col1] + preSum[row1][col1];
        }
    }

    // 差分数组工具类
    class Difference {
        // 差分数组
        private int[] diff;

        /* 输入一个初始数组，区间操作将在这个数组上进行 */
        public Difference(int[] nums) {
            diff = new int[nums.length];
            // 根据初始数组构造差分数组
            diff[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                diff[i] = nums[i] - nums[i - 1];
            }
        }

        /* 给闭区间 [i, j] 增加 val（可以是负数）*/
        public void increment(int i, int j, int val) {
            diff[i] += val;
            if (j + 1 < diff.length) {
                diff[j + 1] -= val;
            }
        }

        /* 返回结果数组 */
        public int[] result() {
            int[] res = new int[diff.length];
            // 根据差分数组构造结果数组
            res[0] = diff[0];
            for (int i = 1; i < diff.length; i++) {
                res[i] = res[i - 1] + diff[i];
            }
            return res;
        }
    }
    public int[] corpFlightBookings(int[][] bookings, int n) {
        // nums 初始化为全 0
        int[] nums = new int[n];
        // 构造差分解法
        Difference df = new Difference(nums);

        for (int[] booking : bookings) {
            // 注意转成数组索引要减一哦
            int i = booking[0] - 1;
            int j = booking[1] - 1;
            int val = booking[2];
            // 对区间 nums[i..j] 增加 val
            df.increment(i, j, val);
        }
        // 返回最终的结果数组
        return df.result();
    }
    public boolean carPooling(int[][] trips, int capacity) {
        int[] nums = new int[1001];
        Difference dfHelper = new Difference(nums);

        for(int[] tmp : trips){
            int val = tmp[0];
            int i = tmp[1];
            int j = tmp[2] - 1;

            dfHelper.increment(i, j, val);
        }

        int[] res = dfHelper.result();
        for(int a : res){
            if(a > capacity) return false;
        }
        return true;
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // do the mirror symmetry for the matrix first
        //just swap the top-right part with the bottom-left part, so let j >= i!
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // swap(matrix[i][j], matrix[j][i]);
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // then reverse each row in this matrix
        for (int[] row : matrix) {
            reverse(row);
        }
    }

    // reverse an array
    private void reverse(int[] arr) {
        int i = 0, j = arr.length - 1;
        while (j > i) {
            // swap(arr[i], arr[j]);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

    //while the traverse of this matrix, the corresponding bounds(top bottom left right)
    //shrink until the spiral traverses the entire matrix!
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

    //same strategy as above
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int upper_bound = 0, lower_bound = n - 1;
        int left_bound = 0, right_bound = n - 1;
        // the number to fill in the matrix
        int num = 1;

        while (num <= n * n) {
            if (upper_bound <= lower_bound) {
                // on the top, traverse from left to right
                for (int j = left_bound; j <= right_bound; j++) {
                    matrix[upper_bound][j] = num++;
                }
                //top++
                upper_bound++;
            }

            if (left_bound <= right_bound) {
                // on the right, traverse from top to bottom
                for (int i = upper_bound; i <= lower_bound; i++) {
                    matrix[i][right_bound] = num++;
                }
                // right++
                right_bound--;
            }

            if (upper_bound <= lower_bound) {
                // on the bottom, traverse from right to left
                for (int j = right_bound; j >= left_bound; j--) {
                    matrix[lower_bound][j] = num++;
                }
                // bottom++
                lower_bound--;
            }

            if (left_bound <= right_bound) {
                // on the left side, traverse from bottom to top
                for (int i = lower_bound; i >= upper_bound; i--) {
                    matrix[i][left_bound] = num++;
                }
                // left++
                left_bound++;
            }
        }
        return matrix;
    }

    public String minWindow(String s, String t) {
        // window and records of need
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        // count each character's frequency in string t
        for (char c : t.toCharArray())
            need.put(c, need.getOrDefault(c, 0) + 1);

        int left = 0, right = 0;
        int valid = 0; // The number of characters in the window to meet the needs

        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            // c 是将移入窗口的字符
            char c = s.charAt(right);
            // 扩大窗口
            right++;
            // 进行窗口内数据的一系列更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c)))
                    valid++; // 只有当 window[c] 和 need[c] 对应的出现次数一致时，才能满足条件，valid 才能 +1
            }

            // 判断左侧窗口是否要收缩
            while (valid == need.size()) {
                // 更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // d 是将移出窗口的字符
                char d = s.charAt(left);
                // 缩小窗口
                left++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d)))
                        valid--; // 只有当 window[d] 内的出现次数和 need[d] 相等时，才能 -1
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        // 返回最小覆盖子串
        return len == Integer.MAX_VALUE ?
                "" : s.substring(start, start + len);
    }
}

