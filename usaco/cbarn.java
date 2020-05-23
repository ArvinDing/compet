
import java.io.*;
import java.util.*;

public class cbarn {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cbarn.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
		int rooms = Integer.parseInt(in.readLine());
		int[] info = new int[rooms];
		for (int i = 0; i < rooms; i++) {
			info[i] = Integer.parseInt(in.readLine());
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < rooms; i++) {
			int curr = 0;
			for (int k = 0; k < rooms; k++) {
				curr += info[k] * ((rooms - (i - k)) % rooms);
			}
			min = Math.min(min, curr);
		}
		out.println(min);
		out.close();
		in.close();
	}

}