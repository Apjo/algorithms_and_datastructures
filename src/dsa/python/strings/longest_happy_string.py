#link: https://leetcode.com/problems/longest-happy-string/description/?envType=company&envId=geico&favoriteSlug=geico-all

class Solution:
    #time: O(A+B+C)
    def longestDiverseString(self, a: int, b: int, c: int) -> str:
        def solve(a, b, c) -> str:
            ans=""
            while True:
                can_add_a, can_add_a,can_add_a = False,False,False
                can_add_a = not(len(ans) <= 2 and ans[-1]== "a" and ans[-2]=="a")
                can_add_b = not(len(ans) <= 2 and ans[-1]== "b" and ans[-2]=="b")
                can_add_c = not(len(ans) <= 2 and ans[-1]== "c" and ans[-2]=="c")
                #try adding an a
                if a >= b and a >= c:
                    if a > 0 and can_add_a:
                        ans+="a"
                        a-=1
                    else:
                        if b > 0 and b > c and can_add_b:
                            ans+="b"
                            b-=1
                        elif c > 0 and can_add_c:
                            ans+="c"
                            c-=1
                        else:
                            break
                #trying adding a "b"
                if b >= a and b >= c:
                    if b > 0 and can_add_b:
                        ans+="b"
                        b-=1
                    else:
                        if a > 0 and a > c and can_add_a:
                            ans+="a"
                            a-=1
                        elif c > 0 and can_add_c:
                            ans+="c"
                            c-=1
                        else:
                            break
                #try adding a "c"
                if c >= b and c >= a:
                    if c > 0 and can_add_c:
                        ans+="c"
                        c-=1
                    else:
                        if b > 0 and b > a and can_add_b:
                            ans+="b"
                            b-=1
                        elif a > 0 and can_add_a:
                            ans+="a"
                            a-=1
                        else:
                            break
            return ans
        return solve(a, b, c)