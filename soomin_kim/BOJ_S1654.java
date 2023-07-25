import org.omg.Messaging.SyncScopeHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_S1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nums = br.readLine().split(" ");
        int k = Integer.parseInt(nums[0]);
        int n = Integer.parseInt(nums[1]);

        //영식이가 가진 랜선 배열
        int[] lines = new int[k];
        for(int i = 0; i<k; i++){
            lines[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(lines); // 오름차순

        long max = lines[k-1];
        long min = 1; // 문제에서 랜선의 길이는 자연수라고 함
        long mid = 0; //int 범위를 넘을 수 있음

        while(min <= max){
            mid = (max+min) / 2; //반

            int count = 0;
            for(int i = 0; i<k; i++){
                count += lines[i]/mid;
            }

            if(count >= n){ // 필요한 개수보다 많다 => 길이를 늘려야함
                min = mid+1;
            }
            else if(count < n) {//필요한 개수보다 작다 => 길이를 줄여야함
                max = mid-1;
            }
        }
        System.out.println(max);
    }
}
