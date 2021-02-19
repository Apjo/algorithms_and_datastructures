package datastructures.graphs.problems;

import java.util.*;

public class StringTransformations {
    //N = no.of words in the given dictionary
    //K = len of each word
//O(N * K * min(N, 26*K))
    private static List<String>transform(String s, Set<String> dict) {
        List<String> r = new ArrayList<>();
        char[]arr = s.toCharArray();

        for(int i = 0; i < s.length(); i++) {
            char orig = arr[i];
            for (char j = 'a'; j <= 'z'; j++) {
                if (orig == j) { continue; } // skip as char matches
                arr[i] = j; // substitute char
                String newWordStr = new String(arr); // NOTE : arr.toString() doesn't give String
                if(dict.contains(newWordStr)) {
                    r.add(newWordStr);
                }
            }
            arr[i] = orig;
        }
        return r;
    }

    private static List<String>search(String s, Set<String> dict) {
        List<String> r = new ArrayList<>();
        for(String y: dict) {
            int diff = 0;

            for (int i = 0; i < s.length(); i++) {
                if (y.charAt(i) != s.charAt(i)) {
                    diff++;
                }
            }
            if(diff == 1) {
                r.add(y);
            }
        }
        return r;
    }

    static String[] string_transformation(String[] words, String start, String stop) {
        if (words.length == 2 && start.equals(stop)) { return new String[]{"-1"};}
        HashSet<String> dict = new HashSet<>(Arrays.asList(words));

        dict.add(start);
        dict.add(stop);

        Queue<String> q = new LinkedList<>();

        HashSet<String> visited = new HashSet<>();

        q.add(start);
        visited.add(start);

        Map<String, String> m = new HashMap<>();
        m.put(start, null);
        int N = dict.size();
        int K = start.length();
        while(!q.isEmpty()) {
            String curr = q.poll();
            List<String> neighbors;
            if (N*N*K  > N*K*K || N == 3) {
                neighbors = transform(curr, dict);
            } else {
                neighbors = search(curr, dict);
            }
            for(String neighbor:neighbors) {
                if (neighbor.equals(stop)) {
                    List<String> res = new ArrayList<>();
                    res.add(neighbor);
                    String p = curr;
                    while (p != null) {
                        res.add(p);
                        p = m.get(p);
                    }
                    Collections.reverse(res);
                    return res.toArray(new String[0]);
                } else {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        m.put(neighbor, curr);
                        q.add(neighbor);
                    }
                }
            }
        }
        return new String[]{"-1"};
    }
}
