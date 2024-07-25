package dsa.problemsolving.elementsofprogramminginterviews;

public class Chapter11 {
    //remember when x < 1 then sqrt(x) > x
    public static double realSquareRoot(double x) {
        double TOLERANCE = 0.00001;
        double lo = 0.0, hi = Math.ceil(x);
        while (lo <= hi) {
            double mid = lo + (hi - lo) / 2.0;
            if (Math.abs(mid * mid - x) <= TOLERANCE) {
                return mid;
            } else if (Math.abs(mid * mid - x) < x) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return hi;
    }
}
