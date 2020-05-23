package save;
import java.io.*;
import java.util.*;

public class median {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("median.in"));
		PrintWriter out = new PrintWriter(new File("median.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int x = Integer.parseInt(read.nextToken());
		int[] appear = new int[2 * 100001];
		long ans = 0;
		appear[100001]=1;
		int rS=0;
		long add=0;
		for (int i = 0; i < n; i++) {
			int curr = Integer.parseInt(in.readLine());
			if (curr >= x) {
				rS++;
				add+=appear[rS+100001]+1;
			} else {
				add-=appear[rS+100001]-1;
				rS--;
			}
			appear[rS+100001]++;
			ans+=add;
		}
		out.println(ans);
		out.close();
		in.close();
		System.out.println("Sucess");
	}
}
