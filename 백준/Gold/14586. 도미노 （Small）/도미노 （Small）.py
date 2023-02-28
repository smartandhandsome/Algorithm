def hitleft(i):
    x = domino[i][0] - domino[i][1]
    for j in range(i-1, -1, -1):
        if domino[j][0] < x:
            return j+1
        x = min(x, domino[j][0] - domino[j][1])
    return 0


def hitright(i):
    x = domino[i][0] + domino[i][1]
    for j in range(i+1, n):
        if domino[j][0] > x:
            return j-1
        x = max(x, domino[j][0] + domino[j][1])
    return n-1


n = int(input())
domino = sorted(tuple(map(int, input().split())) for i in range(n))
L = [hitleft(i) for i in range(n)]
R = [hitright(i) for i in range(n)]

dp = [float('inf')] * n
dp[0] = 1

for i in range(n):
    # 1이 의미하는건 i를 왼쪽으로 넘긴 1회 임 => L[i]까지 넘어갔음
    # 이후 L[i] - 1번째를 또 처리 해야됨
    # 그 L[i] - 1를 처리 한거는 이미 dp로 처리하면서 이전에 배열(dp)에 담아둠 (dp[L[i]-1])
    dp[i] = min(dp[i], 1 + (dp[L[i]-1] if L[i]-1 >= 0 else 0))
    # 오른쪽으로 넘기는 거 처리
    for j in range(i):  # 0번째부터 i-1번째까지 오른쪽으로 쓰러트려봄
        # j번째꺼 넘겼는데 i보다 많이 넘어가거나 같으면 발탁
        if R[j] >= i:
            # 1은 j를 넘긴거(R[j]) j의 오른쪽은 다 넘어갔으니까 j이전꺼 더 해줘야 함(dp[j-1])
            dp[i] = min(dp[i], 1 + (dp[j-1] if j-1 >= 0 else 0))
print(dp[n-1])
