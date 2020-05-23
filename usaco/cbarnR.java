import java.io.*;
import java.util.*;

public class cbarnR {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cbarn.in"));
		PrintWriter out = new PrintWriter(new File("cbarn.out"));
		int n = Integer.parseInt(in.readLine());
		int[] info = new int[n];
		for (int i = 0; i < n; i++) {
			info[i] = Integer.parseInt(in.readLine());
		}
		int[] changed = new int[n];
		Arrays.fill(changed, -1);
		LinkedList<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < info[i]; k++) {
				queue.add(i);
			}
			if(!queue.isEmpty()) {
			changed[i]=queue.poll();
			}
		}
		for (int i = 0; i < n; i++) {
			if(changed[i]!=-1)
				queue.add(changed[i]);
			changed[i]=queue.poll();
		}
		long sum=0;
		for(int i=0;i<n;i++) {
			if(changed[i]>i)
				sum+=(n-changed[i]+i)*(n-changed[i]+i);
			else
				sum+=(i-changed[i])*(i-changed[i]);
		}
		out.println(sum);
		out.close();
		in.close();
	}
}
