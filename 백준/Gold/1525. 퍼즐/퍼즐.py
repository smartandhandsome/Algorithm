from collections import deque


def switch(puzzle, pos, target_pos):
    temp = list(puzzle)
    temp[pos], temp[target_pos] = temp[target_pos], temp[pos]
    return "".join(temp)


def bfs(puzzle):
    pos = puzzle.index('0')
    q = deque()
    visited = {}
    visited[puzzle] = 0
    q.append([pos, puzzle, 0])
    while q:
        pos, p, cnt = q.popleft()
        if p == "123456780":
            return cnt
        row = pos // 3
        col = pos % 3
        direction = [(0, 1), (0, -1), (1, 0), (-1, 0)]
        for i in range(4):
            move_row, move_col = direction[i]
            moved_row = move_row + row
            moved_col = move_col + col
            if 0 <= moved_row < 3 and 0 <= moved_col < 3:
                target_pos = moved_row * 3 + moved_col
                moved_puzzle = switch(p, pos, target_pos)
                if moved_puzzle not in visited:
                    visited[moved_puzzle] = cnt+1
                    q.append([target_pos, moved_puzzle, cnt+1])
    return -1


puzzle = ""
location = []
for i in range(3):
    puzzle += "".join(input().split())
print(bfs(puzzle))
