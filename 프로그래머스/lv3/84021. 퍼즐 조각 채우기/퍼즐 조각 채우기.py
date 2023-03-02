import copy
from collections import deque


def find_out(a, b, matrix, isTable):
    direction = ((0, 1), (1, 0), (-1, 0), (0, -1))
    queue = deque([(a, b)])
    block = [(0, 0)]
    matrix[a][b] = (0 if isTable else 1)
    while queue:
        y, x = queue.popleft()
        for i in range(4):
            moveY, moveX = direction[i]
            movedY = y + moveY
            movedX = x + moveX
            if 0 <= movedY < len(matrix) and \
                    0 <= movedX < len(matrix[0]) and \
                    matrix[movedY][movedX] == (1 if isTable else 0):
                matrix[movedY][movedX] = (0 if isTable else 1)
                queue.append((movedY, movedX))
                block.append((movedY-a, movedX-b))
    return block


def reset_point(stuff):
    stuff.sort()
    a, b = stuff[0]
    for i in range(len(stuff)):
        ta, tb = stuff[i]
        stuff[i] = (stuff[i][0] - a, stuff[i][1] - b)
    return stuff


def spin(block):
    spined = []
    for b in block:
        y, x = b
        spined.append((x, -y))
    return reset_point(spined)


def same_or_not(blank, blocks):
    for block in blocks:
        temp = copy.deepcopy(block)
        for i in range(4):
            if temp == blank:
                blocks.remove(block)
                return len(blank)
            temp = spin(temp)
    return 0


def solution(game_board, table):
    answer = 0
    blocks = []
    for i in range(len(table)):
        for j in range(len(table[0])):
            if table[i][j] == 1:
                blocks.append(reset_point(find_out(i, j, table, True)))
    blanks = []
    for i in range(len(game_board)):
        for j in range(len(game_board[0])):
            if game_board[i][j] == 0:
                answer += same_or_not(reset_point(find_out(i,
                                      j, game_board, False)), blocks)
    return answer