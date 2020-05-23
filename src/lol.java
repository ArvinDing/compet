import java.util.*;

public class lol {
	public static void main(String[] args) {
		int[] info = new int[] { 1,9,11,15 };
		List<Integer> plus = new ArrayList<Integer>();
		List<Integer> minus = new ArrayList<Integer>();
		for (int i = 0; i < info.length; i++) {
			List<Integer> old = new ArrayList<Integer>(plus);
			for (int a : minus) {
				plus.add(a + info[i]);
			}
			for (int b : old) {
				minus.add(b - info[i]);
			}
			plus.add(info[i]);
			minus.add(-info[i]);
			Collections.sort(plus);
			Collections.sort(minus);

			for (int k = 0; k < plus.size(); k++)
				System.out.print(plus.get(k) + " ");
			System.out.print("|");
			for (int k = 0; k < minus.size(); k++)
				System.out.print(minus.get(k) + " ");
			System.out.println();
		}
	}
}
