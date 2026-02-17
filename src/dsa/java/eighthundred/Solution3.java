package dsa.java.eighthundred;

import java.io.*;
import java.util.*;

public class Solution3 {
    static class FastIO {
        BufferedReader br;
        StringTokenizer st;
        PrintWriter pw;

        public FastIO() {
            br = new BufferedReader(new InputStreamReader(System.in));
            pw = new PrintWriter(System.out, true);
        }
        // Generic print method for different data types
        void print(Object obj) {
            if (obj instanceof int[]) {
                int[] arr = (int[]) obj;
                for (int num : arr) {
                    pw.print(num + " ");
                }
            } else if (obj instanceof long[]) {
                long[] arr = (long[]) obj;
                for (long num : arr) {
                    pw.print(num + " ");
                }
            } else if (obj instanceof char[]) {
                char[] arr = (char[]) obj;
                for (char ch : arr) {
                    pw.print(ch + " ");
                }
            } else if (obj instanceof Object[]) {
                Object[] arr = (Object[]) obj;
                for (Object o : arr) {
                    pw.print(o + " ");
                }
            } else {
                pw.print(obj);
            }
        }

        // Print with a newline
        void println(Object obj) {
            print(obj);
            pw.println();
        }

        // Flush the PrintWriter to ensure all output is printed
        void flush() {
            pw.flush();
        }

        // Close the PrintWriter
        void close() {
            pw.close();
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = br.readLine(); // Read the next line from input
                    if (line == null) {
                        throw new RuntimeException("Unexpected end of input");
                    }
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    throw new RuntimeException("Input error occurred", e);
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
    public static void main(String[] args) throws IOException {
        FastIO io = new FastIO();
        try {
            int t = io.nextInt();
//            io.println("Test cases count: " + t);

            StringBuilder sb = new StringBuilder();
            // Process each test case
            while (t-- > 0) {
                //n: no.of cells
                int n = io.nextInt();
                String test = io.next();

                //io.print(test);
                //io.println("Received test string: " + test);
                //##....#
                //#...#..#.#
                //find 3 empty cells, if we find ret. 2, else ans==num empty cells
                int ans = 0;
                for (int i = 0; i < n; i++) {
                    if (test.charAt(i) == '#') {
                        continue;
                    }
                    if (i >= 1 && i + 1 < n && test.charAt(i - 1) == '.' && test.charAt(i) == '.' && test.charAt(i + 1) == '.') {
                        ans = 2;
                        break;
                    } else {
                        ans++;
                    }
                }
                sb.append(ans).append("\n");
            }
            // Print the result and ensure all output is flushed
            io.print(sb.toString());
            io.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
