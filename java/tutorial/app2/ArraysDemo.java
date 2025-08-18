package app2;

import java.util.Arrays;

public class ArraysDemo {
    public static void main(String[] args) {

        // arrays do not have dynamic size

        // here we are creating a variable that can be assigned an
        // array of ints. The value does not exist yet.
        int[] arr; // arrays can be declared like this
        int arr2[]; // or like this

        // here we assign an array of ints of size 10 to the arr variable we created above
        arr = new int[10];

        System.out.println(arr.length); // 10
        System.out.println(arr[0]); // will be 0 because we only set the size but not the values so it defaults to 0 for each index

        double[] arr3 = new double[10];

        System.out.println(arr3[3]); //0.0

        int[] arr5 = {1, 3, 7, 2}; //array literals. Creates an array of length 4 with the provided values
        System.out.println(arr5[2]); // 7
        System.out.println(arr5.length); // 4

        // array of arrays
        int[][] matrix = {
            {1,2,3},
            {4,5,6}
        };

        System.out.println("matrix length " + matrix.length); // 2
        System.out.println("matrix first array length " + matrix[0].length); // 3

        System.out.println("value of matrix[1][1] " + matrix[1][1]); // 5
        // arrays are reference types
        //int[][] matrix2 = new int[10][0]; // this creates 10 arrays of size 0. Means that it is empty
        int[][] matrix2 = new int[10][]; // this creates 10 arrays without array literals
        System.out.println(matrix2.length); // 10
        System.out.println(matrix2[0]); // null
        matrix2[0] = new int[10]; // we set the first array of arrays to an array of length 10
        System.out.println(matrix2[0]); // address (to string?)
        System.out.println(matrix2[0][0]); // 0
        matrix2[0][0] = 1000;
        System.out.println(matrix2[0][0]); // 1000
        System.out.println(matrix2[0][1]); // 0
        System.out.println(matrix2[1]); // null

        // printing arrays
        // Arrays requires the java.util import at the top or
        // we can put the entire path
        System.out.println(Arrays.toString(arr5)); // [1, 3, 7, 2]
        System.out.println(Arrays.toString(matrix)); // [address, address] (do one at a time)
        System.out.println(java.util.Arrays.toString(matrix[0])); // [1, 2, 3] (using full path in case we didnt import Arrays)

        // can also sort the array first
        Arrays.sort(arr5);
        System.out.println(Arrays.toString(arr5)); // [1, 2, 3, 7]

    }
}
