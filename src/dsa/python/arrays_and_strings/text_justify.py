"""
Filename: text_justify.py
Date: 2026-11-05
"""


class Solution:
    # time: O(N * maxWidth)
    def fullJustify(self, words: list[str], maxWidth: int) -> list[str]:
        line_len, curr_buff, letter_count = 0, [], 0
        res = []

        for word in words:
            # calculate line len
            line_len = len(word) + len(curr_buff) + letter_count
            # if line_len > W
            if line_len > maxWidth:
                # do justification
                num_spaces = maxWidth - letter_count
                for i in range(num_spaces):
                    # size will be used for modulo "magic" for round robin
                    # we use max. 1 because atleast one word would be there and to avoid modulo by 0
                    size = max(1, len(curr_buff) - 1)
                    index = i % size
                    # add space to each word in round robin fashion
                    curr_buff[index] += " "

                res.append("".join(curr_buff))
                curr_buff = []
                letter_count = 0
            # else keep on adding current word to curr_buff, and incrementing the letter count
            curr_buff.append(word)
            letter_count += len(word)
        # finally we need to handle the last string to be always lefy justified.
        # To form the last line by joining with space and left justify to maxWidth using ljust (python method) that means we are to pad additional spaces to the right to make string length equal to maxWidth
        res.append(" ".join(curr_buff).ljust(maxWidth))
        return res


if __name__ == "__main__":
    Solution().solve()
