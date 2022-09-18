import sys
read = sys.stdin.readline

n= int(input())
m = int(input())

v = 1

graph = [[0] * (n + 1) for _ in range(n + 1)] 
visit_list = [0] * (n + 1)

for _ in range(m):
  a, b = map(int, read().split())
  graph[a][b] = graph[b][a] = 1

result =[]

def dfs(v):
  result.append(v)
  # 방문처리
  visit_list[v] = 1

  for i in range(n+1):
    if graph[v][i] ==1 and visit_list[i] == 0:
      dfs(i)

  return

dfs(v)

print(result)
