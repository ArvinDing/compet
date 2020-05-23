
import java.io.*;
import java.util.*;

public class opn {
	public static void main(String args[]) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int i = 0; i < test; i++) {
			char[] info = in.readLine().toCharArray();
			StringBuilder ans = new StringBuilder();
			Stack<Character> stack = new Stack<Character>();
			for (int k = 0; k < info.length; k++) {
				char curr = info[k];
				if (97 <= curr && curr <= 122) {
					ans.append(curr);
				} else {
					if (curr == ')')
						ans.append(stack.pop());
					else if (curr != '(')
						stack.push(curr);
				}
			}
			System.out.println(ans.toString());
		}
		in.close();
	}
}
