import java.io.*;
import java.util.*;

public class outofplace {
	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("outofplace.in"));
		PrintWriter out = new PrintWriter(new File("outofplace.out"));
		int cows = Integer.parseInt(in.readLine());
		int[] info = new int[cows];
		int[] sort = new int[cows];
		for (int i = 0; i < cows; i++) {
			info[i] = Integer.parseInt(in.readLine());
			sort[i] = info[i];
		}
		Arrays.sort(sort);
		int cnt = 0;
		for (int i = 0; i < cows; i++) {
			if (sort[i] != info[i])
				cnt++;
		}
		out.println(cnt-1);
		in.close();
		out.close();
	}

}
