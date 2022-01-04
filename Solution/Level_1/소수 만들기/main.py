from itertools import combinations

def prime_number(num):
    for i in range(2,num):
        if num%i ==0:
            return 0
    return 1

def solution(nums):
    cnt=0
    
    comb = combinations(nums,3)
    sums=list(map(sum,comb))
    

    for i in sums:
        if prime_number(i) == 1:
            cnt+=1

    return cnt