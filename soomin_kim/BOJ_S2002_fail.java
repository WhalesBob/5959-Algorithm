import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_S2002_fail {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        Map<String, Integer> inTurnel = new HashMap<>(); // 대근이!!
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            inTurnel.put(st.nextToken(), i+1 ); // 차량번호, 순서
        }
        
        int count = 0; //추월 차 count 변수
        
        // Queue에 정상적인 순서를 저장한다.
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i<n; i++){
            queue.add(i+1);
        }
        
        int index = 1;
        for(int i = 0; i<n; i++){
            // 영식이!!
            st = new StringTokenizer(br.readLine());
            String carNumbers = st.nextToken();

            // Queue의 첫번째 원소랑 터널을 나온 차량의 들어올 때 순서와 비교한다. => 올바른 순서
            if(inTurnel.get(carNumbers) != queue.peek()){
                count++;
                queue.remove(inTurnel.get(carNumbers)); // 비교한 차량의 들어온 순서를 삭제
            }
            else { // 정상적인 순서라면 count하지 않고 pop한다.
                queue.poll();
            }

        }
        System.out.println(count);
    }

}
