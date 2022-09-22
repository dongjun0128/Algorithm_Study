N = int(input())
N_List = set(map(int,input().split()))
M = int(input())
M_list = list(map(int,input().split()))

for m in M_list:
    if m in N_List:
        print(1)
    else:
        print(0)
