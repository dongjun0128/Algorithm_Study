def solution(a, b):
    day_of_week=['FRI','SAT','SUN','MON','TUE','WED','THU']
    a-=1
    b-=1

    days=0

    while a>0:
        if a in [1,3,5,7,8,10,12]:
            days+=31
        elif a in [4,6,9,11]:
            days+=30
        else:
            days+=29

        a-=1

    days+=b
    return day_of_week[days%7]