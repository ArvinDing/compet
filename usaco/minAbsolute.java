
import java.io.*;
import java.util.*;

public class minAbsolute {

	static int minimumAbsoluteDifference(int n, int[] arr) {
		Arrays.sort(arr);
		int smallest = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length - 1; i++) {
			smallest = Math.min(arr[i + 1] - arr[i], smallest);
		}
		return smallest;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] arr = new int[n];
		for (int arr_i = 0; arr_i < n; arr_i++) {
			arr[arr_i] = in.nextInt();
		}
		int result = minimumAbsoluteDifference(n, arr);
		System.out.println(result);
		in.close();
	}

}
