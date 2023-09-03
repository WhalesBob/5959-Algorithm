import java.io.*;
import java.util.StringTokenizer;

public class BOJ11501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++){
            int n = Integer.parseInt(br.readLine());
            int[] array = makeArray(br, n);
            // 주식 현황을 받아옵니다.
            long profit = 0;
            // 이후 수익을 0으로 초기화해줍니다. 문제에서 64bit 정수라고 했으므로, long 으로 초기화합니다.
          
            int currentMax = array[array.length - 1]; 
          // 뒤에서부터, 가장 최고점 값이 얼마인지 갱신해 줄 것입니다. 먼저, 맨 뒤 값으로 초기화해줍니다. 
          // 부분마다 최고점을 갱신하면서, 기존 최고점보다 값이 작으면 사는 지점이고 최고점이 갱신되면 그 지점에서
          // 팔아야 한다고 생각했습니다. 

            for(int i = array.length - 2; i >= 0; i--){ // 뒤에서부터 거꾸로 for 문을 돌아 줍니다.
                if(array[i] >= currentMax){
                    currentMax = array[i]; // 현 최고점보다 array[i] 가 더 크거나 같으면, 갱신합니다.
                }else{
                    profit += (currentMax - array[i]); // 현 최고점보다 array[i] 가 더 작으면, 
                  // 그때는 주식을 산 것입니다. 현 최고점에서 팔 것이므로, profit 에다 그 차이만큼 더해 줍니다.
                }
            }
            // 한번 for 문을 돌때마다, BufferedWriter 에 결과를 입력해 줍니다.
            bw.write(profit + "\n");
        }
        bw.flush();
    }
    static int[] makeArray(BufferedReader br, int size) throws IOException{
        int[] array = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < array.length; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }
        return array;
    }
}
