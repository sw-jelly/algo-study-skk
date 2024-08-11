package Math.p11690_LCM12n;

import java.util.*;
import java.io.*;

public class p11690_SL {

    static final long MOD = 1L << 32;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 에라토스테네스의 체 이용
        // n이하의 모든 소수 찾아서, 최소공배수 계산
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false; // 소수 아님

        for (int i=2; i*i<=n; i++) {
            if (isPrime[i]) {
                for (int j=i*i; j<=n; j+=i) {
                    isPrime[j] = false;
                }
            }
        }

        long lcm = 1;
        for (int i=2; i<=n; i++) {
            if (isPrime[i]) {
                long tmp = i;
                while (tmp * i <= n) {
                    tmp *= i;
                }
                lcm = (lcm * tmp) % MOD;
            }
        }

        System.out.println(lcm);
    }
}
