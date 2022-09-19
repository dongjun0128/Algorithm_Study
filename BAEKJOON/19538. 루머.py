import sys
from collections import deque

def bfs(N,graph,fisrtInfected) :

    queue = deque([])
    #정답을 저장할 리스트
    answer = [-1] * (N + 1)
    #내가 루머를 믿을 턴인가를 알 수 있는 리스트
    turn = [0] * (N+1)

    #첫 번째 감염자들을 큐에 넣고 0분으로 초기화
    for x in fisrtInfected:
        queue.append(x)
        answer[x] = 0

    # 주변에 반 이상이 걸려야 나도 걸린다.
    for i in range(1,N+1):
        turn[i] = len(graph[i]) // 2        
    
    #BSF 시작
    while queue :
        # 현재 감염자
        current_infected = queue.popleft()

        # 다음 후보들을 뽑아놓고
        for next_infected in graph[current_infected] :
            if next_infected == 0:
                break

            # 주변에 걸렸으므로 카운팅
            turn[next_infected] -= 1

            # 주변에 반 이상이 걸렸고, 아직 본인은 걸리지 않았다면,
            if turn[next_infected] == 0 and answer[next_infected] == -1 :
                # 큐에 넣는다.(감염이 된다.)
                queue.append(next_infected)
                # 감염된 시간은 전 사람이 걸린 시간 +1
                answer[next_infected] = answer[current_infected] + 1

        
    return answer[1:]



input = sys.stdin.readline
N = int(input())
graph = [[] for _ in range(N+1)]
for i in range(1, N+1):
    graph[i] = list(map(int, input().split()))


#print(graph)

M = int(input())
firstInfected = list(map(int, input().split()))
print(" ".join(map(str, bfs(N, graph, firstInfected))))

