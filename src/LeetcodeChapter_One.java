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
    static class NumMatrix {
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

}






























