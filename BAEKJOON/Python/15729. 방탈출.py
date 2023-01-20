N = int(input())
correct = list(map(int,input().split()))
answer = [0] * (N)

cnt =0

for idx in range(N):
    if correct[idx] != answer[idx]:
        answer[idx] = 1 - answer[idx]
        cnt+=1
        if idx+1 < N:
            answer[idx+1] = 1 - answer[idx+1]
        if idx + 2< N:
            answer[idx+2] = 1 - answer[idx+2]

print(cnt)


