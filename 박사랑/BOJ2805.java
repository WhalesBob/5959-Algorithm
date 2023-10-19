import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2805 {

    static int N,M;
    static int[] len;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        len=new int[N];
        st = new StringTokenizer(br.readLine());
        int max=0;
        for(int i=0;i<N;i++){
            len[i]=Integer.parseInt(st.nextToken());
            max=Math.max(max,len[i]);
        }
        int min=0;
        int mid=0;
        int ans=0;
        while(min<max) {
            mid=(min+max)/2;
            if(isPossible(mid)){
                min=mid+1;
                ans=mid;
            }else{
                max=mid;
            }
        }
        System.out.println(ans);
    }

    public static boolean isPossible(int cri) {
        long sum=0;
        for(int i=0;i<N;i++){
            if(len[i]>cri){
                sum+=len[i]-cri;
            }
            if(sum>=M) return true;
        }
        return false;
    }
}
