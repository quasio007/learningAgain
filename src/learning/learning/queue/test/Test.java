package learning.queue.test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Test {
    public static void main(String[] args) {

        Person p1 = new Person(3, "Cat");
        Person p2 = new Person(1, "Apple");
        Person p3 = new Person(2, "Ball");


        PriorityQueue<Person> pq = new PriorityQueue<>(Comparator.comparing(Person::getId).reversed());
        pq.add(p1);
        pq.add(p2);
        pq.add(p3);

        while (!pq.isEmpty())
            System.out.println(pq.poll());

    }
}

class Person implements Comparable<Person>{
    private int id;
    private String name;

    Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return id + " : " + name;
    }

    @Override
    public int compareTo(Person p) {
        if (id == p.id)
            return 0;
        else if (id > p.id)
            return 1;
        else
            return -1;
    }


//    @Override
//    public int compare(Person p1, Person p2) {
//        if (p1.id == p2.id)
//            return 0;
//        else if (p1.id > p2.id)
//            return 1;
//        else
//            return -1;
//    }
}
