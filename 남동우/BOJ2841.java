import java.io.*;
import java.util.*;

public class BOJ2841 {
    static int count;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int n = Integer.parseInt(st.nextToken());
        count = 0;

        Map<Integer, Stack<Integer>> soundMap = new HashMap<>();
      // 입력받는 소리를 해시맵을 통해 매핑합니다.

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int prat = Integer.parseInt(st.nextToken());
            int sound = Integer.parseInt(st.nextToken());
          // 프랫과 소리를 입력받습니다. 
          // 이후, map 에 prat 이 없다면, 스택을 새로 넣어 줍니다.

            if(!soundMap.containsKey(prat)) {
                soundMap.put(prat, new Stack<>());
            }

          // prat 을 가지고, soundMap 에서 stack 을 받아옵니다.
          // 이후, 해당 스택의 맨 위가 sound 보다 작아지거나, 스택이 빌 때까지 stack 에서 위의 요소를 빼 줍니다.
          // 물론, stack에서 맨 위를 뺄 때도, "손가락을 떼는" 행위이므로, 함수 내에서 count 를 증가시켜 줍니다.
            Stack<Integer> stack = soundMap.get(prat);
            modifyMap(stack, sound);

          // 혹시 stack 이 비어 있지 않았으면서도, 스택의 맨 위가 sound 와 같으면 
          // 새로 "손가락을 눌러 줄" 필요가 없습니다.
            if(!stack.isEmpty() && stack.peek() == sound){
                continue;
            }

          // 위의 if 문에 걸리지 않았다면, "손가락을 눌러 주어야" 합니다.
          // 입력받은 prat 의 stack에 sound 를 추가해 줍니다.
            count++;
            stack.push(sound);
        }

        System.out.println(count);
    }
  // 스택이 비어 있지 않았으면서도, 스택의 맨 위가 sound 보다 크다면, 
  // "손가락을 움직여 주어야" 합니다. count 를 1 증가시키고, stack에서 맨 위 요소를 빼 줍니다.
    static void modifyMap(Stack<Integer> stack, int sound){
        while(!stack.isEmpty() && stack.peek() > sound){
            count++;
            stack.pop();
        }
    }
}
