def solution(s):
    Y=s.count('y') +s.count('Y')
    P=s.count('p') +s.count('P')

    if Y==P:
        return True

    else:
        return False