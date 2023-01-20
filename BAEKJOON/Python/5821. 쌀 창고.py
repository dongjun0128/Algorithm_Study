import sys, math
import heapq
 
from collections import deque
input = sys.stdin.readline
 
#input
def ip(): return int(input())
def sp(): return str(input().rstrip())
 
def mip(): return map(int, input().split())
def msp(): return map(str, input().split().rstrip())
 
def lmip(): return list(map(int, input().split()))
def lmsp(): return list(map(str, input().split().rstrip()))
 
############### Main! ###############
 
MAX = 100003
p = [0 for _ in range(MAX)]
n, k, b = mip()
a = []
for i in range(n):
    a.append(ip())
 
for i in range(n):
    p[i] = p[i - 1] + a[i]
 
l = 1
r = n
ans = 0
while l <= r:
    m = (l + r) // 2
    c = 0
    for i in range(n - m + 1):
        x = i
        y = i + m - 1
        mid = (x + y) // 2
        z = p[y] - p[mid] - (y - mid) * a[mid] + (mid - x) * a[mid] - (p[mid - 1] - p[x - 1])
        if z <= b:
            c = 1
            break
    if c:
        l = m + 1
        ans = max(ans, m)
    else:
        r = m - 1
 
print(ans)