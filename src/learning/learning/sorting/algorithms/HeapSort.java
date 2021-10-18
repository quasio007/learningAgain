package learning.sorting.algorithms;

public class HeapSort {
    public void heapSort(int[] arr) {
//        Step1: Heapify the given array: O(N)
        for (int i = (arr.length - 2) / 2; i >= 0; i--) {
            heapify(i, arr, arr.length);
        }


        int[] temp = new int[arr.length];
//        Step 2 : Remove and add to new array  O(NlogN)
        for (int i = 0; i < arr.length; i++) {
            temp[i] = remove(arr, arr.length - i);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = temp[i];
        }
    }

    private void heapify(int parentIndex, int[] arr, int size) {
        int minAt = parentIndex;
        int leftChild = 2 * parentIndex + 1;
        int rightChild = 2 * parentIndex + 2;

        if (leftChild < size && arr[minAt] > arr[leftChild])
            minAt = leftChild;

        if (rightChild < size && arr[minAt] > arr[rightChild])
            minAt = rightChild;

        if (minAt != parentIndex) {
            swap(parentIndex, minAt, arr);
            heapify(minAt, arr, size);
        }
    }

    private void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private Integer remove(int[] arr, int size) {
        if (size == 0) return null;

//        Step 1 : Replace last element with first element
        swap(0, size - 1, arr);

        int element = arr[size - 1];
        heapify(0, arr, size - 1);
        return element;
    }


    public static void main(String[] args) {
        HeapSort hs = new HeapSort();
        int[] arr = {12, 6, 17, 19, 24, 1, 3, 0, -3};
        hs.heapSort(arr);

        System.out.println("Sorted array: ");
        for (var val : arr)
            System.out.print(val + " ");
    }

}
