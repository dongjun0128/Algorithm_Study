from itertools import product


class Solution:
    def letterCombinations(self, digits: str) -> List[str]:
        dic = {
    2:['a','b','c'],
    3:['d','e','f'],
    4:['g','h','i'],
    5:['j','k','l'],
    6:['m','n','o'],
    7:['p','q','r','s'],
    8:['t','u','v'],
    9:['w','x','y','z']
}
        combi=[]

        for num in digits:
            combi.append(dic[int(num)])

        answer=[]

        if len(combi) > 0:
            for tup in list(product(*combi)):
                answer.append(''.join(tup))
            
        return answer