
import java.io.*;
import java.util.*;

public class standingout {
	private static LinkedList<String[]> all;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("standingout.in"));
		PrintWriter out = new PrintWriter(new File("standingout.out"));
		int lines = Integer.parseInt(in.readLine());
		all = new LinkedList<String[]>();
		int cnt=0;
		for (int i = 0; i < lines; i++) {
			char[] line = in.readLine().toCharArray();
			String save = "";
			for (int k = line.length - 1; k >= 0; k--) {
				cnt++;
				save = line[k] + save;
				all.add(new String[] { save, i + "" });
			}
		}
		System.out.println(cnt);
		Collections.sort(all, new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				if (o1.equals(o2))
					return 0;
				return o1[0].compareTo(o2[0]);
			}
		});
		String[] nextUnIn = cal(all.iterator(), false);
		String[] prevUnIn = cal(all.descendingIterator(), true);

		int[] ans = new int[lines];
		int index = 0;
		String imPrev = "";
		int imPrevStrI = -1;
		while (!all.isEmpty()) {
			String[] a = all.poll();
			String next = nextUnIn[index];
			String prev = prevUnIn[index];
			String curr = a[0];
			int idx = Integer.parseInt(a[1]);
			int unique = Math.min(compareS(curr, next), compareS(curr, prev));
			if (idx == imPrevStrI)
				unique = Math.min(unique, compareS(curr, imPrev));
			ans[idx] += unique;
			imPrev = curr;
			imPrevStrI = idx;
			
			index++;
		}
		for (int i = 0; i < lines; i++) {
			out.println(ans[i]);
		}
		out.close();
		in.close();

	}

	private static int compareS(String curr, String other) {
		for (int i = 0; i < curr.length(); i++) {
			if (i >= other.length() || curr.charAt(i) != other.charAt(i)) {
				return curr.length() - i;
			}
		}
		return 0;
	}

	private static String[] cal(Iterator<String[]> in, boolean reversed) {
		String[] temp = new String[all.size()];
		LinkedList<int[]> nDone = new LinkedList<int[]>();
		int index = (reversed) ? all.size() - 1 : 0;
		while (in.hasNext()) {
			String[] curr = in.next();
			int strIdx = Integer.parseInt(curr[1]);

			nDone.add(new int[] { strIdx, index });
			Iterator<int[]> itr = nDone.iterator();
			while (itr.hasNext()) {
				int[] a = itr.next();
				if (strIdx != a[0]) {
					temp[a[1]] = curr[0];
					itr.remove();
				}
			}
			if (reversed)
				index--;
			else
				index++;
		}
		for (int[] a : nDone) {
			temp[a[1]] = "";
		}
		return temp;
	}

}