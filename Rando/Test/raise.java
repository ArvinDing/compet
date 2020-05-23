package Test;

public class raise {
	public static void main(String[] args) {
		long c = 1081341203;
		long d = 175225713;
		long n = 1489523719;
		long result = 1;
		long base = c;
		while (d != 0) {
			if (d % 2 == 1) {
				result = result * base % n;
				d--;
			}
			base = base * base % n;
			d = d / 2;
		}
		System.out.println(result);
	}
}
