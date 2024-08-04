import java.util.*;
import java.io.*;

public class p1339_SA {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] arr = new int[26];
    static int N;

    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; ++i){
            String str = br.readLine();
            int len = str.length();
            for (int j=0; j<len; j++) {
                char c = str.charAt(j);
                arr[c -'A'] += (int) Math.pow(10, len-1-j);
            }
        }

        Arrays.sort(arr);

        int num = 9;
        int result = 0;

        for(int i=25; i>-1 && arr[i]!=0; --i){
            result += arr[i]*num;
            --num;
        }
        System.out.println(result);

    }

}
