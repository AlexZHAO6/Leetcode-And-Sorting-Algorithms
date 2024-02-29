public class LeetCode_ChapterZero_TF {
    public static void main(String[] args) {
        LinkedListAlgorithms_TF linkedListAlgorithms = new LinkedListAlgorithms_TF();

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

        return null;
    }
}





