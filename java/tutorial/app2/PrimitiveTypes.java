package app2;

public class PrimitiveTypes {
    public static void main(String[] args) {
        byte b = 1;
        short s;
        s = 2;
        int i = 3;
        long l = 4;

        char c = 'a';

        boolean bool = true;

        float f = 1.2F;

        long l2 = 2_000_000_000_000L;

        double d = 1.3;

        var v = 1; // after initialisation it gets int type assigned

        // we can always convert smaller types to larger types, or same size -> widened conversion - this is automatic. 
        int i3 = s; // s is short
        // to convert larger types to smaller -> narrow conversion - we need to set the type when assigning
        b = (byte)i3;

        // this is outside the values byte can hold so it overflows
        byte b2 = (byte)1000;

        System.out.println(b2); // -24

        // careful converting long to double due to how floating point numbers are stored
        long number = 499_999_999_000_000_001L;
        double converted = (double) number;
        System.out.println(number - (long) converted); // 1 expected 0

        // boolean and integers are not compatible types so we cannot do this
        int x = 1;
        while(x) {} // throws the error: incompatible types: int cannot be converted to boolean
        x needs to be a boolean value
    }
}