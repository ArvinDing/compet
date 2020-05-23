
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class poker {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("poker.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("poker.out")));
		int types = Integer.parseInt(in.readLine());
		int[] info = new int[types];
		long answer = Integer.MAX_VALUE;
		for (int i = 0; i < types; i++) {
			info[i] = Integer.parseInt(in.readLine());
			answer = Math.min(info[i], answer);

		}
		TreeSet<Integer> solution = new TreeSet<Integer>();
		for (int i = 0; i < types; i++) {
			info[i] -= answer;
			if (info[i] == 0) {
				solution.add(i);
			}
		}

		solution.add(-1);
		solution.add(types);
		for (int removeL = types - 2; removeL >= 0; removeL--) {

			Iterator<Integer> a = solution.iterator();
			int old = a.next();
			List<Integer> temp = new ArrayList<Integer>();
			int max = Integer.MIN_VALUE;
			while (a.hasNext()) {
				int curr = a.next();
				max = Math.max(max, (curr - 1) - (old + 1));
				if ((curr - 1) - (old + 1) == removeL) {
					int minR = Integer.MAX_VALUE;
					for (int i = old + 1; i <= curr - 1; i++) {
						minR = Math.min(minR, info[i]);
					}

					for (int i = old + 1; i <= curr - 1; i++) {
						info[i] -= minR;
						if (info[i] == 0) {
							temp.add(i);
						}
					}
					answer += minR;

				}
				old = curr;
			}
			if (max != removeL) {
				removeL = max+1;
			}
			solution.addAll(temp);
		}
		out.println(answer);
		in.close();
		out.close();
	}
}