def solution(answers):
    answer = []
    st1=[1,2,3,4,5]
    st2=[2, 1, 2, 3, 2, 4, 2, 5]
    st3=[3, 3, 1, 1, 2, 2, 4, 4, 5, 5]

    cnt1=0
    cnt2=0
    cnt3=0

    answer=[]

    for a in range(len(answers)):
        if st1[a%len(st1)] == answers[a]:
            cnt1+=1
            #print(st1[a%5])

        if st2[a%len(st2)] == answers[a]:
            cnt2+=1
            #print(st2[a%5])

        if st3[a%len(st3)] == answers[a]:
            cnt3+=1
            #print(st3[a%5])

    cnt=[cnt1,cnt2,cnt3]

    for i in range(len(cnt)):
        if max(cnt) == cnt[i]:
            answer.append(i+1)
    return answer