package prep.meta;

public class MinAddToMakeStringValid {
    public static int minAddToMakeValid(String s) {
        int open = 0, close =0;
        for(char cc : s.toCharArray()) {
            if(cc == '(') {
                open++;
            } else if(cc == ')') {
                if(open > 0) {
                    open--;
                } else {
                    close++;
                }
            }
        }
         return open + close;
    }
        public static void main(String[] args) {
        assert minAddToMakeValid("()") == 0;
        assert minAddToMakeValid("())") == 1;
        assert minAddToMakeValid("()(") == 1;
        assert minAddToMakeValid("(()") == 1;
        assert minAddToMakeValid("))((") == 4;
    }
}
