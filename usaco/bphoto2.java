import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class bphoto2 {
	private static int[] info;
	private static int sum;
	private static int Oleft;
	private static int Oright;
	private static int[] heights;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new FileReader("bphoto.in"));
		PrintWriter out = new PrintWriter(new File("bphoto.out"));
		int size = Integer.parseInt(in.readLine());
		sum = 0;
		info=new int [size];
		for (int i = 0; i < size; i++) {
			int temp = Integer.parseInt(in.readLine());
			info[i] = temp;
			sum += temp;

		}

	
		int unbalanced = 0;
		for (int i=0;i<info.length;i++) {
			if (!checkBalance(i,info[i])) {
				unbalanced++;
			
			}
		}
		out.println(unbalanced);
		out.close();
		in.close();

	}

	private static boolean checkBalance(int index,int value) {
		int sum1=0;
		int sum2=0;
		for(int i=0;i<index;i++){
			if(value<info[i]){
				sum1++;
			}
		}
		for(int i=index;i<info.length;i++){
			if(value<info[i]){
				sum2++;
				if(sum2>2*sum1){
					return false;
				}
				
			}
		}
		if (sum2 * 2 < sum1) {
			return false;
		}
		return true;

	}
}