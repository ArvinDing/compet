
/*
ID: dingarv1
LANG: JAVA
TASK:  msquare
*/

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class msquare {

	public static void main(String[] args) throws Exception {
		int[][] info = new int[2][4];
		Scanner in = new Scanner(new FileReader("msquare.in"));
		PrintWriter out = new PrintWriter(new File("msquare.out"));
	
			for (int k = 0; k < 4; k++) {

				info[0][k] = in.nextInt();
			}
			for (int k = 0; k < 4; k++) {

				info[1][3-k] = in.nextInt();
			}
		List<hash> list = new ArrayList<hash>();
		TreeSet<String> repeat = new TreeSet<String>();
		hash lol = new hash("", new int[][] { new int[] { 1, 2, 3, 4 }, new int[] { 8, 7, 6, 5 } });
		list.add(lol);
		repeat.add(convert(info));
		abc: {
			while (true) {
				hash wow = list.remove(0);
				String path = wow.path;
				int[][] node = wow.node;
				if(isSame(node,info)){
					out.println(path.length());
					out.println(path);
					break abc;
				}
				int[][] b = clone(node);
				int[][] c = clone(node);
				int[][] d = clone(node);
				doA(b);
				if (isSame(b, info)) {
					out.println(path.length() + 1);
					out.println(path + "A");
					break abc;
				}
				doB(c);
				if (isSame(c, info)) {
					out.println(path.length() + 1);
					out.println(path + "B");
					break abc;
				}
				doC(d);
				if (isSame(d, info)) {
					out.println(path.length() + 1);
					out.println(path + "C");
					break abc;
				}
				if (!repeat.contains(convert(b))) {
					repeat.add(convert(b));
					hash no = new hash(path + "A", b);
					list.add(no);

				}
				if (!repeat.contains(convert(c))) {
					repeat.add(convert(c));
					hash no = new hash(path + "B", c);
					list.add(no);
				}
				if (!repeat.contains(convert(d))) {
					repeat.add(convert(d));
					hash no = new hash(path + "C", d);
					list.add(no);
				}
			}
		}

		out.close();
		in.close();
	}

	public static int[][] clone(int[][] a) {
		int[][] b = new int[2][4];
		for (int k = 0; k < 2; k++) {
			for (int i = 0; i < 4; i++) {

				b[k][i] = a[k][i];
			}
		}
		return b;
	}

	public static String convert(int[][] a) {

		StringBuilder b = new StringBuilder();

		for (int k = 0; k < 2; k++) {
			for (int i = 0; i < 4; i++) {
				b.append("" + a[k][i]);
			}
		}
		return b.toString();
	}

	public static class hash {
		String path;
		int[][] node;

		public hash(String path, int[][] node) {
			this.path = path;
			this.node = node;
		}
	}

	public static boolean isSame(int[][] a, int[][] b) {

		for (int k = 0; k < 2; k++) {
			for (int i = 0; i < 4; i++) {
				if (a[k][i] != b[k][i]) {
					return false;
				}
			}
		}
		return true;
	}

	public static int[][] doA(int[][] a) {
		for (int i = 0; i < 4; i++) {
			int save = a[0][i];
			a[0][i] = a[1][i];
			a[1][i] = save;
		}
		return a;
	}

	public static int[][] doB(int[][] a) {
		int lol = a[0][3];
		int wow = a[1][3];

		for (int i = 3; i > 0; i--) {
			a[0][i] = a[0][i - 1];
			a[1][i] = a[1][i - 1];
		}
		a[1][0] = wow;
		a[0][0] = lol;
		return a;
	}

	public static int[][] doC(int[][] a) {
		int u = a[0][1];
		int u1 = a[1][1];
		int u2 = a[0][2];
		int u3 = a[1][2];
		a[0][1] = u1;
		a[1][1] = u3;
		a[0][2] = u;
		a[1][2] = u2;

		return a;
	}

}