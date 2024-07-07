package TwoPointer.p14572_스터디그룹;

import java.io.*;
import java.util.*;

public class p14572_YK {

    static int N, K, D;
    static Student[] students;
    static int[] algos;

    static class Student implements Comparable<Student> {
        int skill, algo;

        public Student(int algo, int skill) {
            this.algo = algo;
            this.skill = skill;
        }

        public Student(int skill) {
            this.algo = 0;
            this.skill = skill;
        }

        @Override
        public int compareTo(Student o) {
            return Integer.compare(this.skill, o.skill);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        students = new Student[N];
        algos = new int[K];

        for (int i = 0, M, d; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            int algo = 0;
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                algo |= 1 << (Integer.parseInt(st.nextToken()) - 1);
            }

            students[i] = new Student(algo, d);
        }

        Arrays.sort(students);
        int left = 0, result = 0;
        for (int right = 0; right < N; ++right) {
            while (right < N && students[right].skill - students[left].skill <= D) {
                addStudent(right);
                ++right;
            }

            --right;
            if (students[right].skill - students[left].skill <= D) {
                result = Math.max(result, calculate(left, right));
                removeStudent(left);
            }
            ++left;
        }

        System.out.println(result);
        br.close();
    }

    private static void addStudent(int idx) {
        for (int i = 0, mask = 1; i < K; ++i) {
            if ((students[idx].algo & mask) > 0) {
                ++algos[i];
            }
            mask <<= 1;
        }
    }

    private static void removeStudent(int idx) {
        for (int i = 0, mask = 1; i < K; ++i) {
            if ((students[idx].algo & mask) > 0) {
                --algos[i];
            }
            mask <<= 1;
        }
    }

    private static int calculate(int left, int right) {
        int cnt = right - left + 1;
        int allCnt = 0;
        int commonCnt = 0;

        System.out.println(Arrays.toString(algos));
        for (int i = 0; i < K; ++i) {
            if (algos[i] == cnt) ++commonCnt;
            if (algos[i] > 0) ++allCnt;
        }

        return (allCnt - commonCnt) * cnt;
    }
}
