def solution(sizes):
    answer = 0
    
    
#큰 값을 무조건 x, 작은값을 무조건 y로 두고 계산
    max_x,max_y=0,0

    for temp_x,temp_y in sizes:
        x=max(temp_x,temp_y)
        y=min(temp_x,temp_y)

        max_x=max(x,max_x)
        max_y=max(y,max_y)

    answer=(max_x*max_y)

    return answer