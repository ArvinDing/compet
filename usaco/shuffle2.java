
import java.io.*;
import java.util.*;

public class shuffle2 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("shuffle.in"));
		PrintWriter out = new PrintWriter(new File("shuffle.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int cards = Integer.parseInt(read.nextToken());
		int shuffle = Integer.parseInt(read.nextToken());
		int queries = Integer.parseInt(read.nextToken());
		int times = cards - shuffle + 1;
		int[] replace = new int[shuffle];
		for (int i = 0; i < shuffle; i++) {
			int thing = Integer.parseInt(in.readLine());
			replace[i] = thing - 1;
		}
		int[] info = new int[cards];
		for (int i = 0; i < cards; i++) {
			info[i] = i + 1;
		}
		for (int o = 0; o < times; o++) {
			int[] old = new int[shuffle];
			for (int i = 0; i < shuffle; i++) {
				old[i] = info[o + i];
			}
			for (int i = 0; i < shuffle; i++) {
				info[o + replace[i]] = old[i];
			}
		}
		for (int i = 0; i < queries; i++) {
			out.println(info[cards - (Integer.parseInt(in.readLine()))]);
		}
		in.close();
		out.close();
	}
}