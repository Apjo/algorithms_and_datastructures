# My Notes on DP
## Designing algo for combinatorial optimization:
1. Brute force: enumerate every possible solution, and then pick the best. This leads to combinatorial explosion
2. Branch and bound:
   3. like backtracking prune away subtrees that are guaranteed to not contain the optimal solution
   4. improved exponential, good for NP-hard optimization problems
5. Greedy strategy:pick the locally optimal choice hoping that it will be a part of the globally optimal solution. Will require proof if it does work!
6. DP: provided there is a "optimal substructure" existing in the problem

So, what we can conclude from above is:
- Branch and Bound is slow, BUT always works
- Greedy: fast, generally doesn't work
- DP: fast, works(provided the property listed above is satisfied)

*Optimal substructure*: any prefix(or suffix) of an optimal solution is also optimal

## Combinatorial optimization
Here "combinatorial" means that without DP, the combinatorial explosion of possibilities would lead to an exponential time 
complexity.
After all, we are optimizing permutations, and combinations(which are "combinatorial objects", always exponential in number)
by trying to pick the "best".