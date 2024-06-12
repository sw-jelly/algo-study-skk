package BackTracking.p18428_감시피하기;

import java.util.*;
import java.io.*;

public class p18428_SA {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;

    static char[][] map;

    static final int[] dr = new int[]{0,0,1,-1};
    static final int[] dc = new int[]{1,-1,0,0};
    static List<Point> teachers = new ArrayList<>();


    static class Point{
        int r;
        int c;

        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    static void solve(){
        go(0);
        System.out.println("NO");
    }

    static boolean find(){

        for(Point teacher : teachers){

            for(int i=0; i<4; ++i){
                int nr = teacher.r;
                int nc = teacher.c;
                while(true){
                    nr += dr[i];
                    nc += dc[i];

                    if(nr < 0 || nc < 0 || nr > N-1 || nc > N-1 || map[nr][nc]=='O'){
                        break;
                    }

                    if(map[nr][nc]=='S'){
                        return false;
                    }
                }
            }


        }

        return true;
    }

    static void go(int wallCnt){
        if(wallCnt == 3){
            boolean result = find();
            if(result){
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }

        for(int i=0; i<N; ++i){
            for(int j=0; j<N; ++j){
                if(map[i][j] == 'X'){
                    map[i][j] = 'O';
                    go(wallCnt+1);
                    map[i][j] = 'X';
                }
            }

        }
    }


    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for(int i=0; i<N; ++i){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; ++j){
                map[i][j] = st.nextToken().charAt(0);
                if(map[i][j]=='T'){
                    teachers.add(new Point(i,j));
                }
            }
        }
    }
}