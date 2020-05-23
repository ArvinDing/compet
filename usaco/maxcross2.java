
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class maxcross2 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("maxcross.in"));
		PrintWriter out = new PrintWriter(new File("maxcross.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int crosswalks = Integer.parseInt(read.nextToken());
		int streak = Integer.parseInt(read.nextToken());
		int lines = Integer.parseInt(read.nextToken());
		TreeMap<Integer, Integer> info = new TreeMap<Integer, Integer>();
		for (int i = 0; i < lines; i++) {
			int curr = Integer.parseInt(in.readLine());
			if (!info.containsKey(curr)) {
				info.put(curr, 0);
			}
			if (!info.containsKey(curr + streak)) {
				info.put(curr + streak, 0);
			}
			info.put(curr, info.get(curr) + 1);
			info.put(curr + streak, info.get(curr + streak) - 1);
		}
		if(!info.containsKey(crosswalks))info.put(crosswalks, 0);
		int min = Integer.MAX_VALUE;
		int curr = 0;
		for (Entry<Integer, Integer> thing : info.entrySet()) {
			if (thing.getKey() > crosswalks) {
				break;
			}
			int a = thing.getValue();
			curr += a;
			if (thing.getKey() >= streak) {
				
				min = Math.min(min, curr);
			}
		}
		out.println(min);
		out.close();
		in.close();
	}
}