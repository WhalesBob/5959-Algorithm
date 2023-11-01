import java.io.*;
import java.util.*;

public class BOJ2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 나무 수
        long M = Integer.parseInt(st.nextToken()); // 나무 길이

        long[] trees = new long[N];
        st = new StringTokenizer(br.readLine(), " ");

        long max = 0;

        for(int i=0; i<N; i++) {
            trees[i] = Integer.parseInt(st.nextToken()); // 나무 길이
            if(max < trees[i])
                max = trees[i];
        }

        // 나무 높이의 최댓값 찾는다
        long result = search(trees, 0,max,M);

        System.out.println(result);

    }

    static long search(long[] trees, long start, long end, long M) {
        long result = M;

        while(start <= end) {
            long n = (start+end)/2;

            if(check(trees, n,M)) {
                result = n;
                start = n + 1;
            }
            else {
                end = n - 1;
            }
        }

        return result;
    }

    // n 높이로 나무를 자를 경우 M미터를 만들 수 있는지
    static boolean check(long[] trees, long n, long M) {
        long sum = 0;
        for(long tree : trees)
            if(tree >= n)
                sum += tree - n;

        if(sum >= M) return true;
        else return false;
    }
}
