import heapq
import sys

input = sys.stdin.readline

V, E = map(int, input().split())
graph = [[] for _ in range(V+1)]
visit = [False] * (V+1)
for _ in range(E):
    A, B, C = map(int, input().split())
    graph[A].append([C, B])
    graph[B].append([C, A])

ans = 0
cnt = 0
heap = [[0, 1]]
while heap:
    if cnt == V:
        break
    w, s = heapq.heappop(heap)
    if not visit[s]:
        visit[s] = True
        ans += w
        for g in graph[s]:
            heapq.heappush(heap, g)
print(ans)
