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
}

