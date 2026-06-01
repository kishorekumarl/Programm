class Solution876 {
    public ListNode middleNode(ListNode head) {
        // Initialize both pointers to the head of the list
        ListNode rab = head; // Fast pointer (the hare)
        ListNode tot = head; // Slow pointer (the tortoise)

        // Move rab by two steps and tot by one step in each iteration
        while (rab != null && rab.next != null) {
            rab = rab.next.next;
            tot = tot.next;
        }

        // When rab reaches the end, tot will be at the middle
        return tot;
    }
}


