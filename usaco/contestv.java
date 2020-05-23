import java.io.*;
import java.util.*;

public class contestv {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		while (test-- != 0) {
			int curr = Integer.parseInt(in.readLine());
			System.out.println(Math.abs((((((curr * 63 + 7492) * 235) / 47) - 498) % 100) / 10));
		}
		in.close();
	}
}