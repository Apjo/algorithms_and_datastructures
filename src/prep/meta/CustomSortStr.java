package prep.meta;

import java.util.HashMap;
import java.util.Map;

public class CustomSortStr {
    public static String customSortString(String order, String s) {
        char[]arr = s.toCharArray();
        //create a frequency map of each character in s
        Map<Character, Integer> m = new HashMap<>();
        for(char cc : arr) {
            m.put(cc, m.getOrDefault(cc, 0) + 1);
        }
        //iterate over the string "order", and continue appending all the chars in order that appear in the freq. map for the frequency times
        StringBuilder sb = new StringBuilder();
        for(char ord : order.toCharArray()) {
            if (!m.containsKey(ord)) {
                continue;
            } else {
                int val = m.get(ord);
                while(val > 0) {
                    sb.append(ord);
                    val--;
                }
                if (val == 0) {
                    m.remove(ord);
                }
            }
        }
        //finally, iterate over the frequency map since we still need to append those characters that are "extra" in s
        for(Map.Entry<Character, Integer> entry : m.entrySet()) {
            char k = entry.getKey();
            int val = entry.getValue();
            while(val > 0) {
                sb.append(k);
                val--;
            }
        }
        return sb.toString();
    }
    public static String customSortString2(String order, String s) {
        int[]count = new int[26];
        for(char xx : s.toCharArray()) {
            count[xx - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for(char xx : order.toCharArray()) {
            while(count[xx - 'a']-- > 0) {
                sb.append(xx);
            }
        }
        for(char st = 'a'; st <= 'z'; st++) {
            while(count[st -'a']-- > 0) {
                sb.append(st);
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        customSortString("bcafg", "abcd");
    }
}
