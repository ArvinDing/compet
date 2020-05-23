
import java.io.*;
import java.util.*;

public class cowfind {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new FileReader("cowfind.in"));
		PrintWriter out = new PrintWriter(new FileWriter("cowfind.out"));
		String info = in.readLine();
		int start = 0;
		int startend = 0;
		for (int i = 0; i < info.length() - 1; i++) {
			if (info.charAt(i) == ')' && info.charAt(i + 1) == ')') {
				startend+=start;
			}
			if (info.charAt(i) == '(' && info.charAt(i + 1) == '(') {
				start++;
			}
		}
		out.println(startend);
		in.close();
		out.close();
	}

}
