
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class smallFact {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int lines = Integer.parseInt(in.readLine());
		for (int i = 0; i < lines; i++) {
			int curr = Integer.parseInt(in.readLine());
			BigInteger cheat = BigInteger.ONE;
			for (int z = 2; z <= curr; z++) {
				cheat = cheat.multiply(BigInteger.valueOf(z));
			}
			System.out.println(cheat);
		}
		in.close();
	}
}