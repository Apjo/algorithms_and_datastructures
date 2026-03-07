#link: https://leetcode.com/problems/determine-color-of-a-chessboard-square/description/?envType=company&envId=jpmorgan&favoriteSlug=jpmorgan-all

class Solution:
    def squareIsWhite(self, coordinates: str) -> bool:
        '''
        - map out a..h to respective numbers on the chess board
        - we find that:
            -if the letter position is even is ALWAYS WHITE, then the digit position which is odd is ALWAYS white
            - knowing that, if we get:
                - odd letter, even digit  : always WHITE -> true
                - odd letter, odd digit   : always BLACK -> false
                - even letter, odd digit  : always white -> true
                - even letter, even digit : always BLACK -> false
        '''
        letter_map = {"a":1, "b":2, "c": 3, "d":4,"e":5,"f":6,"g":7, "h":8}
        letter = coordinates[0]
        corresponding_letter_number = letter_map[letter]
        digit = int(coordinates[1])
        #odd letter, even digit OR even letter, odd digit
        if (corresponding_letter_number % 2 == 1 and digit % 2 == 0) or (corresponding_letter_number % 2 == 0 and digit % 2 == 1):
            return True
        #odd letter, odd digit OR even letter, even digit
        return False
