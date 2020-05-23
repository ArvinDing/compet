
import java.io.*;
import java.util.*;

public class listarray {
	static List<List> constant = getInitialList();
    private static List<List> getInitialList() {
        return Arrays.asList(new List[8]);
    }
	public static void main(String[] args) {
		List<List> imp = new ArrayList<List>();
		int[][] info = new int[800000][8];
		for (int i = 0; i < 800000; i++) {

			Random r = new Random();
			for (int k = 0; k < 8; k++) {
				info[i][k] = r.nextInt(100000);
			}
			List a=generate(info[i]);
		}
		for (int i = 0; i < 800000; i++) {
			List<Integer> temp = imp.get(i);
			int []k=info[i];
			
		}
	}
	 static List generate(int[] temp) {
	        List<List>level = constant;
	        for (int i = 0; i < 7; i ++) {
	            List<List> nl = level.get(temp[i]);
	            if (nl == null) {
	                nl = getInitialList();
	                level.set(temp[i], nl);
	            }
	            level = nl;
	        }
	        return level;
	    }

}
