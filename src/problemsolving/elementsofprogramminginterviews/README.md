###BIT BASICS

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
*   `(N >>> i)~~~~~~~~ & 1`