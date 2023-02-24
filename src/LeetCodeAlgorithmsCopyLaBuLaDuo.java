import java.util.List;
import java.util.PriorityQueue;

public class LeetCodeAlgorithmsCopyLaBuLaDuo {
    public static void main(String[] args){
        System.out.println("Alex GOGOGO");
    }
}

class Algorithms
{
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
