import java.util.ArrayList;
import java.util.Arrays;

public class milk2 {

	static long sum(ArrayList<Long> list) {
		long sum = 0;
		for (long i : list) {
			sum += i;
		}
		return sum;
	}

	public static void main(String[] args) {
		ArrayList<Long> numbers = new ArrayList<Long>(Arrays.asList(0l, 1l, 1l));
		int n = 4;;
		for (int i = 0; i < n - 1; i++) {
			numbers.set(i % 3, sum(numbers));
		}
		System.out.println(sum(numbers));
	}
}