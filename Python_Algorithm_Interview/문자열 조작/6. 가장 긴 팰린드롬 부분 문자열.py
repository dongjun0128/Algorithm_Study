class Solution:
    def longestPalindrome(self, s: str) -> str:
        
        #투 포인터가 늘어나면서 가장 긴 팰린드롬을 리턴
        def expand(left,right) :
            while left >= 0 and right < len(s) and s[left] == s[right] :
                left -= 1
                right += 1
            return s[left+1 : right]

        if len(s) < 2 or s == s[::-1] :
            return s

        result = ''

        #문장 전체를 순회
        for i in range(len(s) - 1) :
            #가장 긴 팰린드롬을 result 값에 저장
            result = max(result, expand(i,i+1), expand(i,i+2), key= len)
            
        return result
        
        
    
    
    
    