import collections
import re
from typing import List

class Solution:
    def mostCommonWord(self, paragraph: str, banned: List[str]) -> str:
        
        #re.sub(r'[^\w]',' ',paragraph) 에서 \w는 단어 문자를 뜻하며, ^는 not을 의미
        #re.sub(r'[^\w]',' ',paragraph)의 의미는 paragraph에서 단어 문자가 아닌 모든 문자를 공백으로 치환
        words=[word for word in re.sub(r'[^\w]',' ',paragraph).lower().split() 
               if word not in banned]

        #딕셔너리 형태로 단어가 몇 번 나왔는지 세준다.
        counts = collections.Counter(words)
        
        #가장 많이 나온 단어를 출력
        return (counts.most_common(1)[0][0])