import sys

# N: 컴퓨터 수
# M: 로그 수
# K: 감염된 컴퓨터 수
N, M, K = map(int, sys.stdin.readline().split())
infected = list(map(int, sys.stdin.readline().split()))
if len(infected) == 1:
    print(infected[0])
    exit(0)

logs = []
for _ in range(M):
    logs.append(list(map(int, sys.stdin.readline().split())))

logs = sorted(logs, key=lambda log: log[0])
checked = [False] * (N+1)

for infected_vertex in infected:
    checked[infected_vertex] = True

for infected_vertex in infected:
    visited = [False] * (N+1)
    visited[infected_vertex] = True
    cnt = 1
    for _, src, dst in logs:
        if visited[src] and not checked[dst]:
            break
        
        if checked[src] and checked[dst] and visited[src] and not visited[dst]:
            visited[dst] = True;
            cnt += 1
            continue
            
    if cnt == K:
        print(infected_vertex)
        break
            