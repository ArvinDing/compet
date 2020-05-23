
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class cruise {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cruise.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cruise.out")));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int ports = Integer.parseInt(read.nextToken());
		int directL = Integer.parseInt(read.nextToken());
		int repeats = Integer.parseInt(read.nextToken());
		int[][] info = new int[ports][2];
		for (int i = 0; i < ports; i++) {
			read = new StringTokenizer(in.readLine());
			info[i][0] = Integer.parseInt(read.nextToken()) - 1;
			info[i][1] = Integer.parseInt(read.nextToken()) - 1;
		}
		int[] easier = new int[ports];
		int[] directions = new int[directL];
		read = new StringTokenizer(in.readLine());
		for (int i = 0; i < directL; i++) {	
			directions[i] = (read.nextToken().equals("L")) ? 0 : 1;
		}
		for (int i = 0; i < ports; i++) {
			int curr = i;
			for (int k = 0; k < directL; k++) {
				curr = info[curr][directions[k]];
			}
			easier[i] = curr;
		}
		int curr = 0;
		for (int i = 0; i < repeats; i++) {
			curr = easier[curr];
		}
		out.println(curr+1);
		in.close();
		out.close();
	}
}