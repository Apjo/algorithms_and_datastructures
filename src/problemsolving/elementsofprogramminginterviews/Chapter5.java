package problemsolving.elementsofprogramminginterviews;

import java.util.*;

public class Chapter5 {
    //O(N) time, O(1) space
    public static List<Integer> addArrayToForm(int []A) {
        List<Integer> res = new LinkedList<>();
        int currSize = A.length - 1;
        res.set(0, A[currSize] + 1);
        for (int i = currSize; i>=0 && A[i] == 10; i--) {
            res.set(i, 0);
            res.set(i - 1, A[i - 1] + 1);
        }
        if (res.get(0) == 10) {
            res.set(0, 0);
            res.add(0, 1);
        }
        return res;
    }
    //O(M*N)
    public static List<Integer> multiplyLists(List<Integer> l1, List<Integer> l2) {
        l1.set(0, Math.abs(l1.get(0)));
        l2.set(0, Math.abs(l2.get(0)));
        List<Integer> res = new LinkedList<>(Collections.nCopies(l1.size() + l2.size(), 0));
        for (int i = l1.size() - 1; i >= 0; i--) {
            for (int j = l2.size() - 1; j >= 0; j--) {
                int p1 = i + j;
                int p2 = i + j + 1;
                int mult = l1.get(i) * l2.get(j);
                int sum = res.get(p1) + mult;
                res.set(p2, sum);
                int carry = res.get(p1) + res.get(p2) / 10;
                res.set(p1, carry);
                res.set(p2, res.get(p2)%10);
            }
        }
        int firstNonZero = 0;
        while(firstNonZero < res.size() && res.get(firstNonZero) == 0) {
            ++firstNonZero;
        }
        res = res.subList(firstNonZero, res.size());
        return res;
    }
    //O(N) time, O(1) space
    public static boolean canReachEnd(int[] A) {
        int furthestSoFar = 0;
        int N = A.length - 1;
        for (int i = 0; i < furthestSoFar && furthestSoFar < N; i++) {
            furthestSoFar = Math.max(furthestSoFar, i + A[i]);
        }
        return furthestSoFar >= N;
    }

    public static int deleteDuplicates(List<Integer> A) {
        if (A.isEmpty()) {
            return 0;
        }
        int ctr = 0;
        for(Integer i: A) {
            if (!A.get(i).equals(A.get(i - 1))) {
                A.set(ctr, A.get(i));
                ctr++;
            }
        }
        return ctr;
    }
    //O(N) time
    public static int maxProfitBuySellStockOnce(int[] prices) {
        if(prices.length == 0 || prices == null) {
            return 0;
        }
        int minPrice = Integer.MAX_VALUE, maxProfit = 0;
        for (int price: prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, maxProfit - minPrice);
        }
        return maxProfit;
    }
    public static int maxProfitBuySellStockOnceTake2(int[] prices) {
        if(prices.length == 0 || prices == null) {
            return 0;
        }
        int minPrice = prices[0], maxProfit = 0;
        for (int price = 0; price < prices.length - 1; price++) {
            if (prices[price] > minPrice) {
                maxProfit = Math.max(maxProfit, prices[price] - minPrice);
            } else {
                minPrice = prices[price];
            }
        }
        return maxProfit;
    }
    //LC explanation: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/39611/Is-it-Best-Solution-with-O(n)-O(1)./533661
    // follows basic idea "buy first -> sell first -> buy 2nd -> sell 2nd"
    public static int maxProfitBuySellAtmostTwice(int[] prices) {
        if(prices.length == 0 || prices == null) {
            return 0;
        }
        int priceForBuyingOnce = Integer.MIN_VALUE, maxProfitForBuyingOnce = 0, priceForBuyingSecondTime = Integer.MIN_VALUE, maxProfitForBuyingSecondTime = 0;
        for (int price : prices) {
            priceForBuyingOnce = Math.max(priceForBuyingOnce, -price);
            maxProfitForBuyingOnce = Math.max(maxProfitForBuyingOnce, price + priceForBuyingOnce);
            priceForBuyingSecondTime = Math.max(priceForBuyingSecondTime, maxProfitForBuyingOnce - price);
            maxProfitForBuyingSecondTime = Math.max(maxProfitForBuyingSecondTime, priceForBuyingSecondTime + price);
        }
        return maxProfitForBuyingSecondTime;
    }
    //OR the second way to do it
    // explanation: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/39611/Is-it-Best-Solution-with-O(n)-O(1)./242914
    public static int maxProfitBuySellAtmostTwiceAttempt2(int[] prices) {
        if(prices.length == 0 || prices == null) {
            return 0;
        }
        int priceForBuyingOnce = Integer.MAX_VALUE, maxProfitForBuyingOnce = 0, priceForBuyingSecondTime = Integer.MAX_VALUE, maxProfitForBuyingSecondTime = 0;
        for (int price : prices) {
            priceForBuyingOnce = Math.min(priceForBuyingOnce, price);
            maxProfitForBuyingOnce = Math.max(maxProfitForBuyingOnce, price - priceForBuyingOnce);
            priceForBuyingSecondTime = Math.min(priceForBuyingSecondTime, price - maxProfitForBuyingOnce);
            maxProfitForBuyingSecondTime = Math.max(maxProfitForBuyingSecondTime, price - maxProfitForBuyingSecondTime);
        }
        return maxProfitForBuyingSecondTime;
    }
    private static void swap(int []A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    //suboptimal tc: O(NlogN)
    static void peaksAndValleys1(int []A) {
        Arrays.sort(A);
        for (int i = 1; i < A.length; i+=2) {
            swap(A, i - 1, i);
        }
    }
    //O(N)
    static void peaksAndValleys2(int []A) {
        for (int i = 1; i< A.length - 1; i+=2) {
            if ((i % 2 == 0 && A[i - 1] < A[i]) || (i%2 != 0 && A[i - 1] > A[i]))
            swap(A, i - 1, i);
        }
    }
    private static int maxIndx(int []A, int lo, int mid, int hi) {
        int a = (lo >= 0 && lo <= A.length -1) ? A[lo]: Integer.MAX_VALUE;
        int b = (mid >= 0 && mid <= A.length -1) ? A[mid]: Integer.MAX_VALUE;
        int c = (hi >= 0 && hi <= A.length -1) ? A[hi]: Integer.MAX_VALUE;
        int currMax = Math.max(a, Math.max(b, c));
        if (a == currMax) {return lo;}
        else if (b == currMax) {return mid;}
        return hi;
    }
    //O(N)
    static void peaksAndValleys3(int []A) {
        for (int i = 1; i < A.length; i ++) {
            int biggestIndx = maxIndx(A, i - 1, i, i + 1);
            if (i != biggestIndx) {
                swap(A, i, biggestIndx);
            }
        }
    }
    //O(N) sieve of eratosthenes Algo to find all primes upto N
    static List<Integer> findAllPrimesUptoN(int N) {
        boolean[] arr = new boolean[N + 1];
        List<Integer> res = new ArrayList<>();
        Arrays.fill(arr, true);
        arr[0] = false;
        arr[1] = false;
        for (int i = 2; i <= N; i++) {
            if (arr[i]) {
                res.add(i);
                //we found i to be a prime, so "sieve" out the multiples of i since they(multiples) cannot be primes
                for (int j = i; j <=N; j+=i) {
                    arr[j] = false;
                }
            }
        }
        return res;
    }
    //O(N), ref: https://www.geeksforgeeks.org/permute-the-elements-of-an-array-following-given-order/
    public static int[] applyPermutation(int[]A, int[] P) {
        int N = A.length;
        for (int i = 0; i < A.length - 1; i++) {
            int next = i;
            //check if already considered in a cycle
            while(P[next] >= 0) {
                //swap current element according to the correct position in P
                swap(A, i, P[next]);
                //save original P
                int temp = P[next];
                //subtract n from an entry in P to make it negative indicating we performed this cycle/move
                P[next]-=N;
                next= temp;
            }
        }
        return A;
    }

    private static int findPivot(int[] A) {
        for (int i = A.length - 1; i > 0; i--) {
            if (A[i - 1] < A[i]) {
                return i;
            }
        }
        return -1;
    }
    static int findFirstSuffixGreaterThanPivotFromRight(int []A, int p) {
        for (int i = A.length - 1; i > 0; i--) {
            if (p < A[i]) {
                return i;
            }
        }
        return -1;
    }

    static void reverseArr(int []A, int start) {
        int end = A.length -1;
        while (start < end) {
            swap(A, start, end);
            start++;
            end--;
        }
    }
    //O(N) very tricky Algo
    //1. Pick a pivot that is the first smallest element from Right to left
    //2. Pick the first element k, s.t. A[k] > A[pivot] from right to left
    //3. Swap pivot and k
    //4. reverse the subarray between pivot and k
    //5. return the array which is the next permutation
    public static int[] nextPermutation(int []A) {
        if (A.length == 0 || A == null) {return null; }
        int pivot = findPivot(A) - 1;
        if (pivot != -1) {
            int suffixLoc = findFirstSuffixGreaterThanPivotFromRight(A, pivot);
            swap(A, suffixLoc, pivot);
        }
        reverseArr(A, pivot);
        return A;
    }
    //O(k) time, O(1) space
    public static List<Integer> selectRandomSubsetSizeK(List<Integer> A, int k) {
        Random r = new Random();
        for (int i = 0; i < k; i++) {
            Collections.swap(A, i, i + r.nextInt(A.size() - i));
        }
        return A;
    }
    //O(size of stream) time complexity, and O(k) space complexity
    public static List<Integer> sampleOnline(Iterator<Integer> seq, int k) {
        List<Integer> res = new ArrayList<>(k);
        for (int i = 0; seq.hasNext() && i < k; i++) {
            res.add(seq.next());
        }
        int seenSoFar = 0;
        while(seq.hasNext()) {
            int elem = seq.next();
            seenSoFar++;

            int nextIndx = new Random().nextInt(seenSoFar);
            res.set(nextIndx, elem);
        }
        return res;
    }
    //O(N) time
    public static List<Integer> computeRandomPermutation(int N) {
        List<Integer> res = new ArrayList<>(N);
        for(int i = 0; i < N; i++) {
            res.add(i);
        }
        for (int i = 0; i < N; i++) {
            Collections.swap(res, i, i + new Random().nextInt(res.size() - 1));
        }
        return res;
    }

    public static List<Integer> generateRandomSubset(int N, int k) {
        List<Integer> res = new ArrayList<>(k);
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < k; i ++) {
            int rIdx = new Random().nextInt(N - 1) + i;
            Integer p1 = m.get(rIdx);
            Integer p2 = m.get(i);
            if (p1 == null && p2 == null) {
                m.put(rIdx, i);
                m.put(i, rIdx);
            } else if (p1 == null && p2 != null) {
                m.put(rIdx, p2);
                m.put(i, rIdx);
            } else if (p1 != null && p2 == null) {
                m.put(i, p1);
                m.put(rIdx, i);
            } else {
                m.put(i, p1);
                m.put(rIdx, p2);
            }
        }
        for (int i = 0; i < k; i ++) {
            res.add(m.get(i));
        }
        return res;
    }
    //time:O(N), space: O(N)
    public static Integer generateUniformRandomNumbers(List<Integer> ip, List<Double> probabilities) {
        List<Double> prefixSumProbabilities = new ArrayList<>();
        prefixSumProbabilities.add(0.0);
        for (Double prob : probabilities) {
            prefixSumProbabilities.add(prefixSumProbabilities.get(prefixSumProbabilities.size() - 1) + prob);
        }
        double randIdx = new Random().nextDouble();
        int idx1 = Collections.binarySearch(prefixSumProbabilities, randIdx);
        if (idx1 < 0) {
            int t = Math.abs(idx1) - 1;
            return ip.get(t);
        } else {
            return ip.get(idx1);
        }
    }
    //Solution using 3 boolean arrays for each row in the grid to validate rowI, colC, and boxB whether they contribute
    //for a valid sudoku
    public static boolean sudokuChecker(List<List<Integer>> G) {
        int size = G.size();
        for (int i = 0; i < size; i++) {
            boolean[]rowValid = new boolean[9];
            boolean[]colValid = new boolean[9];
            boolean[]boxValid = new boolean[9];
            for (int j = 0; j < size; j++) {
                int elemRow = G.get(i).get(j);
                //if the location contains a 0 ignore
                if (elemRow != 0) {
                    //check if you have seen this number at previously in this row
                 if (rowValid[elemRow]) {
                     return false;
                 }
                 rowValid[elemRow] = true;
                }
                //check for column duplicate
                int elemCol = G.get(j).get(i);
                if (elemCol != 0) {
                    //check if you have seen this number at previously in this column
                    if (colValid[elemCol]) {
                        return false;
                    }
                    colValid[elemCol] = true;
                }
                //For more explanation: https://leetcode.com/problems/valid-sudoku/discuss/15450/Shared-my-concise-Java-code/15493
                //check whether each sub matrix of size 3*3 is valid
                //to move next block/sub matrix horizontally use %
                int indexForNextBlockCol = (i%3)*3 + j % 3;
                //to move next block/sub matrix vertically, use /
                int indexForNextBlockRow = (i/3)*3 + j / 3;
                int value = G.get(indexForNextBlockRow).get(indexForNextBlockCol);
                if (value != 0) {
                    //check if you have seen this number at previously in this box
                    if (boxValid[value]) {
                        return false;
                    }
                    boxValid[value] = true;
                }
            }
        }
        return true;
    }
    //O(M*N), time, O(1) space
    public static List<Integer> matrixSpiralOrder(List<List<Integer>> A) {
        int stRow = 0, endRow = A.size() - 1, stCol = 0, endCol = A.get(0).size() - 1;
        List<Integer> res = new ArrayList<>();
        int DIR = 0;
        while(stRow <= endRow && stCol <= endCol) {
            switch (DIR) {
                case 0 : //GO RIGHT
                    for(int i = stCol; i <= endCol; i++) {
                    res.add(A.get(stRow).get(i));
                }
                    stRow++;
                    break;
                case 1: //GO DOWN
                    for(int i = stRow; i <= endRow; i++) {
                        res.add(A.get(i).get(endCol));
                    }
                    endCol--;
                    break;
                case 2: //GO LEFT
                    for(int i = endCol; i >= stCol; i--) {
                        res.add(A.get(endRow).get(i));
                    }
                    endRow--;
                    break;
                case 3: //GO UP
                    for(int i = endRow; i >= stRow; i--) {
                        res.add(A.get(i).get(stCol));
                    }
                    stCol++;
                    break;
            }
            DIR = (DIR + 1) % 4;
        }
        return res;
    }
    //Time: O(M*N), space:O(1)
    //Ref: https://leetcode.com/problems/rotate-image/discuss/18872/A-common-method-to-rotate-the-image
    public int[][] rotateImage(int[][]A) {
        //reverse each row of the image
        for (int row = 0; row < A.length - 1; row++) {
            int first = 0;
            int last = A[row].length - 1;
            while (first < last) {
                int temp = A[row][first];
                A[row][first] = A[row][last];
                A[row][last] = temp;
                first++;
                last--;
            }
        }
        //transpose image
        for (int row = 0; row < A.length - 1 - 1; row++) {
            for (int col = row + 1; col < A[row].length - 1; col++) {
                int temp = A[row][col];
                A[row][col] = A[col][row];
                A[col][row] = temp;
            }
        }
        return A;
    }
    //time: O(N*N), space: O(N*N)
    List<List<Integer>> pascalsTriangleNRows(int N) {
        List<List<Integer>> pascals = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            List<Integer> currRow = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                currRow.add((j > 0 && j < i) ? pascals.get(i - 1).get(j - 1) + pascals.get(i - 1).get(j): 1);
            }
            pascals.add(currRow);
        }
        return pascals;
     }

    public static void main(String[] args) {

    }
}
