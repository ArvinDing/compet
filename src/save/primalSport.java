package save;
import java.io.*;
import java.util.*;

public class primalSport {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int twoTurn = Integer.parseInt(in.readLine());
		int copy = twoTurn;
		int biggestPrime = -1;
		for (int i = 2; i < copy; i++) {
			while (twoTurn % i == 0) {
				twoTurn /= i;
				biggestPrime = i;
			}
		}
		int start = copy - biggestPrime + 1;
		int ans = Integer.MAX_VALUE;
		for (int i = start; i < copy; i++) {
			int index = i;
			biggestPrime = -1;
			for (int k = 2; k < i; k++) {
				while (index % k == 0) {
					index /= k;
					biggestPrime = k;
				}
			}
			ans = Math.min(i - biggestPrime + 1, ans);
		}
		System.out.println(Math.min(start, ans));
		in.close();
	}
}
