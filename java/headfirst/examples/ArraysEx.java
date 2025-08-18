import java.util.ArrayList;

public class ArraysEx {

    public static void main(String[] args) {

        int x = 5;
        // if no values are passed the default is 0
        int numbers[] = new int[5];

        numbers[1] = x;

        numbers[3] = 4;

        System.out.println("Using regular array");

        for(int i = 0; i < numbers.length; i++) {
            System.out.println("Index " + i + " has the value " + numbers[i]);
        }

        int newNumbers[] = new int[]{2, 3, 4, 7};
        
        for(int i = 0; i < newNumbers.length; i++) {
            System.out.println("New Numbers array Index " + i + " has the value " + newNumbers[i]);
        }

        System.out.println("Using ArrayList");

        ArrayList<Integer> dynamicArray = new ArrayList<Integer>();

        Integer z = 1;

        dynamicArray.add(z);

        dynamicArray.add(2);

        for(int i = 0; i < dynamicArray.size(); i++) {
            System.out.println("Index " + i + " has the value " + dynamicArray.get(i));
        }

        boolean[] boolArray = new boolean[5];

        boolArray[2] = true;

        for(int i = 0; i < boolArray.length; i++) {
            System.out.println("Index " + i + " has the value " + boolArray[i]);
        }

    }
    
}