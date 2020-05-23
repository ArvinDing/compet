import java.io.*;
import java.util.*;

public class tidyNumbers {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("tidyNumbers.in"));
		PrintWriter out = new PrintWriter(new File("tidyNumbers.out"));
		int lines = Integer.parseInt(in.readLine());
		 for (int i = 1; i <= lines; i++) {
			long input=Long.parseLong(in.readLine());
			lowestTidy(input);
		}
		in.close();
		out.close();
	}

	private static long lowestTidy(long input) {
		return 0;
	}
}