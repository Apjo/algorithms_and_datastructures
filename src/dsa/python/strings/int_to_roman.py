#link: https://leetcode.com/problems/integer-to-roman/description/?envType=company&envId=geico&favoriteSlug=geico-all
class Solution:
    #time: O(D) space: O(D+k) where D=number of digits in input, k=roman chars that make up the int
    def intToRoman(self, num: int) -> str:
        #define the symbol table of "roman" -> integer including numbers starting with 4/9 upto M i.e. 1000
        '''
        1. If the value does not start with 4 or 9, select the symbol of the maximal value that can be subtracted from the input, append that symbol to the result, subtract its value, and convert the remainder to a Roman numeral.
        2. If the value starts with 4 or 9 use the subtractive form representing one symbol subtracted from the following symbol, for example, 4 is 1 (I) less than 5 (V): IV and 9 is 1 (I) less than 10 (X): IX. Only the following subtractive forms are used: 4 (IV), 9 (IX), 40 (XL), 90 (XC), 400 (CD) and 900 (CM).
        3. Only powers of 10 (I, X, C, M) can be appended consecutively at most 3 times to represent multiples of 10. You cannot append 5 (V), 50 (L), or 500 (D) multiple times. If you need to append a symbol 4 times use the subtractive form.
        '''
        val_to_symbol = {1: "I", 4: "IV", 5: "V", 9: "IX", 10: "X", 40: "XL", 50: "L", 90:"XC", 100: "C", 400: "CD", 500: "D", 900: "CM", 1000: "M"}
        res=[]
        for val, symbol in val_to_symbol:
            if num == 0:
                break
            append_times = num // val
            if append_times > 0:
                res.append(symbol * val)
            num-=(append_times * val)
        
        return "".join(res)
