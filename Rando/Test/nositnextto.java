package Test;

public class nositnextto {
	static int cnt = 0;

	public static void main(String[] args) {
		recursion(5, 3, -1, "");
		System.out.println(cnt);

	}

	private static void recursion(int boy, int girl, int previous, String a) {
		if (boy == 0 && girl == 0) {
			cnt++;
			System.out.println(a);
			return;
		}
		if (previous == 1) {
			if (boy == 0)
				return;
			recursion(boy - 1, girl, -1, a + 'b');

		} else {
			if (boy != 0)
				recursion(boy - 1, girl, -1, a + 'b');
			if (girl != 0)
				recursion(boy, girl - 1, 1, a + 'g');
		}
	}
}
