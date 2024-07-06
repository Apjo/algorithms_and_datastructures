package problemsolving.leetcode.dailychallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_2024_07_05 {
    public int passThePillow(int n, int time) {
        int p=1;
        boolean f=true, b=false;
        while(true) {
            if(time == 0) {
                break;
            }
            System.out.println("time= " + time+" p= " + p);
            if (f==true && b==false) {
                p++;
            }
            if(f==false && b==true && p > 0) {
                p--;
            }
            if (p==1 && f==false && b == true) {
                f=true;
                b=false;
                //p++;
            }
            if (p == n && f==true && b==false) {
                b=true;
                f=false;
                //p--;
            }
            //System.out.println("End time= " + time+" p= " + p);
            time--;
        }
        return p;
    }
}
