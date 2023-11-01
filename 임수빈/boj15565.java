import java.io.*;
import java.util.*;

public class boj15565 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        String[] dolls = new String[n];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            dolls[i] = st.nextToken();
        }

        int j = 0; // 앞 포인터
        int cnt = 0; // 라이언 인형의 개수
        int result = 0; // 집합의 크기
        int answer = Integer.MAX_VALUE;

        // 뒤 포인터 옮기기
        for (int i=0; i<n; i++) {
            while (j < n && cnt < k) {
                // 더할 값이 라이언인 경우
                if (dolls[j].equals("1")) {
                    cnt++;
                }
                // 앞 포인터 옮기기
                j++;
                // 집합의 크기 늘리기
                result++;
            }

            // 최솟값 갱신
            if (cnt == k) {
                answer = Math.min(answer, result);
            }

            // 뺄 값이 라이언인 경우
            if (dolls[i].equals("1")) {
                cnt--;
            }

            // 집합의 크기 줄이기
            result--;
        }

        if (answer == Integer.MAX_VALUE) { // 집합이 없는 경우
            answer = -1;
        }
        System.out.println(answer);
    }

}