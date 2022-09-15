def solution(arr):
    answer = []
    for n in arr:
        try:
            if n != answer[-1]:
                answer.append(n)
        except:
            answer.append(n)
    return answer