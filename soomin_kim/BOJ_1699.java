import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1699 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 자연수 n입력
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[100001];

        // 1로 나타냈을 때 몇 항일까요?
        for(int i = 1; i<=100000; i++){
            dp[i] = i;
        }

        // 잘 모르겠으니까 걍 제곱수를 다 미리 구해둡시당!
        // 이 부분을 이용해서 적절한 제곱수를 구하려고 했으나 굳이 필요없는 부분이었습니다.
//        int[] sqrt =  new int[100001];
//        for(int i = 1; i<=(int)Math.sqrt(100000); i++){
//            sqrt[i*i] = 1;
//        }

        // 1로 나타냈을 때와 적절한 제곱수를 이용했을 때의 항의 개수를 반복문으로 찾아서 가장 작은 항의 갯수를 구해용가리
        for(int i = 1; i<=n; i++){
            for(int j = 1; j * j<= i; j++) {
                    dp[i] = Math.min(dp[i], dp[i-(j*j)] + 1);
            }

        }

        System.out.println(dp[n]);
    }
}
