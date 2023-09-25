import java.io.*;
import java.util.*;

public class BOJ12761 {
    static final int MAX_VALUE = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int aCong = Integer.parseInt(st.nextToken());
        int bCong = Integer.parseInt(st.nextToken());

        int current = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new ArrayDeque<>();
        int[] jumpCount = new int[100_001]; // jumpCount 를 저장할 배열을 만듭니다.

        Arrays.fill(jumpCount, MAX_VALUE); // 초기 jumpCount 를 Integer 최대값으로 저장하고
        jumpCount[current] = 0; // 현재 점프카운트는 0으로 만들어 줍니다.
        queue.add(current); // 그리고 queue 에도 넣어 줍니다.
        int[] justGoArray = new int[]{aCong, -1 * aCong, bCong, -1 * bCong, 1, -1};
        int[] jumpArray = new int[]{aCong, bCong}; 
      // 노가다를 줄여주기 위해, 어디로 단순히 갈 수 있는지, 몇 배로 점프할 수 있는지
      // 배열을 만들어 줍니다.

        while(!queue.isEmpty() && jumpCount[dest] == MAX_VALUE){
            int position = queue.remove();

            for(int cong : jumpArray){
                if(canPowerJump(jumpCount, position, cong)){ 
                  // 배열 범위 안이면서도, 가고자 하는 곳이 현재 position 의 jumpCount + 1 보다 
                  // 크다면, 업데이트 대상입니다. jumpCount 를 더 작게 업데이트 해주고, 
                  // queue 에도 집어 넣어 줍니다.
                    jumpCount[position * cong] = jumpCount[position] + 1;
                    queue.add(position * cong);
                }
            }

            for(int cong : justGoArray){
                if(canJustGo(jumpCount, position, cong)){
                  // 위 조건 그대로 판별해주고, 판별 결과 업데이트 대상이면
                  // jumpCount 를 더 작게 업데이트 해주고, queue 에도 집어 넣어 줍니다.
                    jumpCount[position + cong] = jumpCount[position] + 1;
                    queue.add(position + cong);
                }
            }
        }

        System.out.println(jumpCount[dest]);
    }
  // x 배 점프해서 갈 수 있는지 여부를 돌려줍니다.
    static boolean canPowerJump(int[] count, int position, int toJump){
        return (position * toJump) < count.length
                && count[position * toJump] > count[position] + 1;
    }
  // 이동해 갈 수 있는지 여부를 돌려줍니다.
    static boolean canJustGo(int[] count, int position, int toGo){
        return 0 <= (position + toGo) && (position + toGo) < count.length
                && count[position + toGo] > count[position] + 1;
    }
}
