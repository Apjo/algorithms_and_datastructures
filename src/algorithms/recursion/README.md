### Recursion BASICS
### Lazy manager approach for a general recursion solution
```
IF (test for a simple case) THEN
    compute the simple solution without using recursion
ELSE
    #Divide & conquer OR Decrease & conquer
    BREAK the problem into subproblems of the same form
    solve each of the subproblems by calling THIS function recursively
    reassemble the subproblem solutions into a solution for the whole
```
### Recursive Leap of faith
 Assume that any recursive call automatically gets the right answer as long as the arguments are simpler
than the original arguments

- Rule of sum
  choose one of A different options OR one of B different options -> A + B choices
- Rule of product
  choose one of A different options FOLLOWED by choose one of B different options ->
    A * B choices
  
- When repetition is allowed the ordering is an "Arrangement" 
- When repetition is not allowed the ordering is a "Permutation"
- Decrease the problem by a constant factor vs. a number will gain a large gain in complexity
think for ex. modulo division for calculating x ^ y
