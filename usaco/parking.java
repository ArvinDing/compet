import java.io.*;
import java.util.*;

public class parking {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int i = 0; i < test; i++) {
			int stores = Integer.parseInt(in.readLine());
			StringTokenizer read = new StringTokenizer(in.readLine());
			int[] info = new int[stores];
			for (int k = 0; k < stores; k++) {
				info[k] = Integer.parseInt(read.nextToken());
			}
			Arrays.sort(info);
			int sum = 0;
			for(int k=1;k<stores;k++) {
				sum+=info[k]-info[k-1];
			}
			System.out.println(2*sum);

		}
		in.close();
	}
}