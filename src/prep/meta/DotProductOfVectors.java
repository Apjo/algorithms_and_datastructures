package prep.meta;

import java.util.HashMap;
import java.util.Map;

public class DotProductOfVectors {
    //using HM of index to num
    class SparseVector {
        Map<Integer, Integer> idxToNum;

        SparseVector(int[] nums) {
            this.idxToNum = new HashMap<>();
            for(int i=0; i < nums.length; i++) {
                idxToNum.put(i, nums[i]);
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec) {
            int ans=0;
            for(Map.Entry<Integer, Integer> entry : vec.idxToNum.entrySet()) {
                int idx = entry.getKey();
                int val = entry.getValue();
                if (this.idxToNum.containsKey(idx)) {
                   ans+=(this.idxToNum.get(idx) * val);
                }
            }
            return ans;
        }
    }
    //using arrays only
    class SparseVector2 {
        int[]buff;
        SparseVector2(int[] nums) {
            int N = nums.length;
            this.buff = new int[N];
            System.arraycopy(nums, 0, this.buff, 0, this.buff.length);
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector2 vec) {
            int ans=0;
            for(int i=0; i < vec.buff.length; i++) {
                ans+=(this.buff[i] * vec.buff[i]);
            }
            return ans;
        }
    }
    public static void main(String[] args) {
    }
}
