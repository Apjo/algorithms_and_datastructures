package prep.meta;

public class CountElementsWithMaxFrequency {
    public static int maxFrequencyElements(int[] nums) {
        int currMaxNum = 0;
        for(int ii: nums) {
            currMaxNum = Math.max(currMaxNum, ii);
        }
        int[] count = new int[currMaxNum + 1];
        int currMaxFreq = 0;
        for(int i=0; i < nums.length; i++) {
            count[nums[i]]++;
            currMaxFreq = Math.max(currMaxFreq, count[nums[i]]);
        }
        int ans=0;
        for(int i=0; i <= currMaxNum; i++) {
            if (count[i] == currMaxFreq) {
                ans+=currMaxFreq;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        assert maxFrequencyElements(new int[]{1,1,1,2,2,3}) == 3;
        assert maxFrequencyElements(new int[]{11,1,12,2,23,31}) == 1;
    }
}
