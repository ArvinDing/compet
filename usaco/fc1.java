import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TreeMap;

/*
ID: six.sal2
LANG: JAVA
TASK: fc
 */
public class fc1 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("fc.in"));
		PrintWriter out = new PrintWriter(new FileWriter("fc.out"));
		int N = Integer.parseInt(in.readLine());
		double [][] points = new double[N][2];
		for (int i = 0; i < N; i ++) {
			String [] one = in.readLine().split(" ");
			points[i][0] = Double.parseDouble(one[0]);
			points[i][1] = Double.parseDouble(one[1]);
		}
		double [] center = new double[2];
		for (double [] one : points) {
			center[0] += one[0];
			center[1] += one[1];
		}
		center[0] /= N;
		center[1] /= N;
		TreeMap<Double, double []> pointMap = new TreeMap<Double, double []>();
		for (double [] one : points) {
			pointMap.put(Math.atan2(one[1] - center[1], one[0] - center[0]), one);
	
		}
		ArrayList<double[]> pArray = new ArrayList<double[]>();
		pArray.addAll(pointMap.values());
		for(double [ ]a:pArray){
			System.out.println(a[0]+" "+a[1]);
		}
		int len = pArray.size();
		boolean done = false;
		while (!done) {
			done = true;
			while (crossProductSign(pArray.get(len-1), pArray.get(0), pArray.get(1))) {
				pArray.remove(0);
				len --;
				done = false;
			}
			for (int i = 1; i < len-1; i ++) {
				while (i < len-1 && crossProductSign(pArray.get(i-1), pArray.get(i), pArray.get(i+1))) {
					pArray.remove(i);
					len --;
					done = false;
				}
			}
			while (crossProductSign(pArray.get(len-2), pArray.get(len-1), pArray.get(0))) {
				pArray.remove(len-1);
				len --;
				done = false;
			}
		}
		for (int i = 0; i < pArray.size() ; i ++) {
			double [] p1 = pArray.get(i);
			
		}
		double rtn = 0;
		for (int i = 0; i < pArray.size() - 1; i ++) {
			double [] p1 = pArray.get(i), p2 = pArray.get(i + 1);
			rtn += Math.sqrt((p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]));
		}
		double [] p1 = pArray.get(pArray.size() - 1), p2 = pArray.get(0);
		DecimalFormat df = new DecimalFormat("0.00");
		out.println(df.format(rtn + Math.sqrt((p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]))));
		out.close();
		in.close();
	}

	private static boolean crossProductSign(double[] fs1, double[] fs2, double[] fs3) {
		double dx1 = fs1[0] - fs2[0], dy1 = fs1[1] - fs2[1], 
				dx2 = fs3[0] - fs2[0], dy2 = fs3[1] - fs2[1];
		return dx1*dy2 - dx2*dy1 > 0;
	}
}
