from collections import deque

queue = deque()

#BFS 소스코드 구현
def bfs() :
    #큐가 빌 때까지 반복
    while queue:
        x,y = queue.popleft()

        #현재 위치에서 4가지 방향으로 위치 확인
        for i in range(4) :
            nx = x + dx[i]
            ny = y + dy[i]

            # 미로 찾기 공간을 벗어난 경우 무시
            if nx<0 or nx >= n or ny < 0 or ny >= m:
                continue

            # 변인 경우 무시
            if graph[nx][ny] == -1 :
                continue

            # 해당 노드를 처음 방문하는 경우에만 최단 거리 기록
            if graph[nx][ny] == 0:
                graph[nx][ny] = graph[x][y] + 1
                queue.append((nx,ny))
    # 가장 오른쪽 아래까지의 거리 반환
    return

#N,M을 공백을 기준으로 구분하여 입력 받기
m,n = map(int, input().split())

# 2차원 리스트의 맵 정보 입력 받기
graph = []

for i in range(n) :
    graph.append(list(map(int, input().split())))
    for j in range(m) :
        #익은 토마토 queue에 넣기
        if graph[i][j] == 1:
            queue.append([i,j])
            

# 이동할 네 가지 방향 정의 (상,하,좌,우)
dx = [-1,1,0,0]
dy = [0,0,-1,1]

bfs()
cnt = 0
for i in graph :
    for j in i:
        if j ==0:
            print(-1)
            exit(0)
        cnt = max(cnt,j)

print(cnt-1)
