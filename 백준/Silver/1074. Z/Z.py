N, r, c = map(int, input().split())  # n이 클수록 4, 16, 64


def check(n, r, c):
    if n == 1:
        if r == 0 and c == 1:
            return 1
        elif r == 0 and c == 0:
            return 0
        elif r == 1 and c == 0:
            return 2
        elif r == 1 and c == 1:
            return 3
    x = 2 ** n // 2
    if r < x <= c:  # 1사분면
        return 4 ** (n - 1) + check(n - 1, r, c - x)
    elif r < x and c < x:  # 2사분면:
        return check(n - 1, r, c)
    elif r >= x > c:  # 3사분면:
        return 4 ** (n - 1) * 2 + check(n - 1, r - x, c)
    elif r >= x and c >= x:  # 4사분면:
        return 4 ** (n - 1) * 3 + check(n - 1, r - x, c - x)


print(check(N, r, c))