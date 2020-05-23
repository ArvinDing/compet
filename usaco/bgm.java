
import java.io.*;
import java.util.*;

public class bgm {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("bgm.in"));
		PrintWriter out = new PrintWriter(new File("bgm.out"));
		int n = Integer.parseInt(in.readLine());

		long[][] info = new long[7][7];
		for (int i = 0; i < n; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			String poss = read.nextToken();
			int value = Integer.parseInt(read.nextToken());
			value = (7 + (value % 7)) % 7;
			switch (poss) {
			case "B":
				info[0][value]++;
				break;
			case "E":
				info[1][value]++;
				break;
			case "S":
				info[2][value]++;
				break;
			case "I":
				info[3][value]++;
				break;
			case "G":
				info[4][value]++;
				break;
			case "O":
				info[5][value]++;
				break;
			case "M":
				info[6][value]++;
				break;

			}
		}
		long answer = 0;
		for (int bIndex = 0; bIndex < 7; bIndex++) {
			for (int eIndex = 0; eIndex < 7; eIndex++) {
				for (int sIndex = 0; sIndex < 7; sIndex++) {
					for (int iIndex = 0; iIndex < 7; iIndex++) {
						for (int gIndex = 0; gIndex < 7; gIndex++) {
							for (int oIndex = 0; oIndex < 7; oIndex++) {
								for (int mIndex = 0; mIndex < 7; mIndex++) {
									int bessie = (bIndex + (2 * eIndex) + (2 * sIndex) + iIndex);
									int goes = (gIndex + oIndex + eIndex + sIndex);
									int moo = (mIndex + (2 * oIndex));
									if ((bessie % 7 == 0) || (goes % 7 == 0) || (moo % 7 == 0)) {
										answer += info[0][bIndex] * info[1][eIndex] * info[2][sIndex] * info[3][iIndex]
												* info[4][gIndex] * info[5][oIndex] * info[6][mIndex];
									}
								}
							}
						}
					}
				}
			}
		}
		out.print(answer);
		in.close();
		out.close();
	}

}
