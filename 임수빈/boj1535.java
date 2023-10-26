import java.io.*;
import java.util.*;

public class boj1535 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] L = new int[n+1]; // 잃는 체력
        int[] J = new int[n+1]; // 얻는 기쁨

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1; i<=n; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=n; i++) {
            J[i] = Integer.parseInt(st.nextToken());
        }

        int[][] result = new int[n+1][100];
        for (int i=1; i<=n; i++) {
            for (int j=1; j<100; j++) {
                // 인사를 할 수 없는 경우
                if (L[i] > j) {
                    result[i][j] = result[i-1][j];
                    continue;
                }
                // 인사를 할 수 있는 경우
                result[i][j] = Math.max(result[i-1][j], J[i] + result[i-1][j-L[i]]);
            }
        }

        System.out.println(result[n][99]);
    }

}
