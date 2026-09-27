#link: https://leetcode.com/problems/reverse-words-with-same-vowel-count/description/
class Solution:
    def reverseWords(self, s: str) -> str:
        VOWEL = "aeiou"
        
        def check_dicts_equal(f1, f2):
            if f1 == f2:
                return True
            f1_vals = sum(f1.values())
            f2_vals = sum(f2.values())
            return f1_vals == f2_vals

        def rev_str(curr_str: str) -> str:
            return curr_str[::-1]

        def contains_vowel(curr_str: str):
            for c in curr_str:
                if c in VOWEL:
                    return True
            return False
        
        def build_freq_map_vowels(curr_str: str) -> dict:
            freq={}
            for cc in curr_str:
                if cc in VOWEL:
                    if cc in freq:
                        freq[cc]+=1
                    else:
                        freq[cc]=1
            return freq
        
        text = s.lower().split()
        ss = ""
        first = text[0]
        freq={}
        first_contains_vowel=False
        
        if contains_vowel(first):
            first_contains_vowel = True
            f1=build_freq_map_vowels(first)

        ss+=first+" "
        #go through each string
        for i in range(1, len(text)):
            curr_str = text[i]
            if first_contains_vowel:
                if contains_vowel(curr_str):
                    freq2 = build_freq_map_vowels(curr_str)
                    if check_dicts_equal(f1, freq2):
                        rev_stri = rev_str(curr_str) #curr_str[::-1]
                        ss+=rev_stri+" "
                    else:
                        ss+=curr_str+" "
                else:
                    ss+=curr_str+" "
            else:
                #first string doesn't contain any vowel, so all such strings that do not contain vowel must be reversed, others kept as is
                if contains_vowel(curr_str):
                    ss+=curr_str+" "
                else:
                    rev_stri = rev_str(curr_str) #curr_str[::-1]
                    ss+=rev_stri+" "

        return ss.strip()
