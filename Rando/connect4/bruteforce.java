package connect4;

import java.io.*;
import java.util.*;

public class bruteforce {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int movesAhead = 3;
		int moves = 32;
		int[] height = new int[7];
		int[][] board = new int[7][6];
		while (moves > 0) {
			int move = Integer.parseInt(in.readLine());
			board[move][height[move]] = 1;
			height[move]++;
			int best = Integer.MIN_VALUE;
			int bestMove = -1;
			for (int i = 0; i < 7; i++) {
				if (height[i] == 6)
					continue;
				board[i][height[i]] = -1;
				int curr = value(board, height, movesAhead, 1, moves);
				System.out.print(curr + " ");
				if (curr > best) {
					best = curr;
					bestMove = i;
				} else if (curr == best) {
					if (Math.abs(3 - i) < Math.abs(3 - bestMove)) {

						bestMove = i;
					}
				}
				board[i][height[i]] = 0;
			}
			System.out.println();
			board[bestMove][height[bestMove]] = -1;
			height[bestMove]++;
			System.out.println("Computer moves: " + bestMove);

		}
	}

	private static int[] cnt(int[][] board, int turn) {
		int[] lengths = new int[5];
		for (int i = 0; i < 7; i++) {
			for (int k = 0; k < 6; k++) {
				int hLength = 0;
				for (int idx = 0; idx < 4; idx++) {
					if (i + idx >= 7)
						break;
					if (board[i + idx][k] != turn)
						break;
					else
						hLength++;

				}
				int vLength = 0;
				for (int idx = 0; idx < 4; idx++) {
					if (k + idx >= 6)
						break;
					if (board[i][k + idx] != turn)
						break;
					else
						vLength++;

				}
				int dLength = 0;
				for (int idx = 0; idx < 4; idx++) {
					if (i + idx >= 7 || k + idx >= 6)
						break;
					if (board[i + idx][k + idx] != turn)
						break;
					else
						dLength++;

				}
				lengths[hLength]++;
				lengths[vLength]++;
				lengths[dLength]++;
			}
		}
		return lengths;
	}

	private static int value(int[][] board, int[] height, int levelsDeep, int turn, int moves) {
		int[] computer = cnt(board, 1);
		int[] player = cnt(board, -1);
		if (levelsDeep == 0) {
			if (computer[4] > 0)
				return 1000;
			if (player[4] > 0)
				return -1000;
			return computer[3] * 10 - player[3] * 10 + computer[2] - player[2];
		}
		if (turn == 1) {
			if (computer[4] > 0)
				return 1000;
		} else {
			if (player[4] > 0)
				return -1000;
		}
		if (moves < 0)
			return 0;
		if (turn == 1) {
			int best = Integer.MIN_VALUE;
			for (int i = 0; i < 7; i++) {
				if (height[i] == 6)
					continue;
				board[i][height[i]] = 1;
				height[i]++;
				int curr = value(board, height, levelsDeep - 1, -1, moves - 1);
				height[i]--;
				board[i][height[i]] = 0;
				if (curr > best) {
					best = curr;
				}
				return best;
			}
		} else {
			int worst = Integer.MAX_VALUE;
			for (int i = 0; i < 7; i++) {
				if (height[i] == 6)
					continue;
				board[i][height[i]] = -1;
				height[i]++;
				int curr = value(board, height, levelsDeep - 1, 1, moves - 1);
				height[i]--;
				board[i][height[i]] = 0;
				if (curr < worst) {
					worst = curr;
				}
			}
			return worst;
		}
		return 0;

	}
}
