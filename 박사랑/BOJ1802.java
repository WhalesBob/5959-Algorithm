import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1802 {

    static String input;
    static int len;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T= Integer.parseInt(br.readLine());
        StringBuilder ans=new StringBuilder();
        while(T-->0){
            input=br.readLine();
            len=input.length();
            if(divide(0,len-1)){ // 종이접기 가능한지 검사
                ans.append("YES").append("\n");
            }else{
                ans.append("NO").append("\n");
            }
        }
        System.out.println(ans);
    }

    public static boolean divide(int left, int right){
        if(left==right) { // 범위를 더 이상 줄일 수 없을 때
            return true;
        }
        else{
            int middle=(left+right)/2;
            // 좌우 대칭인지 검사
            for(int i=0;i<middle;i++){
                char l=input.charAt(i);
                char r=input.charAt(right-i);
                if(l==r){ // 좌우가 같으면 NO
                    return false;
                }
            }
            // 검사를 통과하면 범위를 줄여서 검사하도록
            return divide(left,middle-1);
        }
    }
}
