package save;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class check {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("twofive.in"));
		PrintWriter out = new PrintWriter(new File("twofive.out"));
		String previous = "AA";
		for (int i = 0; i < 10022; i++) {
			String curr = in.readLine();
			if (curr.compareTo(previous) <= 0) {
				for (int z = 0; z < 5; z++) {
					System.out.println(previous.substring(z * 5, z * 5 + 5) + " " + curr.substring(z * 5, z * 5 + 5));
				}
				System.out.println();
			}

			previous = curr;
		}
	}

}
