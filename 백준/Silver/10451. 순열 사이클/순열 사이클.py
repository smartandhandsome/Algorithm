def check_cycle(num, start_num):
    if num == start_num:
        visited[num] = True
        return
    visited[num] = True
    check_cycle(nums[num], start_num)


T = int(input())
for _ in range(T):
    N = int(input())
    nums = [0] + list(map(int, input().split(" ")))
    visited = [False] + [False] * N
    cnt = 0
    for i in range(1, N+1):
        if not visited[i]:
            check_cycle(nums[i], i)
            cnt += 1
    print(cnt)
