### BIT BASICS

#### Left shift
*  `A << B ==> A * 2 ^ B`

#### Right shift:
*  `A >> B ==> A / (2 ^ B)`

#### Logical right shift:
*  `A >>> B ==> A / 2 ^ B, does not care about sign`

#### Check if ith bit set in a number N:
*  `return N &(1 << i) != 0`

#### Set ith bit in a number N:
*  `N = N | (1 << i)`

#### Clear ith bit in a number N:
    1. create a mask: ~(1 << i)
    2. return (N & mask)

#### Update the ith bit in a number N to a new value v(could be 0 or 1):
    1. create a mask: ~(1 << i)
    2. Shift value v by i bits: v << i
    3. Finally, return (N & mask | v << i)

#### Inverse all bits of a number N:
*  `~N`

#### 2's complement of a number N:
*  `~N + 1`

#### Removes last set bit in a number N:
*  `N = N & (N - 1)`

#### Clear all the bits from LSB to bit location i in a number N:
    1. create a mask: (1 << i + 1)
    2. mask = mask - 1
    3. return N & mask

#### Clear all the bits from MSB to bit location i in a number N:
    1. create a mask: (1 << i)
    2. mask = mask - 1
    3. return N & mask

#### Check if a number N is a power of 2:
*   `return (N != 0) && ((N & (N - 1)) == 0)`

#### Extract lowest set bit in a number N:
*   `N & ~(N - 1)`

#### Extract ith bit in a number N:
*   `(N >>> i)~ & 1`

### Strings

1. it takes constant time to find the length of the string every time, because length is cached at the time of
   String object creation and Strings are immutable after that
2. substring function creates a new String object. every time you use substring function you are taking a space
   complexity of O(N)
   Tries:
1. If L is the size of the given alphabet, and N is the size of the longest string in the input,
   the time complexity of Insert, Delete and Query operations on a Trie ==> O(N),O(N), and O(N) respectively
2. Given a corpus of N strings, of length L each, if we used a BST instead of a Trie, in order to store strings for efficient lookups
   the time complexity of inserting a new string in such a BST, Assume that it's a self-balancing BST would be
   ==> O(L* log N) : compare each incoming string to the given string in the tree node and make LogN such decisions to find its
   right place

`Substring search`

1. subarray, substring, subsequence -> all of them preserve the order
2. no concept of ordering elements in a subset

`KMP`
- If N is the size of the text, and M is the size of the pattern, then what is the time complexity of KMP ==> O(M + N)
- we need to preprocess the pattern, but not the text

`Suffix trie`
- time to create a suffix trie of adding N suffixes of length N ==> O(N^2)
- lookup a suffix ==> O(length of pattern)
- number of nodes made out of string of length N ==> O(N*N), N suffixes, with max length N and each node is added to the
  tree
- problems that can be solved by first building a suffix trie:

`Encodings`

Unicode

Unicode, a letter maps to something called a code point

`Code point`:

Every platonic letter in every alphabet is assigned a magic number by the Unicode consortium which is written like this: U+0639. This magic number is called a code point. The U+ means “Unicode” and the numbers are hexadecimal.
eg: U+0639 is the Arabic letter Ain.
Encoding

`UCS-2`

uses 2 bytes for storage

`UTF-16`

uses 16 bits

`UTF-8`

In UTF-8, every code point from 0-127 is stored in a single byte.
Only code points 128 and above are stored using 2, 3, in fact, up to 6 bytes.

ASCII

### Binary Trees
1. Full binary tree: A binary tree where each node has 2 children

2. Perfect binary tree:
    A full binary tree where all leaf nodes are at the same depth, and every parent has 2 children

3. Complete binary tree:
    Every level except possibly the last, is completely filled, and all nodes are as far left as possible
4. Height of complete binary tree is Floor(log n) with n nodes
5. A perfect binary tree of height h contains exactly 2^h+1 - 1 nodes, of which 2^h are leaves
6. For a balanced binary tree height=O(log N), else for a skewed one O(N)