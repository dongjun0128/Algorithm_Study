def solution(board, moves):
    stack=[]
    cnt=0

    for m in moves:
        m-=1
        for y in range(len(board)):
            if board[y][m] != 0:
                if len(stack)>=1 and stack[-1] == board[y][m]:
                    del stack[-1]
                    cnt+=1
                else:
                    stack.append(board[y][m])  

                board[y][m]=0
                break

    return cnt*2