def solution(keymap, targets):
    answer = []
    for target in targets:
        cnt = 0
        for ch in target:
            c = counting(keymap, ch)
            if c == -1:
                break;
            else:
                cnt += c
        if c == -1:
            answer.append(-1)
        else:
            answer.append(cnt)
    return answer

def counting(keymap, ch):
    m = -1
    for key in keymap:
        cnt = key.find(ch) + 1
        if cnt != 0:
            if m == -1:
                m = cnt
            else:
                m = min(cnt, m)
    return m