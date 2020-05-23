
import java.io.*;
import java.util.*;

public class testMake {
	public static void main(String[] args) throws Exception {
		PrintWriter out = new PrintWriter(new File("input"));
		int n = 60000;
		out.println(n);
		Random rand = new Random();
		int[] sieve = new int[1000001];
		for (int i = 1; i <= 1000000; i++) {
			sieve[i] = i;
		}
		for (int i = 2; i <= 1000000; i++) {
			if (sieve[i] == i)
				for (int k = i * i; k <= 1000000; k += i) {
					if (k < 0)
						break;
					if (sieve[k] == k)
						sieve[k] = i;
				}
		}
		int cnt = 0;
		int currIdx = 2;
		while (cnt < n) {
			if (sieve[currIdx] == currIdx) {
				out.print(currIdx + " ");
				cnt++;
			}
			currIdx++;
		}
		out.println();
		out.close();
	}
}
