
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class moocrypt {
	private static char[][] info;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("moocrypt.in"));
		PrintWriter out = new PrintWriter(new File("moocrypt.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int height = Integer.parseInt(read.nextToken());
		int width = Integer.parseInt(read.nextToken());
		info = new char[height][width];
		for (int i = 0; i < height; i++) {
			String save = in.readLine();
			for (int k = 0; k < width; k++) {
				info[i][k] = save.charAt(k);
			}
		}
		List<char[]> combos = new ArrayList<char[]>();
		for (int i = 0; i < height; i++) {
			for (int k = 0; k < width; k++) {
				combos.addAll(findcombos(i, k));
			}
		}

		int max = 0;
		for (int i = 0; i < 26; i++) {

			char replaceM = (char) (i + 65);
			if (replaceM == 'M') {
				continue;
			}
			for (int k = 0; k < 26; k++) {
				if (k == i) {
					continue;
				}
				char replaceO = (char) (k + 65);
				if (replaceO == 'O') {
					continue;
				}
				int temp = 0;
				for (char[] a : combos) {
					if (a[0] == replaceM) {
						if (a[1] == replaceO) {
							temp++;
						}
					}
				}
				max = Math.max(temp, max);

			}

		}
		out.println(max);
		out.close();
		in.close();

	}

	private static List<char[]> findcombos(int i, int k) {
		List<char[]> answer = new ArrayList<char[]>();
		if (i >= 2) {
			if (info[i - 1][k] == info[i - 2][k]) {
				answer.add(new char[] { info[i][k], info[i - 1][k], info[i - 2][k] });
			}
		}

		if (i <= info.length - 3) {
			if (info[i + 1][k] == info[i + 2][k]) {
				answer.add(new char[] { info[i][k], info[i + 1][k], info[i + 2][k] });
			}
		}
		if (k >= 2) {
			if (info[i][k - 1] == info[i][k - 2]) {
				answer.add(new char[] { info[i][k], info[i][k - 1], info[i][k - 2] });
			}
		}
		if (k <= info[0].length - 3) {
			if (info[i][k + 1] == info[i][k + 2]) {
				answer.add(new char[] { info[i][k], info[i][k + 1], info[i][k + 2] });
			}
		}
		// diagonals
		if (i >= 2 && k >= 2) {
			if (info[i - 1][k - 1] == info[i - 2][k - 2]) {
				answer.add(new char[] { info[i][k], info[i - 1][k - 1], info[i - 2][k - 2] });
			}
		}
		if (i >= 2 && k <= info[0].length - 3) {
			if (info[i - 1][k + 1] == info[i - 2][k + 2]) {
				answer.add(new char[] { info[i][k], info[i - 1][k + 1], info[i - 2][k + 2] });
			}
		}
		if (i <= info.length - 3 && k >= 2) {
			if (info[i + 1][k - 1] == info[i + 2][k - 2]) {
				answer.add(new char[] { info[i][k], info[i + 1][k - 1], info[i + 2][k - 2] });
			}
		}
		if (i <= info.length - 3 && k <= info[0].length - 3) {
			if (info[i + 1][k + 1] == info[i + 2][k + 2])
				answer.add(new char[] { info[i][k], info[i + 1][k + 1], info[i + 2][k + 2] });
		}
		return answer;

	}

}
