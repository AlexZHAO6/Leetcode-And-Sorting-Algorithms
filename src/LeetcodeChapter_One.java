public class LeetcodeChapter_One {
    public static void main(String[] args){
        System.out.println("Alex GO GO GO");
    }
}


class LinkedListAlgorithms{
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
}


