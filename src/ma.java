
public class ma {

	public static void main(String[] args) {
		int even=0;
		for (int n = 2; n < 993; n++) {
			int temp=(int)func(n);
			if(temp%2==0)even++;
			System.out.println(temp);
		}
		System.out.println("Even:"+ even);
	}

	public static double func(int n) {
		return ((double) (1D / 48)) * (2 * n * n * n + 3 * Math.pow(-1, n) * (n + 4) + 12 * n * n + 25 * n + 24
				+ 12 * Math.cos(n * Math.PI / 2));

	}
}
