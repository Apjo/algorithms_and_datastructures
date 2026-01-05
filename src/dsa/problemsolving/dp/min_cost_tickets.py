#link https://leetcode.com/problems/minimum-cost-for-tickets/description/

''''
Look at the last day, and ask how could i travel on the last day, we have 3 options

covered by 1-day pass: is it necessary that the previous prefix must be optimal? yes, because if it wasn't optimal then
we could cover the previous days in a cheaper way, and then add the 1-day pass and make the overall trip cheaper

7-day pass: if i used this pass to cover the 7th day before today the solution upto that must be optimal since if it wasn't
then I could improve it by include this day

30-day pass: similar argument here

so, f(n) = min no. of dollars needed to travel from days[0..n]
         = min(
            f(n-1) + costs[0] if we used 1-day pass,
            f(n - <=6) costs[1] if day i is covered by a 7-day pass, then we have to assume that the 6 previous days were also covered by it
            f(n - <=29) costs[2] if day i is covered by a 30-day pass, then we have to assume that the 29 previous days were also covered by it
            )
'''
def solve(days, costs):
    N = len(days)
    ans=[0]*(N)
    ans[0] = min(costs)
    for i in range(1, N):
        #if day i covered by 1 day pass, then we have to pick the optimal solution for all previous days
        case1 = ans[i - 1] + costs[0]
        #if day i covered by 7 day pass, then we have to assume that previous 6 days are also covered
        j = i - 1
        while j>=0 and days[j] >= days[i] - 6:
            j-=1
        #if all the previous days are covered by the 7-day pass since j went to the beginning of the array
        if j >=0:
            case2=ans[j] + costs[1]
        else:
            #if j goes beyond the days array then it means all days were covered by 7 day pass, so there won't be any previous solution, so we just use the cost for this
            case2=costs[1]
        #now do the same thing for 30 day pass
        j=i-1
        while j>=0 and days[j] >= days[i] - 29:
            j-=1
        #if all the previous days are covered by the 30-day pass since j went to the beginning of the array
        if j >=0:
            case3=ans[j] + costs[2]
        else:
            #if j goes beyond the days array then it means all days were covered by 30 day pass, so there won't be any previous solution, so we just use the cost for this
            case3=costs[2]
        ans[i] = min(case1, case2, case3)
    return ans[N - 1]


def solve_2(days, costs):
    last_day = days[len(days) - 1]
    print("last day=",last_day)
    ans = [0]*(last_day + 1)
    travel_day = 0 #or we could use a boolean array, then first iterate over the days array, and mark all the travel days.
    for i in range(1, last_day + 1):
        print(f"current day={i}, current travel day={days[travel_day]}")
        # If we don't need to travel on this day, the cost won't change.
        # if using a boolean array to keep track of travel days, we would check to see if not travel_days[i] reuse the cost of previous day
        if i < days[travel_day]:
            ans[i] = ans[i - 1]
        else:
            #buy a pass on this day, and move to next travel day
            travel_day += 1
            ans[i] = min(ans[i - 1] + costs[0],
                     ans[max(i - 7, 0)] + costs[1],
                     ans[max(i - 30, 0)] + costs[2])
    return ans[last_day]


def main():
    assert solve(days = [1,4,6,7,8,20], costs = [2,7,15]) == 11
    assert solve_2(days = [1,4,6,7,8,20], costs = [2,7,15]) == 11


if __name__ == '__main__':
    main()
