package DataStructure.p19583_싸이버개강총회;

import java.io.*;
import java.util.*;

public class p19583_YK {

    static Time[] times;

    static class Time implements Comparable<Time> {
        int h, m;

        public Time(int h, int m) {
            this.h = h;
            this.m = m;
        }

        @Override
        public int compareTo(Time o) {
            if (this.h == o.h) return Integer.compare(this.m, o.m);
            return Integer.compare(this.h, o.h);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        times = new Time[3];

        for (int i = 0; i < 3; ++i) {
            times[i] = convertToTime(st.nextToken());
        }

        Set<String> entered = new HashSet<>();
        int result = 0;
        try {
            while (true) {
                st = new StringTokenizer(br.readLine());
                Time t = convertToTime(st.nextToken());
                String name = st.nextToken();

                if (t.compareTo(times[0]) <= 0) {
                    entered.add(name);
                } else if (t.compareTo(times[1]) >= 0 && t.compareTo(times[2]) <= 0) {
                    if (entered.contains(name)) {
                        entered.remove(name);
                        ++result;
                    }
                }

                if (t.compareTo(times[2]) > 0) {
                    System.out.println(result);
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println(result);
        }
    }

    private static Time convertToTime(String st) {
        int h = Integer.parseInt(st.substring(0, 2));
        int m = Integer.parseInt(st.substring(3, 5));

        return new Time(h, m);
    }
}
