package dsa.graphs.problems;
import java.util.*;
public class CourseSchedule {
    //the solution has tc: O(V+E), space:O(V)
    public static List<Integer> course_schedule(int n, List<List<Integer>> prerequisites) {
        List<List<Integer>> adj = new ArrayList<List<Integer>>(n);
        for (int i = 0; i < n; i++) adj.add(i, new ArrayList<>());
        for (int i = 0; i < prerequisites.size(); i++) adj.get(prerequisites.get(i).get(1)).add(prerequisites.get(i).get(0));

        int[] visited = new int[n];
        Stack<Integer> st = new Stack<>();
        List<Integer> op = new ArrayList<>();
        for (int v = 0; v < n; v++) {
            if(!top(v, adj, visited, st)) {
                return Arrays.asList(-1);
            }
        }
        while(!st.isEmpty()) {
            int x = st.pop();
            op.add(x);
        }
        return op;
    }

    private static boolean top(int from, List<List<Integer>> adj, int[] visited, Stack<Integer> st) {
        //if visiting again, return false
        if(visited[from] == 1) {return false;}
        //if visited, return false
        if(visited[from] == 2) {return true;}
        //mark as visiting
        visited[from] = 1;

        List<Integer> neighbors = adj.get(from);

        for(Integer to:neighbors) {
            if(!top(to, adj, visited, st)) {
                return false;
            }
        }
        //finally mark as visited, since we covered all this vertex's neighbors
        visited[from] = 2;
        st.push(from);
        return true;
    }

    public static void main(String[] args) {
        int n = 4;
        List<List<Integer>> ll = new ArrayList<>();
        List<Integer> op ;
        List<Integer> c1 = new ArrayList<>();
        c1.add(1);
        c1.add(0);
        ll.add(c1);
        List<Integer> c2 = new ArrayList<>();
        c2.add(2);
        c2.add(0);
        ll.add(c2);
        List<Integer> c3 = new ArrayList<>();
        c3.add(3);
        c3.add(1);
        ll.add(c3);
        List<Integer> c4 = new ArrayList<>();
        c4.add(3);
        c4.add(2);
        ll.add(c4);
        op = CourseSchedule.course_schedule(n, ll);
        System.out.println(Arrays.toString(op.toArray()));//output could be [0,2,1,3]

    }
}
