package learning.set.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetTest {
    static class Node {
        int row;
        int col;
        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + col;
            result = prime * result + row;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            if (col != other.col)
                return false;
            if (row != other.row)
                return false;
            return true;
        }
    }

    public static void main(String args[]) {
        Set<Node> nodes = new HashSet<>();

        //note 1, 2 are added 3 times, but only one should be staying in the set
        nodes.add(new Node(1,2));
        nodes.add(new Node(1,3));
        nodes.add(new Node(1,4));
        nodes.add(new Node(1,2));
        nodes.add(new Node(2,1));
        nodes.add(new Node(1,2));
        nodes.add(new Node(1,5));
        System.out.println(nodes.size());

        Iterator<Node> iterator = nodes.iterator();
        Node n;
        //As expected, the node with 1, 2 only show up once here when printing out all the nodes in the set
        while(iterator.hasNext()) {
            n = iterator.next();
            System.out.println(n.row + " " + n.col);
        }
    }
}
