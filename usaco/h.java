
public class h {
	public static void main(String[] args) {

		System.out.println(maxBlock("xyzz"));
		System.out.println(maxBlock("abbCCCddBBBxx"));
		System.out.println(maxBlock(""));

	}

	public static int maxBlock(String str) {
		if(str.length()==0)return 0;
		int counter = 1;
		int biggest = 1;

		for (int i = 1; i < str.length(); i++) {
		
			if (str.charAt(i) == str.charAt(i - 1)) {
				counter++;
				if (counter > biggest) {
					biggest = counter;
				}
				
			}else{
				
				counter = 1;
			}
			
		}
		return biggest;
	}
}
