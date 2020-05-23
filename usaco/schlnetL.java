
/*
ID: dingarv1
LANG: JAVA
TASK: schlnet
*/
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class schlnetL {  
    public static void main(String[] args) throws Exception {  
		long start=System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("schlnet.in"));
		PrintWriter out = new PrintWriter(new FileWriter("schlnet.out"));
		int N = Integer.parseInt(in.readLine());
		String [] line;
		boolean [][] cons = new boolean[N][N], r_cons = new boolean[N][N];
		for (int i = 0; i < N; i ++) {
			line = in.readLine().split(" ");
			for (int j = 0; j < line.length - 1; j++) {
				cons[i][Integer.parseInt(line[j])-1] = true;
				r_cons[Integer.parseInt(line[j])-1][i] = true;
			}
		}
		List<Integer> tops = getTops(cons), bottoms = getTops(r_cons);
		Collections.sort(tops);
		for(int a:tops)System.out.println(a);
		out.println(tops.size());
		HashSet<Integer> common = new HashSet<Integer>();
		common.addAll(tops);
		tops.removeAll(bottoms);
		common.removeAll(tops);
		bottoms.removeAll(common);
		int r_size = Math.max(tops.size(), bottoms.size());
		int c_s = common.size();
		if (c_s > 0) {
			if (r_size == 0) {
				if (c_s == 1) {
					out.println(0);
				} else {
					out.println(c_s);
				}
			} else {
				out.println(r_size + c_s);
			}
		} else {
			out.println(r_size);
		}
		out.close();
		in.close();
		System.out.println(System.currentTimeMillis()-start);
    }

	private static List<Integer> getTops(boolean[][] cons) {
		int [] tops = new int[cons.length];
		Arrays.fill(tops, -1);
		for (int i = 0; i < cons.length; i ++) {
			for (int j = 0; j < cons.length; j ++) {
				if (cons[i][j]) {
					if (tops[j] == -1) {
						tops[j] = tops[i] == -1 ? i : tops[i];
					}
				}
			}
		}
		for (int i = 0; i < tops.length; i ++) {
			while (tops[i] != -1 && tops[i] != i) {
				int tt = tops[tops[i]];
				if (tt == -1 || tt == tops[i]) {
					break;
				}
				tops[i] = tt;
			}
		}
		ArrayList<Integer> ts = new ArrayList<Integer>(), cs = new ArrayList<Integer>();
		for (int i = 0; i < tops.length; i ++) {
			if (tops[i] == -1) {
				ts.add(i);
			} else if (tops[i] == i) {
				cs.add(i);
			}
		}
		boolean [] visited = new boolean[cons.length];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.addAll(ts);
		while(! queue.isEmpty()) {
			int one = queue.poll();
			visited[one] = true;
			for (int i = 0; i < cons.length; i ++) {
				if (cons[one][i] && (! visited[i])) {
					cs.remove(new Integer(i));
					queue.offer(i);
				}
			}
		}
		ts.addAll(cs);
		
		return ts;
	}
}