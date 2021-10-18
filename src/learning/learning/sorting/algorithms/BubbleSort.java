package learning.sorting.algorithms;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {12, 6, 17, 19, 24, 1, 3, 0, -3};
        bubbleSort(arr);
        System.out.println("Sorted array: ");
        for (var val : arr)
            System.out.print(val + " ");
    }

    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            for (int j = 1; j < arr.length - i; j++)
                if (arr[j - 1] > arr[j])
                    swap(j, j - 1, arr);
    }

    private static void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
