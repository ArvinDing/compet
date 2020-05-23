
import java.io.*;
import java.util.*;

public class fashion {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int z = 0; z < test; z++) {
			int participants = Integer.parseInt(in.readLine());
			StringTokenizer read = new StringTokenizer(in.readLine());
			List<Integer> male = new ArrayList<Integer>();
			for (int i = 0; i < participants; i++) {
				male.add(Integer.parseInt(read.nextToken()));
			}
			 read = new StringTokenizer(in.readLine());
			List<Integer> female = new ArrayList<Integer>();
			for (int i = 0; i < participants; i++) {
				female.add(Integer.parseInt(read.nextToken()));
			}
			Collections.sort(male);
			Collections.sort(female);
			Iterator<Integer> itr = male.iterator();
			Iterator<Integer> itr1 = female.iterator();
			int sum = 0;
			while (itr.hasNext()) {
				sum += (itr.next() * itr1.next());
			}
			System.out.println(sum);
		}
		in.close();

	}
}