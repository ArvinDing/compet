package save;

/*
ID: dingarv1
LANG: JAVA
TASK: cowxor
*/

import java.util.*;
import java.io.*;

public class cowxor {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cowxor.in"));
		PrintWriter out = new PrintWriter(new File("cowxor.out"));
		int n = Integer.parseInt(in.readLine());
		node info = new node(null, null);
		info.index = 0;
		int ans = -1;
		int start = -1;
		int end = -1;
		set(info, 0, 0);
		int prefix = 0;
		for (int i = 0; i < n; i++) {
			int curr = Integer.parseInt(in.readLine());
			prefix ^= curr;
			node temp = info;
			int add = 0;
			for (int idx = 20; idx >= 0; idx--) {
				int important = (1 << idx);
				if ((prefix & important) == important) {
					if (temp.zero != null) {
						add += (1 << idx);
						temp = temp.zero;
					} else {
						temp = temp.one;
					}
				} else {
					if (temp.one != null) {
						add += (1 << idx);
						temp = temp.one;
					} else {
					
						temp = temp.zero;
					}
				}
			}
			if (add > ans) {
				ans = add;
				start = temp.index + 1;
				end = i + 1;
			}
			set(info, prefix, i + 1);
		}
		out.println(ans + " " + start + " " + end);
		in.close();
		out.close();
	}

	static void set(node info, int number, int start) {
		node loop = info;
		for (int idx = 20; idx >= 0; idx--) {
			int important = 1 << idx;
			if ((number & important) == important) {
				if (loop.one == null) {
					loop.one = new node(null, null);
				}
				loop = loop.one;

			} else {
				if (loop.zero == null) {
					loop.zero = new node(null, null);
				}
				loop = loop.zero;

			}
		}
		loop.index = Math.max(loop.index, start);
	}

	static class node {
		node zero;
		int index;
		node one;

		node(node one, node zero) {
			this.one = one;
			this.zero = zero;
		}
	}
}
