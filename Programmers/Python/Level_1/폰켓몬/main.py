def solution(nums):
    answer = 0
    set_num = set(nums)

    if len(nums)/2 <= len(set_num):
        answer=len(nums)/2
    else:
        answer=len(set_num)
    return answer