def solution(dartResult):
    answer = 0
    
    numbers=[]
    n=''

    for s in dartResult:
        if s.isdecimal() == True:
            n+=s

        elif s=='S':
            numbers.append(int(n))
            n=''

        elif s == 'D':
            numbers.append(int(n)**2)
            n=''

        elif s=='T':
            numbers.append(int(n)**3)
            n=''

        elif s=='*':
            numbers[-1]*=2

            try:
                numbers[-2]*=2
            except:
                continue

        elif s=='#':
            numbers[-1]*=-1
            
        answer=sum(numbers)
    return answer