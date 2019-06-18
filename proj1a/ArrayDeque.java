public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    private static double usageFactor = 0.25;

    public ArrayDeque() {
        this.items = (T[]) new Object[8];
        this.size = 0;
        this.nextFirst = 0;
        this.nextLast = 1;
    }

    /*
    public ArrayDeque(int capacity) {
        this.items = (T[]) new Object[capacity];
        this.size = 0;
        this.nextFirst = 0;
        this.nextLast = 1;
    }

    public ArrayDeque(ArrayDeque<T> other) {
        this.items = (T[]) new Object[other.size()];
        this.size = other.size();
        this.nextFirst = 0;
        this.nextLast = 1;

        for (int i = 0; i < other.size(); ++i) {
            this.items[i] = other.get(i);
        }
    }
     */

    public boolean isEmpty() {
        return (this.size() == 0);
    }

    public int size() {
        return this.size;
    }

    public T get(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }

        int firstIdx = plusOne(this.nextFirst);
        int realIdx = (firstIdx + index) % this.items.length;

        return this.items[realIdx];
    }

    public void addFirst(T t) {
        if (this.size == this.items.length) {
            this.resize(this.size * 2);
        }

        this.items[this.nextFirst] = t;
        this.nextFirst = minusOne(this.nextFirst);
        this.size += 1;
    }

    public void addLast(T t) {
        if (this.size == this.items.length) {
            this.resize(this.size * 2);
        }

        this.items[this.nextLast] = t;
        this.nextLast = plusOne(this.nextLast);
        this.size += 1;
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }

        int firstIdx = plusOne(this.nextFirst);
        T res = this.items[firstIdx];
        this.items[firstIdx] = null;
        this.nextFirst = firstIdx;

        this.size -= 1;
        this.checkUsageRatio();

        return res;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }

        int lastIdx = minusOne(this.nextLast);
        T res = this.items[lastIdx];
        this.items[lastIdx] = null;
        this.nextLast = lastIdx;

        this.size -= 1;
        this.checkUsageRatio();

        return res;
    }

    public void printDeque() {
        for (int i = 0; i < this.size; ++i) {
            System.out.println(this.get(i));
        }
        System.out.println();
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];

        for (int i = 0; i < this.size; ++i) {
            newItems[i] = this.get(i);
        }

        this.items = newItems;
        this.nextFirst = minusOne(0);
        this.nextLast = this.size;
    }

    private int plusOne(int x) {
        return (x + 1) % this.items.length;
    }

    private int minusOne(int x) {
        if (x == 0) {
            return this.items.length - 1;
        }

        return x - 1;
    }

    private void checkUsageRatio() {
        double curUsageRatio = (double) this.size / this.items.length;

        if (curUsageRatio < usageFactor && this.items.length > 16) {
            this.resize(this.items.length / 2);
        }
    }

}
