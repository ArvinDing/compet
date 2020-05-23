
/*
ID: dingarv1
LANG: JAVA
TASK: range
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class range {
	private static int[] count;
	private static int size;
	private static int[][] info;

	public static void main(String[] argv) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("range.in"));
		PrintWriter out = new PrintWriter(new File("range.out"));
		size = Integer.parseInt(in.readLine());
		info = new int[size][size];
		for (int i = 0; i < size; i++) {
			String a = in.readLine();
			for (int k = 0; k < size; k++) {
				info[i][k] = Integer.parseInt(""+a.charAt(k));

			}
		}
		count = new int[size + 1];
		solve();
		for (int i = 2; i < size+1; i++) {
			if (count[i] != 0) {
				out.println(i + " " + count[i]);

			}
		}
	
		in.close();
		out.close();
	}

	private static void solve() {
		int [][]dp=new int [size][size];
		for(int i=0;i<size;i++){
			dp[0][i]=info[0][i];
		}
		
		for(int i=0;i<size;i++){
			dp[i][0]=info[i][0];
		}
		for (int i = 1; i < size; i++) {
			for (int k = 1; k < size; k++) {
				if(info[i][k]==0){
					dp[i][k]=0;
				}else{
					int add=Math.min(Math.min(dp[i-1][k],dp[i-1][k-1]),dp[i][k-1])+1;
					for(int p=1;p<=add;p++){
					count[p]++;
					}
					dp[i][k]= add;
				}
			}
		}
	}
}
