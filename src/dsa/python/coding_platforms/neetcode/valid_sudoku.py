from typing import List
class Solution:
    def isValidSudoku(self, board: List[List[str]]) -> bool:
        seen_row=[[False]*(9) for _ in range(9)]
        seen_col=[[False]*(9) for _ in range(9)]
        seen_box=[[False]*(9) for _ in range(9)]
        for r in range(9):
            for c in range(9):
                if board[r][c] == '.':
                    continue
                curr_num = ord(board[r][c] - ord('1'))
                if seen_row[r][curr_num]:
                    return False
                seen_row[r][curr_num] = True
                if seen_col[curr_num][c]:
                    return False
                seen_col[curr_num][c] = True
                box_row = r // 3
                box_col = c // 3
                box_index = box_row * 3 + box_col
                if seen_box[box_index][curr_num]:
                    return False
                seen_box[box_index][curr_num] = True
        return True