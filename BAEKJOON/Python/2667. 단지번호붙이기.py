#DFS로 특정 노드를 방문하고 연결된 모든 노드들도 방문
def dfs(x,y) :
    #주어진 범위를 벗어나는 경우에는 즉시 종료
    if x<= -1 or x>=n or y<= -1 or y>=n:
        return False

    #현재 노드를 아직 방문하지 않았다면
    if graph[x][y] == 1:
        #해당 노드 방문 처리
        graph[x][y] = 0

        num = 1

        #상,하,좌,우에 있는 단지들 모두 더하기
        num+=dfs(x-1,y)
        num+=dfs(x,y-1)
        num+=dfs(x+1,y)
        num+=dfs(x,y+1)
        return num

    return False


#n,m을 공백을 기준으로 구분하여 입력받기
n = int(input())

# 2차원 리스트의 맵 정보 입력 받기
graph = []
for i in range(n) :
    graph.append(list(map(int, input())))

#모든 노드(위치)에 대하여 음료수 채우가
result = []

for i in range(n) :
    for j in range(n) :
        #현재 위치에서 DFS 수행
        num = dfs(i,j)
        if num != 0:
            result.append(num)

result.sort()

#정답 출력
print(len(result))
for num in result:
    print(num)