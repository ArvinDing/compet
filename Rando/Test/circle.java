package Test;

public class circle {
	static boolean[][] info = new boolean[2 * 45][2 * 45];

	public static void main(String[] args) {
		int r = 43;
		double pi = Math.PI;
		for (int degree = 0; degree < 360; degree++) {
			int x = (44 + (int) Math.floor(r * Math.cos((double)degree / 360 * 2 * pi)));
			int y = (44 + (int) Math.floor(r * Math.sin((double)degree / 360 * 2 * pi)));
			info[x][y] = true;
		}
		info[44][44]=true;
		info[44][45]=true;
		info[45][44]=true;
		info[45][45]=true;

		for (int i = 0; i < 2 * 44; i++) {
			for (int k = 0; k < 2 * 44; k++) {
				
				System.out.print(((info[i][k])?'|':'.'));
			}
			System.out.println();
		}
	}
}
