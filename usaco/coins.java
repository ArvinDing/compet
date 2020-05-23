import java.io.*;
import java.util.*;

public class coins {
	private static HashMap<Integer, Long> info;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = in.readLine()) != null) {
			info = new HashMap<Integer, Long>();
			System.out.println(recursion(Integer.parseInt(line)));
		}
		in.close();

	}

	private static long recursion(int curr) {
		if (curr == 0)
			return 0;
		if (info.containsKey(curr))
			return info.get(curr);
		long ans = Math.max(curr, recursion(curr / 4) + recursion(curr / 2) + recursion(curr / 3));
		info.put(curr, ans);
		return ans;
	}
}
