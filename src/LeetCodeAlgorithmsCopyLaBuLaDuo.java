import java.util.List;

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

}
