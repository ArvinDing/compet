
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class cow {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cow.in"));
		PrintWriter out = new PrintWriter(new File("cow.out"));

		int length = Integer.parseInt(in.readLine());
		char[] info = in.readLine().toCharArray();
		long cowCount = 0;
		long coCount = 0;
		long cCount = 0;
		for (int i = 0; i < length; i++) {
			if (info[i] == 'C') {
				cCount++;
			} else if (info[i] == 'O') {
				coCount += cCount;
			} else {
				cowCount += coCount;
			}
		}

		out.println(cowCount);
		in.close();
		out.close();
	}
}