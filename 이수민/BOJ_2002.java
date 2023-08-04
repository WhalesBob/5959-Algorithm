import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<String> beforeTunnel = new LinkedList<>(); // 터널 지나기 전 차량 목록
        Queue<String> afterTunnel = new LinkedList<>(); // 터널 지난 후 차량 목록

        // 터널 전 후 차량 순서 입력 받음
        for(int i=0; i<n; i++){
            beforeTunnel.add(br.readLine());
        }
        for(int i=0; i<n; i++){
            afterTunnel.add(br.readLine());
        }

        int cnt = 0; // 추월 차량 카운트

        // 차량들을 앞에서부터 검사하고 검사가 끝난 차량은 삭제한다.
        while(!beforeTunnel.isEmpty()){ // 추월 경우를 검색할 차들이 남아 있는 경우,
            String cur = afterTunnel.poll(); // '터널 지난 후의 차량'을 순서대로 뽑는다.

            if(!cur.equals(beforeTunnel.peek())){ // 현재 차량이 추월 차량인 경우
                cnt++; // 추월 차량을 카운트 한다.
                beforeTunnel.remove(cur); // 터널 지나기 전 차량 목록에서도 현재 차량을 삭제한다.
            }
            else { // 현재 차량이 추월 차량이 아닌 경우
                beforeTunnel.poll(); // 현재 차량을 삭제한다.
            }
        }

        System.out.println(cnt);
    }
}
