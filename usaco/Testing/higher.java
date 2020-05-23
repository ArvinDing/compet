package Testing;

import java.util.PriorityQueue;
import java.util.TreeSet;

public class higher {
	public static void main(String[] args) {
		TreeSet<Custom> test = new TreeSet<Custom>();
		test.add(new Custom(1, true));
		System.out.println(test.higher(new Custom(0, true))==null);
	}

	private static class Custom implements Comparable<Custom> {
		int x;
		boolean random;

		private Custom(int x, boolean random) {
			this.x = x;
			this.random = random;
		}

		@Override
		public int compareTo(Custom o) {
			return x - o.x;
		}

	}

}
