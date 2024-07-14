package DivideAndConquer.p2447_별찍기10;

import java.io.*;

public class p2447_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        String THREE = "***", TWO = "* *", NONE = "   ";

        for (int i = 0; i < N; ++i) {
            for (int j = 0, size = N / 3; j < size; ++j) {
                boolean flag = true;
                for (int k = 9; k <= N; k *= 3) {
                    if ((i / (k / 3)) % 3 != 1) continue;
                    if ((j % (k / 3)) / (k / 9) == 1) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    sb.append(i % 3 == 1 ? TWO : THREE);
                } else {
                    sb.append(NONE);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
