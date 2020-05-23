
import java.io.*;
import java.util.*;

public class diamond {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("diamond.in"));
		PrintWriter out = new PrintWriter(new File("diamond.out"));
		TreeMap<Integer, Integer> info = new TreeMap<Integer, Integer>();
		StringTokenizer read = new StringTokenizer(in.readLine());
		int lines = Integer.parseInt(read.nextToken());
		int range = Integer.parseInt(read.nextToken());
		for (int i = 0; i < lines; i++) {
			int curr = Integer.parseInt(in.readLine());
			if (!info.containsKey(curr)) {
				info.put(curr, 0);
			}
			info.put(curr, info.get(curr) + 1);
			if (!info.containsKey(curr + range + 1)) {
				info.put(curr + range + 1, 0);
			}
			info.put(curr + range + 1, info.get(curr + range + 1) - 1);
		}
		int max = 0;
		int curr = 0;
		for (int value : info.values()) {
			curr += value;
			max = Math.max(max, curr);
		}
		out.println(max);
		in.close();
		out.close();

	}

}
