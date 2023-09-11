import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long sum = 0;
        int max = -1; // 최대값을 설정해 주기 위해, max 를 초기화합니다.
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < n; i++){
            int value = Integer.parseInt(st.nextToken());
            sum += value; // 옷의 총 갯수를, 옷이 몇벌 있는지 받으며 더해서 만들어 줍니다.
            if(max < value){
                max = value; // 가장 많은 옷이 몇개 있는지 확인합니다.
            }
        }

        if(sum == 1){ // 옷이 단 하나라면, 단독사진이므로 "이웃 간에 같은 옷을 입고 있지" 않습니다.
            System.out.println("Happy"); // Happy 를 출력하고 끝냅니다.
            return;
        }

      // sum 에서 나누기 2를 한 것이 max 보다 크거나 같다면, max 개가 있는 옷 입은 사람을
      // 중간중간에만 배치하고 나머지는 다른 옷을 입으면 됩니다.
        System.out.println((sum / 2) >= max ? "Happy" : "Unhappy");
    }
}
