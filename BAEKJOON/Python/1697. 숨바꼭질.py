
from collections import deque


def bfs() : 
    queue = deque()
    queue.append(n)

    while(queue) :
        now = queue.popleft()

        if now == k:
            print(graph[now])
            break

        for next in (now -1 , now +1 , now *2):
            if 0 < next < 10000 and not graph[next] :
                graph[next] = graph[now] + 1
                queue.append(next)


n,k = map(int,input().split())

graph = [0] * 100001

bfs()
