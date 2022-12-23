from itertools import combinations

def solution(numbers):
    answer = []
    temp=[]

    comb=list(combinations(numbers,2))

    for x,y in comb:
        temp.append(x+y)

    answer=sorted(list(set(temp)))
    return answer