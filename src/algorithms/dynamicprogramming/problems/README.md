## 2 stages in building a DP problem
1. Use decrease and conquer or divide and conquer to come up with a clear recursive 
formula/algo.
   Think: 
   
    How will you combine solutions to smaller instances of exactly the same problem to answer overall problem?
   
2. Translate the recursive algo/formula in a bottom-up DP solution:
    
    Process:
    - Identify the different subproblems (how many there are)
    - Create dependency graph for those subproblems (DAG)
    - Chose a datastructure to store the solutions to different subproblems built up 
        in the topological sort order
    - Find a convenient evaluation order to fill up the datastructure(with base cases)
    - Analyse time and space complexity
   
## Designing Algos for combinatorial optimizations
If you had no idea what do
1. Brute force: Enumerate every possible solution and pick the best. This leads to combinatorial explosion
2. Branch and Bound: Like backtracking, prune away subtrees that are guaranteed to not contain the optimal solution.
   Improved exponential, good for NP-hard optimization problems
3. Greedy strategy: pick the locally optimal choice,hoping that it will be part of the globally optimal solution.
   This requires a proof.
4. DP, requires optimal substructure property i.e. any prefix of an optimal solution is also optimal