
import java.io.*;
import java.util.*;

public class beautifulPair {

	static int beautifulPairs(int[] A, int[] B) {
		int[] unmatched = new int[1001];
		for (int curr : A) {
			unmatched[curr]++;
		}
		int pairs = 0;
		for (int curr : B) {
			if (unmatched[curr] > 0) {
				pairs++;
				unmatched[curr]--;
			}
		}
		return (pairs == A.length) ? pairs - 1 : pairs + 1;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] A = new int[n];
		for (int A_i = 0; A_i < n; A_i++) {
			A[A_i] = in.nextInt();
		}
		int[] B = new int[n];
		for (int B_i = 0; B_i < n; B_i++) {
			B[B_i] = in.nextInt();
		}
		int result = beautifulPairs(A, B);
		System.out.println(result);
		in.close();
	}

}
