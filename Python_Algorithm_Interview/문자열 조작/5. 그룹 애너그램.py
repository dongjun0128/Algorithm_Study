import collections
from typing import List


class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        anagrams = collections.defaultdict(list)
        
        #애너그램이므로 소팅하면 같은 값을 같는다를 이용
        for word in strs:
            anagrams[''.join(sorted(word))].append(word)
            
        return list(anagrams.values())