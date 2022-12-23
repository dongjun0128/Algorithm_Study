def solution(n, lost, reserve):
    answer = 0
    st=[1] * n

    lost=sorted(lost)
    reserve=sorted(reserve)

    for x in lost:
        x-=1
        st[x]=0

    for i in list(set(lost)&set(reserve)):
        reserve.remove(i)
        st[i-1]=1

    for o in reserve:
        o-=1

        if o != 0 and st[o-1] == 0:
            st[o-1]=1
            continue

        if o != n-1 and st[o+1] == 0:
            st[o+1] =1
            continue
            
    answer=st.count(1)
    
    return answer