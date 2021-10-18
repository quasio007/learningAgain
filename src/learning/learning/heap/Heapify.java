package learning.heap;

public class Heapify {

    public void heapify(int parentIndex, int[] arr) {
        int minAt = parentIndex;
        int leftChild = 2 * parentIndex + 1;
        int rightChild = 2 * parentIndex + 2;

        if (leftChild < arr.length && arr[minAt] > arr[leftChild])
            minAt = leftChild;

        if (rightChild < arr.length && arr[minAt] > arr[rightChild])
            minAt = rightChild;

        if (minAt != parentIndex) {
            swap(parentIndex, minAt, arr);
            heapify(minAt, arr);
        }
    }

    private void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        Heapify h = new Heapify();
        int[] arr = {12, 6, 17, 19, 24, 1, 3, 0,-3};
        for (int i = (arr.length - 2) / 2; i >= 0; i--) {
            h.heapify(i, arr);
        }

        for (var val : arr)
            System.out.println(val);
    }
}
