
import java.io.*;
import java.util.*;

public class clumsy {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new FileReader("clumsy.in"));
		PrintWriter out = new PrintWriter(new FileWriter("clumsy.out"));
		String info = in.readLine();
		int open = 0;
		int close = 0;

		int changes = 0;
		for (int i = 0; i < info.length(); i++) {
			if (info.charAt(i) == '(') {

				open++;
			} else {
				if (open <= close) {
					open++;

					changes++;
				} else {
					close++;
				}
			}

		}
		for (int i = info.length() - 1; i >= 0; i--) {
			if (open == close) {
				out.println(changes);
				break;

			}
			if (info.charAt(i) == '(') {
				changes++;
				open--;
				close++;
			}
		}

		in.close();
		out.close();
	}
}
