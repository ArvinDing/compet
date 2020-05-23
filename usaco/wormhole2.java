
/*
ID: dingarv1
LANG: JAVA
TASK: wormhole
*/

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class wormhole2 {
	private static int maxX = 0;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("wormhole.in"));
		int amt = in.nextInt();
		BufferedWriter out = new BufferedWriter(new FileWriter("wormhole.out"));

		List<int[]> result = new ArrayList<int[]>();

		for (int i = 0; i < amt; i++) {
			int[] arry = new int[2];
			arry[0] = in.nextInt();
			arry[1] = in.nextInt();
			if (arry[0] > maxX)
				maxX = arry[0];

			result.add(arry);
		}

		in.close();
		List<List<int[][]>> b = getPairCombinations(result);
//		printArray(b);
		out.write("" + isCycle(result, b));

		out.newLine();
		out.close();

	}

	public static void printArray(List<List<int[][]>> a) {

		for (List<int[][]> twoD : a) {
			for (int[][] oneD : twoD) {
				for (int[] z : oneD) {
					System.out.print(Arrays.toString(z) + ", ");
				}
				System.out.print("* ");
			}
			System.out.println();
		}
	}

	static List<List<int[][]>> getPairCombinations(List<int[]> locations) {
		List<List<int[][]>> result = new ArrayList<List<int[][]>>();
		if (locations.size() == 2) {
			ArrayList<int[][]> comb = new ArrayList<int[][]>();
			comb.add(new int[][] { locations.get(0), locations.get(1) });
			result.add(comb);
			return result;
		}
		List<int[]> firstPair = locations.subList(0, 2);
		List<List<int[][]>> combs = getPairCombinations(locations.subList(2, locations.size()));
		for (List<int[][]> comb : combs) {
			addToResult(result, firstPair, comb);
		}
		// printArray(result);
		for (List<int[][]> comb : combs) {
			List<int[][]> comb_c = new ArrayList<int[][]>(comb);
			for (int pi = 0; pi < comb_c.size(); pi++) {
				int[][] pair = new int[][] { comb_c.get(pi)[0], comb_c.get(pi)[1] };
				comb_c.set(pi, pair);
				for (int i = 0; i < 2; i++) {
					List<int[]> tmpP = new ArrayList<int[]>(firstPair);
					pair[i] = tmpP.set(0, pair[i]);
					for (List<int[][]> cb : combs) {
						if (cb == comb) {
							addToResult(result, tmpP, comb_c);
						} else {
							addToResult(result, tmpP, cb);
						}
					}
					pair = new int[][] { pair[0], pair[1] };
					pair[i] = tmpP.get(0);
					comb_c.set(pi, pair);
					// printArray(result);
				}
			}
		}
		return result;
	}

	private static void addToResult(List<List<int[][]>> result, List<int[]> firstPair, List<int[][]> comb) {
		List<int[][]> clone = new ArrayList<int[][]>(comb);
		clone.add(new int[][] { firstPair.get(0), firstPair.get(1) });
		result.add(clone);
	}

	public static int isCycle(List<int[]> whList, List<List<int[][]>> whCombinations) {

		int cnt = 0;
		for (List<int[][]> oneComb : whCombinations) {
			oneComb: for (int[] startPos : whList) {
				int[] curPos = new int[] { startPos[0], startPos[1] };
				List<int[]> visitedL = new ArrayList<int[]>();
				visitedL.add(startPos);
				while (curPos[0] <= maxX) {
					int[] whEnd = getWHEndPoint(curPos, oneComb);
					if (whEnd != null) {
						visitedL.add(curPos);
						curPos = whEnd;
					}
					curPos[0] = curPos[0] + 1;
					if (visitedPos(curPos, visitedL)) {
						cnt++;
						break oneComb;
					}
				}
			}
		}
		return cnt;

	}

	private static boolean visitedPos(int[] curPos, List<int[]> visitedL) {
		for (int [] one : visitedL) {
			if (samePoint(curPos, one)) {
				return true;
			}
		}
		return false;
	}

	private static int[] getWHEndPoint(int[] curPos, List<int[][]> oneComb) {
		for (int[][] onePair : oneComb) {
			if (samePoint(curPos, onePair[0])) {
				return new int[] { onePair[1][0], onePair[1][1] };
			}
			if (samePoint(curPos, onePair[1])) {
				return new int[] { onePair[0][0], onePair[0][1] };
			}
		}
		return null;
	}

	private static boolean samePoint(int[] pos1, int[] pos2) {
		return pos1[0] == pos2[0] && pos1[1] == pos2[1];
	}
}
