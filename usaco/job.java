
/*
ID: dingarv1
LANG: JAVA
TASK: job
*/

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class job {
	private static int jobs;

	public static void main(String[] args) throws Exception {

		Scanner in = new Scanner(new File("job.in"));
		PrintWriter out = new PrintWriter(new File("job.out"));
		jobs = in.nextInt();
		int alength = in.nextInt();
		int blength = in.nextInt();
		List<Integer> a = new ArrayList<Integer>(), b = new ArrayList<Integer>();
		for (int i = 0; i < alength; i++) {
			a.add(in.nextInt());
		}
		for (int i = 0; i < blength; i++) {
			b.add(in.nextInt());
		}
		Collections.sort(a);
		Collections.sort(b);
		List<Integer> aInfo = solve(a);
		List<Integer> bInfo = solve(b);

		int big=Integer.MIN_VALUE;
		
		for (int i = 0; i < jobs; i++) {
			if(big<(aInfo.get(i)+bInfo.get((bInfo.size() - 1) - i))){
				big=(aInfo.get(i)+bInfo.get((bInfo.size() - 1) - i));
			}
		}
		out.println(aInfo.get(aInfo.size()-1) + " " + big);
		in.close();
		out.close();
	}

	public static List<Integer> solve(List<Integer> a) {
		List<Integer> answer = new ArrayList<Integer>();
		int[] tick = new int[100000];
		for (int i = 1; i <= jobs; i++) {
			for (int k : a) {
				tick[k * i]++;
			}
		}
		
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			if (tick[i] != 0) {
			
				for(int z=0;z<tick[i];z++){
				answer.add(i);
				}
			}
			if (answer.size() >= jobs) {
				break;
			}
		}
		return answer.subList(0, jobs);
	}

}
