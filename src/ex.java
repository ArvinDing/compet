public class ex {
     
    public static void recursivePrint(int num) {
        System.out.println("Number: " + num);
         
        if(num == 0)
            return;
        else
            recursivePrint(++num);
    }
     
    public static void main(String[] args) {
       recursivePrint(1);
    }
}
