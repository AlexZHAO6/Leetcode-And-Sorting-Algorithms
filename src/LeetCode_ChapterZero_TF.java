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


}





