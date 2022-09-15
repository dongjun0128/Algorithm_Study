def solution(s):
    
    if len(s) != 4 and len(s) != 6:
        return False
    
    for str in s:
        if str.isdecimal() == False:
            return False
    return True