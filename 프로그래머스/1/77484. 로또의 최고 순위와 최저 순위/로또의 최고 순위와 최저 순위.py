def solution(lottos, win_nums):
    answer = [0, 0];
    nums = set()
    
    zero = 0
    for lotto in lottos:
        if lotto == 0:
            zero+=1
        nums.add(lotto)
    
    cnt = 0;
    for win_num in win_nums:
        if win_num in nums:
            cnt+=1
    
    if cnt == 6:
        answer[1] = 1;
    elif cnt == 5:
        answer[1] = 2;
    elif cnt == 4:
        answer[1] = 3;
    elif cnt == 3:
        answer[1] = 4;
    elif cnt == 2:
        answer[1] = 5;
    else:
        answer[1] = 6;
    
    if cnt + zero == 6:
        answer[0] = 1;
    elif cnt + zero == 5:
        answer[0] = 2;
    elif cnt + zero == 4:
        answer[0] = 3;
    elif cnt + zero == 3:
        answer[0] = 4;
    elif cnt + zero == 2:
        answer[0] = 5;
    else:
        answer[0] = 6;
    
    return answer