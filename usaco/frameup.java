
/*
ID: dingarv1
LANG: JAVA
TASK: frameup
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class frameup {

	public static void main(String[] args) throws Exception {
		// read

		BufferedReader in = new BufferedReader(new FileReader("frameup.in"));
		PrintWriter out = new PrintWriter(new File("frameup.out"));
		StringTokenizer tokeniz3r = new StringTokenizer(in.readLine());
		int height = Integer.parseInt(tokeniz3r.nextToken());
		int width = Integer.parseInt(tokeniz3r.nextToken());
		char[][] info = new char[height][width];
		for (int i = 0; i < height; i++) {
			String temp = in.readLine();
			for (int k = 0; k < width; k++) {
				info[i][k] = temp.charAt(k);
			}
		}
		int frameC = 0;
		frame[] frames = new frame[26];
		for (int i = 0; i < height; i++) {
			for (int k = 0; k < width; k++) {
				if (info[i][k] != '.') {
					if (frames[info[i][k] - 65] == null) {
						frames[info[i][k] - 65] = new frame(k, k, i, i);
						frameC++;
					} else {
						frame temp = frames[info[i][k] - 65];
						temp.h = Math.min(temp.h, i);
						temp.Bh = Math.max(temp.Bh, i);
						temp.w = Math.min(temp.w, k);
						temp.Bw = Math.max(temp.Bw, k);

					}
				}
			}
		}
		TreeSet<String> key = new TreeSet<String>();
		TreeMap<String, char[][]> answer = new TreeMap<String, char[][]>();
		answer.put("", info);
		while (!answer.isEmpty()) {

			Entry<String, char[][]> temp = answer.pollFirstEntry();
			String a = temp.getKey();
			char[][] tempInfo = temp.getValue();
			if (a.length() == frameC) {
				String temporary = "";
				for (int i = 0; i < a.length(); i++) {
					temporary += a.charAt(a.length() - 1 - i);
				}
				key.add(temporary);
				continue;
			}
			bigloop: for (int index = 0; index < frames.length; index++) {
				frame curr = frames[index];

				if (curr == null) {
					continue;
				}
				for (int i = 0; i < a.length(); i++) {
					if (a.charAt(i) == (char) (index + 65)) {
						continue bigloop;
					}
				}
				if (checkRectangle((char) (index + 65), curr.w, curr.Bw, curr.h, curr.Bh, tempInfo)) {
					char[][] copy = new char[tempInfo.length][tempInfo[0].length];
					for (int i = 0; i < tempInfo.length; i++) {
						for (int k = 0; k < tempInfo[0].length; k++) {
							if (((i == curr.h || i == curr.Bh) && (k >= curr.w && k <= curr.Bw))
									|| ((k == curr.w || k == curr.Bw) && (i >= curr.h && i <= curr.Bh))) {
								copy[i][k] = ' ';
							} else {
								copy[i][k] = tempInfo[i][k];
							}
						}
					}
					answer.put(a + (char) (index + 65), copy);
				}
			}

		}
		for (String a : key) {
			
			out.println(a);
		}
		in.close();
		out.close();
	}

	private static class frame {
		int w, Bw, h, Bh;

		public frame(int w, int Bw, int h, int Bh) {
			this.w = w;
			this.Bw = Bw;
			this.h = h;
			this.Bh = Bh;

		}
	}

	private static boolean checkRectangle(char frame, int w, int Bw, int h, int Bh, char[][] info) {
		for (int i = 0; i < info.length; i++) {
			for (int k = 0; k < info[0].length; k++) {
				if (((i == h || i == Bh) && (k >= w && k <= Bw)) || ((k == w || k == Bw) && (i >= h && i <= Bh))) {
					if (!(info[i][k] == ' ' || info[i][k] == frame)) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
