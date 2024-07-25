package dsa.problems;
//LC#935

/**
 * A counting problem
 * Q: How many different phone numbers are we looking at if the length is N of a phone #?
 * Ans: For each digit on an avg. it would be exponential no.of phone no.s
 * If there were no restrictions on the digits, it would be counting the no.of permutations of len N, where in for each iteration(or blank)
 * we can fill in 10 digits
 * PREPARE:
 * A mapping of which digit can come from which set of digits, in this case 5 cannot be reached.
 * We want from my neighbor(say X), all phone no.s of len N - 1, which end with either of their neighbors(x1, x2,..xk)
 * So we need the length, and the digit in our recurrence relation
 * We interpret our recurrence relation f(i, d) to be # of phone nos. of len i, ending with digit d
 * eg f(i, 0)        = f(i-1, 4) + f(i - 1, 6) => since 0 is reachable from 4 and 6
 * We will have an entry for f(i,5) to be 0 all the way.
 * So, a digit can vary from 0-9, and len of the no. can vary from 1-N
 *Follow up: could be instead of a Knight, a queen
 *O(10N + no.of edges for each vertex) ~ O(N)
 */
public class KnightDialer {
    //TODO
}
