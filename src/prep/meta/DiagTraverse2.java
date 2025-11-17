package prep.meta;


import java.util.*;
public class DiagTraverse2 {
    //using BFS
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        Queue<int[]> queue = new LinkedList<>();
        int M = nums.size(), N = nums.get(0).size();
        List<List<Boolean>> visited = new ArrayList<>();
        for(int i=0; i < M; i++) {
            List<Boolean> temp = new ArrayList<>();
            for(int j=0; j < N; j++) {
                temp.add(false);
            }
            visited.add(temp);
        }
        List<Integer> res = new ArrayList<>();
        queue.offer(new int[]{0,0});
        visited.get(0).set(0, true);
        int[][]directions = {{0,1}, {1,0}};
        while(!queue.isEmpty()) {
            int[]curr = queue.poll();
            List<Integer> temp = new ArrayList<>();
            temp.add(0, nums.get(curr[0]).get(curr[1]));
            for(int[] direction: directions) {
                int newX = curr[0] + direction[0];
                int newY = curr[1] + direction[1];
                if (newX >= 0 && newX < nums.size() && newY >= 0 && newY < nums.get(newX).size() && !visited.get(newX).get(newY)) {
                   queue.offer(new int[]{curr[0], curr[1]});
                   visited.get(newX).set(newY, true);
                }
            }
            res.addAll(temp);
        }
        return res.stream().mapToInt(i -> i).toArray();
    }
    public static void main(String[] args) {
    }
}
