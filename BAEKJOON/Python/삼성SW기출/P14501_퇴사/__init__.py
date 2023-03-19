import sys

sys.stdin = open('input.txt', 'r')

N = int(input())
L = [list(map(int, input().split())) for _ in range(N)]
dp = [0] * (N + 1)

for i in range(N):
    for j in range(i + L[i][0], N + 1):
        if dp[j] < L[i][1] + dp[i]:
            dp[j] = L[i][1] + dp[i]
print(max(dp))
