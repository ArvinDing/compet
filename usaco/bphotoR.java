import java.io.*;
import java.util.*;

public class bphotoR {
	private static int[] bit;
	private static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("bphoto.in"));
		PrintWriter out = new PrintWriter(new File("bphoto.out"));
		n = Integer.parseInt(in.readLine());
		bit = new int[n + 1];
		TreeMap<Integer, List<Integer>> info = new TreeMap<Integer, List<Integer>>(Collections.reverseOrder());
		for (int i = 0; i < n; i++) {
			int value = Integer.parseInt(in.readLine());
			if (!info.containsKey(value))
				info.put(value, new ArrayList<Integer>());
			info.get(value).add(i);
		}
		TreeSet<Integer> binaryS = new TreeSet<Integer>();
		int length = 0;
		int cnt = 0;
		for (List<Integer> indexes : info.values()) {
			for (int curr : indexes) {
				int a=get(curr);
				int b=length-a;
				if(a>b*2||b>a*2){
					cnt++;
				}
			}
			for (int curr : indexes) {
				insert(curr, 1);
				length++;
			}
		}
		out.println(cnt);
		out.close();
		in.close();

	}

	private static void insert(int curr, int value) {
		curr++;
		while (curr <= n) {
			bit[curr] += value;
			curr += (curr & -curr);
		}
	}

	private static int get(int curr) {
		curr++;
		int ans = 0;
		while (curr > 0) {
			ans += bit[curr];
			curr = (curr & curr - 1);
		}
		return ans;
	}

}