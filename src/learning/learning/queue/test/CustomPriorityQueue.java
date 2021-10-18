package learning.queue.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * This Priority Queue will work for integer values only
 */
public class CustomPriorityQueue implements Iterable<Integer> {

    private final ArrayList<Integer> data;

    public CustomPriorityQueue(int size) {
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
        CustomPriorityQueue customPriorityQueue = new CustomPriorityQueue(4);
        customPriorityQueue.offer(10);
        customPriorityQueue.offer(16);
        customPriorityQueue.offer(5);
        customPriorityQueue.offer(74);
        customPriorityQueue.offer(10);
        customPriorityQueue.offer(6);
        customPriorityQueue.offer(51);
        customPriorityQueue.offer(17);

//        for(var e : customPriorityQueue)


        System.out.println("Size of queue :: " + customPriorityQueue.getSize());
        System.out.println("Peek queue :: " + customPriorityQueue.peek());

        System.out.print("Testing Iterator: ");
        for (var val : customPriorityQueue)
            System.out.print(val + " ");

        System.out.println();

        System.out.print("Testing Foreach : ");
        customPriorityQueue.forEach(val -> System.out.print(val + " "));

        System.out.println();

        System.out.print("Elements in queue :: ");
        while (!customPriorityQueue.isEmpty())
            System.out.print(customPriorityQueue.poll() + " ");

    }
}



/*
        System.out.println(customPriorityQueue.peek());
        System.out.println(-1 / 2);


        ArrayList<Integer> l = new ArrayList<>();

        l.add(0);
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.remove(4);

//        l.set(2,10);

        int i = 3, j = 1;

        int temp = l.get(i);
        l.set(i, l.get(j));
        l.set(j, temp);


//        int jth = l.get(j);
//        l.set(i, jth);
//        l.set(j, ith);


        System.out.println(l);
 */