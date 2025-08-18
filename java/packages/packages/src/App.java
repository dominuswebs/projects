import add.Addition;
import sub.Subtraction;

public class App {
    public static void main(String[] args)  {
        
        Addition a = new Addition();

        Subtraction s = new Subtraction();

        a.operation(4, 5);

        s.operation(10, 9);

    }
}
