package learning.set.test;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        Student s1 = new Student("H", 1);
        Student s2 = new Student("B", 2);
        Student s3 = new Student("C", 3);
        Student s4 = new Student("D", 1);
        Student s5 = new Student("A", 1);

        Set<Student> set = new HashSet<>();
        set.add(s3);
        set.add(s1);
        set.add(s4);
        set.add(s2);
        set.add(s5);


        set.forEach(System.out::println);

        System.out.println("\n\n");

        List<Student> list = new ArrayList<>();
        list.add(s3);
        list.add(s1);
        list.add(s4);
        list.add(s2);
        list.add(s5);


        list.forEach(System.out::println);

        Collections.sort(list, Comparator.comparing(Student::getId).thenComparing(Student::getName));
        System.out.println("\n\n");
        list.forEach(System.out::println);

    }
}


class Student implements Comparable<Student> {
    private String name;
    private int id;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
//        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return name + " : " + id;
    }


    @Override
    public int compareTo(Student st) {
        if (id == st.id)
            return 0;
        else if (id > st.id)
            return 1;
        else
            return -1;
    }
}



/*
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
 */