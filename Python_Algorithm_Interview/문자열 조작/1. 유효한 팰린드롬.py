import re

class Solution:
    def isPalindrome(self, s: str) -> bool:
        s=s.lower()
            
        #정규식을 이용해 필요한 문자들만 추출
        #[a-z]는 소문자 알파벳만, [0-9]는 숫자만, 앞에 ^는 not을 의미
        s=re.sub('[^a-z0-9]','',s)
        
        return s==s[::-1]

