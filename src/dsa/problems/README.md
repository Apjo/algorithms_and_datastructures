## 2 stages in building a DP problem
Think of the optimal solution as something that will be laid out in a sequence
1. Use decrease and conquer or divide and conquer to come up with a clear recursive 
formula/algo.
   
2. Focus on the rightmost element of that sequence, because you are a lazy manager who just wants to complete the last
   step of the overall project, depending on your reports to do all the rest of the work
   As a lazy manager i will think of:
   - the last move in a unique paths/min cost path problem or n stairs problem
   - the last coin in the coin change problem
3. Think about the different choices/options for what the rightmost element could be, and for each of those options, what
   the preceding subproblem(neighbor) could be, and what information from those preceding subproblem neighbors would be 
   required for you to solve your problem
4. Continuing on the lazy manager approach, each preceding subproblem will be solved by an equally lazy neighbor, and those too
   will be working on the rightmost bit of the solution, and relying on their subordinates for the solution to the remaining
   prefix of the problem. In this way, the leaf level workers will be solving the base cases
5. think of every subproblem as a DAG
6. each manager will have multiple reports, and each worker can now have multiple managers they report to. Every worker 
   is using the solutions from their reports to build the solution for a slightly larger prefix, and handing it above to
   all their managers
   
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