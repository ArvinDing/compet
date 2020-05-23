
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class gcd {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(in.readLine());
		int b = Integer.parseInt(in.readLine());
		int c = Integer.parseInt(in.readLine());
		System.out.println(fGcd(fGcd(a, b), fGcd(b, c)));
		in.close();
	}

	static int fGcd(int a, int b) {
		if (a < b) {
			int old = b;
			b = a;
			a = old;
		} 
		if (b == 0)
			return a;
		return fGcd(a % b, b);
	}
}
