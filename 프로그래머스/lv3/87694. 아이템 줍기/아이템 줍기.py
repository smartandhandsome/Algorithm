from collections import deque
import copy

def solution(rectangle, characterX, characterY, itemX, itemY):
    # 초기 세팅
    max_x = float('-inf')
    max_y = float('-inf')
    for rec in rectangle:
        small_x, small_y, big_x, big_y = rec
        max_x = max(max_x, big_x)
        max_y = max(max_y, big_y)
    my_map = [[0] * (max_x*2+2) for _ in range(max_y*2+2)]
    
    # 사각형 그리기
    for rec in rectangle:
        small_x, small_y, big_x, big_y = rec
        for y in range(small_y*2, big_y*2+1):
            for x in range(small_x*2, big_x*2+1):
                my_map[y][x] = 1
    
    search_edge(max_y*2+1, max_x*2+1, my_map)
    
    answer = bfs(characterX*2, characterY*2, itemX*2, itemY*2, my_map)
    for my in my_map:
        for m in my:
            print(m, end=" ")
        print()
    return answer//2

def search_edge(MAX_Y, MAX_X, my_map):
    visited = [[False] * (MAX_X+1) for _ in range(MAX_Y+1)]
    queue = deque()
    directions = [(1, 0), (0, 1), (-1, 0), (0, -1), (-1, -1), (-1, 1), (1, -1), (1, 1)]
    queue.append([MAX_Y, MAX_X])
    while queue:
        y, x = queue.popleft()
        for i in range(8):
            move_y, move_x = directions[i]
            moved_y = y + move_y
            moved_x = x + move_x
            if moved_y < 0 or moved_y > MAX_Y or moved_x < 0 or moved_x > MAX_X or visited[moved_y][moved_x]:
                continue    
            
            visited[moved_y][moved_x] = True
            if my_map[moved_y][moved_x] == 1:
                my_map[moved_y][moved_x] += 1
                continue
            queue.append([moved_y, moved_x])
def bfs(chX, chY, targetX, targetY, my_map):
    MAX_Y = len(my_map)
    MAX_X = len(my_map[0])
    print(MAX_Y, MAX_X)
    queue = deque()
    movement = [(chY, chX)]
    my_map[chY][chX] += 1
    queue.append([chY, chX, 0, movement])
    directions = [(1, 0), (-1, 0), (0, 1), (0, -1)]
    while queue:
        y, x, cnt, mvmt = queue.popleft()
        if y == targetY and x == targetX:
            print(mvmt)
            return cnt
        for i in range(4):
            moveY, moveX = directions[i]
            movedY = y + moveY
            movedX = x + moveX
            if 0 <= movedY < MAX_Y and 0 <= movedX < MAX_X and my_map[movedY][movedX] == 2:
                temp = copy.deepcopy(mvmt)
                temp.append((movedY, movedX))
                my_map[movedY][movedX] += 1     
                queue.append([movedY, movedX, cnt+1, temp])
    return -1