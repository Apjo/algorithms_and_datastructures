package prep.meta;
import java.util.*;
//using HM
class SparseVector {
    Map<Integer, Integer> idxToNum;
    public SparseVector(int[] nums) {
        idxToNum = new HashMap<>();
        for(int i=0; i < nums.length; i++) {
            idxToNum.put(i, nums[i]);
        }
    }
    public int dotProduct(SparseVector vec) {
        int ans=0;
        for(Map.Entry<Integer, Integer> e : vec.idxToNum.entrySet()) {
            int idx = e.getKey();
            int v = e.getValue();
            if (idxToNum.containsKey(idx)) {
                ans+=v*idxToNum.get(idx);
            }
        }
        return ans;

    }
}
//using arrays
class SparseVector2 {
    int[] idxToNum;
    public SparseVector2(int[] arr) {
        idxToNum = new int[arr.length];
        System.arraycopy(arr, 0, idxToNum, 0, idxToNum.length);
    }
    public int dotProduct(SparseVector2 vec) {
        int ans=0;
        for(int i=0; i < vec.idxToNum.length; i++) {
            ans+=(idxToNum[i]*vec.idxToNum[i]);
        }
        return ans;
    }
}


public class DotProductOfScalaVectors {

    public static void main(String[] args) {
    }
}
