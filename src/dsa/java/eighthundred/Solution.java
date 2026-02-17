package dsa.java.eighthundred;

import java.io.*;
import java.util.*;
//compile:javac dsa/cp/tle/eighthundred/Solution3.java
public class Solution {
        private static int solve(int n, int x, int[]gases) {
            List<Integer> al = new ArrayList<>();
            al.add(0);
            for(int g : gases) {
                al.add(g);
            }
            al.add(x);
            Collections.sort(al);
            int a = 0;
            for(int i=1; i < al.size(); i++) {
                a = Math.max(a, al.get(i) - al.get(i - 1));
            }
            int lastLegGap = (x - al.get(al.size() - 2)) * 2;

            //int returnGap = al.get(al.size() - 1) - al.get(0);

            return Math.max(a, lastLegGap);
        }
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();

            // Read the number of test cases
            int t = Integer.parseInt(br.readLine());

            // Process each test case
            while(t-- > 0) {
                String[] firstLine = br.readLine().split(" ");
                int n = Integer.parseInt(firstLine[0]);
                int[]gases = new int[n];
                int x = Integer.parseInt(firstLine[1]);
                String[] secondLine = br.readLine().split(" ");

                for(int i=0; i < n; i++) {
                    gases[i] = Integer.parseInt(secondLine[i]);
                }
                int ans = solve(n, x, gases);
                sb.append(ans).append("\n");
            }

            // Print all results at once
            System.out.print(sb.toString());
        }
}
