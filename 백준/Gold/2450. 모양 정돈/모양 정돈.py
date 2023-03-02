from itertools import permutations

cnt = [0] * 4

n = int(input())
shapes = list(map(int, input().split()))
cnt = [0, shapes.count(1), shapes.count(2), shapes.count(3)]

answer = 9999999999999999
for p in permutations(range(1, 4)):
    stat = [[0]*4 for _ in range(4)]
    cur = 0
    for i in range(3):
        for c in range(cnt[p[i]]):
            stat[p[i]][shapes[cur + c]] += 1
        cur += cnt[p[i]]
    answer = min(answer, stat[1][2] + stat[1][3] + max(stat[2][3], stat[3][2]))

print(answer)
