import java.io.*;
import java.util.*;

public class oop {
	private static String[] info;
	private static HashMap<String, Integer> dfsS, dfsE;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		PrintWriter out = new PrintWriter(new File("output"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int q = Integer.parseInt(read.nextToken());
		int cnt = 0;
		p pT = new p();
		info = new String[n];
		for (int i = 0; i < n; i++) {
			String a = in.readLine();
			info[i] = a;
			buildP(a, pT, i);
		}

		dfsS = new HashMap<String, Integer>();
		dfsE = new HashMap<String, Integer>();
		dfs(pT);
		outer: for (int i = 0; i < q; i++) {
			String a = in.readLine();
			String pre = a.substring(0, a.indexOf('*'));
			String suf = a.substring(a.indexOf('*') + 1);
			if (!dfsS.containsKey(pre) || !dfsE.containsKey(pre)) {
				out.println(0);
				break;
			}
			int s = dfsS.get(pre);// include start
			int e = dfsE.get(pre);// include end
			s currS = sTree;
			for (int k = 0; k < suf.length(); k++) {
				int curr = suf.charAt(k) - 97;
				if (currS.child[curr] == null) {
					out.println(0);
					continue outer;
				}
				currS = currS.child[curr];
			}
//			for (int z : currS.imp)
//				System.out.print(z + " ");
//			System.out.println(currS.imp.tailSet(s).headSet(e + 1));
			out.println(currS.imp.tailSet(s).headSet(e + 1).size());
		}
		in.close();
		out.close();
	}

	static int index = 0;
	static s sTree = new s();

	static void dfs(p curr) {
		int[] a = curr.imp;
		String temp = info[a[0]].substring(0, a[1]);
		dfsS.put(temp, index);
		if (curr.word) {
			buildS(info[a[0]], sTree, index);
		}
		index++;
		for (int i = 0; i < 26; i++) {
			if (curr.child[i] != null) {
				dfs(curr.child[i]);
			}
		}
		dfsE.put(info[a[0]].substring(0, a[1]), index - 1);

	}

	static void buildP(String a, p currP, int index) {
		for (int k = 0; k < a.length(); k++) {
			int curr = a.charAt(k) - 97;
			currP.imp = new int[] { index, k };
			if (currP.child[curr] == null)
				currP.child[curr] = new p();
			currP = currP.child[curr];
		}
		currP.imp = new int[] { index, a.length() };
		currP.word = true;

	}

	static void buildS(String a, s currS, int index) {
		for (int k = a.length() - 1; k >= 0; k--) {
			int curr = a.charAt(k) - 97;
			currS.imp.add(index);
			if (currS.child[curr] == null)
				currS.child[curr] = new s();
			currS = currS.child[curr];
		}
		currS.imp.add(index);

	}

	static class p {
		p[] child;
		int[] imp;
		boolean word;

		public p() {
			child = new p[26];
			imp = new int[2];
		}
	}

	static class s {
		s[] child;
		TreeSet<Integer> imp;

		public s() {
			child = new s[26];
			imp = new TreeSet<Integer>();
		}
	}

}
