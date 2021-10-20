package learning.basics.test;

public class ArrayTest {
    public static void main(String[] args) {
        String[] arr = new String[3];
        arr[1] = "";
        for (var s : arr)
            System.out.println(s);
    }
}
