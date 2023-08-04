import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] in = new String[n];
		String[] out = new String[n];
		int[] order = new int[n];
		for(int i=0;i<n;i++)
			in[i]=br.readLine();
		for(int i=0;i<n;i++)
			out[i]=br.readLine();
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(out[i].equals(in[j])) {
					order[i]=j+1;
					break;
				}
			}
		}
		int cnt=0;
		for(int i=0;i<n;i++){
			for(int j=i+1;j<n;j++) {
				if(order[i]>order[j]) {
					cnt++;
					break;
				}
			}
		}
		System.out.println(cnt);
	}
}