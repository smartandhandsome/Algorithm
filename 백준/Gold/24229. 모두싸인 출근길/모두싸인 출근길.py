"""
4
0 3
2 5
9 10
12 14

5
2 9
0 2
11 15
16 17
19 20
"""
import sys


def jump(index):
    """
    dfs
    """
    s, e, target = bridges[index]
    if s == 0:
        return True
    for i in range(index + 1, len(bridges)):
        if s <= bridges[i][2]:
            return jump(i)
    return False


input = sys.stdin.readline
sys.setrecursionlimit(10**6)
N = int(input())
LR = [list(map(int, input().split())) for _ in range(N)]
LR.sort()
first_L, first_R = LR[0]
bridges = [(first_L, first_R, first_R - first_L)]
for i in range(len(LR)):
    L, R = LR[i]
    left, right, des = bridges[-1]
    if left <= L <= right:
        bridges.pop()
        concat_L, concat_R = min(left, L), max(right, R)
        destination = concat_R - concat_L
        bridges.append((concat_L, concat_R, concat_R * 2 - concat_L))
    else:
        bridges.append((L, R, R * 2 - L))


bridges.sort(key=lambda x: -x[1])
# for bridge in bridges:
#     print(bridge)
answer = -1
for i in range(len(bridges)):
    if jump(i):
        answer = bridges[i][1]
        break
print(answer)
