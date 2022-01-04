import math

def prime_num(num):
    for i in range(2,int(math.sqrt(num))+1):
        if num % i == 0:
            return False
    return True

def solution(n):
    answer = 0
    
    for i in range(2,n+1):
        if prime_num(i) == True:
            answer+=1
    
    return answer