
def binary_search(array,target,start,end):
    if start > end :
        return

    mid = (start + end) //2

    total=0

    for x in array :
        if x > mid :
            total += x - mid

    if target == total :
        return mid

    elif target < total:
        return binary_search(array,target,mid+1,end)

    else:
        return binary_search(array,target,start,mid-1)


n,m = map(int,input().split())

array = list(map(int,input().split()))

print(binary_search(array,m,0,max(array)))
