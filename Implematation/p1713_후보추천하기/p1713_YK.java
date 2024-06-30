package Implematation.p1713_후보추천하기;

import java.io.*;
import java.util.*;

public class p1713_YK {

    static class Student implements Comparable<Student> {
        int cnt, time, id;
        boolean isDeleted;

        public Student(int time, int id) {
            this.cnt = 1;
            this.id = id;
            this.time = time;
            isDeleted = false;
        }

        public Student(int time, int id, int cnt) {
            this.cnt = cnt;
            this.id = id;
            this.time = time;
            isDeleted = false;
        }

        @Override
        public int compareTo(Student o) {
            if (this.cnt == o.cnt) return Integer.compare(this.time, o.time);
            return Integer.compare(this.cnt, o.cnt);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        Queue<Student> pq = new PriorityQueue<>();
        Map<Integer, Student> frames = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int time = 0, id; time < M; ++time) {
            id = Integer.parseInt(st.nextToken());

            // 이미 게재된 학생
            if (frames.containsKey(id)) {
                Student s1 = frames.get(id);
                s1.isDeleted = true;

                Student s2 = new Student(s1.time, id, s1.cnt + 1);
                pq.add(s2);
                frames.remove(id);
                frames.put(id, s2);
                continue;
            }

            // 개제되지 않는 학생
            Student s = new Student(time, id);

            if (frames.size() >= N) { // 자리가 없다
                while (!pq.isEmpty()) {
                    Student tmp = pq.poll();
                    if (tmp.isDeleted) continue;
                    frames.remove(tmp.id);
                    break;
                }
            }

            frames.put(id, s);
            pq.add(s);
        }

        Integer[] students = frames.keySet().toArray(new Integer[0]);
        Arrays.sort(students);
        for (int s : students) {
            sb.append(s).append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}
