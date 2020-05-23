
import java.io.*;
import java.util.*;

public class typo {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new FileReader("typo.in"));
		PrintWriter out = new PrintWriter(new FileWriter("typo.out"));
		String info = in.readLine();
		int C = 0;
		int D = 0;
		int ansC = 0;

		for (int i = 0; i < info.length(); i++) {
			if (info.charAt(i) == '(') {
				C++;
				ansC++;
			} else {
				D++;
			}
			if (C - D == 1 || C - D == 0) {
				ansC = 0;
			}
			if (C - D == -1) {
				out.println(D);
				break;
			}
		}
		if (C - D >= 0) {
			out.println(ansC);
		}
		in.close();
		out.close();
	}

}
