package dsa;

public final class DSAUtils {
    private DSAUtils(){}
    public static void swapIntArr(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
