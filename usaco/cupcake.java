
import java.io.*;
import java.util.*;

public class cupcake {

	static long marcsCakewalk(Integer[] calorie) {
		Arrays.sort(calorie, Collections.reverseOrder());
		long sum = 0;
		long mult = 1;
		for (Integer a : calorie) {
			sum += mult * a;
			mult *= 2;
		}
		return sum;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Integer[] calorie = new Integer[n];
		for (int calorie_i = 0; calorie_i < n; calorie_i++) {
			calorie[calorie_i] = in.nextInt();
		}
		long result = marcsCakewalk(calorie);
		System.out.println(result);
		in.close();
	}

}
