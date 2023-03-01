import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline


def find(n):
    if n != friends[n]:
        friends[n] = find(friends[n])
        return friends[n]
    return n


def union(a, b):
    a = find(a)
    b = find(b)
    if a == b:
        return a
    elif a > b:
        friends[a] = b
        numbers[b] += numbers[a]
        return b
    else:
        friends[b] = a
        numbers[a] += numbers[b]
        return a


T = int(input())
for _ in range(T):
    F = int(input())
    friends = {}
    numbers = {}
    for _ in range(F):
        A, B = input().split()
        if not friends.get(A, False):
            friends[A] = A
            numbers[A] = 1
        if not friends.get(B, False):
            friends[B] = B
            numbers[B] = 1
        res = union(A, B)
        print(numbers[res])
