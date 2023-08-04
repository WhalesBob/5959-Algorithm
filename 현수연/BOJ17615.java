import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException; 

public class Main
{
    public static void main(String[] args) throws Exception
    {BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split("");
        int same_cnt1=0, diff_cnt1=0;
        for(int i=n-2;i>=0;i--){
            if(!str[n-1].equals(str[i])){
                diff_cnt1++;
            }
            else if(str[n-1].equals(str[i])&&diff_cnt1>0){
                    same_cnt1++;
            }
        }
        int same_cnt2=0, diff_cnt2=0;
        for(int i=1;i<n;i++){
            if(!str[0].equals(str[i])){
                diff_cnt2++;
            }
            else if(str[0].equals(str[i])&&diff_cnt2>0){
                    same_cnt2++;
            }
        }
        int min_diff = Math.min(diff_cnt1,diff_cnt2);
        int min_same = Math.min(same_cnt1,same_cnt2);

        System.out.println(Math.min(min_diff,min_same));	
    }
}