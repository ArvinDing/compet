
import java.io.*;
import java.util.*;

public class candyI {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		for (int curr = Integer.parseInt(in.readLine()); curr != -1; curr = Integer.parseInt(in.readLine())) {
			List<Integer> info = new ArrayList<Integer>();
			int sum = 0;
			for (int i = 0; i < curr; i++) {
				int candy = Integer.parseInt(in.readLine());
				sum += candy;
				info.add(candy);
			}
			if (sum % info.size() == 0) {
				int average = sum / info.size();
				int ans = 0;
				for (int a : info) {
					ans += Math.abs(average - a);
				}
				System.out.println(ans/2);
			} else {
				System.out.println(-1);
			}
		}
		in.close();

	}

}