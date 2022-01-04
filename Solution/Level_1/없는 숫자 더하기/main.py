def solution(numbers):
    sum=0
    num=[0,1,2,3,4,5,6,7,8,9]
    
    for i in num:
        if i not in numbers:
            sum+=i
    
    answer = sum
    return answer