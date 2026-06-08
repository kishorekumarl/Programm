package programms;
import java.util.*;
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution142 {

    public ListNode detectCycle(ListNode head) {

        if (head == null)
            return null;

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                ListNode third = head;
                while (third != slow) {
                    third = third.next;
                    slow = slow.next;
                }
                return third;

            }


        }
        return null;
    }
}
