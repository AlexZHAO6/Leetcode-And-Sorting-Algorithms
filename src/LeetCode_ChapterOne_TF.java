import java.util.*;

public class LeetCode_ChapterOne_TF {
    public static void main(String[] args) {

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
}
