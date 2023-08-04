import java.util.*;
import java.io.*;

public class BOJ2784 {
    static String [] result = new String[3];
    static List<String> wd = new ArrayList<>();
 
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<6; i++)
            wd.add(br.readLine());
        //Collections.sort(wd);
        
        select();

        if(result[0] == null)
            System.out.println(0);
        
        else
            for(String r : result)
                System.out.println(r);
    }

    static void select() { /* 3개의 행 고르는 함수 */
        for(int i=0; i<6; i++) {
            for(int j=0; j<6; j++) {
                if(i == j) continue;
                for(int k=0; k<6; k++) {
                    if(k == i || k == j) continue;
                    if(compare(i, j, k)) return; // 첫 번째 정답 발견 시 리턴
                }
            }
        }
    }

    static boolean compare(int n1, int n2, int n3) { /* 비교하는 함수 */
        boolean[] check = new boolean[6]; // 중복 체크 위한 배열
        
        String s1 = wd.get(n1);
        String s2 = wd.get(n2);
        String s3 = wd.get(n3);
        
        check[n1] = true;
        check[n2] = true;
        check[n3] = true;
        
        String[] w = {"","",""}; // 행에서 추출한 열
        
        for(int i=0; i<3; i++)
        	w[i] = String.valueOf(s1.charAt(i)) 
        			+ String.valueOf(s2.charAt(i))
        			+ String.valueOf(s3.charAt(i));
        
        int cnt = 0;
        for(int i=0; i<3; i++) {
        	for(int j=0; j<6; j++) {
        		if(check[j]) continue;
        		if(!w[i].equals(wd.get(j))) continue;
        		
        		check[j] = true;
        		cnt += 1;
        		break;
        	}
        }
        
        if (cnt != 3) return false;
        
        result[0] = s1;
        result[1] = s2;
        result[2] = s3;
    
        return true;
    }
}
