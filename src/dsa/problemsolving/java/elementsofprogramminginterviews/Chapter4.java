package dsa.problemsolving.java.elementsofprogramminginterviews;

public class Chapter4 {

    private static int countBitsSet(long n) {
        int ctr = 0;
        while(n !=0) {
            n = n & (n - 1);
            ctr ++;
        }
        return ctr;
    }
    //O(N) time
    public static int countParityV1(long N) {
        int ctr = 0;
        //to count parity we count how many bits are set. If the count is odd then parity is 1, else 0
        ctr = countBitsSet(N);
        if (ctr % 2 > 0) { return 1;}
        return 0;
    }
    public static int countParityV2(long N) {
        int ctr = 0;
        //to count parity we count how many bits are set. If the count is odd then parity is 1, else 0
        while (N !=0) {
            ctr ^= (N & 1);
            N >>>= 1;
        }
        return ctr;
    }
    //O(k) time, where k is the number of bits set in N
    public static int countParityByDropSetBits(long N) {
        int res = 0;
        while(N != 0) {
            res ^= 1;
            N = N & (N - 1); //drop the lowest set bit of N
        }
        return res;
    }

    public static boolean isNumberPalindrome(int A) {
        if (A < 0) {
            return false;
        }
        int numDigits = (int) Math.floor(Math.log10(A)) + 1;
        int msbMask   = (int) Math.pow(10, numDigits - 1);
        for (int i = 0; i< numDigits / 2; i++) {
            //if MSB != LSB
            if (A / msbMask != A % 10) {
                return false;
            }
            //remove the MSB
            A %= msbMask;
            A /= 10;
            msbMask /= 100;
        }
        return true;
    }
}
