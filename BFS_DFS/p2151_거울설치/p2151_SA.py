import sys
from collections import deque



def go():
    visited = [[[-1] * 4 for _ in range(N)] for _ in range(N)]
    for r, c, d in q:
        visited[r][c][d] = 0
    
    while q:
        r, c, d = q.popleft()
        nr = r + dr[d]
        nc = c + dc[d]
        
        if nr < 0 or nc < 0 or nr > N-1 or nc > N-1 or map[nr][nc]=="*":
            continue
            
        # 빈공간
        if map[nr][nc] == ".":
            if visited[nr][nc][d] == -1 or visited[nr][nc][d] > visited[r][c][d]:
                visited[nr][nc][d] = visited[r][c][d]
                q.append([nr, nc, d])
            continue
             
        # 거울 설치   
        if map[nr][nc] == "!":
            if visited[nr][nc][d] == -1 or visited[nr][nc][d] > visited[r][c][d]:
                visited[nr][nc][d] = visited[r][c][d]
                q.append([nr, nc, d])
            
            for ni in new_dir[d]:
                if visited[nr][nc][ni] == -1 or visited[nr][nc][ni] > visited[r][c][d]+1:
                    visited[nr][nc][ni] = visited[r][c][d] + 1
                    q.append([nr, nc, ni])
    
    min = 9999999999
    er = doors[1][0]
    ec = doors[1][1]
    
    for v in visited[er][ec]:
        if v == -1:
            continue
        if min > v:
            min = v
    print(min)



dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]
new_dir = [[2, 3], [2, 3], [0, 1], [0, 1]]


N = int(sys.stdin.readline())
map = []
doors = []
for i in range(N):
    map.append(list(sys.stdin.readline().strip()))
    for j in range(N):
        if map[i][j] == "#":
            doors.append([i,j])
            map[i][j] = "."
                        

q = deque()
for i in range(4):
    nr = doors[0][0] + dr[i]
    nc = doors[0][1] + dc[i]
    
    if nr < 0 or nc < 0 or nr > N-1 or nc > N-1 or map[nr][nc]=="*":
        continue
    
    q.append([doors[0][0], doors[0][1], i])
    
go()
