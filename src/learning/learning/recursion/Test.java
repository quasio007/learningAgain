package learning.recursion;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        Integer integer = 3;
        int[] intArr = new int[10];
        String[] srtArr = new String[10];
        String str = "A";
        testMethod(list, integer, intArr, srtArr, str);
        System.out.println(list);
    }

    public static void testMethod(List<String> list, Integer integer, int[] intArr, String[] srtArr, String str) {
        if (integer >= 9) return;
//        For each Recursive call:
        integer++;
        list.add("Integer value - " + integer);
        intArr[integer] = integer;
        srtArr[integer] = "integer : " + integer;
        str += integer;
        System.out.println("\n");
        System.out.println("list: " + list);
        System.out.println("integer: " + integer);
        Arrays.stream(intArr).sequential().forEach(System.out::print);
        System.out.println();
        Stream.of(intArr," ",3).forEach(System.out::print);
        System.out.println();
        Arrays.stream(srtArr).sequential().forEach(System.out::print);
        System.out.println("str: " + str);
        System.out.println("\n");
        testMethod(list, integer, intArr, srtArr, str);

    }
}
