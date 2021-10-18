package learning.sorting.algorithms;

public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {12, 6, 17, 19, 24, 1, 3, 0, -3};

        selectionSort(arr);
        System.out.println("Sorted array: ");
        for (var val : arr)
            System.out.print(val + " ");
    }

    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int max = Integer.MIN_VALUE;
            int maxAt = 0;
            for (int j = 0; j < arr.length - i; j++)
                if (max < arr[j]) {
                    max = arr[j];
                    maxAt = j;
                }
            swap(arr.length - 1 - i, maxAt, arr);
        }
    }

    private static void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
