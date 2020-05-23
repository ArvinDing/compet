
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class geteven {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("geteven.in"));
		PrintWriter out = new PrintWriter(new File("geteven.out"));
		int N = Integer.parseInt(in.readLine());
		List<Integer> B = new ArrayList<Integer>();
		List<Integer> E = new ArrayList<Integer>();
		List<Integer> S = new ArrayList<Integer>();
		List<Integer> I = new ArrayList<Integer>();
		List<Integer> G = new ArrayList<Integer>();
		List<Integer> O = new ArrayList<Integer>();
		List<Integer> M = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			String temp = read.nextToken();
			int temp1 = Integer.parseInt(read.nextToken()) % 2;
			switch (temp) {
			case "B":
				B.add(temp1);
				break;
			case "E":
				E.add(temp1);
				break;
			case "S":
				S.add(temp1);
				break;
			case "I":
				I.add(temp1);
				break;
			case "G":
				G.add(temp1);
				break;
			case "O":
				O.add(temp1);
				break;
			case "M":
				M.add(temp1);
				break;

			}

		}
		int odd = 0;
		for (int b : B) {
			for (int e : E) {
				for (int s : S) {
					for (int i : I) {
						if ((b + e + s + s + i + e) % 2 == 0) {
							continue;
						}
						for (int g : G) {
							for (int o : O) {
								if ((g + o + e + s) % 2 == 0) {
									continue;
								}
								for (int m : M) {
									if ((m + o + o) % 2 == 0) {
										continue;
									}
									odd++;
								}
							}
						}
					}
				}
			}
		}
		int all = B.size() * E.size() * S.size() * I.size() * G.size() * O.size() * M.size();
		out.println(all - odd);
		out.close();
		in.close();

	}

}
