
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class hayfeast {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("hayfeast.in"));
		PrintWriter out = new PrintWriter(new File("hayfeast.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int haybales = Integer.parseInt(read.nextToken());
		long minFla = Long.parseLong(read.nextToken());
		long ans = Integer.MAX_VALUE;
		long[][] info = new long[haybales][2];
		for (int i = 0; i < haybales; i++) {
			read = new StringTokenizer(in.readLine());
			info[i] = new long[] { Long.parseLong(read.nextToken()),  Long.parseLong(read.nextToken()) };
		}
		for (int i = 0; i < haybales; i++) {
			long curr = 0;
			long min = 0;
			for (int k = i; k < haybales; k++) {
				curr += info[k][0];
				if(info[k][1]>=ans){
					i=k;
					break;
				}
				min = Math.max(min, info[k][1]);
				if (curr >= minFla){
					ans = min;
					break;
				}
			}
		}
		out.print(ans);
		in.close();
		out.close();
	}

}
