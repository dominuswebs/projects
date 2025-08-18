public class ConditionalTest {
    public static void main(String[] args) {
        /* 
        int x = 1;
        while(x) {
            System.out.println("Does not work. Compiler throws an error.");
        }
        */

        boolean x = true;
        while (x) {
            System.out.println("Works");
            x = false;
        }
    }
}
