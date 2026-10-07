"""
Filename: reorder_log_files.py
Date: 2026-10-06
"""


class Solution:
    def reorderLogFiles(self, logs: list[str]) -> list[str]:
        # prepare a list of only letter logs
        # prepare a list/map of digit only logs
        # for each log in letter logs
        # sort them per instructions
        # finally append all digit logs to the sorted letter logs
        letter_logs = []
        digit_logs = []
        for log in logs:
            identifier = log.split(" ", 1)[0]
            content = log.split(" ", 1)[1]

            def is_all_numbers(ss: str):
                return bool(ss.strip()) and all(
                    item.isdigit() for item in ss.split(" ")
                )

            if not is_all_numbers(content):
                letter_logs.append(log)
            else:
                digit_logs.append(log)

        sorted_letter_logs = sorted(
            letter_logs, key=lambda x: (x.split(" ", 1)[1], x.split(" ", 1)[0])
        )
        sorted_letter_logs.extend(digit_logs)

        return sorted_letter_logs


if __name__ == '__main__':
    Solution().solve()