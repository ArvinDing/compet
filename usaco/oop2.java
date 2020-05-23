import java.io.*;
import java.util.*;

public class oop2 {
	static suffix sTree;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		PrintWriter out = new PrintWriter(new File("output"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int q = Integer.parseInt(read.nextToken());
		int cnt = 0;
		prefix pTree = new prefix(cnt++);
		for (int i = 0; i < n; i++) {
			String a = in.readLine();
			prefix build = pTree;
			for (int k = 0; k < a.length(); k++) {
				int curr = a.charAt(k) - 97;
				build.suffix.add(a.substring(k));
				if (build.child[curr] == null) {
					build.child[curr] = new prefix(cnt++);
				}
				build = build.child[curr];
			}
		}
		sTree = new suffix();
		firstSeen = new int[cnt];
		lastSeen = new int[cnt];
		dfsInfo = new List[cnt];
		index=0;
		dfs(pTree);
		for (int i = 0; i < cnt; i++) {
			for (List<String> loop : dfsInfo) {
				for (String a : loop) {
					suffix build = sTree;
					for (int k = 0; k < a.length(); k++) {
						int curr = a.charAt(k) - 97;
						if (build.child[curr] == null) {
							build.child[curr] = new suffix();
						}
						build = build.child[curr];
					}
					build.imp.add(i);
				}
			}
		}
		
		outer: for (int i = 0; i < q; i++) {
			String a = in.readLine();
			int k;
			for (k = 0; k < a.length(); k++) {
				if (a.charAt(k) == '*') {
					break;
				}
			}
			String p = a.substring(0, k);
			String s = a.substring(k + 1);
			prefix pSearch = pTree;
			for (k = 0; k < p.length(); k++) {
				int curr = p.charAt(k) - 97;
				if (pSearch.child[curr] == null) {
					out.println(0);
					continue outer;
				}
				pSearch = pSearch.child[curr];
			}
			int start = firstSeen[pSearch.identity];
			int end = lastSeen[pSearch.identity];
			suffix sSearch = sTree;
			for (k = 0; k < s.length(); k++) {
				int curr = s.charAt(k) - 97;
				if (sSearch.child[curr] == null) {
					out.println(0);
					continue outer;
				}
				sSearch = sSearch.child[curr];
			}
			out.println(sSearch.imp.indexOf(end) - sSearch.imp.indexOf(start));
		}

		in.close();
		out.close();
	}

	static int[] firstSeen;
	static int[] lastSeen;
	static List<String>[] dfsInfo;
	private static int index;
	static void dfs(prefix curr) {
		firstSeen[curr.identity] = index;
		dfsInfo[index++] = curr.suffix;
		for (int i = 0; i < 26; i++) {
			if (curr.child[i] != null) {
				dfs(curr.child[i] );
			}
		}
		lastSeen[curr.identity] = index;

	}

	static class prefix {
		prefix[] child;
		List<String> suffix;
		int identity;

		public prefix(int identity) {
			child = new prefix[26];
			suffix = new ArrayList<String>();
			this.identity = identity;
		}
	}

	static class suffix {
		suffix[] child;
		List<Integer> imp;

		public suffix() {
			child = new suffix[26];
			imp = new ArrayList<Integer>();
		}
	}

}
