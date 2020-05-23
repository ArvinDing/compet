
import java.io.*;
import java.util.*;

public class palin {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int lines = Integer.parseInt(in.readLine());
		for (int i = 0; i < lines; i++) {
			char[] input = removeleading0(in.readLine().toCharArray());
			StringBuilder ans = new StringBuilder();
			boolean flag = false;
			int max = (input.length / 2) + ((input.length % 2 == 0) ? -1 : 0);

			if (input.length == 0) {
				ans.append(1);
			} else if (all9(input)) {
				ans.append('1');
				for (int z = 0; z < input.length - 1; z++) {
					ans.append('0');
				}
				ans.append('1');
			} else if (input.length == 1) {
				if (input[0] == '9')
					ans.append("11");
				else
					ans.append(Integer.parseInt(input[0] + "") + 1);
			} else {

				boolean addOne = true;
				int less = input.length / 2;
				int more = input.length / 2;
				if (input.length % 2 == 0)
					less--;
				while (less >= 0) {
					if (input[less] > input[more]) {
						addOne = false;
						break;
					} else if (input[less] < input[more]) {
						break;
					}
					less--;
					more++;
				}
				if (addOne) {
					int leftOffon = -1;
					StringBuilder temp = new StringBuilder();
					for (int k = max; k >= 0; k--) {
						if (input[k] == '9') {
							temp.append(0);
						} else {
							leftOffon = k;
							break;
						}
					}

					if (leftOffon != -1) {
						temp.insert(0, Integer.parseInt(input[leftOffon] + "") + 1);
					} else {
						temp.insert(0, 1);
						flag = true;
					}
					for (int k = 0; k < leftOffon; k++) {
						ans.append(input[k]);
					}
					ans.append(temp);

				} else {
					for (int k = 0; k <= max; k++) {
						ans.append(input[k]);
					}
				}
				StringBuilder reverse = new StringBuilder(ans.toString());
				reverse.reverse();
				if (input.length % 2 == 1 && !flag) {
					reverse.delete(0, 1);
				}
				ans.append(reverse);

			}
			System.out.println(ans.toString());
		}

	}

	private static char[] removeleading0(char[] chrArry) {
		int cnt = 0;
		for (char a : chrArry) {
			if (a == '0')
				cnt++;
			else
				break;
		}
		char[] changed = new char[chrArry.length - cnt];
		for (int i = cnt; i < chrArry.length; i++) {
			changed[i - cnt] = chrArry[i];
		}
		return changed;
	}

	private static boolean all9(char[] in) {
		for (char a : in) {
			if (a != '9')
				return false;
		}
		return true;
	}

}