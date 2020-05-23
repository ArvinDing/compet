import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class fibonacciMod {

	static BigInteger fibonacciModified(int t1, int t2, int n) {
		BigInteger back2 = BigInteger.valueOf(t1);
		BigInteger back1 = BigInteger.valueOf(t2);
		int curr = 3;
		while (curr < n) {
			BigInteger current = back2.add((back1.multiply(back1)));
			back2 = back1;
			back1 = current;
			curr++;
		}
		return back2.add((back1.multiply(back1)));

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t1 = in.nextInt();
		int t2 = in.nextInt();
		int n = in.nextInt();
		BigInteger result = fibonacciModified(t1, t2, n);
		System.out.println(result);
		in.close();
	}
}
