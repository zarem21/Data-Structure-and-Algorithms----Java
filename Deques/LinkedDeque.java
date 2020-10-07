package deques;

public class LinkedDeque<T> implements Deque<T> {
    private int size;
    private Node sentinel; //Create sentinel

    public LinkedDeque() {
        size = 0;
        sentinel = new Node(null); //Initialize sentinel
    }

    private class Node {
        private T value;
        private Node last;
        private Node next;

        Node(T value) {
            this(value, sentinel, sentinel);
        }

        Node(T value, Node last, Node next) {
            this.value = value;
            this.next = next;
            this.last = last;
        }
    }

    public void addFirst(T item) {
        if (size == 0) {
            Node newNode = new Node(item, sentinel, sentinel);
            sentinel.next = newNode;
            sentinel.last = newNode;
        } else {
            Node newNode = new Node(item, sentinel, sentinel.next);
            sentinel.next.last = newNode;
            sentinel.next = newNode;
        }
        size += 1;

    }

    public void addLast(T item) {
        if (size == 0) {
            Node newNode = new Node(item, sentinel, sentinel);
            sentinel.next = newNode;
            sentinel.last = newNode;
        } else {
            Node newNode = new Node(item, sentinel.last, sentinel);
            sentinel.last.next = newNode;
            sentinel.last = newNode;
            // System.out.println(sentinel.last.value);
            // System.out.println(sentinel.last.last.value);
        }
        size += 1;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T result = sentinel.next.value;

            sentinel.next.last = null;
            sentinel.next = sentinel.next.next;
            sentinel.next.last = sentinel;

            //System.out.println(result);
            size -= 1;
            return result;
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T result = sentinel.last.value;
            sentinel.last.last.next = null;
            sentinel.last = sentinel.last.last;
            sentinel.last.next = sentinel;
            //System.out.println(result);
            size -= 1;
            return result;
        }

    }

    public T get(int index) {
        if ((index > size) || (index < 0)) {
            return null;
        }
        Node curr = sentinel.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.value;
    }

    public int size() {
        return size;
    }
}
