# Algorithms and Data Structures

A comprehensive repository containing implementations of classic algorithms, data structures, and solutions to competitive programming problems. This repository serves as both a learning resource and a reference for algorithmic problem-solving.

## 📚 Contents Overview

### Languages
- **Java**: Full implementations of data structures, algorithms, and daily problem solutions
- **Python**: Algorithms, data structures, and solutions to various online judge problems

## 🏗️ Repository Structure

### Java Implementation (`src/dsa/java/`)

#### Core Data Structures (`datastructures/`)
- **Graphs**: Graph representations and algorithms
- **Hashtable**: Hash table implementations and variations
- **Linked Lists**: Single and double linked list implementations
- **Stacks and Queues**: Stack and queue data structures
- **Trees**: Binary trees, BSTs, and other tree variants

#### Algorithms
- **Sorting and Searching** (`sortingandsearching/`)
  - Binary Search
  - Bubble Sort, Selection Sort, Insertion Sort
  - Merge Sort, Quick Sort, Heap Sort
  
- **Recursion** (`recursion/`)
  - Basic recursion examples
  - N-Queens problem
  - Backtracking techniques

#### Problem Solutions
- **Daily Solutions** (`_2024/`, `_2024_08_*/`): Daily algorithm challenge solutions
- **Problem Categories** (`problems/`)
  - Matrix Chain Multiplication (Dynamic Programming)
  - String problems
  - Subset Sum (DP)

#### Utilities
- `Template.java`: Competitive programming template with input reader
- `DSAUtils.java`: Utility functions for common operations

### Python Implementation (`src/dsa/python/`)

- **Daily Solutions** (`dailybyte/`): Daily coding challenges
- **LeetCode Contests** (`lc_contests/`): Competitive solutions
- **CodeForces** (`cf_solves/`): CodeForces problem solutions
- **NeetCode** (`neetcode/`): Solutions from NeetCode curriculum
- **Data Structures & Algorithms**
  - Arrays (`arrays/`)
  - Dynamic Programming (`dp/`)
  - Heaps (`heaps/`)
  - Recursion & Backtracking (`recursion_backtracking/`)
  - Stack (`stack/`)
  - Strings (`strings/`)
- **Specialized**
  - Segment Tree implementation (`segment_tree_impl.py`)

## 🎯 Problem Categories

### Dynamic Programming
Understanding DP requires:
1. Recursive formula/algorithm with decrease & conquer or divide & conquer
2. Focus on the rightmost element and subproblem structure
3. Enumeration of choices for each state
4. Translation to bottom-up DP solution with proper dependency handling

### Algorithm Design Strategies
- **Brute Force**: Enumerate all possible solutions (combinatorial explosion)
- **Branch and Bound**: Prune guaranteed non-optimal subtrees
- **Greedy**: Select locally optimal choices
- **Dynamic Programming**: Optimal substructure exploitation

### Problem Sources
- LeetCode: Popular online judge
- CodeForces: Competitive programming platform
- DailyByte: Daily coding challenges
- NeetCode: Curated algorithm curriculum

## 🚀 Quick Start

### Java
Navigate to `src/dsa/java/` and compile the desired file:
```bash
javac path/to/File.java
java dsa.java.File
```

### Python
Navigate to `src/dsa/python/` and run the desired file:
```bash
python3 path/to/file.py
```

## 📝 Notes

- Solutions are organized by problem source and date for easy navigation
- Each problem typically includes the solution implementation and approach commentary
- The repository serves as both a learning journal and reference for future problems
- Template.java provides a competitive programming foundation for Java solutions

## 📖 Learning Resources

For deeper understanding of key concepts, see:
- `src/dsa/java/recursion/README.md`: Recursion and backtracking explanations
- `src/dsa/java/problems/README.md`: Dynamic programming design methodology

**Purpose**: This repository documents the journey of learning and mastering algorithms and data structures through consistent practice and implementation.