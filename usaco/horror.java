import java.io.*;
import java.util.*;

public class horror {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int i = 0; i < test; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(read.nextToken());
			LinkedList<Integer> info = new LinkedList<Integer>();
			for (int k = 0; k < n; k++) {
				info.add(Integer.parseInt(read.nextToken()));
			}
			Collections.sort(info);
			System.out.println("Case "+(i+1)+": "+info.pollLast());
		}
	}
}