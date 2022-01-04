def solution(n):
    str=['수','박']
    answer = ''
    
    for i in range(n):
        answer+=str[i%2]
    return answer