import sys

def binary_search(array,target,start,end) :
    if start > end:
        return

    mid = (start + end) // 2

    if target == array[mid]:
        return mid

    elif target > array[mid]:
        return binary_search(array,target,mid+1,end)

    else :
        return binary_search(array,target,start,mid-1)

N = int(sys.stdin.readline().rstrip())
array = list(map(int,input().split()))
array.sort()

M = int(sys.stdin.readline().rstrip())
x = list(map(int,input().split()))

for target in x:
    result = binary_search(array,target,0,N-1)

    if result != None:
        print('yes',end=' ')
    else :
        print('no',end=' ')

'''
[input]
5
8 3 7 9 2 
3
5 7 9

[ouput]
no yes yes
'''