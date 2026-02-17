package dsa.java.eighthundred;

import java.io.*;
import java.util.*;

public class SolutionDoremyPaint {
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

        void println() {
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
                    } else if (line.trim().isEmpty()) {
                        // Skip empty lines
                        continue;
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
            while (t-- > 0) {
                int n = io.nextInt();
                //int[]arr = new int[n];
                Map<Integer, Integer> mm = new TreeMap<>();
                for(int i=0; i < n; i++) {
                  //  arr[i] = io.nextInt();
                    int x = io.nextInt();
                    mm.put(x, mm.getOrDefault(x, 0) + 1);
                }
                if (mm.size() > 2) {
                    io.println("no");
                }
                else if (mm.size() == 1) {
                    io.println("yes");
                } else {
                    // Check if the absolute difference between the counts is at most 1
                    Iterator<Integer> iter = mm.values().iterator();
                    int firstCount = iter.next();
                    int secondCount = iter.next();

                    if (Math.abs(firstCount - secondCount) <= 1) {
                        io.println("Yes");
                    } else {
                        io.println("No");
                    }
                }
            }
            io.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
