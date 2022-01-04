def solution(s):
    answer = ''
    word=s.split(' ')

    for w in word:
        answer+=' '
        temp=''
        for idx in range(len(w)):
            if idx % 2==0:
                temp+=w[idx].upper()
            else:
                temp+=w[idx].lower()
        answer+=temp

    answer=answer[1:]
    return answer