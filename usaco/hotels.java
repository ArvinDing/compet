import java.io.*;
import java.util.*;

public class hotels {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		StringTokenizer read = new StringTokenizer(in.readLine());
		int[] info = new int[n];
		for (int i = 0; i < n; i++) {
			info[i] = Integer.parseInt(read.nextToken());
		}
		int k = Integer.parseInt(in.readLine());
		LinkedList<Integer> sliding = new LinkedList<Integer>();
		for (int i = 0; i < k; i++) {
			while (!sliding.isEmpty()&&info[i] > info[sliding.peekLast()]) {
				sliding.removeLast();
			}
			sliding.add(i);
		}
		System.out.print(info[sliding.peek()]);
		for (int i = k; i < n; i++) {
			if(sliding.peek()<=i-k) {
				sliding.removeFirst();
			}
			while (!sliding.isEmpty()&&info[i] > info[sliding.peekLast()]) {
				sliding.removeLast();
			}
			sliding.add(i);
			System.out.print(" "+info[sliding.peek()]);
		}
	}
}
