package dsa.graphs.problems;
import java.util.*;
public class CycleInDirectedGraph {
    /*
     * Complete the 'hasCycle' function below.
     *
     * The function accepts INTEGER N, INTEGER M and 2D_INTEGER_ARRAY edges as parameter.
     * The function is expected to return a BOOLEAN.
     */
    public static boolean hasCycle(int N, int M, List<List<Integer>> edges) {
        Set<Integer> s1 = new HashSet<>(N);
        Set<Integer> s2 = new HashSet<>(N);
        Set<Integer> s3 = new HashSet<>(N);
        List<List<Integer>> li = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            li.add(i, new ArrayList<>());
        }
        for (List<Integer> edge:edges) {
            li.get(edge.get(0)).add(edge.get(1));
        }

        for (int i = 0; i < N; i++) {
            s1.add(i);
        }

        while(s1.size() > 0) {
            int v = s1.iterator().next();
            if(dfs(v,s1, s2, s3, li)) { return true;}
        }
        return false;
    }
    private static boolean dfs(int from, Set<Integer> w, Set<Integer> g, Set<Integer> b, List<List<Integer>> gr) {
        w.remove(from);
        g.add(from);
        for(Integer to:gr.get(from)) {
            if(b.contains(to)) { continue; }
            if(g.contains(to)) { return true; }
            if(dfs(to, w, g, b, gr)) { return true; }
        }
        g.remove(from);
        b.add(from);
        return false;
    }
}
