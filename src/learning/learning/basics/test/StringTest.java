package learning.basics.test;

public class StringTest {
    public static void main(String[] args) {
        String str = "Mohit";
        System.out.println(str.substring(1, 3)); //oh


        String s1 = "Hi";
        String s2 = "Hi";
        String s3 = new String("Hi");


        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1.equals(s3));
        System.out.println();


        Integer i1 = 7;
        Integer i2 = 7;
        Integer i3 = new Integer(7);
        System.out.println(i1 == i2);
        System.out.println(i1 == i3);
        System.out.println(i1.equals(i3));

    }
}
