import java.io.*;
import java.util.*;

public class help {

	public static void main(String[] args) throws Exception {
		int cnt = 0;
		for (int i = 0; i < 2; i++) {
			for (int i1 = 0; i1 < 2; i1++) {
				for (int i2 = 0; i2 < 2; i2++) {
					for (int i3 = 0; i3 < 2; i3++) {
						for (int i4 = 0; i4 < 2; i4++) {
							for (int i5 = 0; i5 < 2; i5++) {
								if (i == 0 || i1 == 0 || i2 == 0) {
									if (i1 == 0 || i2 == 0 || i3 == 0) {
										if (i2 == 0 || i3 == 0 || i4 == 0) {
											if (i3 == 0 || i4 == 0 || i5 == 0) {
												cnt++;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		System.out.println(cnt);
	}
}
