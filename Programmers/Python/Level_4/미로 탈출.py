import heapq as hq

def solution(n, start, end, roads, traps):
    edges = [[] for _ in range(n + 1)]

    trap_idx = {t : n for n, t in enumerate(traps)}
    traps = set(traps)
	
    #역방향은 마이너스간선을 넣어줘서 방향을 구분한다.
    for v1, v2, w in roads:
        edges[v1].append((v2, w))
        edges[v2].append((v1, -w))
	
    #heap :(distance, 현재 위치, 현재 상태) 
    heap = [(0, start, 0)]
    dist = {}

	#다익스트라    
    while heap:
    	
        #원소 중 가장 이동 거리가 짧은 경로를 갖고 온다.
        dis, here, state = hq.heappop(heap)
        
        #이미 현재 상태를 방문했다면 아래의 과정을 생략한다. 
        if dist.get((here, state), None):
            continue
		
        #현재 상태 기록
        dist[(here, state)] = dis
        
        #현재 위치가 목적지라면 거리 반환
        if here == end:return dis
		
        direction = 1 #방향을 나타내는 변수로 현재 위치가 함정일 때 눌렸는지 안눌렸는지를 나타낸다.
        #만약 현재 위치가 트랩이고 그 트랩을 밟은 상태라면 direction을 바꿔준다.
        if here in traps and (state & (1 << trap_idx[here])):
            direction *= -1
            #이렇게 하는 이유 : 눌려 있다면 here로 들어오는 간선들의 방향이 바뀌었을 것

        for there, w in edges[here]: #인접한 노드에 대해서
            #다음에 방문할 노드가 트랩이고 현재 그 트랩을 한번 방문했다면 가중치 값의 부호를 바꿔준다.
            if there in traps and (state & (1 << trap_idx[there])):
                w *= -1
                
                """
                아래와 같이 4가지 경우를 나누어주기 위함
                1. here가 눌려 있지 않고 there가 눌려 있지 않다면 정방향 (w가 양수, direction 양수)
                2. here가 눌려 있고 there가 눌려있지 않다면 역방향 		(w가 음수, direction 음수)
                3. here가 눌려 있지 않고 there만 눌려 있다면 정방향 	(w가 양수, direction 양수)
                4. here도 눌려 있고 there도 눌려 있다면 정방향  		 (w가 음수, direction 음수)
                """

            #현재 방향과 가중치 값의 곱이 양수라면
            if w * direction > 0:
                new_state = state
                if there in traps:
                    if state & (1 << trap_idx[there]): #트랩을 밟지 않은 상태로 바꿔준다.
                        new_state = state & ~(1 << trap_idx[there])
                    else: #트랩을 밟은 상태로 바꿔준다.
                        new_state = state | (1 << trap_idx[there]) 

                hq.heappush(heap, (dis + w * direction, there, new_state))
    return dist

n,start,end,roads,traps = 4,1,4,[[1, 2, 1], [3, 2, 1], [2, 4, 1]],[2, 3]

print(solution(n,start,end,roads,traps))

