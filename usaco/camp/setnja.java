
package camp;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class setnja {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String instructions = in.readLine();
		BigInteger a = BigInteger.ONE;
		BigInteger possible=BigInteger.ONE;
		BigInteger two = BigInteger.valueOf(2);
		BigInteger three = BigInteger.valueOf(3);
		BigInteger five = BigInteger.valueOf(5);
		for (int i = 0; i < instructions.length(); i++) {
			char curr = instructions.charAt(i);
			if (curr == 'R') {
				a = a.multiply(two);     
				a = a.add(possible);
			} else if (curr == 'L') {
				a = a.multiply(two);
			} else if (curr == '*') {
				a = a.multiply(five);
				a = a.add(possible);
				possible=possible.multiply(three);
			}
		}
		System.out.println(a); 
		in.close();
	}
}