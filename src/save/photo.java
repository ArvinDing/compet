package save;
import java.io.*;
import java.util.*;

public class photo {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("photo.in"));
		PrintWriter out = new PrintWriter(new File("photo.out"));
		TreeMap<Integer, Integer>[] link = new TreeMap[5];
		int n = Integer.parseInt(in.readLine());
		for (int i = 0; i < 5; i++) {
			link[i]=new TreeMap<Integer,Integer>();
			for (int k = 0; k < n; k++) {
				link[i].put(Integer.parseInt(in.readLine()), k);
			}
		}
		List<Integer> ans = new ArrayList(link[0].keySet());
		Collections.sort(ans, new Comparator<Integer>() {

			@Override
			public int compare(Integer arg0, Integer arg1) {
				int cnt = 0;
				for (int i = 0; i < 5; i++) {
					if (link[i].get(arg0) < link[i].get(arg1)) {
						cnt++;
					}
				}
				if (cnt >= 3)
					return -1;
				return 1;
			}

		});
		for(int a:ans) {
			out.println(a);
		}
		in.close();
		out.close();
	}
}