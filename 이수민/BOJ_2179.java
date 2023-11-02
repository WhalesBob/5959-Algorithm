import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2179 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        ArrayList<String> words = new ArrayList<>();
        for(int i=0; i<n; i++){
            words.add(br.readLine());
        }
        
        String word1="";
        String word2="";
        int max = 0; // m이 최대인 경우
        
        for(int i=0; i<words.size(); i++){
            for(int j=i+1; j<words.size(); j++){
                int m = 0;

                String w1 = words.get(i); 
                String w2 = words.get(j);
		// 더 짧은 문자열의 길이만큼 탐색
                int len = Math.min(w1.length(), w2.length());

                for(int idx=0; idx<len; idx++){
		    // 문자가 다르다면, 접두사의 최대 길이 끝!
                    if (w1.charAt(idx) != w2.charAt(idx)){
                        m = idx;
                        break;
                    }
                    if (idx == len -1){
                        m = idx+1; // 짧은 문자열 전체가 두 단어의 접두사인 경우
                    }

                }
		// 현재 접두사의 길이가 최대인 경우, 두 단어 갱신
                if (m > max){
                    max = m;
                    word1 = w1;
                    word2 = w2;
                }

            }
        }

        System.out.println(word1);
        System.out.println(word2);
    }
}
