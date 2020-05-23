import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class lazy1 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("lazy.in"));
		PrintWriter out = new PrintWriter(new File("lazy.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int lines = Integer.parseInt(read.nextToken());
		int reach = Integer.parseInt(read.nextToken());
		TreeMap<Integer, Integer> info = new TreeMap<Integer, Integer>();
		for (int i = 0; i < lines; i++) {
			read = new StringTokenizer(in.readLine());
			int amount = Integer.parseInt(read.nextToken());
			info.put(Integer.parseInt(read.nextToken()), amount);
		}
		int max=0;
		SortedMap<Integer, Integer> inRange = new TreeMap<Integer, Integer>();
		for (Entry<Integer, Integer> curr : info.entrySet()) {
			int location = curr.getKey() - reach;
			inRange.put(curr.getKey(), curr.getValue());
			inRange = inRange.tailMap(location - reach);
			int sum=0;
			for(int now:inRange.values()){
				sum+=now;
			}
			max=Math.max(sum, max);
		}
		out.println(max);
		in.close();
		out.close();
	}
}