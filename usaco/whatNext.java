
import java.io.*;
import java.util.*;

public class whatNext {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken());
			int b = Integer.parseInt(read.nextToken());
			int c = Integer.parseInt(read.nextToken());
			if (a == 0 && b == 0 && c == 0)
				break;
			if (b - a == c - b) {
				System.out.println("AP " + (c + c - b));
			} else {
				System.out.println("GP " + (c * (c / b)));
			}
		}
	}

}