package BinarySearch.p16434_드래곤앤던전;

import java.util.*;
import java.io.*;

public class p16434_SL {

    static int N, atk;
    static int[][] rooms;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        atk = Integer.parseInt(st.nextToken());
        rooms = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            rooms[i][0] = Integer.parseInt(st.nextToken());
            rooms[i][1] = Integer.parseInt(st.nextToken());
            rooms[i][2] = Integer.parseInt(st.nextToken());
        }

        long left = 1;
        long right = Long.MAX_VALUE;
        long answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (canClear(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    // hp의 체력을 갖고 성공할 수 있는가
    private static boolean canClear(long hp) {
        long curHp = hp;
        long curAtk = atk;

        for (int t=0; t<N; t++) {
            int what1 = rooms[t][0];
            int what2 = rooms[t][1];
            int what3 = rooms[t][2];

            if (what1 == 1) { // 몬스터 공격
                long monAtk = what2;
                long monHp = what3;
                long cnt = (monHp+curAtk-1) / curAtk; // 공격을 몇 번 해야 죽일 수 있는지 계산
                curHp -= monAtk * (cnt-1); // 그 동안 받는 데미지 계산

                if (curHp <= 0) return false; // 체력이 0 이하가 되면 실패

            } else if (what1 == 2) { // 회복
                curAtk += what2;
                curHp = Math.min(hp, curHp + what3); // 체력을 최대 체력으로 회복
            }
        }

        return curHp > 0; // 던전 클리어 여부 반환
    }
}

/*
    HMaxHP : 용사의 최대 생명력입니다. 이 값은 1이상이어야 하며 던전에 들어간 이후로 변하지 않습니다.
    HCurHP : 현재 용사의 생명력입니다. 이 값은 HMaxHP보다 커질 수 없습니다.
    HATK : 용사의 공격력

    던전 총 N개의 방.
    i번째 방 통해서만 i+1번째 방으로 이동 가능.
    N번째 방에는 공주 & 용 !!

    N HATK
    1인 경우 몬스터 공격력, 몬스터 생명력
    2인 경우 공격력 증가, 생명력 회목

    MaxHP = x
    CurHP = x
    HATK = 3

    mon = 20 =>=> -1
    CurHP = x - 1*7

    // 2
    HATK => 6, CurHP = x

    // 3 마지막
    mon = 100 =>=> 17번 때리기
    CurHP = x - 16 * 3;

*/
