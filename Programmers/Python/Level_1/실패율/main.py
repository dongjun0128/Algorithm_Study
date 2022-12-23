def solution(N, stages):
    answer = []
    denominator = len(stages)
    fail_ratio = {}

    for s in range(1, N+1):
        if denominator == 0:
            fail_ratio[s] = 0    

        else:
            fail_ratio[s] = stages.count(s)/denominator
            denominator-=stages.count(s)

    fail_ratio = dict(
        sorted(fail_ratio.items(), key=lambda item: item[1], reverse=True))

    answer = list(fail_ratio.keys())
    return answer