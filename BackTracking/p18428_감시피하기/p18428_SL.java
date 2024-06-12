package BackTracking.p18428_감시피하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p18428_SL {

    static class Info {
        int x, y;
        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Info> studentList;
    static ArrayList<Info> teacherList;
    static HashMap<Info, Integer> hm;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 장애물 3개 설치해서 모든 학생들을 감시로부터 피하게 할 수 있는가? -> YES or NO
        N = Integer.parseInt(st.nextToken());

        studentList = new ArrayList<>();
        teacherList = new ArrayList<>();
        hm = new HashMap<>();

        map = new char[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if(map[i][j] == 'S') {
                    studentList.add(new Info(i, j));
                } else if(map[i][j] == 'T') {
                    teacherList.add(new Info(i, j));
                }
            }
        }

        dfs(0);

        System.out.println("NO");

    }

    private static void dfs(int cnt) {
        if(cnt == 3) {
            check();
            return;
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] == 'X') {
                    map[i][j] = 'O';
                    dfs(cnt+1);
                    map[i][j] = 'X';
                }
            }
        }
    }

    public static void check() {
        Queue<Info> q = new LinkedList<>();
        char[][] copyMap = new char[N][N];
        boolean[][] visited = new boolean[N][N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        for(Info teacher : teacherList) {
            q.add(teacher);
            visited[teacher.x][teacher.y] = true;
        }

        while(!q.isEmpty()) {
            Info cur = q.poll();
            int x = cur.x;
            int y = cur.y;

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                while(nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if(copyMap[nx][ny] != 'O') {
                        visited[nx][ny] = true;
                        nx += dx[i];
                        ny += dy[i];
                    } else {
                        break;
                    }
                }
            }
        }

        if(isPossible(visited)) {
            System.out.println("YES");
            System.exit(0);
        }
    }

    private static boolean isPossible(boolean[][] visited) {
        for(Info student : studentList) {
            if(visited[student.x][student.y]) {
                return false;
            }
        }
        return true;
    }

}
