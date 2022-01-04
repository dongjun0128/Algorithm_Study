def solution(s, n):
    answer = ''
    for a in s:
        if a >='A' and a<='Z':
            if ord(a)+n > 90:
                answer+=chr(ord(a)+n -26)
            else:
                answer+=chr(ord(a)+n)

        elif a>='a' and a<='z':
            if ord(a)+n > 122:
                answer+=chr(ord(a)+n -26)
            else:
                answer+=chr(ord(a)+n)

        else:
            answer+=' '
    return answer