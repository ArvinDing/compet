
public class ah {
	public static void main(String[] args) {
		int cnt = 0;
		for (int i = 0; i <= 2020; i++) {
			int b = calc(i);
			System.out.println(i);
			int c = calc(2020 - i);
			int a = calc(2020);
			if (a == b + c)
				cnt++;
		}
		System.out.println(cnt);
	}

	public static int calc(int num) {
		int sum = 0;
		while (num != 0) {
			num = num / 2;
			sum += num;
		}
		return sum;
	}
}
