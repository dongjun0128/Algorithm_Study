def solution(lottos, win_nums):
    
    rank=[6,6,5,4,3,2,1]
    
    cnt_0=lottos.count(0)
    rank_index=0
    
    for num in lottos:
        if num in win_nums:
            rank_index+=1   
            
    
    answer = [rank[rank_index + cnt_0], rank[rank_index]]
    return answer