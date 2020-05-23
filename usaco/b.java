
public class b {
	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE + 1+Integer.MAX_VALUE);

	}

	public static int lastDigitsFactorial(String n, int k) {
		int z = 1;
		int a = 1;
		for (int i = 0; i < k; i++) {
			a *= 10;
		}

		long bee = Long.parseLong(n);
		for (int i = 1; i <= bee; i++) {

			z = (z * i);
			while (z % 10 == 0) {
				z = z / 10;
			}
			z = z % a;
		}
		return z;
	}

}
