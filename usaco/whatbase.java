
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class whatbase {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("whatbase.in"));
		PrintWriter out = new PrintWriter(new File("whatbase.out"));
		int time = Integer.parseInt(in.readLine());
		for (int i = 0; i < time; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			out.println(solve(Integer.parseInt(read.nextToken()), Integer.parseInt(read.nextToken())));
		}

		out.close();
		in.close();
	}

	private static String solve(int one, int two) {
		int base1 = 10;
		int base2 = 10;
		while (true) {
			int num1 = base(one, base1);
			int num2 = base(two, base2);
			if (num1 == num2) {
				break;
			}
			if (num1 < num2) {
				base1++;
			} else {
				base2++;
			}
		}
		return base1 + " " + base2;
	}

	private static int base(int threeDigits, int base) {
		int first = (threeDigits / 100) % 10;
		int second = (threeDigits / 10) % 10;
		int third = threeDigits % 10;
		return base * base * first + base * second + third;
	}
}