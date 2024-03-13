import java.util.*;

public class LeetCode_ChapterZero_TF {
    public static void main(String[] args) {
        LinkedListAlgorithms_TF linkedListAlgorithms = new LinkedListAlgorithms_TF();
        ArrayAlgorithms_TF arrayAlgorithmsTf = new ArrayAlgorithms_TF();

    }

}
class LinkedListAlgorithms_TF{
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        // virtual head node
        ListNode dummy = new ListNode(-1), p = dummy;
        ListNode p1 = l1, p2 = l2;

        while(p1 != null && p2 != null){
            if(p1.val >= p2.val){
                p.next = p2;
                p2 = p2.next;
            }
            else {
                p.next = p1;
                p1 = p1.next;
            }

            p = p.next;
        }

        if(p1 != null) p.next = p1;

        if(p2 != null) p.next = p2;

        return dummy.next;
    }

    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(-1);
        ListNode dummy2 = new ListNode(-2);

        ListNode p1 = dummy1, p2 = dummy2;
        ListNode p = head;

        while(p != null){
            if(p.val < x){
                p1.next = p;
                p1 = p1.next;
            }
            else {
                p2.next = p;
                p2 = p2.next;
            }

            ListNode tmp = p.next;
            p.next = null;
            p = tmp;
        }

        p1.next = dummy2.next;

        return dummy1.next;
    }

    //using min-heap
    //time complexity: O(Nlogk) , N is node nums, k is the length of the list
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        // 小顶堆
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                lists.length, (a, b) -> (a.val - b.val));

        for(ListNode node : lists){
            if(node != null) pq.add(node);
        }

        while (!pq.isEmpty()){
            ListNode tmp = pq.poll();
            p.next = tmp;
            p = p.next;
            if(tmp.next != null) pq.add(tmp.next);
        }

        return dummy.next;
    }

    //using dummy node for simplify
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode p1 = dummy, p2 = dummy;

        for(int i = 0; i < n + 1; i++){
            p1 = p1.next;
        }

        while(p1 != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        if(p2.next != null) p2.next = p2.next.next;
        return dummy.next;
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }

        return false;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) break;
        }

        if(fast == null || fast.next == null) return null;

        //can explain by calculation, slow and fast will meet at the start point
        //of the circle
        slow = head;
        while(fast != slow){
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;

        while (p1 != p2){
            if(p1 == null) p1 = headB;
            else p1 = p1.next;

            if(p2 == null) p2 = headA;
            else p2 = p2.next;
        }

        return p1;
    }

}

class ArrayAlgorithms_TF{
    //我们让慢指针 slow 走在后面，快指针 fast 走在前面探路，找到一个不重复的元素就赋值给 slow 并让 slow 前进一步
    public int removeDuplicates(int[] nums) {
        int slow = 0, fast = 0;

        while(fast < nums.length){
            if(nums[slow] != nums[fast]){
                slow++;
                nums[slow] = nums[fast];
            }

            fast++;
        }

        return slow + 1;
    }

    //same logic as above
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        ListNode slow = head, fast = head;

        while(fast != null){
            if(slow.val != fast.val){
                slow.next = fast;
                slow = slow.next;
            }

            fast = fast.next;
        }

        slow.next = null;
        return head;
    }

    //如果 fast 遇到值为 val 的元素，则直接跳过，否则就赋值给 slow 指针，并让 slow 前进一步。
    public int removeElement(int[] nums, int val) {
        int slow = 0, fast = 0;

        while(fast < nums.length){
            if(nums[fast] != val){
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        return slow;
    }


    //题目让我们将所有 0 移到最后，其实就相当于移除 nums 中的所有 0，然后再把后面的元素都赋值为 0 即可。
    public void moveZeroes(int[] nums) {
        int slow = 0, fast = 0;

        while(fast < nums.length){
            if(nums[fast] != 0){
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        for(; slow < nums.length; slow++){
            nums[slow] = 0;
        }
    }

    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right){
            if(numbers[left] + numbers[right] == target){
                return new int[]{left+1, right+1};
            }
            else if(numbers[left] + numbers[right] < target) left++;
            else right--;
        }

        return new int[]{-1,-1};
    }

    public String longestPalindrome(String s) {
        String res = "";

        for(int i = 0; i < s.length(); i++){
            String tmp1 = longestPalindrome_help(s,i,i);
            String tmp2 = longestPalindrome_help(s,i,i+1);

            res = res.length() < tmp1.length() ? tmp1 : res;
            res = res.length() < tmp2.length() ? tmp2 : res;
        }

        return res;
    }
    public String longestPalindrome_help(String s, int l, int r){
        while(l >=0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }

        return s.substring(l+1, r);
    }

}

class BinaryTreeAlgorithms_TF{
    int depth = 0;
    int res = 0;
    //遍历一遍二叉树，用一个外部变量记录每个节点所在的深度，取最大值就可以得到最大深度，这就是遍历二叉树计算答案的思路。
    int longestPath = 0;
    public int maxDepth(TreeNode root) {
        maxDepth_traverse(root);
        return res;
    }
    void maxDepth_traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序位置
        depth++;
        if (root.left == null && root.right == null) {
            // 到达叶子节点，更新最大深度
            res = Math.max(res, depth);
        }
        maxDepth_traverse(root.left);
        maxDepth_traverse(root.right);
        // 后序位置
        depth--;
    }

    //分解思路：由子问题推导出问题
    public int maxDepth2(TreeNode root) {
        if(root == null) return 0;

        int left = maxDepth2(root.left);
        int right = maxDepth2(root.right);

        return Math.max(left, right) + 1;
    }

    //每一条二叉树的「直径」长度，就是一个节点的左右子树的最大深度之和。
    //通过后续遍历，基于左右子树最大深度 得到当前节点直径长度 traverse求解
    public int diameterOfBinaryTree(TreeNode root) {
        diameterOfBinaryTree_traverse(root);

        return longestPath;
    }
    int diameterOfBinaryTree_traverse(TreeNode root){
        if(root == null) return 0;

        int left = diameterOfBinaryTree_traverse(root.left);
        int right = diameterOfBinaryTree_traverse(root.right);

        int currtMax = left + right;
        longestPath = Math.max(currtMax, longestPath);

        return 1 + Math.max(left, right);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorderTraversal_help(root, res);
        return res;
    }
    void preorderTraversal_help(TreeNode root, List<Integer> res){
        if(root == null) return;

        res.add(root.val);
        preorderTraversal_help(root.left, res);
        preorderTraversal_help(root.right, res);
    }
}

class dpAlgorithms_TF{
    public int fib(int n) {
        if (n == 0) return 0;
        int[] dp = new int[n + 1];
        // base case
        dp[0] = 0; dp[1] = 1;
        // 状态转移
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // 数组大小为 amount + 1，初始值也为 amount + 1
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;
        for(int i  = 1; i <= amount; i++){
            for(int coin : coins){
                if(i - coin < 0) continue;

                dp[i] = Math.min(dp[i], dp[i-coin] + 1);
            }
        }

        return (dp[amount] == amount + 1) ? -1 : dp[amount];

    }
}

class backtrackAlgorithms{
    List<List<Integer>> res_permute = new LinkedList<>();
    List<List<String>> res_Nqueues = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        // 「路径」中的元素会被标记为 true，避免重复使用
        boolean[] used = new boolean[nums.length];

        backtrack_permute(nums, track, used);
        return res_permute;
    }
    void backtrack_permute(int[] nums, LinkedList<Integer> track,  boolean[] used){
        if(track.size() == nums.length){
            res_permute.add(new ArrayList<>(track));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if(used[i]) continue;

            //make choice
            used[i] = true;
            track.add(nums[i]);

            //move to the next level of the decision tree
            backtrack_permute(nums, track, used);

            //cancel the choice
            used[i] = false;
            track.removeLast();
        }
    }


    public List<List<String>> solveNQueens(int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append('.');
            }
            board.add(sb.toString());
        }
        backtrack_nQueues(board, 0);
        return res_Nqueues;
    }
    void backtrack_nQueues(List<String> board, int row){
        if(row == board.size()){
            res_Nqueues.add(new ArrayList<>(board));
            return;
        }

        int n = board.get(row).length();
        for(int col = 0; col < n; col++){
            if(!isValid(board, row, col)) continue;

            StringBuilder sb = new StringBuilder(board.get(row));
            sb.setCharAt(col, 'Q');
            board.set(row, sb.toString());

            backtrack_nQueues(board, row+1);

            sb.setCharAt(col, '.');
            board.set(row, sb.toString());
        }
    }
    /* 是否可以在 board[row][col] 放置皇后？ */
    boolean isValid(List<String> board, int row, int col) {
        int n = board.size();
        // 检查列是否有皇后互相冲突
        for (int i = 0; i <= row; i++) {
            if (board.get(i).charAt(col) == 'Q')
                return false;
        }
        // 检查右上方是否有皇后互相冲突
        for (int i = row - 1, j = col + 1;
             i >= 0 && j < n; i--, j++) {
            if (board.get(i).charAt(j) == 'Q')
                return false;
        }
        // 检查左上方是否有皇后互相冲突
        for (int i = row - 1, j = col - 1;
             i >= 0 && j >= 0; i--, j--) {
            if (board.get(i).charAt(j) == 'Q')
                return false;
        }
        return true;
    }


    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();

        backtrack_subsets(res, track, nums, 0);

        return res;
    }
    void backtrack_subsets(List<List<Integer>> res, LinkedList<Integer> track, int[] nums, int start) {
        res.add(new ArrayList<>(track));

        //使用 start 参数控制树枝的生长避免产生重复的子集
        for(int i = start; i < nums.length; i++){
            track.add(nums[i]);

            backtrack_subsets(res, track, nums, i+1);

            track.removeLast();
        }
    }


    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();

        backtrack_combine(res, track, n, k, 1);

        return res;
    }
    void backtrack_combine(List<List<Integer>> res, LinkedList<Integer> track, int n, int k, int start) {
        if(k == track.size()){
            res.add(new ArrayList<>(track));
            return;
        }

        //使用 start 参数控制树枝的生长避免产生重复的子集
        for(int i = start; i <= n; i++){
            track.add(i);

            backtrack_combine(res, track, n, k, i+1);

            track.removeLast();
        }
    }

}





