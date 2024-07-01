package TwoPointer.p14572_스터디그룹;

import java.io.*;
import java.util.*;

public class p14572_SL {

    static class Info implements Comparable<Info> {
        int good;
        List<Integer> algorithms;

        public Info(int good, List<Integer> algorithms) {
            this.good = good;
            this.algorithms = algorithms;
        }

        @Override
        public int compareTo(Info o) {
            return this.good - o.good;
        }
    }

    static int N, K, D;
    static List<Info> students;
    static int[] algo; // 알고리즘을 알고 있는 학생수 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 학생 수
        K = Integer.parseInt(st.nextToken()); // 알고리즘 수
        D = Integer.parseInt(st.nextToken()); // 그룹 내 가장 잘 하는 학생 - 가장 못 하는 학생

        students = new ArrayList<>();
        algo = new int[K + 1];

        // 그룹 내 가장 잘 하는 학생 - 가장 못 하는 학생 <= D
        // 그룹의 효율성 E = (알고리즘 합집합 수 - 알고리즘 교집합 수) * 그룹원 수
        // 총 알고리즘 수 K개
        // 효율성이 최대가 되는 그룹 => 스터디그룹 ! ==> 효율성 구하기

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int algoCount = Integer.parseInt(st.nextToken());
            int good = Integer.parseInt(st.nextToken());

            List<Integer> algorithms = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < algoCount; j++) {
                algorithms.add(Integer.parseInt(st.nextToken()));
            }
            students.add(new Info(good, algorithms));
        }

        // 실력 기준으로 정렬
        Collections.sort(students);

        // 투포인터
        int sumKnowAlgo = 0; // 스터디원이 알고있는 모든 알고리즘
        int subKnowAlgo = 0; // 모든 스터디원이 알고 있는 알고리즘
        int i = 0, j = 0; // i: 오른쪽, j: 왼쪽
        int answer = -1;

        while (true) {

            // 실력차이가 d 초과면 j(왼쪽) 증가 시키기 => 사람 빼기
            if (students.get(i).good - students.get(j).good > D) {
                for (int algoNum : students.get(j).algorithms) {
                    algo[algoNum]--;
                    if (algo[algoNum] == 0) {
                        sumKnowAlgo--;
                    }
                }
                j++;
            }

            // 실력 차이가 d 이하면 i번째(오른쪽) 학생 추가하고 효율성 계산 => 사람 추가
            if (students.get(i).good - students.get(j).good <= D) {
                subKnowAlgo = 0; // 초기화

                for (int algoNum : students.get(i).algorithms) {
                    algo[algoNum]++;
                    if (algo[algoNum] == i - j + 1) { // 모든 스터디원이 알고 있는 알고리즘
                        subKnowAlgo++;
                    }
                    if (algo[algoNum] == 1) { // 모든 알고리즘
                        sumKnowAlgo++;
                    }
                }

                answer = Math.max(answer, (sumKnowAlgo - subKnowAlgo) * (i - j + 1));
                i++;
            }

            // i가 n이 되면 반복 종료
            if (i == N) {
                break;
            }
        }

        System.out.println(answer);
    }
}