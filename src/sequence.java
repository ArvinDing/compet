
public class sequence {

	public static void main(String[] args) {
		int[] temp = new int[10];
		for (int i = 5; i < 10; i++) {
			temp[0] = i;
			recursion(temp, 1);
		}
		System.out.println(counter);
	}

	private static int counter = 0;

	public static void recursion(int[] temp, int idx) {
		if (idx == 10) {
			if (temp[9] == 3 * temp[0] && temp[1] + temp[7] == 2 * temp[4])
				counter++;
			return;
		}
		temp[idx] = temp[idx - 1] + 1;
		recursion(temp, idx + 1);

		temp[idx] = temp[idx - 1] + 2;
		recursion(temp, idx + 1);

	}
}
