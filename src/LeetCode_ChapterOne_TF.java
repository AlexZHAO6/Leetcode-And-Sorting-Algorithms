import java.util.*;

public class LeetCode_ChapterOne_TF {
    public static void main(String[] args) {
        String originalString = "io.ebay.rheos.kafka.security.iaf.login.RaptorioIAFLoginModule required iafConsumerId=\"urn:ebay-marketplace-consumerid:1069372b-f03a-4e97-b598-a9efa0bd5487\" appName=\"datclnsvc\";";
        String SASL_JAAS_CONFIG = Base64.getUrlEncoder().encodeToString(originalString.getBytes());
        System.out.println(SASL_JAAS_CONFIG);

        List<Integer> re = new ArrayList<>();
        re.add(1);
        re.add(2);
        re.clear();
        System.out.println(re.size());
        System.out.println(new String(Base64.getUrlDecoder().decode("aW8uZWJheS5yaGVvcy5rYWZrYS5zZWN1cml0eS5pYWYubG9naW4uUmFwdG9yaW9JQUZMb2dpbk1vZHVsZSByZXF1aXJlZCBpYWZDb25zdW1lcklkPSJ1cm46ZWJheS1tYXJrZXRwbGFjZS1jb25zdW1lcmlkOjEwNjkzNzJiLWYwM2EtNGU5Ny1iNTk4LWE5ZWZhMGJkNTQ4NyIgYXBwTmFtZT0iZGF0Y2xuc3ZjIjs=")));

    }
}

class LinkedListAlgorithms_One{
    private ListNode successor = null;
    private ListNode left;
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy, q = head;

        while(q != null){
            if(q.next != null && q.val == q.next.val){
                while(q.next != null && q.val == q.next.val){
                    q = q.next;
                }

                q = q.next;
                if(q == null) p.next = null;
            }
            else {
                p.next = q;
                p = p.next;
                q = q.next;
            }
        }

        return dummy.next;
    }


    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;


        return last;
    }


    // 反转以 head 为起点的 n 个节点，返回新的头结点
    ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);

        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;
    }

    //反转一部分，用到了反转前n个节点！！
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // base case
        if (m == 1) {
            return reverseN(head, n);
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }


    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null) return head;

        ListNode pre = head, cur = head;
        for(int i = 0; i < k; i++){
            // 不足 k 个，不需要反转，base case
            if(cur == null) return head;
            cur = cur.next;
        }

        // 反转前 k 个元素
        ListNode first = reverse(pre, cur);
        // 递归反转后续链表并连接起来
        pre.next = reverseKGroup(cur, k);

        return first;
    }
    /** 反转区间 [a, b) 的元素，注意是左闭右开 */
    ListNode reverse(ListNode a, ListNode b) {
        ListNode pre, cur, nxt;
        pre = null; cur = a; nxt = a;
        // while 终止的条件改一下就行了
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的头结点
        return pre;
    }


    public boolean isPalindrome(ListNode head) {
        left = head;

        return traverse(head);
    }
    boolean traverse(ListNode right) {
        if (right == null) return true;
        boolean res = traverse(right.next);
        // 后序遍历代码
        res = res && (right.val == left.val);
        left = left.next;
        return res;
    }

}
class ArrayAlgorithms_One{
    /* 注意：调用这个函数之前一定要先给 nums 排序 */
// n 填写想求的是几数之和，start 从哪个索引开始计算（一般填 0），target 填想凑出的目标和
    public List<List<Integer>> nSumTarget(
            List<Integer> nums, int n, int start, long target) {

        int sz = nums.size();
        List<List<Integer>> res = new ArrayList<>();
        // 至少是 2Sum，且数组大小不应该小于 n
        if (n < 2 || sz < n) return res;
        // 2Sum 是 base case
        if (n == 2) {
            // 双指针那一套操作
            int lo = start, hi = sz - 1;
            while (lo < hi) {
                int sum = nums.get(lo) + nums.get(hi);
                int left = nums.get(lo), right = nums.get(hi);
                if (sum < target) {
                    while (lo < hi && nums.get(lo) == left) lo++;
                } else if (sum > target) {
                    while (lo < hi && nums.get(hi) == right) hi--;
                } else {
                    res.add(Arrays.asList(left, right));
                    while (lo < hi && nums.get(lo) == left) lo++;
                    while (lo < hi && nums.get(hi) == right) hi--;
                }
            }
        } else {
            // n > 2 时，递归计算 (n-1)Sum 的结果
            for (int i = start; i < sz; i++) {
                List<List<Integer>>
                        sub = nSumTarget(nums, n - 1, i + 1, target - nums.get(i));
                for (List<Integer> arr : sub) {
                    // (n-1)Sum 加上 nums[i] 就是 nSum
                    arr.add(nums.get(i));
                    res.add(arr);
                }
                //跳过重复的数字
                while (i < sz - 1 && nums.get(i) == nums.get(i + 1)) i++;
            }
        }
        return res;
    }

    /*
    *
    * ++++++++++ 前缀和 ++++++++++++ ++++++++++ 前缀和 ++++++++++++
    * ++++++++++ 前缀和 ++++++++++++ ++++++++++ 前缀和 ++++++++++++
    * ++++++++++ 前缀和 ++++++++++++ ++++++++++ 前缀和 ++++++++++++
    *
    * */
    class NumArray {
        private int[] preSum;
        public NumArray(int[] nums) {
            preSum = new int[nums.length + 1];
            // 计算 nums 的累加和
            for (int i = 1; i < preSum.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }
        }

        public int sumRange(int left, int right) {
            return preSum[right + 1] - preSum[left];
        }
    }

    class NumMatrix {
        // 定义：preSum[i][j] 记录 matrix 中子矩阵 [0, 0, i-1, j-1] 的元素和
        private int[][] preSum;
        public NumMatrix(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            if (m == 0 || n == 0) return;
            // 构造前缀和矩阵
            preSum = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    // 计算每个矩阵 [0, 0, i, j] 的元素和
                    preSum[i][j] = preSum[i-1][j] + preSum[i][j-1] + matrix[i - 1][j - 1] - preSum[i-1][j-1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return preSum[row2+1][col2+1] - preSum[row2+1][col1] - preSum[row1][col2+1] + preSum[row1][col1];
        }
    }

    /*
     *
     * ++++++++++ 差分 ++++++++++++ ++++++++++ 差分 ++++++++++++
     * ++++++++++ 差分 ++++++++++++ ++++++++++ 差分 ++++++++++++
     * ++++++++++ 差分 ++++++++++++ ++++++++++ 差分 ++++++++++++
     *
     * */
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

    public int[] getModifiedArray(int length, int[][] updates) {
        // nums 初始化为全 0
        int[] nums = new int[length];
        Difference df = new Difference(nums);

        for(int[] up : updates){
            int start = up[0];
            int end = up[1];
            int incr = up[2];
            df.increment(start, end, incr);
        }

        return df.result();
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] nums = new int[n];
        Difference df = new Difference(nums);

        for(int[] booking : bookings){
            int i = booking[0];
            int j = booking[1];
            int incr = booking[2];

            df.increment(i-1, j-1, incr);
        }
        return df.result();
    }

    public boolean carPooling(int[][] trips, int capacity) {
        int[] nums = new int[1001];
        Difference df = new Difference(nums);

        for(int[] trip : trips){
            int i = trip[1];
            int j = trip[2] - 1; // because the passenger has already get off on trip[2]
            int incr = trip[0];

            df.increment(i, j, incr);
        }

        nums = df.result();
        for(int a : nums){
            if(a > capacity) return false;
        }
        return true;
    }

    /*
     *
     * ++++++++++ 二维数组的花式遍历技巧 ++++++++++++ ++++++++++ 二维数组的花式遍历技巧 ++++++++++++
     * ++++++++++ 二维数组的花式遍历技巧 ++++++++++++ ++++++++++ 二维数组的花式遍历技巧 ++++++++++++
     * ++++++++++ 二维数组的花式遍历技巧 ++++++++++++ ++++++++++ 二维数组的花式遍历技巧 ++++++++++++
     *
     * */

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 先沿对角线镜像对称二维矩阵
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // swap(matrix[i][j], matrix[j][i]);
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // 然后反转二维矩阵的每一行
        for (int[] row : matrix) {
            reverse(row);
        }
    }
    void reverse(int[] arr) {
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

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int upper_bound = 0, lower_bound = m - 1;
        int left_bound = 0, right_bound = n - 1;
        List<Integer> res = new LinkedList<>();
        // res.size() == m * n 则遍历完整个数组
        while (res.size() < m * n) {
            if (upper_bound <= lower_bound) {
                // 在顶部从左向右遍历
                for (int j = left_bound; j <= right_bound; j++) {
                    res.add(matrix[upper_bound][j]);
                }
                // 上边界下移
                upper_bound++;
            }

            if (left_bound <= right_bound) {
                // 在右侧从上向下遍历
                for (int i = upper_bound; i <= lower_bound; i++) {
                    res.add(matrix[i][right_bound]);
                }
                // 右边界左移
                right_bound--;
            }

            if (upper_bound <= lower_bound) {
                // 在底部从右向左遍历
                for (int j = right_bound; j >= left_bound; j--) {
                    res.add(matrix[lower_bound][j]);
                }
                // 下边界上移
                lower_bound--;
            }

            if (left_bound <= right_bound) {
                // 在左侧从下向上遍历
                for (int i = lower_bound; i >= upper_bound; i--) {
                    res.add(matrix[i][left_bound]);
                }
                // 左边界右移
                left_bound++;
            }
        }
        return res;
    }



    public ListNode rotateRight(ListNode head, int k) {
        //具体代码中，我们首先计算出链表的长度 nnn，并找到该链表的末尾节点，将其与头节点相连。
        // 这样就得到了闭合为环的链表。
        // 然后我们找到新链表的最后一个节点（即原链表的第 (n−1)−(k mod n) 个节点），将当前闭合为环的链表断开，即可得到我们所需要的结果。
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int n = 1;
        ListNode iter = head;
        while (iter.next != null) {
            iter = iter.next;
            n++;
        }
        int add = n - k % n;
        if (add == n) {
            return head;
        }
        iter.next = head;
        while (add-- > 0) {
            iter = iter.next;
        }
        ListNode ret = iter.next;
        iter.next = null;
        return ret;
    }

    public String removeDuplicateLetters(String s) {
        Stack<Character> stk = new Stack<>();

        // 维护一个计数器记录字符串中字符的数量
        // 因为输入为 ASCII 字符，大小 256 够用了
        int[] count = new int[256];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }

        boolean[] inStack = new boolean[256];
        for (char c : s.toCharArray()) {
            // 每遍历过一个字符，都将对应的计数减一
            count[c]--;

            if (inStack[c]) continue;

            while (!stk.isEmpty() && stk.peek() > c) {
                // 若之后不存在栈顶元素了，则停止 pop
                if (count[stk.peek()] == 0) {
                    break;
                }
                // 若之后还有，则可以 pop
                inStack[stk.pop()] = false;
            }
            stk.push(c);
            inStack[c] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!stk.empty()) {
            sb.append(stk.pop());
        }
        return sb.reverse().toString();
    }
}

class BinaryTree_ChapterOne{
    // 记录所有子树以及出现的次数
    HashMap<String, Integer> subTrees = new HashMap<>();
    // 记录重复的子树根节点
    LinkedList<TreeNode> res = new LinkedList<>();

    int rank;
    int res_kth;

    int sum_converBST;

    // 备忘录
    int[][] memo;
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }

    class DiameterOfBinaryTree {
        int longestPath = 0;
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
    }

    class BinaryTree_level_order_Traverse {
        //using recursion to solve!!!!
        List<List<Integer>> res = new ArrayList<>();

        List<List<Integer>> levelTraverse(TreeNode root) {
            if (root == null) {
                return res;
            }
            // root 视为第 0 层
            traverse(root, 0);
            return res;
        }

        void traverse(TreeNode root, int depth) {
            if (root == null) {
                return;
            }
            // 前序位置，看看是否已经存储 depth 层的节点了
            if (res.size() <= depth) {
                // 第一次进入 depth 层
                res.add(new LinkedList<>());
            }
            // 前序位置，在 depth 层添加 root 节点的值
            res.get(depth).add(root.val);
            traverse(root.left, depth + 1);
            traverse(root.right, depth + 1);
        }
    }

    public TreeNode invertTree(TreeNode root) {
        //核心在于你要给递归函数一个合适的定义，然后用函数的定义来解释你的代码；如果你的逻辑成功自恰，那么说明你这个算法是正确的。
        if(root == null) return null;

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;
        return root;
    }

    public Node_TF connect(Node_TF root) {
        if (root == null) return null;
        // 遍历「三叉树」，连接相邻节点
        traverse(root.left, root.right);
        return root;
    }
    // 三叉树遍历框架
    void traverse(Node_TF node1, Node_TF node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        /**** 前序位置 ****/
        // 将传入的两个节点穿起来
        node1.next = node2;

        // 连接相同父节点的两个子节点
        traverse(node1.left, node1.right);
        traverse(node2.left, node2.right);
        // 连接跨越父节点的两个子节点
        traverse(node1.right, node2.left);
    }

    public void flatten(TreeNode root) {
        if(root == null) return;

        // 利用定义，把左右子树拉平
        flatten(root.left);
        flatten(root.right);

        /**** 后序遍历位置 ****/
        // 1、左右子树已经被拉平成一条链表
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 2、将左子树作为右子树
        root.left = null;
        root.right = left;

        // 3、将原先的右子树接到当前右子树的末端
        TreeNode p = root;
        while(p.right != null) p = p.right;
        p.right = right;
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }
    // 定义：将 nums[lo..hi] 构造成符合条件的树，返回根节点
    TreeNode build(int[] nums, int lo, int hi) {
        // base case
        if (lo > hi) {
            return null;
        }

        // 找到数组中的最大值和对应的索引
        int index = -1, maxVal = Integer.MIN_VALUE;
        for (int i = lo; i <= hi; i++) {
            if (maxVal < nums[i]) {
                index = i;
                maxVal = nums[i];
            }
        }

        // 先构造出根节点
        TreeNode root = new TreeNode(maxVal);
        // 递归调用构造左右子树
        root.left = build(nums, lo, index - 1);
        root.right = build(nums, index + 1, hi);

        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree_help(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    TreeNode buildTree_help(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd){
        if(preStart > preEnd || inStart > inEnd) return null;

        int rootVal = preorder[preStart];
        // rootVal 在中序遍历数组中的索引
        int index = -1;
        for(int i = inStart; i <= inEnd; i++){
            if(inorder[i] == rootVal){
                index = i;
                break;
            }
        }

        int leftSize = index - inStart;
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree_help(preorder, preStart+1,preStart + leftSize,inorder, inStart, index - 1);
        root.right = buildTree_help(preorder,preStart + leftSize + 1,preEnd, inorder, index+1, inEnd);

        return root;
    }

    public TreeNode buildTree_2(int[] inorder, int[] postorder) {
        return buildTree_2_help(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }
    TreeNode buildTree_2_help(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd){
        if (inStart > inEnd) {
            return null;
        }

        int rootVal = postorder[postEnd];
        // rootVal 在中序遍历数组中的索引
        int index = -1;
        for(int i = inStart; i <= inEnd; i++){
            if(inorder[i] == rootVal){
                index = i;
                break;
            }
        }
        int leftSize = index - inStart;
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree_2_help(inorder, inStart,index - 1, postorder, postStart, postStart + leftSize - 1);
        root.right = buildTree_2_help(inorder,index + 1, inEnd, postorder, postStart + leftSize, postEnd - 1);

        return root;
    }

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return constructFromPrePost_help(preorder,0, preorder.length - 1, postorder, 0, postorder.length - 1);
    }
    TreeNode constructFromPrePost_help(int[] preorder, int preStart, int preEnd, int[] postorder, int postStart, int postEnd){
        //1、首先把前序遍历结果的第一个元素或者后序遍历结果的最后一个元素确定为根节点的值。
        //
        //2、然后把前序遍历结果的第二个元素作为左子树的根节点的值。
        //
        //3、在后序遍历结果中寻找左子树根节点的值，从而确定了左子树的索引边界，进而确定右子树的索引边界，递归构造左右子树即可。
        if (preStart > preEnd) {
            return null;
        }
        if (preStart == preEnd) {
            return new TreeNode(preorder[preStart]);
        }

        int rootVal = preorder[preStart];
        int leftRoot = preorder[preStart + 1];
        int index = -1;
        for(int i = postStart; i <= postEnd; i++){
            if(leftRoot == postorder[i]){
                index = i;
                break;
            }
        }
        int leftSize = index - postStart + 1;

        TreeNode root = new TreeNode(rootVal);
        root.left = constructFromPrePost_help(preorder, preStart + 1, preStart + leftSize, postorder, postStart, index);
        root.right = constructFromPrePost_help(preorder, preStart + leftSize + 1, preEnd, postorder, index + 1, postEnd);

        return root;
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        serialize(root);
        return res;
    }
    String serialize(TreeNode root) {
        if (root == null) {
            return "#";
        }

        // 先算左右子树的序列化结果
        String left = serialize(root.left);
        String right = serialize(root.right);

        String myself = left + "," + right+ "," + root.val;

        int freq = subTrees.getOrDefault(myself, 0);
        // 多次重复也只会被加入结果集一次
        if (freq == 1) {
            res.add(root);
        }
        // 给子树对应的出现次数加一
        subTrees.put(myself, freq + 1);
        return myself;
    }

    public int kthSmallest(TreeNode root, int k) {
        traverse_kth(root, k);
        return res_kth;
    }
    void traverse_kth(TreeNode root, int k){
        if (root == null) {
            return;
        }
        traverse_kth(root.left, k);
        /* 中序遍历代码位置 */
        rank++;
        if (k == rank) {
            // 找到第 k 小的元素
            res_kth = root.val;
            return;
        }
        /*****************/
        traverse_kth(root.right, k);
    }


    //核心还是 BST 的中序遍历特性，只不过我们修改了递归顺序，降序遍历 BST 的元素值，从而契合题目累加树的要求

    public TreeNode convertBST(TreeNode root) {
        convertBST_help(root);
        return root;
    }
    void convertBST_help(TreeNode root){
        //traverse right and then root and then left;
        //this can sort the val by desc order
        if(root == null) return;
        convertBST_help(root.right);

        sum_converBST += root.val;
        root.val = sum_converBST;

        convertBST_help(root.left);
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        // 去左子树搜索
        if (root.val > val) {
            return searchBST(root.left, val);
        }
        // 去右子树搜索
        if (root.val < val) {
            return searchBST(root.right, val);
        }
        return root;
    }

    public int numTrees(int n) {
        memo = new int[n + 1][n + 1];
        return count(1, n);
    }
    int count(int left, int right){
        if(left > right) return 1;

        // 查备忘录
        if (memo[left][right] != 0) {
            return memo[left][right];
        }

        int res = 0;
        for (int mid = left; mid <= right; mid++) {
            int l = count(left, mid - 1);
            int r = count(mid + 1, right);
            res += l * r;
        }
        // 将结果存入备忘录
        memo[left][right] = res;

        return res;
     }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new LinkedList<>();
        // 构造闭区间 [1, n] 组成的 BST
        return build(1, n);
    }
    List<TreeNode> build(int lo, int hi){
        List<TreeNode> res = new ArrayList<>();
        if(lo > hi){
            // 这里需要装一个 null 元素，这样才能让下面的两个内层 for 循环都能进入，正确地创建出叶子节点
            // 举例来说吧，什么时候会进到这个 if 语句？当你创建叶子节点的时候，对吧。
            // 那么如果你这里不加 null，直接返回空列表，那么下面的内层两个 for 循环都无法进入
            // 你的那个叶子节点就没有创建出来，看到了吗？所以这里要加一个 null，确保下面能把叶子节点做出来
            res.add(null);
            return res;
        }

        // 1、穷举 root 节点的所有可能。
        for(int i = lo; i <= hi; i++){
            // 2、递归构造出左右子树的所有有效 BST。
            List<TreeNode> left = build(lo, i - 1);
            List<TreeNode> right = build(i + 1, hi);

            // 3、给 root 节点穷举所有左右子树的组合。
            for(TreeNode l : left){
                for(TreeNode r : right){
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;

                    res.add(root);
                }
            }
        }

        return res;
    }


}
// Definition for a Node.
class Node_TF {
    public int val;
    public Node_TF left;
    public Node_TF right;
    public Node_TF next;

    public Node_TF() {}

    public Node_TF(int _val) {
        val = _val;
    }

    public Node_TF(int _val, Node_TF _left, Node_TF _right, Node_TF _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

