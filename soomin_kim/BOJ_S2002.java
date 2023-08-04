import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_S2002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        Map<String, Integer> inTunnel = new HashMap<>(); // 대근이!!
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            inTunnel.put(st.nextToken(), i + 1); // 차량번호, 순서
        }

        //영식이!!
        int[] outTunnel = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            // 들어올때 순서를 가져와서 배열에 저장
            // => 들어올때 5번째 였지만 나올때 첫뻔째였다면 outTunnel[1] = 5
            outTunnel[i] = inTunnel.get(st.nextToken());
        }

        int count = 0;
        for (int i = 0; i < n-1; i++) {
            for(int j = i+1; j<n; j++) {
                if(outTunnel[i] > outTunnel[j]) { // 더 큰 순서가 앞에 있다면 추월했으므로 count++
                    count++;
                    break;
                }
            }
        }
        System.out.println(count);
    }
}
