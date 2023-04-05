import itertools

arr = [('홍길동', 40), ('퓨무개', 40), ('이순신', 40)]

arr.sort(key=lambda x: (x[1], x[0]))

arr1 = ['a', 'b', 'c']

print(arr1.count('a'))
