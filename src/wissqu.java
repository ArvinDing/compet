import java.io.*;
import java.util.*;

public class wissqu {
	static char[][] info;
	static boolean[][] finished;
	static int[][] output, temp;
	static int cnt;
	static boolean update;
	static char imp;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("wissqu.in"));
		PrintWriter out = new PrintWriter(new File("wissqu.out"));
		info = new char[4][4];
		finished = new boolean[4][4];
		output = new int[16][2];
		temp = new int[16][2];
		update = true;
		cnt = 0;
		for (int i = 0; i < 4; i++) {
			String read = in.readLine();
			for (int k = 0; k < 4; k++) {
				info[i][k] = read.charAt(k);
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int k = 0; k < 4; k++) {
				if (info[i][k] != 'D') {
					char orginal = info[i][k];
					imp = orginal;
					info[i][k] = 'D';
					finished[i][k] = true;
					temp[0] = new int[] { i, k };
					recursion(1);
					info[i][k] = orginal;
					finished[i][k] = false;
				}
			}
		}
		recursion(0);
		char prev = 'D';
		for (int i = 0; i < 16; i++) {
			out.println(prev + " " + output[i][0] + " " + output[i][1]);
			prev = info[output[i][0]][output[i][1]];
		}
		out.println(cnt);
		in.close();
		out.close();
	}

	static void recursion(int done) {
		if (done == 16) {
			if (update) {
				for (int i = 0; i < 16; i++) {
					output[i][0] = temp[i][0];
					output[i][1] = temp[i][1];
				}
				update = false;
			}
			cnt++;
		}

		for (int i = 0; i < 4; i++) {
			outer: for (int k = 0; k < 4; k++) {
				if (!finished[i][k]) {
					for (int iAdd = -1; iAdd <= 1; iAdd++) {
						for (int kAdd = -1; kAdd <= 1; kAdd++) {
							if (0 <= i + iAdd && i + iAdd < 4 && 0 <= k + kAdd && k + kAdd < 4) {
								if (info[i + iAdd][k + kAdd] == imp)
									continue outer;
							}
						}
					}
					char save = imp;
					char orginal = info[i][k];
					imp = orginal;
					info[i][k] = save;
					finished[i][k] = true;
					temp[0] = new int[] { i, k };
					recursion(done + 1);
					info[i][k] = orginal;
					imp = save;
					finished[i][k] = false;
				}
			}
		}
		recursion(done + 1);

	}
}
