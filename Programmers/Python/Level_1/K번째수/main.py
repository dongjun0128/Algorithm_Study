def solution(array, commands):
    answer = []
    for c in commands:
        modify_array=array[c[0]-1:c[1]]
        modify_array=sorted(modify_array)
        answer.append(modify_array[c[2]-1])
    return answer