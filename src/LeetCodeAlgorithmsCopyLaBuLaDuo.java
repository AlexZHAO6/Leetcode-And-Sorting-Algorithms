import java.util.*;

public class LeetCodeAlgorithmsCopyLaBuLaDuo {
    public static void main(String[] args){
        System.out.println("Alex GOGOGO");
    }
}

class Algorithms
{
    private int maxBinaryTreeDepth = 0;
    //two pointer, easy
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

       if(list1 != null) res.next = list1;
       if(list2 != null) res.next = list2;

       return dummy.next;
    }

    //two pointer,similar to the previous one
    //split the original list by the value of x, and then merge
    public ListNode partition(ListNode head, int x) {
        ListNode dummySmall = new ListNode(-1);
        ListNode dummyLarge = new ListNode(-1);

        ListNode small = dummySmall;
        ListNode large = dummyLarge;

        ListNode p = head;
        while(p != null){
            if(p.val >= x){
                large.next = p;
                large = large.next;
            }
            else {
                small.next = p;
                small = small.next;
            }

            ListNode tmp = p.next;
            p.next = null;
            p = tmp;
        }

        small.next = dummyLarge.next;

        return dummySmall.next;
    }

    //two pointer
    //the first pointer move n steps, and then the second pointer start to move from head until
    //the first pointer = null.
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummmy = new ListNode(-1);
        dummmy.next = head;

        ListNode first = head;
        for(int i = 0; i < n; i++){
            if(first != null) first = first.next;
        }

        //利用dummy节点，防止空指针，简化代码
        //从dummy节点走n步，即要删除节点的前一个节点
        ListNode second = dummmy;

        while(first != null){
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;
        return dummmy.next;
    }

    //similar to meregeTwoList, Using binary-heap!!
    //time complexity: Nlogk, N = the number of nodes, k = the length of the lists;
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;

        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        //min heap, implement by PriorityQueue
        PriorityQueue<ListNode> myqueue = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);

        for(ListNode tmp : lists){
            if(tmp != null) myqueue.add(tmp);
        }

        while (!myqueue.isEmpty()){
            ListNode tmp = myqueue.poll();
            p.next = tmp;
            if(tmp.next != null) myqueue.add(tmp.next);

            p = p.next;
        }

        return dummy.next;
    }

    //slow and fast pointer
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }


    //slow and fast pointer
    //fast 一定比 slow 多走了 k 步，这多走的 k 步其实就是 fast 指针在环里转圈圈，所以 k 的值就是环长度的「整数倍」。
    //
    //假设相遇点距环的起点的距离为 m，那么结合上图的 slow 指针，环的起点距头结点 head 的距离为 k - m，也就是说如果从 head 前进 k - m 步就能到达环起点。
    //
    //巧的是，如果从相遇点继续前进 k - m 步，也恰好到达环起点。
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) break;
        }

        if(fast == null || fast.next == null) return null;

        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //traverse headA and then headB
    //traverse headB and then headA
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;

        while(p1 != p2){
            if(p1 == null) p1 = headB;
            else p1 = p1.next;

            if(p2 == null) p2 = headA;
            else p2 = p2.next;
        }

        return p1;
    }


    //slow fast pointer
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                // 维护 nums[0..slow] 无重复
                nums[slow] = nums[fast];
            }
            fast++;
        }
        // 数组长度为索引 + 1
        return slow + 1;
    }

    //slow fast pointer
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

    //slow fast pointer, slight difference
    public int removeElement(int[] nums, int val) {
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

    public void moveZeroes(int[] nums) {
        int len = nums.length;
        int slow = 0, fast = 0;

        while(fast < len){
            if(nums[fast] != 0){
                int tmp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = tmp;

                slow++;
            }
            fast++;
        }
    }

    //tow pointer, 中心扩展
    //回文串可以是奇数也可以是偶数！！
    public String longestPalindrome(String s) {
        int len = s.length();
        String res = "";
        for(int i = 0; i < len; i++){
            String s1 = palindrome(s, i, i);
            String s2 = palindrome(s, i, i + 1);

            res = res.length() < s1.length() ? s1 : res;
            res = res.length() < s2.length() ? s2 : res;
        }

        return res;
    }
    public String palindrome(String s, int l, int r) {
        // 防止索引越界
        while (l >= 0 && r < s.length()
                && s.charAt(l) == s.charAt(r)) {
            // 双指针，向两边展开
            l--; r++;
        }
        // 返回以 s[l] 和 s[r] 为中心的最长回文串
        return s.substring(l + 1, r);
    }

    public int maxDepth(TreeNode root) {
        int res = 0;
        if(root == null) return res;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        res = Math.max(left, right) + 1;
        return res;
    }

    //只有后序位置才能通过返回值获取子树的信息。!!!
    //那么换句话说，一旦你发现题目和子树有关，那大概率要给函数设置合理的定义和返回值，在后序位置写代码了。
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


    //每一条二叉树的「直径」长度，就是一个节点的左右子树的最大深度之和。
    //递归函数返回:此节点的最大深度
    //O(n)复杂度
    public int diameterOfBinaryTree(TreeNode root) {
        diameterOfBinaryTree_help(root);

        return maxBinaryTreeDepth;
    }
    public int diameterOfBinaryTree_help(TreeNode root){
        if(root == null) return 0;

        int leftMax = diameterOfBinaryTree_help(root.left);
        int rightMax = diameterOfBinaryTree_help(root.right);

        int max = leftMax + rightMax;
        maxBinaryTreeDepth = Math.max(maxBinaryTreeDepth, max);

        return 1 + Math.max(leftMax, rightMax);
    }

    //classic DP
    //dp[i] represent the minimum number of coins used for hitting i;
    public int coinChange(int[] coins, int amount) {
        int len = coins.length;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for(int i = 1; i <= amount; i++){
            for(int tmp : coins){
                if(i - tmp >= 0) dp[i] = Math.min(dp[i], dp[i - tmp] + 1);
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    //classic backtrack
    //template for backtrack:
    //result = []
    //def backtrack(路径, 选择列表):
    //    if 满足结束条件:
    //        result.add(路径)
    //        return
    //
    //    for 选择 in 选择列表:
    //        做选择
    //        backtrack(路径, 选择列表)
    //        撤销选择
    //回溯算法的一个特点，不像动态规划存在重叠子问题可以优化，回溯算法就是纯暴力穷举，复杂度一般都很高。
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> tmplist = new LinkedList<>();

        int len = nums.length;
        if(len == 0) return res;
        boolean[] visited = new boolean[len];

        permute_help(0,visited,tmplist,res,nums);
        return res;
    }
    public void permute_help(int index, boolean[] visited, List<Integer> tmplist, List<List<Integer>> res, int[] nums){
        if(index == nums.length){
            res.add(new ArrayList<>(tmplist));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if(visited[i]) continue;

            visited[i] = true;
            tmplist.add(nums[i]);
            permute_help(index+1, visited, tmplist, res, nums);
            visited[i] = false;
            tmplist.remove(tmplist.size() - 1);
        }
    }

    //classic N-queen
    //isValid 函数所需的 O(N) 复杂度，所以总的时间复杂度上界是 O(N! * N)
    public List<List<String>> solveNQueens(int n) {
        // '.' 表示空，'Q' 表示皇后，初始化空棋盘
        List<String> board = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append('.');
            }
            board.add(sb.toString());
        }
        backtrack(board, 0, res);
        return res;
    }
    // 路径：board 中小于 row 的那些行都已经成功放置了皇后
    // 选择列表：第 row 行的所有列都是放置皇后的选择
    // 结束条件：row 超过 board 的最后一行
    void backtrack(List<String> board, int row,  List<List<String>> res) {
        // 触发结束条件
        if (row == board.size()) {
            res.add(new ArrayList<>(board));
            return;
        }

        int n = board.get(row).length();
        for (int col = 0; col < n; col++) {
            // 排除不合法选择
            if (!isValid(board, row, col)) {
                continue;
            }
            // 做选择
            StringBuilder sb = new StringBuilder(board.get(row));
            sb.setCharAt(col, 'Q');
            board.set(row, sb.toString());

            // 进入下一行决策
            backtrack(board, row + 1, res);
            // 撤销选择
            sb.setCharAt(col, '.');
            board.set(row, sb.toString());
        }
    }
    /* 是否可以在 board[row][col] 放置皇后？ */
    boolean isValid(List<String> board, int row, int col) {
        int n = board.size();
        /* 检查列是否有皇后互相冲突 */
        for (int i = 0; i < n; i++) {
            if (board.get(i).charAt(col) == 'Q') {
                return false;
            }
        }
        /* 检查右上方是否有皇后互相冲突 */
        for (int i = row - 1, j = col + 1;
             i >= 0 && j < n; i--, j++) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        /* 检查左上方是否有皇后互相冲突 */
        for (int i = row - 1, j = col - 1;
             i >= 0 && j >= 0; i--, j--) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        return true;
    }

    //classic subsets
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        subsets_backtrack(0, res, tmp, nums);

        return res;
    }
    public void subsets_backtrack(int start, List<List<Integer>> res, List<Integer> tmp, int[] nums){
        res.add(new ArrayList<>(tmp));

        for(int i = start; i < nums.length; i++){
            tmp.add(nums[i]);
            // 通过 start 参数控制树枝的遍历，避免产生重复的子集
            subsets_backtrack(i+1, res, tmp, nums);
            tmp.remove(tmp.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        Arrays.sort(nums);
        subsetsWithDup_backtrack(0, res, tmp, nums);

        return res;
    }
    public void subsetsWithDup_backtrack(int start, List<List<Integer>> res, List<Integer> tmp, int[] nums){
        res.add(new ArrayList<>(tmp));

        for(int i = start; i < nums.length; i++){
            //剪枝，值相同的相邻树枝，只遍历第一条
            if(i > start && nums[i] == nums[i-1]) continue;

            tmp.add(nums[i]);
            // 通过 start 参数控制树枝的遍历，避免产生重复的子集
            subsetsWithDup_backtrack(i+1, res, tmp, nums);
            tmp.remove(tmp.size() - 1);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> tmplist = new LinkedList<>();

        int len = nums.length;
        Arrays.sort(nums);
        boolean[] visited = new boolean[len];

        permuteUnique_help(0,visited,tmplist,res,nums);
        return res;
    }
    public void permuteUnique_help(int index, boolean[] visited, List<Integer> tmplist, List<List<Integer>> res, int[] nums){
        if(index == nums.length){
            res.add(new ArrayList<>(tmplist));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if(visited[i]) continue;

            // 新添加的剪枝逻辑，固定相同的元素在排列中的相对位置
            if(i > 0 && nums[i] == nums[i-1] && !visited[i-1]) continue;

            visited[i] = true;
            tmplist.add(nums[i]);
            permuteUnique_help(index+1, visited, tmplist, res, nums);
            visited[i] = false;
            tmplist.remove(tmplist.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        int curSum = 0;
        if(candidates.length == 0) return res;

        combinationSum_backtrack(0, 0, target, track, res, candidates);
        return res;
    }
    public void combinationSum_backtrack(int start, int curSum, int target, LinkedList<Integer> track, List<List<Integer>> res, int[] candidates){
        if(curSum == target){
            res.add(new ArrayList<>(track));
        }

        if(curSum > target) return;

        for(int i = start; i < candidates.length; i++){
            curSum += candidates[i];
            track.add(candidates[i]);
            combinationSum_backtrack(i, curSum, target, track, res, candidates);
            curSum -= candidates[i];
            track.removeLast();
        }
    }

    //BFS
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        // root 本身就是一层，depth 初始化为 1
        int depth = 1;

        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                TreeNode tmp = q.poll();
                if(tmp.left == null && tmp.right == null) return depth;

                if (tmp.left != null)
                    q.offer(tmp.left);
                if (tmp.right != null)
                    q.offer(tmp.right);
            }
            depth++;
        }

        return depth;
    }

    public int openLock(String[] deadends, String target) {
        // 记录需要跳过的死亡密码
        Set<String> deads = new HashSet<>();
        for (String s : deadends) deads.add(s);
        // 记录已经穷举过的密码，防止走回头路
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        // 从起点开始启动广度优先搜索
        int step = 0;
        q.offer("0000");
        visited.add("0000");

        while (!q.isEmpty()) {
            int sz = q.size();
            /* 将当前队列中的所有节点向周围扩散 */
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();

                /* 判断是否到达终点 */
                if (deads.contains(cur))
                    continue;
                if (cur.equals(target))
                    return step;

                /* 将一个节点的未遍历相邻节点加入队列 */
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            /* 在这里增加步数 */
            step++;
        }
        // 如果穷举完都没找到目标密码，那就是找不到了
        return -1;
    }
    // 将 s[j] 向上拨动一次
    String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9')
            ch[j] = '0';
        else
            ch[j] += 1;
        return new String(ch);
    }
    // 将 s[i] 向下拨动一次
    String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0')
            ch[j] = '9';
        else
            ch[j] -= 1;
        return new String(ch);
    }

    //二分变体，寻找左边界
    //因为我们初始化 right = nums.length
    //所以决定了我们的「搜索区间」是 [left, right)
    //所以决定了 while (left < right)
    //同时也决定了 left = mid + 1 和 right = mid
    //
    //因为我们需找到 target 的最左侧索引
    //所以当 nums[mid] == target 时不要立即返回
    //而要收紧右侧边界以锁定左侧边界
   public int left_bound(int[] nums, int target) {
        int left = 0;
        int right = nums.length; // 注意

        while (left < right) { // 注意
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 找到 target 时不要立即返回，而是缩小「搜索区间」的上界 right，在区间 [left, mid) 中继续搜索，即不断向左收缩，达到锁定左侧边界的目的。
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid; // 注意
            }
        }
        return left;
    }

    //二分变体，寻找右边界
    //因为我们初始化 right = nums.length
    //所以决定了我们的「搜索区间」是 [left, right)
    //所以决定了 while (left < right)
    //同时也决定了 left = mid + 1 和 right = mid
    //
    //因为我们需找到 target 的最右侧索引
    //所以当 nums[mid] == target 时不要立即返回
    //而要收紧左侧边界以锁定右侧边界
    //
    //又因为收紧左侧边界时必须 left = mid + 1
    //所以最后无论返回 left 还是 right，必须减一
    int right_bound(int[] nums, int target) {
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                //当 nums[mid] == target 时，不要立即返回，而是增大「搜索区间」的左边界 left，使得区间不断向右靠拢，达到锁定右侧边界的目的。
                left = mid + 1; // 注意
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }

        //因为我们对 left 的更新必须是 left = mid + 1，就是说 while 循环结束时，nums[left] 一定不等于 target 了，而 nums[left-1] 可能是 target。
        //至于为什么 left 的更新必须是 left = mid + 1，当然是为了把 nums[mid] 排除出搜索区间，这里就不再赘述。
        return left - 1; // or right - 1, because left == right here;
    }


    //寻找左右边界
    public int[] searchRange(int[] nums, int target) {
        return new int[]{left_bound_help(nums, target), right_bound_help(nums, target)};
    }
    int left_bound_help(int[] nums, int target) {
        int left = 0, right = nums.length;
        // 搜索区间为 [left, right]
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                // 搜索区间变为 [mid+1, right]
                left = mid + 1;
            } else if (nums[mid] > target) {
                // 搜索区间变为 [left, mid-1]
                right = mid;
            } else if (nums[mid] == target) {
                // 收缩右侧边界
                right = mid;
            }
        }
        // 检查出界情况
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }
    int right_bound_help(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] == target) {
                // 这里改成收缩左侧边界即可
                left = mid + 1;
            }
        }
        // 这里改为检查 right 越界的情况，见下图
        if (right - 1 < 0 || nums[right - 1] != target) {
            return -1;
        }
        return right - 1;
    }

    //滑动窗口！！
    public String minWindow(String s, String t) {
        // 用于记录需要的字符和窗口中的字符及其出现的次数
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        // 统计 t 中各字符出现次数
        for (char c : t.toCharArray())
            need.put(c, need.getOrDefault(c, 0) + 1);

        int left = 0, right = 0;
        int valid = 0; // 窗口中满足需要的字符个数
        // 记录最小覆盖子串的起始索引及长度
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

    //滑动窗口
    public boolean checkInclusion(String t, String s) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;

        while(right < s.length()){
            char tmp = s.charAt(right);
            right++;

            if(need.containsKey(tmp)){
                window.put(tmp, window.getOrDefault(tmp, 0) + 1);
                if(need.get(tmp).equals(window.get(tmp))) valid++;
            }

            while(right - left >= t.length()){ //其实 == 就可以
                if(valid == need.size()) return true;

                char tmpleft = s.charAt(left);
                left++;
                if(need.containsKey(tmpleft)){
                    if (window.get(tmpleft).equals(need.get(tmpleft)))
                        valid--;
                    window.put(tmpleft, window.getOrDefault(tmpleft, 0) - 1);
                }
            }

        }

        return false;
    }

    //滑动窗口
    public List<Integer> findAnagrams(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        List<Integer> res = new ArrayList<>(); // 记录结果
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            // 进行窗口内数据的一系列更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            // 判断左侧窗口是否要收缩
            while (right - left >= t.length()) {
                // 当窗口符合条件时，把起始索引加入 res
                if (valid == need.size()) {
                    res.add(left);
                }
                char d = s.charAt(left);
                left++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return res;

    }

    //滑动窗口
    public int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int len = s.length();
        if(len < 2) return len;

        int left = 0, right = 0;
        int res = 0;
        while(right < s.length()){
            char tmp = s.charAt(right);
            right++;
            map.put(tmp, map.getOrDefault(tmp , 0) + 1);
            while(map.get(tmp) > 1){
                char tmpleft = s.charAt(left);
                left++;
                map.put(tmpleft, map.getOrDefault(tmpleft , 0) - 1);
            }

            res = Math.max(res, right - left);
        }
        return res;
    }





    //recursion version
    //递归的思想相对迭代思想，稍微有点难以理解，处理的技巧是：不要跳进递归，而是利用明确的定义来实现算法逻辑。
    //对于递归，尝试基于题目最简单的例子入手递归的解法！！，如反转链表可以假设链表只有2个节点
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return last;
    }
}
