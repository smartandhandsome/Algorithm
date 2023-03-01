import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)


def find(n):
    # if n == parents[n]:
    #     return n
    # parents[n] = find(parents[n])
    # return parents[n]
    if n != parents[n]:
        parents[n] = find(parents[n])
    return parents[n]


def union(n1, n2):
    num1 = find(n1)
    num2 = find(n2)
    if num1 > num2:
        parents[num1] = num2
    else:
        parents[num2] = num1


N, M = map(int, input().split())
parents = [i for i in range(N+1)]
for _ in range(M):
    op, n1, n2 = map(int, input().split())
    if op == 0:
        union(n1, n2)
    elif op == 1:
        p1 = find(n1)
        p2 = find(n2)
        if p1 == p2:
            print("YES")
        else:
            print("NO")

