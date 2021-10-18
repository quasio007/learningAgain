package learning.queue.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class CustomPriorityQueueWithGeneric<E> implements Iterable<E> {

    private final ArrayList<E> data;

    public CustomPriorityQueueWithGeneric(int size) {
        data = new ArrayList(size);
    }

    /**
     * Offer or add method
     *
     * @return
     */
    public boolean offer(E element) {
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

        boolean flag = false;
        if (data.get(parentIndex) instanceof Integer) {
            if ((int) data.get(parentIndex) > (int) data.get(childIndex))
                flag = true;
        } else if (data.get(parentIndex) instanceof String) {
            int val = ((String) data.get(parentIndex)).compareTo(((String) data.get(childIndex)));
            if (((String) data.get(parentIndex)).compareTo(((String) data.get(childIndex))) >= 0)
                flag = true;
        }


        if (flag) {
            swap(parentIndex, childIndex);
            upheapify(parentIndex);
        }
    }

    /**
     * Poll or remove
     *
     * @return
     */
    public E poll() {
        if (data.size() == 0) return null;

//        Step 1 : Replace last element with first element
        swap(0, data.size() - 1);
        E element = data.remove(data.size() - 1);
        downheapify(0, getSize());
        return element;
    }

    private void downheapify(int parentIndex, int size) {
        int minAt = parentIndex;
        int leftChild = 2 * parentIndex + 1;
        int rightChild = 2 * parentIndex + 2;

        if (leftChild < size && data.get(minAt) instanceof Integer) {
            if (leftChild < size && (int) data.get(minAt) > (int) data.get(leftChild))
                minAt = leftChild;
        } else if (leftChild < size && data.get(minAt) instanceof String) {
            if (leftChild < size && ((String) data.get(minAt)).compareTo((String) data.get(leftChild)) > 0)
                minAt = leftChild;
        }

        if (leftChild < size && data.get(minAt) instanceof Integer) {
            if (rightChild < size && (int) data.get(minAt) > (int) data.get(rightChild))
                minAt = rightChild;
        } else if (leftChild < size && data.get(minAt) instanceof String) {
            if (rightChild < size && ((String) data.get(minAt)).compareTo((String) data.get(rightChild)) > 0)
                minAt = rightChild;
        }

        if (minAt != parentIndex) {
            swap(parentIndex, minAt);
            downheapify(minAt, size);
        }
    }

    public E peek() {
        return data.size() == 0 ? null : data.get(0);
    }

    public int getSize() {
        return data.size();
    }

    private void swap(int i, int j) {
        E ith = data.get(i);
        E jth = data.get(j);
        data.set(i, jth);
        data.set(j, ith);
    }

    private boolean isEmpty() {
        return getSize() == 0;
    }


    @Override
    public Iterator<E> iterator() {
        return data.iterator();
    }

    public static void main(String[] args) {
        CustomPriorityQueueWithGeneric<String> cpq = new CustomPriorityQueueWithGeneric(4);
//        cpq.offer("C");
//        cpq.offer("D");
//        cpq.offer("A");
//        cpq.offer("B");


        cpq.offer("Test");
        cpq.offer("Apple");
        cpq.offer("Air");
        cpq.offer("Mohit");
        cpq.offer("Zebra");
        cpq.offer("Dog");
        cpq.offer("Ball");
        cpq.offer("Jug");


//        cpq.offer(10);
//        cpq.offer(16);
//        cpq.offer(5);
//        cpq.offer(74);
//        cpq.offer(10);
//        cpq.offer(6);
//        cpq.offer(51);
//        cpq.offer(17);


        System.out.println("Size of queue :: " + cpq.getSize());
        System.out.println("Peek queue :: " + cpq.peek());

        System.out.print("Testing Iterator: ");
        for (var val : cpq)
            System.out.print(val + " ");

        System.out.println();

        System.out.print("Testing Foreach : ");
        cpq.forEach(val -> System.out.print(val + " "));

        System.out.println();

        System.out.print("Elements in queue :: ");
        while (!cpq.isEmpty())
            System.out.print(cpq.poll() + " ");

        System.out.println();

        Set<String> set = new TreeSet<>();
        set.add("14");
        set.add("12");
        set.add("18");
        set.add("16");
        System.out.println(set);


    }


}
