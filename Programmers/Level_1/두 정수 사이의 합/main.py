def solution(a, b):
    answer = 0
    x,y=max(a,b),min(a,b)
    for n in range(y,x+1):
        answer+=n
    return answer