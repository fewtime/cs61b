import java.util.LinkedList;

public class LinkedListDeque<T> {
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        private Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    // Constructor
    public LinkedListDeque() {
        this.sentinel = new Node(null, null, null);
        this.sentinel.next = sentinel;
        this.sentinel.prev = sentinel;
        this.size = 0;
    }

    // Constructor: deep copy
    public LinkedListDeque(LinkedListDeque<T> other) {
        this.sentinel = new Node(null, null, null);
        this.sentinel.next = sentinel;
        this.sentinel.prev = sentinel;

        Node p = other.sentinel.next;

        while (p != other.sentinel) {
            this.addLast(p.item);
            p = p.next;
        }
    }

    // Add Function
    public void addFirst(T item) {
        this.sentinel.next = new Node(item, sentinel, sentinel.next);
        this.sentinel.next.next.prev = this.sentinel.next;
        this.size += 1;
    }

    public void addLast(T item) {
        this.sentinel.prev.next = new Node(item, sentinel.prev, sentinel);
        this.sentinel.prev = this.sentinel.prev.next;
        this.size += 1;
    }

    // Helper Function
    public boolean isEmpty() {
        return (this.size() == 0);
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        Node p = sentinel;
        while (p.next != this.sentinel) {
            System.out.println(p.next.item);
            p = p.next;
        }
    }

    // Remove Function
    public T removeFirst() {

        if (this.isEmpty()) {
            return null;
        }

        T res = this.sentinel.next.item;
        this.sentinel.next.next.prev = this.sentinel;
        this.sentinel.next = this.sentinel.next.next;
        this.size -= 1;
        return res;
    }

    public T removeLast() {

        if (this.isEmpty()) {
            return null;
        }

        T res = this.sentinel.prev.item;
        this.sentinel.prev.prev.next = this.sentinel;
        this.sentinel.prev = this.sentinel.prev.prev;
        this.size -= 1;
        return res;
    }

    // Getter
    public T get(int index) {

        if (this.isEmpty() || this.size() < index) {
            return null;
        }

        Node p = this.sentinel;

        for (int i = 0; i < index; ++i) {
            p = p.next;
        }

        return p.item;
    }

    public T getRecursive(int index) {
        if (this.isEmpty() || this.size() < index) {
            return null;
        }

        return travel(sentinel, index);
    }

    private T travel(Node p, int index) {
        if (index == 0) {
            return p.item;
        }
        return travel(p.next, index-1);
    }

}
