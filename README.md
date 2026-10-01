# Algorithms and Data Structures

A learning and reference repository for algorithms, data structures, and coding-problem solutions in Java and Python.

## Repository Layout

### Java (`src/dsa/java/`)
- `datastructures/`: Graphs, hash tables, linked lists, stacks and queues, and trees
- `sortingandsearching/`: Sorting and searching implementations
- `recursion/`: Recursion and backtracking examples, including N-Queens
- `problems/`: Problem-focused implementations, including matrix-chain multiplication and subset sum
- `_2024/`: Date-organized problem solutions
- `DSAUtils.java` and `Template.java`: Shared utilities and a competitive-programming input template

### Python (`src/dsa/python/`)
- Topic directories include `arrays_and_strings/`, `dp/`, `hashmap/`, `heaps/`, `intervals/`, `linked_list/`, `matrix/`, `queue/`, `recursion_backtracking/`, `sliding_window/`, `stack/`, and `trees_and_graphs/`
- `coding_platforms/` and `lc_contests/`: Platform- and contest-related solutions
- `_2026/`: Date-organized solutions
- `ListNode.py` and `TreeNode.py`: Shared node definitions

## Running Solutions

Run commands from the repository root.

### Python

```bash
python3 src/dsa/python/path/to/file.py
```

Replace `path/to/file.py` with the solution's path. Some files may be intended for use by an online judge rather than direct execution.

### Java

Java package declarations and entry points vary by class. Compile a class with `main` using `javac -d out <source-file>`, then run it with `java -cp out <fully.qualified.ClassName>`. Use the package declared in the source for the class name; not every solution is a standalone program.

## Study Notes

- [Recursion and backtracking](src/dsa/java/recursion/README.md)
- [Dynamic programming problem design](src/dsa/java/problems/README.md)

## Maintenance Note

Java remains part of the repository for now. The Java package may be retired in a future cleanup, but no retirement is currently underway.