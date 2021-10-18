package learning.heap;

import java.util.ArrayList;
import java.util.Iterator;

public class CustomHeap implements Iterable<Integer>{
    private final ArrayList<Integer> data;

    public CustomHeap(int size) {
        data = new ArrayList(size);
    }

    /**
     * Offer or add method
     *
     * @return
     */
    public boolean offer(int element) {
        try {
//        Step1 : Add at the end of list
            data.add(element);
//        Step 2: Up Heapify the heap/arraylist
            upheapify(data.size() - 1);

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private void upheapify(int childIndex) {
        if (childIndex <= 0) return;

        int parentIndex = (childIndex - 1) / 2;
        if (data.get(parentIndex) > data.get(childIndex)) {
            swap(parentIndex, childIndex);
            upheapify(parentIndex);
        }
    }

    /**
     * Poll or remove
     *
     * @return
     */
    public Integer poll() {
        if (data.size() == 0) return null;

//        Step 1 : Replace last element with first element
        swap(0, data.size() - 1);
        int element = data.remove(data.size() - 1);
        downheapify(0, getSize());
        return element;
    }

    private void downheapify(int parentIndex, int size) {
        int minAt = parentIndex;
        int leftChild = 2 * parentIndex + 1;
        int rightChild = 2 * parentIndex + 2;

        if (leftChild < size && data.get(minAt) > data.get(leftChild))
            minAt = leftChild;

        if (rightChild < size && data.get(minAt) > data.get(rightChild))
            minAt = rightChild;

        if (minAt != parentIndex) {
            swap(parentIndex, minAt);
            downheapify(minAt, size);
        }
    }

    public Integer peek() {
        return data.size() == 0 ? null : data.get(0);
    }

    public int getSize() {
        return data.size();
    }

    private void swap(int i, int j) {
        int ith = data.get(i);
        int jth = data.get(j);
        data.set(i, jth);
        data.set(j, ith);
    }

    private boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public Iterator<Integer> iterator() {
        return data.iterator();
    }

    public static void main(String[] args) {
        CustomHeap heap = new CustomHeap(4);
        heap.offer(10);
        heap.offer(16);
        heap.offer(5);
        heap.offer(74);
        heap.offer(10);
        heap.offer(6);
        heap.offer(51);
        heap.offer(17);

//        for(var e : heap)


        System.out.println("Size of queue :: " + heap.getSize());
        System.out.println("Peek queue :: " + heap.peek());

        System.out.print("Testing Iterator: ");
        for (var val : heap)
            System.out.print(val + " ");

        System.out.println();

        System.out.print("Testing Foreach : ");
        heap.forEach(val -> System.out.print(val + " "));

        System.out.println();

        System.out.print("Elements in queue :: ");
        while (!heap.isEmpty())
            System.out.print(heap.poll() + " ");
    }
}
