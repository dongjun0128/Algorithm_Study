from collections import deque
import sys
read = sys.stdin.readline

def dfs(v):
  print(v,end=' ')
  # 방문처리
  visit_list[v] = 1

  for i in range(n+1):
    if graph[v][i] ==1 and visit_list[i] == 0:
      dfs(i)

  return

def bfs(v):
  queue = deque([v])
  visit_list2[v] = 1

  while queue:
    v = queue.popleft()
    print(v,end=' ')
    for i in range(1,n+1) :
      if visit_list2[i] == 0 and graph[v][i] == 1:
        queue.append(i)
        visit_list2[i] = 1

  return 

n, m, v = map(int, read().split())

graph = [[0] * (n + 1) for _ in range(n + 1)] 
visit_list = [0] * (n + 1)
visit_list2 = [0] * (n + 1)

for _ in range(m):
  a, b = map(int, read().split())
  graph[a][b] = graph[b][a] = 1

dfs(v)
print()
bfs(v)