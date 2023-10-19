import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] map = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++)
            map[i] = Integer.parseInt(st.nextToken());

        int maxLength = 0;
        int count = 0;
        int index = 0; // 제거를 위한 인덱스
        Map<Integer, Integer> num = new HashMap<>(); // 숫자 카운트 맵

        for(int i=0; i<N; i++) {
            // 숫자를 카운트 해서 K 초과되면,
            // 지금까지의 카운트를 판단해 저장하고
            // 해당 문자까지 제거 인덱스를 당겨준다.
            if(num.containsKey(map[i]) && num.get(map[i])+1 > K) {
                if(maxLength < count) maxLength = count;

                while(map[index]!=map[i]) {
                    num.put(map[index], num.get(map[index])-1);
                    --count;
                    ++index;
                }
                ++index;
            }
            // 다시 카운트 한다.
            else {
                num.put(map[i], num.getOrDefault(map[i], 0)+1);
                ++count;
            }
        }

        if(maxLength < count) maxLength = count;

        System.out.println(maxLength);
    }
}
