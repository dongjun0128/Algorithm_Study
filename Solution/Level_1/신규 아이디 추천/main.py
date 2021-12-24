import re

def solution(new_id):
    
    #1
    new_id=new_id.lower()

    #2
    new_id=new_id.replace("~","").replace("!","").replace("@","").replace("#","").replace("$","").replace("%","").replace("^","").replace("&","").replace("*","").replace("(","").replace(")","").replace("=","").replace("+","").replace("[","").replace("{","").replace("]","").replace("}","").replace(":","").replace("?","").replace(",","").replace("<","").replace(">","").replace("/","")

    #3
    flag=1
    while(flag==1):
        if(".." in new_id):
            new_id=new_id.replace("..",".")
        else:
            flag=0

    #4
    if(new_id[-1]=='.'):
        new_id=new_id[:-1]


    if(len(new_id) != 0 and new_id[0]=='.'):
        new_id=new_id[1:]
        
    #5
    if(len(new_id)==0):
        new_id='a'

    #6
    if(len(new_id)>=16):
        new_id=new_id[:15]

    if(new_id[-1]=='.'):
        new_id=new_id[:-1]

    #7
    if(len(new_id)<=2):
        while(len(new_id)<3):
            new_id=new_id + new_id[-1]
    
    return new_id