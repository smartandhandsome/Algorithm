def remov(row, col, s):
    visit[row][col] = 0
    direction = (-1, 0), (1, 0), (0, -1), (0, 1)
    for i in range(4):
        move_row, move_col = direction[i]
        moved_row = (move_row * s) + row
        moved_col = (move_col * s) + col
        visit[moved_row][moved_col] = 0


def check(row, col):
    direction = (-1, 0), (1, 0), (0, -1), (0, 1)
    scale = 0
    flag = False
    while True:
        for i in range(4):
            move_row, move_col = direction[i]
            moved_row = (move_row * (scale + 1)) + row
            moved_col = (move_col * (scale + 1)) + col
            if 0 > moved_row or moved_row >= N or 0 > moved_col or moved_col >= M or painting[moved_row][moved_col] != "*":
                flag = True
                break
        if flag:
            break
        scale += 1
    return scale


painting = []
N, M = map(int, input().split())
for i in range(N):
    painting.append(list(input()))

visit = [[0] * M for _ in range(N)]
for i in range(N):
    for j in range(M):
        if painting[i][j] == '*':
            visit[i][j] = 1

answer = []
for i in range(N):
    for j in range(M):
        if painting[i][j] == '*':
            scale = check(i, j)
            if scale == 0:
                continue
            for s in range(scale, 0, -1):
                remov(i, j, s)
                answer.append([i+1, j+1, s])
is_can = True
for i in range(N):
    for j in range(M):
        # print(visit[i][j], end="")
        if visit[i][j] == 1:
            is_can = False
    # print()
if is_can:
    print(len(answer))
    for ans in answer:
        print(*ans)
else:
    print(-1)
