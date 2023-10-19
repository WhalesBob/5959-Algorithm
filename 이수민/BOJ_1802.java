import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1802 {

    static char[] paper;
    private static boolean check(int start, int end){
        if (start == end) return true; // 길이가 1인 경우

        int mid = (start+end)/2;

        // mid를 기준으로 양쪽의 범위를 늘려가면서 확인
        for(int i=start; i<mid; i++){
            // 현재 보는 위치들의 값이 같다면, 접을 수 없음
            if (paper[i] == paper[end-i]) return false;
        }

        // mid를 기준으로 나눈 종이의 접은 흔적 살핌
        return check(start, mid-1) && check(mid+1, end);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            paper = br.readLine().toCharArray();

            if (check(0, paper.length-1)) // 접을 수 있음
                sb.append("YES").append("\n");
            else
                sb.append("NO").append("\n");
        }

        System.out.println(sb);
    }
}
