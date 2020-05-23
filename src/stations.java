import java.util.Random;

public class stations {

	public static void main(String[] args) {
		double[] chance = new double[101];
		int win = 0;
		chance[0] = 1;
		for (int i = 0; i < 99; i++) {
			System.out.println(chance[i]);

			int poss = 0;
			chance[i + 1] += chance[i] / 2;
			chance[i + 2] += chance[i] / 2;
		}
		System.out.println(chance[99] / (chance[99] + chance[100]));
	}
}
