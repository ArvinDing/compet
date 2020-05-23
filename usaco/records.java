
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class records {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("records.in"));
		PrintWriter out = new PrintWriter(new FileWriter("records.out"));
		int lines = Integer.parseInt(in.readLine());
		HashMap<String, Integer> info = new HashMap<String, Integer>();

		for (int i = 0; i < lines; i++) {
			String[] curr = in.readLine().split(" ");
			Arrays.sort(curr);
			if (!info.containsKey(curr[0] + " " + curr[1] + " " + curr[2])) {
				info.put(curr[0] + " " + curr[1] + " " + curr[2], 0);
			}
			info.put(curr[0] + " " + curr[1] + " " + curr[2], info.get(curr[0] + " " + curr[1] + " " + curr[2]) + 1);
		}
		int max=Integer.MIN_VALUE;
		for(int a:info.values()){
			max=Math.max(a, max);
		}
		out.println(max);
		in.close();
		out.close();
	}

}
