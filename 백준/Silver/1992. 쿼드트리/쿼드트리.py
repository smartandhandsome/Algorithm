N = int(input())
matrix = []
for _ in range(N):
    matrix.append(list(map(int, input())))
s = ""


def check(a, b, n):
    global s

    c = matrix[a][b]
    for i in range(a, a + n):
        for j in range(b, b + n):
            if matrix[i][j] != c:
                n2 = n // 2
                s += '('
                check(a, b, n2)
                check(a, b + n2, n2)
                check(a + n2, b, n2)
                check(a + n2, b + n2, n2)
                s += ')'
                return
    s += str(c)


check(0, 0, N)
print(s)
