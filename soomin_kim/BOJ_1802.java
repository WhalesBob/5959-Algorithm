import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1802 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine()); // testcase

        for(int tc = 1; tc<=t; tc++) {

            String paper = br.readLine(); // 종이가 적힌 방향 정보

            if(check(paper, 0, paper.length()-1)) { // 분할정복으로 판단하기
                sb.append("YES").append("\n");
            }
            else {
                sb.append("NO").append("\n");
            }
        }
        System.out.println(sb);
    }

    public static boolean check(String paper, int s, int e){ // 될 수 있으면 true

        if(s == e) return true; // 종이 길이가 1이면 재귀 종료

        int mid = (s + e) / 2; // 중간점을 찾아야한다릐

        for(int i = s; i<mid; i++){
            if(paper.charAt(i) == paper.charAt(e-i)) return false; // 방향이 같으면 false
        }

        return check(paper, s, mid-1) && check(paper, mid+1, e); // 분할 정복이므로 반반씩 재귀를 타고 확인
    }
}
