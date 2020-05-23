
public class calc {
	static boolean[] a;
	static boolean[] b;

	public static void main(String[] args) {
		a = new boolean[10];
		b = new boolean[10];
		ways = 0;
		recur(0);
		System.out.println(ways);
	}

	static long ways;

	public static  void recur(int idx) {
		if (idx == 10) {
			ways++;
			return;
		}
		recur(idx + 1);
		if (!a[idx]) {
			a[idx] = true;
			recur(idx + 1);
			a[idx] = false;
		}
		if (!b[idx]) {
			b[idx] = true;
			recur(idx + 1);
			b[idx] = false;
		}
	}

}
