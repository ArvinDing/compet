
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class angry {
	private static int[] haybales;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("angry.in"));
		PrintWriter out = new PrintWriter(new File("angry.out"));
		int cnt = Integer.parseInt(in.readLine());
		haybales = new int[cnt];
		for (int i = 0; i < cnt; i++) {
			haybales[i] = Integer.parseInt(in.readLine());
		}
		Arrays.sort(haybales);
		int max = 0;
		for (int i = 0; i < haybales.length; i++) {
			int curr = haybalesCnt(i);
	//		System.out.println(curr);
			max = Math.max(max, curr);
		}
		out.println(max);
		out.close();
		in.close();
	}
	private static int haybalesCnt(int index) {
		int answer = 0;
		int eRadius = 1;
		int i = index;
		while (i < haybales.length) {
			int copy = i;
			while (true) {
				if (copy <= haybales.length - 1 && haybales[copy] <= haybales[i] + eRadius) {
					copy++;
				} else {
					copy--;
					break;
				}
			}
			if (copy == i)
				break;
			answer += copy - i;
			i = copy;
			eRadius++;
		}		
		eRadius = 1;
		i = index;
		while (i >= 0) {
			int copy = i;
			while (true) {
				if (copy >= 0 && haybales[copy] >= haybales[i] - eRadius) {
					copy--;
				} else {
					copy++;
					break;
				}
			}
			if (copy == i)
				break;
			answer += i-copy;
			i = copy;
			eRadius++;
	
		}
		return answer + 1;
	}
}