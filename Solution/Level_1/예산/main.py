def solution(d, budget):
    answer = 0
    while budget > 0 and len(d) > 0:
        budget-= min(d)
        d.remove(min(d))
        answer+=1

    if budget < 0:
        answer -= 1
    return answer