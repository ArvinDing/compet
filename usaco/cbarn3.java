
import java.io.*;
import java.util.*;

public class cbarn3 {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cbarn.in"));
		PrintWriter out = new PrintWriter(new File("cbarn.out"));
		int n = Integer.parseInt(in.readLine());
		int[] info = new int[n];
		int[] real = new int[n];
		int index = 0;
		for (int i = 0; i < info.length; i++) {
			info[i] = Integer.parseInt(in.readLine());
			for (int k = 0; k < info[i]; k++) {
				real[index] = i;
				index++;
			}
		}
		int extra = 0;
		for (int i = 0; i < info.length; i++) {
			extra += info[i];
			info[i] = 0;
			if (extra != 0) {
				extra--;
				info[i]++;
			}
		}
		long curr = 0;
		int start = extra;
		for (int i = 0; i < info.length; i++) {
			int target = (start + i) % n;
			int distance = target - real[i];
			if (target < real[i]) {
				distance += n;
			}
			curr += (distance) * (distance);
		}
		out.println(curr);
		out.close();
		in.close();
	}

}