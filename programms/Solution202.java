class Solution {
    
    public boolean isHappy(int n) {
       private int getNext(int n) {
            int totalSum = 0;
            while (n != 0) {
                int d = n % 10;
                n = n / 10;
                totalSum += d * d;
            }
            return totalSum;
        }
        if (n == 1) return true;
        int slow = n;
        int fast = n;
        while (true) {
            fast = getNext(getNext(fast));
            slow = getNext(slow);

            if (fast == 1) return true;
            if (fast == slow) return false;
        }
    }
}