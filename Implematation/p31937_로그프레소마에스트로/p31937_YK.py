import sys
input = sys.stdin.readline

def check(idx, start):
    visited = [False] * (N + 1)
    visited[start] = True
    cnt = 1

    for i in range(idx, M):
        a, b, c = logs[i]
        if not visited[b]:
            continue
        if visited[c]:
            continue
        if not infected[c]:
            return False
        cnt += 1
        visited[c] = True

    if cnt != K:
        return False

    return True

def main():
    global N, M, K, infected, checked, logs

    N, M, K = map(int, input().split())
    infected = [False] * (N + 1)
    checked = [False] * (N + 1)
    logs = []

    list = input().split()
    for l in list:
        infected[int(l)] = True

    for _ in range(M):
        a, b, c = map(int, input().split())
        logs.append((a, b, c))

    if K == 1:
        print(list[0])
        return

    logs.sort()
    result = -1
    for i in range(M):
        a, b, c = logs[i]
        if checked[b] or not infected[b]:
            continue
        checked[b] = True
        if check(i, b):
            result = b
            break

    print(result)

if __name__ == "__main__":
    main()