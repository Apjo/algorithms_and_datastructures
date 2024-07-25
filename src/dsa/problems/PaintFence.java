package dsa.problems;


/**
 * LC# 276:
 *
 * Look at the last post, and ask, how many color options are there? k
 * need to know how many valid colorings there are before the last post which can be extended by adding the last color
 * so the no.of valid colorings for the first N - 1(or i) posts =
 * How many fences of len N - 1 had the last 2 of the same color(for the last fence we have k -1 choices), say same(i)
 * &
 * How many fences of len N - 1 had the last 2 of different color?(for the last fence we have k choices), say diff(i)
 * total(i) => same(i) + different(i) , no.of ways to paint the fences 0 to i
 * same(i)  => total no.of ways to paint posts 1..i, so that the posts i - 1 & i have same color
 *          => different(i - 1)
 * different(i) => no.of ways to paint posts 1..i so that the last fence has a different color from penultimate fence.
 *                  We don't care what the colors used were for the neighbors.
 *              => (k - 1)[same(i - 1) + different(i - 1)]
 *              => (k-1) * total(i -1)
 * Base cases:
 * same(2) = 2
 * different(2) = k*(k - 1)
 * total(2) = k*k

 * LC# 256
 * time: O(n), space: O(n) -> can be optimized to O(1)
 * options for the last house: R,G,B
 * What we need from our neighbor is: min cost of painting N - 1 houses ending with G(assuming last house is painted in G)
 * green(i): min cost of painting houses 1..i ending with green
 *          => costs[i][2] + Min(red(i-1), blue(i-1))
 * similarly
 * blue(i) => costs[i][1] + Min(red(i-1), green(i-1))
 * red(i) => costs[i][0] + Min(blue(i-1), green(i-1))
 * for i in 1 to N - 1:
 *  .... as above
 *  return min(green[n-1], blue[n-1], red[n-1])
 *
 */
public class PaintFence {
}
