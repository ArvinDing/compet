
import java.io.*;
import java.util.*;

public class shuffle3 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("shuffle.in"));
		PrintWriter out = new PrintWriter(new File("shuffle.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int cards = Integer.parseInt(read.nextToken());
		int shuffle = Integer.parseInt(read.nextToken());
		int queries = Integer.parseInt(read.nextToken());
		int times = cards - shuffle + 1;
		int[] replace = new int[cards];
		int[] info = new int[cards];
		for (int i = 0; i < cards; i++) {
			info[i] = i + 1;
		}
		for (int i = 0; i < shuffle; i++) {
			int thing = Integer.parseInt(in.readLine());
			replace[i] = thing - 1;
		}
		for (int i = shuffle; i < cards; i++) {
			replace[i] = i;
		}
		for (int i = 0; i < cards; i++) {

			if (replace[i] == 0)
				replace[i] += cards - 1;
			else
				replace[i] = (replace[i] - 1) % cards;
		}
		int[] old;
		String a = Integer.toString(times, 2);
		for (int i = a.length() - 1; i >= 0; i--) {
			if (a.charAt(i) == '1') {
				old = Arrays.copyOf(info, info.length);
				for (int z = 0; z < cards; z++) {
					info[replace[z]] = old[z];
				}
			}
			int[] test = new int[cards];
			for (int z = 0; z < cards; z++) {
				test[replace[z]] = z;
			}
			old = Arrays.copyOf(test, test.length);
			for (int z = 0; z < cards; z++) {
				test[replace[z]] = old[z];
			}
			for (int z = 0; z < cards; z++) {
				replace[test[z]] = z;

			}
		}
		old = Arrays.copyOf(info, info.length);
		int remainder = cards - times;
		for (int i = remainder; i < cards; i++) {
			info[i - remainder] = old[i];
		}
		for (int i = 0; i < remainder; i++) {
			info[cards - (remainder - i)] = old[i];
		}
		for (int i = 0; i < queries; i++) {
			int debug = Integer.parseInt(in.readLine()) - 1;
			out.println(info[cards - debug - 1]);
		}
		in.close();
		out.close();
	}
}