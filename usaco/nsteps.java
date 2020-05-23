
import java.io.*;
import java.util.*;

public class nsteps {
	public static void main(String args[]) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int i = 0; i < test; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			if (x == y) {
				System.out.println(2 * x - (x % 2));
			} else if (x - 2 == y) {
				System.out.println(2 * x - (2 + x % 2));
			}else {
				System.out.println("No Number");
			}
		}
		in.close();
	}
}
