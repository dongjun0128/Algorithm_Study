def solution(numbers, hand):
    answer = ''
    L_Level = 4
    R_Level = 4
    R=12
    L=10

    for num in numbers:
        if num in [1, 4, 7]:
            L_Level = num//3 + 1
            L=num
            answer += 'L'

        elif num in [3, 6, 9]:
            R_Level = num//3
            R=num
            answer += 'R'

        else:
            if num == 0:
                num_level=4
                num=11
            else:
                num_level=num//3+1

            if L in [2,5,8,11]:
                L_distance= abs(num_level - L_Level)
            else:
                L_distance= abs(num_level - L_Level) + 1

            if R in [2,5,8,11]:
                R_distance=abs(num_level-R_Level)
            else:
                R_distance=abs(num_level-R_Level) + 1

            if L_distance < R_distance:
                L=num
                L_Level=num//3+1
                answer += 'L'

            elif L_distance > R_distance:
                R=num
                R_Level=num//3+1
                answer += 'R'            

            else :
                if hand =='right' :
                    R=num
                    R_Level=num//3+1
                    answer += 'R'            
                else:
                    L=num
                    L_Level=num//3+1
                    answer += 'L'            

    return answer