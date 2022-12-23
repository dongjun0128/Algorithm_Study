def solution(n, arr1, arr2):
    answer = []
    bin_arr1=[]
    bin_arr2=[]

    for i in arr1:
        if len(list(bin(i))[2:]) < n:
            temp=list(bin(i))[2:]
            while len(temp) != n:
                temp.insert(0,'0')
            bin_arr1.append(temp)

        else:
            bin_arr1.append(list(bin(i))[2:])


    for i in arr2:
        if len(list(bin(i))[2:]) < n:
            temp=list(bin(i))[2:]
            while len(temp) != n:
                temp.insert(0,'0')
            bin_arr2.append(temp)

        else:
            bin_arr2.append(list(bin(i))[2:])

    answer=[]

    for idx1 in range(n):
        temp_str=''
        for idx2 in range(n):
            if bin_arr1[idx1][idx2]=='1' or bin_arr2[idx1][idx2]=='1':
                temp_str+='#'
            else :
                temp_str+=' '
        answer.append(temp_str)
    return answer