'''
n = int(input())
x,y=1,1
plans=input().split()

for p in plans :
    if p == "L":
        if y - 1 < 1:
            continue
        y-=1
    elif p == "R":
        if y + 1 > n:
            continue
        y+=1
    elif p == "U" :
        if x - 1 < 1:
            continue
        x -=1
    elif p == "D" :
        if x - 1 > n:
            continue
        x +=1

print(x,y)
'''

n = int(input())
x,y = 1,1
plans = input().split()

dx = [0,0,-1,1]
dy = [-1,1,0,0]
move_types = ['L','R','U','D']

#이동 계획을 하나씩 확인
for plan in plans:
    #이동 후 좌표 구하기
    idx = move_types.index(plan)

    nx = x + dx[idx]
    ny = y + dy[idx]

    if nx < 1 or ny < 1 or nx > n or ny > n:
        continue

    x,y = nx,ny

print(x,y)
