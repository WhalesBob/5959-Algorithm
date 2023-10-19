import java.util.*;

public class BOJ1802 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
      
      // 중간을 기점으로 반대 값 대칭 되어야 한다
        for (int t = 1; t <= T; t++) {
            char[] pp = sc.next().toCharArray();

            if(check(pp,0,pp.length-1))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }

    static boolean check(char[] pp, int start, int end) {
       // 더 쪼갤 수 없는 상태면 true
        if (start == end)
            return true;

        int n = (start + end) / 2;

        // 좌우가 같으면 false
        for (int i = start; i < n; i++)
            if (pp[i] == pp[end - i])
                return false;

        // 좌우가 다르면 쪼개서 다시 검사한다
        return check(pp, start, n-1) && check(pp,n+1, end);
    }
}
