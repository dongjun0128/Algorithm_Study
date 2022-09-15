class Solution:
    def reorderLogFiles(self, logs: List[str]) -> List[str]:
        
        digits,letters=[],[]
        
        for log in logs:
            if log.split()[1].isdigit() :
                digits.append(log)
                
            else:
                letters.append(log)
                
        answer=[]
        
        #x.split()[1:]로 소팅을 하되, 동일한 값이면 x.split()[0]값으로 소팅
        letters.sort(key = lambda x : (x.split()[1:], x.split()[0]))
        
        return(letters + digits)