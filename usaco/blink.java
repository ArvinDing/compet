
import java.io.*;
import java.util.*;

public class blink {

	public static void main(String[] argv) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("blink.in"));
		PrintWriter out = new PrintWriter(new FileWriter("blink.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(read.nextToken());
		long B = Long.parseLong(read.nextToken());
		boolean[] prev = new boolean[N];

		for (int i = 0; i < N; i++) {
			prev[i] = in.readLine().equals("1");
		}

		List<String> answer = new ArrayList<String>();
		outer: while (true) {
			boolean[] curr = new boolean[N];
		
			if (prev[N - 1]) {
				curr[0] = !(prev[0]);
			}else{
				curr[0]=prev[0];
			}
			for (int i = 1; i < N; i++) {
				if (prev[i - 1]) {
					curr[i] = !(prev[i]);
				}else{
					curr[i]=prev[i];
				}
			}
			String current = convertString(curr);
			
			if(answer.size()+1==B){
				char[] print = current.toCharArray();
				for (char a : print) {
					out.println(a);
				}
				break outer;
			}
		
			for (int i = 0; i < answer.size(); i++) {
				if (answer.get(i).equals(current)) {
					int loopSize = answer.size() - i;
					char[] print = answer.get((int) (i + ((B-i-1) % loopSize))).toCharArray();
					for (char a : print) {
						out.println(a);
					}
					break outer;
				}

			}
			answer.add(current);
			prev = curr;

		}

		in.close();
		out.close();

	}

	private static String convertString(boolean[] a) {
		String ans = "";
		for (int i = 0; i < a.length; i++) {
			if (a[i]) {
				ans += "1";

			} else {
				ans += "0";
			}

		}
		return ans;
	}
}
