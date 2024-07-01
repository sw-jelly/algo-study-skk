package Implematation.p1713_후보추천하기;

import java.util.*;
import java.io.*;

public class p1713_SL {

    static class Info implements Comparable<Info> {
        int studentNum, recommend, time;

        public Info(int studentNum, int recommend, int time) {
            this.studentNum = studentNum;
            this.recommend = recommend;
            this.time = time;
        }

        @Override
        public int compareTo(Info o) { // 추천수가 같으면 시간 오름차순
            if(this.recommend == o.recommend) {
                return this.time - o.time;
            }
            return this.recommend - o.recommend; // 추천수 오름차순
        }
    }

    static int N, M;
    static ArrayList<Info> list = new ArrayList<>(); // 후보 추천 리스트 크기 N 확인
    static int[] students = new int[101]; // 추천받은만큼 증가시킬 배열

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            int studentNum = Integer.parseInt(st.nextToken());
            if(students[studentNum] == 0) { // 처음 추천
                // 처음 추천인데, 리스트가 꽉 차지 않음
                if(list.size() < N) {
                    list.add(new Info(studentNum, 1, i));
                    students[studentNum]++;
                } else { // 처음 추천인데, 리스트가 꽉 차서, 가장 적은 추천수 학생 뺴야할 경우
                    Collections.sort(list);
                    students[list.get(0).studentNum] = 0; // 추천받은 수 초기화
                    list.remove(0); // 가장 추천수가 적은 학생 제거
                    list.add(new Info(studentNum, 1, i)); // 새로운 학생 추가
                    students[studentNum]++;
                }
            } else { // 추천받은 적 있음
                for (Info info : list) {
                    if (info.studentNum == studentNum) {
                        info.recommend++;
                        students[studentNum]++;
                        break;
                    }
                }
            }
        }

        for(int i=0; i<101; i++) {
            if(students[i] != 0) {
                System.out.print(i + " ");
            }
        }

    }

}
