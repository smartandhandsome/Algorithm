from collections import deque

N, K = map(int, input().split())
q = deque([N])
visit = [-1] * 100001
visit[N] = 0
while q:
    location = q.popleft()
    if location == K:
        print(visit[location])
        break
    for move in [location * 2, location - 1, location + 1]:
        if 0 <= move < 100001 and visit[move] == -1:
            if move == location * 2:
                visit[move] = visit[location]
                q.appendleft(move)
            else:
                visit[move] = visit[location] + 1
                q.append(move)
