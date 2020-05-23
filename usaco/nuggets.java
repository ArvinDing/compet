
/*
ID: dingarv1
LANG: JAVA
TASK: nuggets
*/

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class nuggets {
	public static void main(String[] argv) throws IOException {
		Scanner in = new Scanner(new File("nuggets.in"));
		PrintWriter out = new PrintWriter(new File("nuggets.out"));
		int wow = in.nextInt();
		List<Integer> info = new ArrayList<Integer>();
		for (int i = 0; i < wow; i++) {
			info.add(in.nextInt());
		}
		int answer = 0;
		if (!gcd(info)) {
			int start = 0;
			List<Boolean> dp = new ArrayList<Boolean>();
			dp.add(true);

			int streak = 0;
			abc: for (int i = 1; i < 2000000000; i++) {
				if (streak >= 256) {
					break;
				}
				for (int k : info) {

					if (i - k >= 0 && dp.get((i - k) - start) == true) {
						while (dp.size() > 256) {
							dp.remove(0);
							start++;
						}
						dp.add(true);

						streak++;
						continue abc;
					}
				}
				answer = i;
				dp.add(false);
				streak = 0;
			}
		}
		out.println(answer);
		in.close();
		out.close();
	}

	public static boolean gcd(List<Integer> a) {
		
		for (int i = 2; i < a.get(0); i++) {
			boolean answer=true;
			for (int k : a) {
				if (k % i != 0) {
					answer= false;
				}
			}
			if(answer){
				return true;
			}
		}
		return false;
		
	}
}
