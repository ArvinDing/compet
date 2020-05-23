import java.io.IOException;

public class program {
	public static void main(String[] args) throws IOException {
		int cnt=0;
		for(int i=0;i<=2001;i++){
			if((i%3==0||i%4==0)&&i%5!=0){
				cnt ++;	}
		}
		System.out.print(cnt);
	}
}
