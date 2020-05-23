import java.io.*;
import java.util.*;

public class nlogonia {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int queries = Integer.parseInt(in.readLine());
		while (queries != 0) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			for (int i = 0; i < queries; i++) {
				read = new StringTokenizer(in.readLine());
				int currX = Integer.parseInt(read.nextToken());
				int currY = Integer.parseInt(read.nextToken());
				if (currX == x || currY == y) {
					System.out.println("divisa");
				} else if (currX > x) {
					if (currY > y) {
						System.out.println("NE");
					} else {
						System.out.println("SE");
					}
				} else {
					if (currY > y) {
						System.out.println("NO");
					} else {
						System.out.println("SO");
					}
				}
			}
			queries = Integer.parseInt(in.readLine());
		}
		in.close();
	}
}