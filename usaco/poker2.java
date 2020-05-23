
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class poker2 {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("poker.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("poker.out")));
		int types = Integer.parseInt(in.readLine());
		long previous = 0;
		long curr = 0;
		long sum = 0;
		for (int i = 0; i < types; i++) {
			curr = Integer.parseInt(in.readLine());
			sum += Math.abs(curr - previous);
		
			previous = curr;
		}
		sum += curr;
		out.print(sum / 2);
		in.close();
		out.close();
	}
}