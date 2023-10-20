import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15565 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 입력
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int doll[] = new int[N]; 
        for (int i = 0; i < N; i++) {
            doll[i] = Integer.parseInt(st.nextToken());
        }
        
        int dollNum = N+1; // 라이언 K개 이상 존재하는 가장 작은 집합의 크기
        int lion = 0; // 현재 라이언 인형 개수
        int start = 0, end = 0; // 전후 포인터
        
        // 투 포인터 연산
        for (int i = 0; i < N; i++) {
        	// 뒤 포인터 연산
        	end=i;
        	// 라이언일 경우 개수 증가
            if(doll[i]==1) {
            	lion++;
            }
            // 만일 라이언이 현재 K개 이상일 경우 앞 포인터 연산
            if(lion>=K) {
            	for(int j=start; j<=end;j++) {
            		if(doll[j]==1) {
            			// 만일 라이언이 K개 초과하면 개수 감소
            			if(lion>K)
            				lion--;
            			// 딱 K개일 경우 start 갱신 후 break
            			else if(lion==K) {
            				start=j;
            				break;
            			}
            		}
            	}
            	// 정해진 전후 포인터 사용해서 최소 집합 크기 갱신
            	dollNum = dollNum<(end-start+1)?dollNum:(end-start+1);
            }
        }
        // 만일 갱신이 한번도 이루어지지 않을 경우 -1
        if(dollNum==N+1)
        	dollNum=-1;
        System.out.println(dollNum);
    }
}