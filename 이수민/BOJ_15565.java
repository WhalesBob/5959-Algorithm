import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15565 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] dolls = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            dolls[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int cnt = 0; // 현재 범위(right-left)에서 라이언 인형의 개수
        int minSize = Integer.MAX_VALUE; // 가장 작은 연속된 인형들의 집합의 크기

        while (left < n) {
            // 현재 범위로 연속된 인형들의 집합을 만들 수 있는 경우,
            System.out.println(left+" "+right+" ");
            if (cnt >= k) {
                minSize = Math.min(minSize, right - left); // 집합의 크기 확인
                // 인형의 범위를 더 줄여본다. (left++)
                // 현재 위치가 라이언 인형이 있던 위치라면 cnt도 줄인다.
                if (dolls[left++] == 1) cnt--;
            } else if (right == n) { // 오른쪽으로 더 갈 수 없으면, 왼쪽을 조정한다.
                if (dolls[left++] == 1) cnt--;
            } else { // 아직 인형의 개수가 모자르다면, 오른쪽 범위를 넓힌다.
                if (dolls[right++] == 1) cnt++;
            }
        }

        System.out.println(minSize == Integer.MAX_VALUE ? -1 : minSize);
    }
}
