import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_10819 {
    static int max = 0;
    static int[] array;
    static boolean[] visited;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        array = new int[n];
        for(int i = 0; i<n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[n];
        int[] nums = new int[n];
        permutation(0, nums);

        System.out.println(max);
    }

    public static void permutation(int count, int[] nums){
        if(count == n) {
            //합 구하기
            max = Math.max(sum(nums), max);
            return;
        }

        for(int i = 0; i<n; i++){
            if(!visited[i]){
                visited[i] = true;
                nums[count] = array[i];
                permutation(count+1, nums);
                visited[i] = false;
            }
        }
    }

    public static int sum(int[] nums){
        int result = 0;

        for(int i = 0; i<nums.length-1; i++){
            result += Math.abs(nums[i] - nums[i+1]);
        }

        return result;
    }

}