import java.util.*;
import java.util.stream.Collectors;

public class SimpleLinkedList<E> implements ISimpleList<E> {

    private Node<E> root = null;

    @Override
    public boolean add(E e) {
        // 1. Wrap the element in a Node
        Node<E> element = new Node<E>(e);
        // 2. Traverse to the end of the list
        if (root == null){
            root = element;
        } else {

            Node<E> n = root;

            while (n.getNext() != null){
                n = n.getNext();
            }
            // 3. Add the element.
            n.setNext(element);
            element.setParent(n);
        }

        return true;
    }

    @Override
    public boolean insert(int i, E e) {
        Node<E> n = root;
        for(int j = 0; j < i-1; ++j) {
            n = n.getNext();
        }

        // 1. Wrap e in a Node
        Node<E> element = new Node<E>(e);
        // 2. Set the new node's next to n's old next node
        element.setNext(n.getNext());

        n.getNext().setParent(element);

        // 3. Set n's next element to the new node
        n.setNext(element);

        element.setParent(n);

        return true;
    }

    @Override
    public E replace(int i, E e) {
        Node<E> n = root;
        for(int j = 0; j < i; ++j) {
            n = n.getNext();
        }
        E oldData = n.getData();
        n.setData(e);
        return oldData;
    }

    @Override
    public E remove(int i) {
        Node<E> n = root;
        for(int j = 0; j < i-1; ++j) {
            n = n.getNext();
        }
        Node<E> removed = n.getNext();// Gives us reference to the removed node.
        n.setNext(removed.getNext());// removes the node from the list

        removed.setParent(null);

        return removed.getData();
    }

    @Override
    public E get(int index) {
        Node<E> n = root;

        for (int i = 0; i < index; ++i) {
            n = n.getNext();
        }

        return n.getData();
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public int size() {
        int s = 0;
        if (root != null) {
            Node<E> n = root;
            while(n.getNext() != null) {
                n = n.getNext();
                ++s;
            }
        }
        return s;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public ISimpleList<E> subList(int startIndex, int endIndex) {
        Node<E> n = root;
        for(int j = 0; j < startIndex; ++j) {
            n = n.getNext();
        }

        SimpleLinkedList<E> newList = new SimpleLinkedList<E>();
        for (int i = startIndex; i < endIndex; ++i) {
            newList.add(n.getData());
            n = n.getNext();
        }

        return newList;
    }

    public SimpleLinkedList<E> deleteRange(int starti, int num) {

        Node<E> start = null, end = null, n = root;

        for (int i = 0;; i++) {

            if (n == null) {
                break;
            }

            if (i == starti) {
                start = n;
            }

            if (i == num+starti-1) {
                end = n;
            }

            if (start != null && end != null) {
                break;
            }

            n = n.getNext();

        }

        start.getParent().setNext(end.getNext());

        start.setParent(null);
        end.setNext(null);

        SimpleLinkedList<E> newList = new SimpleLinkedList<>();

        newList.root = start;

        return newList;

    }

    @Override
    public String toString() {

        /*String output = "[";

        Node<E> n = root;

        while (n != null) {
            output += (n.getData() == null ? "null" : n.getData().toString()) + ", ";
            n = n.getNext();
        }

        if (output.length() > 2) {

            output = output.substring(0, output.length() - 2);

        }

        return output + "]";*/

        ArrayList<E> list = new ArrayList<>();

        Node<E> n = root;

        while (n != null) {
            list.add(n.getData());
            n = n.getNext();
        }

        return list.toString();

    }

    public void insertAll(int index, SimpleLinkedList<E> list) {

        Node<E> n = root;

        if (index == size() + 1) {
            while (true) {
                if (n.getNext() == null) {
                    break;
                } else {
                    n = n.getNext();
                }
            }
            n.setNext(list.root);
            list.root.setParent(n);
        } else {

            for (int i = 0; n != null; i++) {

                if (i == index - 1) {

                    Node<E> other = n.getNext();

                    Node<E> last = list.root;

                    while (true) {
                        if (last.getNext() == null) {
                            break;
                        } else {
                            last = last.getNext();
                        }
                    }

                    last.setNext(other);

                    other.setParent(last);

                    n.setNext(list.root);

                    list.root.setParent(n);

                }

                n = n.getNext();

            }
        }

    }

    public void reverse() {

        ArrayList<Node<E>> list = new ArrayList<>();

        Node<E> n = root;

        while (n != null) {

            list.add(n);

            n = n.getNext();

        }

        Collections.reverse(list);

        for (int i = 0; i < list.size(); i++) {

            Node<E> node = list.get(i);

            if (i > 0) {

                node.setParent(list.get(i - 1));

            } else {
                node.setParent(null);
            }

            if (i < list.size() - 1) {

                node.setNext(list.get(i + 1));

            } else {
                node.setNext(null);
            }

        }

        root = list.get(0);

    }

    public static void main(String[] args) {

        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();

        System.out.println(list);

        list.add(5);

        System.out.println(list);

        list.add(7);

        list.add(3);

        System.out.println(list);

        list.add(8);

        list.add(4);

        list.add(6);

        list.add(-4);

        System.out.println(list);

        SimpleLinkedList<Integer> delList = list.deleteRange(1, 3);

        System.out.println(list);

        System.out.println(list.size());

        System.out.println(delList);

        list.insertAll(4, delList);

        System.out.println(list);

        list.reverse();

        System.out.println(list);

    }

}
