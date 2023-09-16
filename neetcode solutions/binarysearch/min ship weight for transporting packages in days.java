class Solution {
    public int shipWithinDays(int[] weights, int days) {
            int minWeight = weights[0];
            int maxWeight = 0;
            for(int i=0; i < weights.length; i++) {
                minWeight = Math.min(weights[i], minWeight);
                maxWeight += weights[i];
            }
            while(minWeight <= maxWeight) {
                int currentCapacity = minWeight + maxWeight / 2;
                if (canShip(weights, currentCapacity, days)) {
                    maxWeight = currentCapacity - 1;
                } else {
                    minWeight = currentCapacity + 1;
                }
            }
            return minWeight;
    }
    private static boolean canShip(int[] weights, int currentCap, int days) {
        int currentWeight = 0;
        for(int i=0; i < weights.length; i++) {
            if (currentWeight + weights[i] <= currentCap) {
                currentWeight += weights[i];
            } else {
                currentWeight = weights[i];
                days--;
            }
        }
        return days >=0;
    }
}