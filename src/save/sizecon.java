package save;
import java.io.*;
import java.util.*;

public class sizecon {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		outer: for (int n = Integer.parseInt(in.readLine()); n != 0; n = Integer.parseInt(in.readLine())) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int[] info = new int[n];
			for (int i = 0; i < n; i++) {
				info[i] = Integer.parseInt(read.nextToken());
			}
			for (int i = 0; i < n; i++) {
				if (info[info[i] - 1] != i + 1) {
					System.out.println("not ambiguous");
					continue outer;
				}
			}
			System.out.println("ambiguous");
		}
	}
}