def solution(arr, divisor):
    answer = []
    for n in arr:
        if n % divisor==0:
            answer.append(n)

    answer=sorted(answer)
    if len(answer) == 0:
        answer.append(-1)
    return answer