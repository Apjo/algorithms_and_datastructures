from typing import List


def solve(num_rows: int) -> List[List[int]]:
    output = []
    for row in range(num_rows):
        temp=[]
        for col in range(row + 1):
            #here we are hitting a wall i.e. thinking of nCk, we set nC0=1 where k=0, and nCn=1 where k=n
            if col == 0 or col == row:
                temp.append(1)
            else:
                #here following from above we know nCk = n-1Ck-1 + n-1Ck
                res1 = output[row - 1][col - 1]
                res2 = output[row - 1][col]
                temp.append(res1 + res2)
        output.append(temp)

    return output


def main():
    assert solve(num_rows=5) == [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]


if __name__ == '__main__':
    main()
